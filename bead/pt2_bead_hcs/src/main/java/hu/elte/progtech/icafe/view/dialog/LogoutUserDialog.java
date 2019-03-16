/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.api.entity.User;

import javax.swing.*;
import java.awt.*;

public abstract class LogoutUserDialog extends UserAdministrativeDialog {

    public LogoutUserDialog(JFrame frame, String title, boolean isModal, final User user) {
        super(frame, title, isModal, user);
    }

    @Override
    protected JPanel createActionButtonPanel(){
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        JButton loginButton = new JButton("Kiléptetés");
        loginButton.addActionListener(e -> onActionConfirm());
        btnPanel.add(loginButton);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btnPanel;
    }
}