/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeLabelUpdateListener implements ActionListener {
    public static final String PATTERN = "YYYY-MM-dd HH:mm:ss";
    private JLabel label;

    public DateTimeLabelUpdateListener(JLabel label) {
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LocalDateTime dt = LocalDateTime.now();
        label.setText(dt.format(DateTimeFormatter.ofPattern(PATTERN)));
    }
}
