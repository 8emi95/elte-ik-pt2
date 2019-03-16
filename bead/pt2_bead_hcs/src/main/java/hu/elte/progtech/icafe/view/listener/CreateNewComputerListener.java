/**
 * Created by Csaba_Hete on 2017.04.13..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.api.service.ComputerService;
import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.dialog.EditComputerDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewComputerListener implements ActionListener {

    private final MainFrame frame;

    public CreateNewComputerListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Computer computer = new Computer();
        JDialog inputDialog = new EditComputerDialog(frame, "Új számítógép rögzítése", true, computer) {

            @Override
            public void onConfirm() {
                try {
                    new ComputerService().addEntity(getEditable());
                    setVisible(false);
                    frame.listComputers();
                } catch (Exception e) {
                    displayErrorDialog("HIBA", "A számítógép rögzítése során hiba történt! \n" + e.getMessage());
                }
            }
        };
        inputDialog.setVisible(true);
    }
}
