/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.api.entity.Session;
import hu.elte.progtech.icafe.api.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PaymentDao extends AbstractEntityDao<Payment>{
    private static final String TABLE = "PAYMENTS";

    public PaymentDao() {
        super(TABLE);
    }

    @Override
    protected Payment newEntity() {
        Payment payment = new Payment();
        payment.setUser(new User());
        payment.setSession(new Session());
        return payment;
    }

    @Override
    protected void setEntityAttributes(Payment entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getLong("ID"));
        entity.setUserId(resultSet.getLong("USER_ID"));
        entity.setSessionId(resultSet.getLong("SESSION_ID"));
        entity.setQuantity(resultSet.getLong("QUANTITY"));
        entity.setUnit(resultSet.getString("UNIT"));
        entity.setNetPrice(resultSet.getDouble("NET_PRICE"));
        entity.setVatRate(resultSet.getInt("VAT_RATE"));
        entity.setDiscount(resultSet.getInt("DISCOUNT"));
        entity.setCurrency(resultSet.getString("CURRENCY"));
        entity.setGrossAmount(resultSet.getDouble("GROSS_AMOUNT"));
        entity.setFulfilled(resultSet.getBoolean("FULFILLED"));
        entity.setCreatedAt(resultSet.getTimestamp("CREATED_AT"));
        entity.setUpdatedAt(resultSet.getTimestamp("UPDATED_AT"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, Payment entity) throws SQLException {
        resultSet.updateLong("USER_ID", entity.getUserId());
        resultSet.updateLong("SESSION_ID", entity.getSessionId());
        resultSet.updateLong("QUANTITY", entity.getQuantity());
        resultSet.updateString("UNIT", entity.getUnit());
        resultSet.updateDouble("NET_PRICE", entity.getNetPrice());
        resultSet.updateInt("VAT_RATE", entity.getVatRate());
        resultSet.updateInt("DISCOUNT", entity.getDiscount());
        resultSet.updateString("CURRENCY", entity.getCurrency());
        resultSet.updateDouble("GROSS_AMOUNT", entity.getGrossAmount());
        resultSet.updateBoolean("FULFILLED", entity.isFulfilled());
        if (null != entity.getCreatedAt())
            resultSet.updateTimestamp("UPDATED_AT", Timestamp.valueOf(LocalDateTime.now()));
    }
}
