/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.api.entity.Session;
import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.backend.configuration.AppConfiguration;
import hu.elte.progtech.icafe.backend.dao.DataSource;
import hu.elte.progtech.icafe.backend.dao.PaymentDao;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class PaymentService implements DaoService<Payment> {
    private PaymentDao paymentDao;

    public PaymentService() {
        paymentDao = DataSource.getInstance().getPaymentDao();
    }

    @Override
    public int getEntityCount() throws Exception {
        return paymentDao.getEntityCount();
    }

    @Override
    public List<Payment> getEntities() throws Exception {
        return paymentDao.getEntities();
    }

    @Override
    public Payment getEntityById(long id) throws Exception {
        Payment payment = paymentDao.getEntityById(id);
        payment.setUser(DataSource.getInstance().getUserDao().getEntityById(payment.getUserId()));
        payment.getUser().setPendingPayment(payment);
        payment.setSession(DataSource.getInstance().getSessionDao().getEntityById(payment.getSessionId()));
        return payment;
    }

    @Override
    public Payment getEntityByRowIndex(int rowIndex) throws Exception {
        return paymentDao.getEntityByRowIndex(rowIndex);

    }

    @Override
    public void addEntity(Payment entity) throws Exception {
        paymentDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(long index) throws Exception {
        paymentDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(Payment entity, long index) throws Exception {
        paymentDao.updateEntity(entity, index);
    }

    @Override
    public List<Payment> getEntitiesByForeignKey(final String column, final long id) throws Exception {
        return paymentDao.getEntitiesByForeignKey(column, id);
    }

    public void fulfillPayment(Payment payment) throws Exception {
        payment.setFulfilled(true);
        paymentDao.updateEntity(payment, payment.getId());
    }

    public Payment createPayment(User user, Session session) {
        Payment payment = new Payment();
        Properties configuration = AppConfiguration.getConfiguration();
        payment.setUserId(user.getId());
        payment.setSessionId(session.getId());
        payment.setNetPrice(Double.parseDouble(configuration.getProperty("netPrice")));
        payment.setQuantity(SessionService.getDiffInCeiledHours(session.getEndTime(), session.getStartTime()));
        payment.setUnit("óra");
        payment.setCurrency(configuration.getProperty("currency"));
        int points = user.getPoints();
        payment.setDiscount(getDiscount(points));
        payment.setVatRate(Integer.parseInt(configuration.getProperty("vatRate")));
        payment.setGrossAmount(getGrossAmount(payment));
        payment.setFulfilled(false);
        return payment;
    }

    public int getDiscount(int points) {
        Properties configuration = AppConfiguration.getConfiguration();
        int maxDiscount = Integer.parseInt(configuration.getProperty("maxDiscount"));
        int discount = points / Integer.parseInt(configuration.getProperty("discountPerPoints"));
        return discount > maxDiscount ? maxDiscount : discount;
    }

    public Double getGrossAmount(Payment payment) {
        double netPrice = payment.getNetPrice();
        double quantity = payment.getQuantity();
        double discount = payment.getDiscount();
        double vatRate = payment.getVatRate();
        return quantity * netPrice * (1 - discount / 100) * (1 + vatRate / 100);
    }

    public Payment getPendingPayment(User user) throws Exception{
        if (null != user.getId()) {
            PaymentService paymentService = new PaymentService();
            List<Payment> payments = paymentService.getEntitiesByForeignKey("USER_ID", user.getId());
            Optional<Payment> pending = payments.stream().filter(o -> !o.isFulfilled()).findFirst();
            if (pending.isPresent()) {
                return paymentService.getEntityById(pending.get().getId());
            }
        }
        return null;
    }
}
