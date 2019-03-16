/**
 * Created by Csaba_Hete on 2017.03.22..
 */
package hu.elte.progtech.icafe.view.listener;

import hu.elte.progtech.icafe.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPaymentsListListener implements ActionListener{

    private MainFrame frame;

    public ShowPaymentsListListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.listPayments();
    }
}
