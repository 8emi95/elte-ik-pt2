/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.dialog.LoginUserDialog;
import hu.elte.progtech.icafe.view.table.UserTable;
import hu.elte.progtech.icafe.view.table.model.UserTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginClientListener implements ActionListener {

    private UserTable table;

    public LoginClientListener(UserTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserTableModel tableModel = (UserTableModel) table.getModel();
        User user = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        if(null != user){
            if (user.isPresent()){
                MainFrame.showError("HIBA", "A felhasználó már be van jelentkezve!");
            } else {
                JDialog dialog = new LoginUserDialog(null, "Ügyfél beléptetése", true, user) {
                    @Override
                    protected void onActionConfirm() {
                        try {
                            new UserService().loginUser(user);
                            setVisible(false);
                            String computerName = user.getActiveSession().getComputer().getName();
                            JOptionPane.showMessageDialog(
                                    null,
                                    String.format("A felhasználó sikeresen beléptetve a %s számítógépre!", computerName),
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
    }
}
