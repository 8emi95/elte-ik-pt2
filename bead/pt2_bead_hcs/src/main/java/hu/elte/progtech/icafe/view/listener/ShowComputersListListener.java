/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowComputersListListener implements ActionListener {

    private MainFrame frame;

    public ShowComputersListListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.listComputers();
    }
}
