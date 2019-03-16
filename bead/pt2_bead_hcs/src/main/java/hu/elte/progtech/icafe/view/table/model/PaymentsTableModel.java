package hu.elte.progtech.icafe.view.table.model;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.api.service.PaymentService;
import hu.elte.progtech.icafe.view.MainFrame;

import java.sql.SQLException;
import java.sql.Timestamp;

public class PaymentsTableModel extends EntityTableModel<Payment> {

    public PaymentsTableModel() {
        super(Payment.FIELD_NAMES, new PaymentService());
    }

    @Override
    public Payment getEntityAtRow(int rowIndex) {
        Payment payment = entities.get(rowIndex);
        try{
            return service.getEntityById(payment.getId());
        } catch (Exception e){
            MainFrame.showError("Hiba", "A megrendelés adatainak lekérése során hiba történt: \n" + e.getMessage());
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Double.class;
            case 1:
                return String.class;
            case 2:
                return Boolean.class;
            case 3:
                return Timestamp.class;
            case 4:
                return Timestamp.class;
            default:
                return null;
        }
    }

    @Override
    protected Object getAttributeOfEntity(Payment payment, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return payment.getGrossAmount();
            case 1:
                return payment.getCurrency();
            case 2:
                return payment.isFulfilled();
            case 3:
                return payment.getCreatedAt();
            case 4:
                return payment.getUpdatedAt();
            default:
                return null;
        }
    }

    @Override
    protected void displayError(SQLException e) {
        MainFrame.showError("HIBA", "Hiba a befizetések listázása közben: \n" + e.getMessage());
    }
}
