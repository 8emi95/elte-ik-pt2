/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.PaymentService;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.dialog.CloseSessionDialog;
import hu.elte.progtech.icafe.view.dialog.FulfillPaymentDialog;
import hu.elte.progtech.icafe.view.dialog.LogoutUserDialog;
import hu.elte.progtech.icafe.view.table.PaymentsTable;
import hu.elte.progtech.icafe.view.table.UserTable;
import hu.elte.progtech.icafe.view.table.model.PaymentsTableModel;
import hu.elte.progtech.icafe.view.table.model.UserTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

public class FulfillPaymentListener implements ActionListener {

    private PaymentsTable table;

    public FulfillPaymentListener(PaymentsTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaymentsTableModel tableModel = (PaymentsTableModel) table.getModel();
        Payment payment = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        if (null != payment) {
            if (payment.isFulfilled()) {
                MainFrame.showError("HIBA", "A befizetést már teljesítették!");
                return;
            }
            User user = payment.getUser();
            JDialog dialog = new FulfillPaymentDialog(null, "Befizetés megerősítése", true, user) {

                @Override
                protected void onActionConfirm() {
                    try {
                        new PaymentService().fulfillPayment(payment);
                        setVisible(false);
                        tableModel.reloadEntities();
                    } catch (Exception ex) {
                        MainFrame.showError("HIBA", ex.getMessage());
                    }
                }
            };
            dialog.setVisible(true);
        }
    }
}
