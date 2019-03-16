/**
 * Created by Csaba_Hete on 2017.03.22..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.backend.configuration.AppConfiguration;
import hu.elte.progtech.icafe.view.dialog.EditSystemSettingsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditSystemSettingsListener implements ActionListener {


    private JFrame frame;

    public EditSystemSettingsListener(JFrame mainFrame) {
        this.frame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog inputDialog = new EditSystemSettingsDialog(frame, "Rendszer beállítások", true) {
            @Override
            public void onConfirm() {
                try {
                    AppConfiguration.storeConfiguration();
                    setVisible(false);
                } catch (IOException e1) {
                    displayErrorDialog("HIBA", "A konfiguráció mentése közben hiba történt!");
                }
            }
        };
        inputDialog.setVisible(true);
    }
}
