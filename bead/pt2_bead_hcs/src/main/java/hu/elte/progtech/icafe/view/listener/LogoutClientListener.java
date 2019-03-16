/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.PaymentService;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.dialog.CloseSessionDialog;
import hu.elte.progtech.icafe.view.dialog.FulfillPaymentDialog;
import hu.elte.progtech.icafe.view.dialog.LogoutUserDialog;
import hu.elte.progtech.icafe.view.table.UserTable;
import hu.elte.progtech.icafe.view.table.model.UserTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

public class LogoutClientListener implements ActionListener {

    private UserTable table;

    public LogoutClientListener(UserTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserTableModel tableModel = (UserTableModel) table.getModel();
        User user = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        if (null != user) {
            if (!user.isPresent()) {
                MainFrame.showError("HIBA", "A felhasználó nincs bejelentkezve!");
                return;
            }
            if(null != user.getActiveSession()){
                showEndSessionDialog(tableModel);
            } else if (null != user.getPendingPayment()){
                showFulfillPaymentDialog(tableModel);
            } else {
                showLogoutDialog(tableModel);
            }

        }
    }

    private void showEndSessionDialog(final UserTableModel tableModel) {
        User user = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));

        user.getActiveSession().setEndTime(new Timestamp(new Date().getTime()));
        JDialog dialog = new CloseSessionDialog(null, "Ügyfél kiléptetése - 1. Munkamenet zárása", true, user) {

            @Override
            protected void onActionConfirm() {
                try {
                    new UserService().closeActiveSession(user);
                    setVisible(false);
                    showFulfillPaymentDialog(tableModel);
                    tableModel.reloadEntities();
                } catch (Exception ex) {
                    MainFrame.showError("HIBA", ex.getMessage());
                }
            }
        };
        dialog.setVisible(true);
    }

    private void showFulfillPaymentDialog(final UserTableModel tableModel) {
        User user = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        JDialog dialog = new FulfillPaymentDialog(null, "Ügyfél kiléptetése - 2. Befizetés megerősítése", true, user) {

            @Override
            protected void onActionConfirm() {
                try {
                    new PaymentService().fulfillPayment(user.getPendingPayment());
                    setVisible(false);
                    showLogoutDialog(tableModel);
                } catch (Exception ex) {
                    MainFrame.showError("HIBA", ex.getMessage());
                }
            }
        };
        dialog.setVisible(true);
    }

    private void showLogoutDialog(final UserTableModel tableModel) {
        User user = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        JDialog dialog = new LogoutUserDialog(null, "Ügyfél kiléptetése - 3. Kiléptetés", true, user) {

            @Override
            protected void onActionConfirm() {
                try {
                    new UserService().logoutUser(user);
                    setVisible(false);
                    JOptionPane.showMessageDialog(
                            null,
                            "A felhasználó kiléptetése sikeres!",
                            "Sikeres beléptetés",
                            JOptionPane.INFORMATION_MESSAGE);
                    tableModel.reloadEntities();
                } catch (Exception ex) {
                    MainFrame.showError("HIBA", ex.getMessage());
                }
            }
        };
        dialog.setVisible(true);
    }
}
