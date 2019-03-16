/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.dialog.EditUserDialog;
import hu.elte.progtech.icafe.view.table.UserTable;
import hu.elte.progtech.icafe.view.table.model.UserTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClientListener implements ActionListener {

    private UserTable table;

    public EditClientListener(UserTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserTableModel tableModel = (UserTableModel) table.getModel();
        User user = tableModel.getEntityAtRow(table.convertRowIndexToModel(table.getSelectedRow()));
        if(null != user){
            JDialog inputDialog = new EditUserDialog(null, "Ügyfél szerkesztése", true, user) {

                @Override
                public void onConfirm() {
                    try {
                        new UserService().updateEntity(getEditable(), getEditable().getId());
                        setVisible(false);
                        tableModel.reloadEntities();
                    } catch (Exception e) {
                        displayErrorDialog("HIBA", "Az ügyfél adatainak mentése során hiba történt! \n" + e.getMessage());
                    }
                }
            };
            inputDialog.setVisible(true);
        }
    }
}
