/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.UserService;
import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.panel.DoubleSidedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

abstract class UserAdministrativeDialog extends JDialog {
    private final JButton cancelBtn = new JButton("Mégse");

    private JTextField name = new JTextField(25);
    private JTextField username = new JTextField(25);

    protected User user;

    UserAdministrativeDialog(JFrame frame, String title, boolean isModal, final User user) {
        super(frame, title, isModal);
        this.user = user;
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setVisible(false);
            }
        });
        cancelBtn.addActionListener(e -> setVisible(false));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createMainPanel(), BorderLayout.NORTH);
        cp.add(createActionButtonPanel(), BorderLayout.CENTER);
        cp.add(createDialogControlButtonsPanel(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(frame);
    }

    protected JPanel createMainPanel() {
        DoubleSidedPanel mainPanel = new DoubleSidedPanel();

        name.setEditable(false);
        username.setEditable(false);

        name.setText(user.getName());
        username.setText(user.getUsername());

        mainPanel.addInput(new JLabel("Név:"), name);
        mainPanel.addInput(new JLabel("Felhasználónév:"), username);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return mainPanel;
    }

    protected abstract JPanel createActionButtonPanel();
    protected abstract void onActionConfirm() ;

    protected JPanel createDialogControlButtonsPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        btnPanel.add(cancelBtn);
        return btnPanel;
    }

}