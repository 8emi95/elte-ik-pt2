/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.Address;
import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.dialog.EditUserDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewClientListener implements ActionListener {

    private final MainFrame frame;

    public CreateNewClientListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User user = new User();
        user.setAddress(new Address());
        JDialog inputDialog = new EditUserDialog(frame, "Új ügyfél rögzítése", true, user) {

            @Override
            public void onConfirm() {
                try {
                    new UserService().addEntity(getEditable());
                    setVisible(false);
                    frame.listUsers();
                } catch (Exception e) {
                    displayErrorDialog("HIBA", "Az ügyfél rögzítése során hiba történt! \n" + e.getMessage());
                }
            }
        };
        inputDialog.setVisible(true);
    }
}
