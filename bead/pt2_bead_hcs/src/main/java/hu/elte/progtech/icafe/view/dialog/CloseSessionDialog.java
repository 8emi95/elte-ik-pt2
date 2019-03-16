/**
 * Created by Csaba_Hete on 2017.04.12..
 */
package hu.elte.progtech.icafe.view.dialog;

import hu.elte.progtech.icafe.api.entity.Session;
import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.api.service.SessionService;
import hu.elte.progtech.icafe.view.panel.DoubleSidedPanel;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public abstract class CloseSessionDialog extends UserAdministrativeDialog {

    public CloseSessionDialog(JFrame frame, String title, boolean isModal, final User user) {
        super(frame, title, isModal, user);
    }

    @Override
    protected JPanel createActionButtonPanel() {
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BorderLayout());
        JButton logoutButton = new JButton("Munkamenet zárása");
        logoutButton.addActionListener(e -> onActionConfirm());
        btnPanel.add(logoutButton, BorderLayout.SOUTH);

        DoubleSidedPanel sessionPanel = new DoubleSidedPanel();
        btnPanel.add(sessionPanel, BorderLayout.NORTH);
        createSessionDetailsPanel(sessionPanel);

        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btnPanel;
    }

    private void createSessionDetailsPanel(DoubleSidedPanel sessionPanel) {
        Session activeSession = user.getActiveSession();
        sessionPanel.setBorder(BorderFactory.createTitledBorder("Munkamenet adatai"));

        JTextField sessionStart = new JTextField(25);
        sessionStart.setEditable(false);
        String startTimeString = String.format("%s  %s",
                activeSession.getStartTime().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE),
                activeSession.getStartTime().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        sessionStart.setText(startTimeString);

        JTextField sessionEnd = new JTextField(25);
        sessionEnd.setEditable(false);
        String endTimeString = String.format("%s  %s",
                activeSession.getEndTime().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE),
                activeSession.getEndTime().toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_TIME)
        );
        sessionEnd.setText(endTimeString);

        JTextField duration = new JTextField(25);
        duration.setEditable(false);
        duration.setText(SessionService.getDiffInCeiledHours(activeSession.getEndTime(), activeSession.getStartTime()).toString());
        sessionPanel.addInput(new JLabel("Munkamenet kezdete"), sessionStart);
        sessionPanel.addInput(new JLabel("Munkamenet vége"), sessionEnd);
        sessionPanel.addInput(new JLabel("Munkamenet időtartama (órákban)"), duration);
    }
}