package hu.valdar.progtech.view;

import hu.valdar.progtech.model.Model;
import hu.valdar.progtech.view.listener.LoginFormEvent;
import hu.valdar.progtech.view.listener.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener{

    private JButton button;
    private JTextField nameField;
    private JPasswordField passwordField;

    private LoginListener loginListener;

    public MainPanel(Model model){
        button = new JButton("Submit");
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(100, 40));

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(350, 50));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 50));

        setPreferredSize(new Dimension(400,400));

        add(nameField);
        add(passwordField);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if(button.equals(event.getSource())) {
            String userName = nameField.getText();
            String password = new String(passwordField.getPassword());

            fireLoginEvent(new LoginFormEvent(userName, password));
        }
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void fireLoginEvent(LoginFormEvent event) {
        if(loginListener != null) {
            loginListener.loginPerformed(event);
        }
    }
}
