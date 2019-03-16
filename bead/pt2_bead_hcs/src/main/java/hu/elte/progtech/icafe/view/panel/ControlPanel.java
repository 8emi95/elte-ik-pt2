/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.panel;

import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.listener.*;
import hu.elte.progtech.icafe.view.menu.ClientsControlButtonContextMenu;
import hu.elte.progtech.icafe.view.menu.ComputersControlButtonContextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControlPanel extends JPanel {
    private static final int WIDTH = 250;
    private static final int HEIGHT = MainFrame.DEFAULT_HEIGHT;

    private static final int BORDER_WIDTH = 2;
    private static final Color BORDER_COLOR = Color.GRAY;

    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private static final Font TIMER_FONT = new Font("Garamond", Font.PLAIN, 18);
    private static final Font BUTTON_FONT = new Font("Garamond", Font.BOLD, 16);
    private static final int BUTTON_HEIGHT = 50;

    private MainFrame mainFrame;

    public ControlPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        configurePanel();
        addTimeStamp();
        addManagementButtons();
        addExitButton();
    }

    private void configurePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, BORDER_WIDTH, BORDER_COLOR));
        setLayout(new BorderLayout());
    }

    private void addTimeStamp() {
        JPanel timerPanel = new JPanel();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateTimeLabelUpdateListener.PATTERN));
        JLabel timestampLabel = new JLabel(now, SwingConstants.CENTER);
        timestampLabel.setFont(TIMER_FONT);
        timestampLabel.setPreferredSize(new Dimension(WIDTH - 50, BUTTON_HEIGHT));
        timestampLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        timerPanel.add(timestampLabel);
        Timer timer = new Timer(1000, new DateTimeLabelUpdateListener(timestampLabel));
        timer.start();
        timerPanel.setBorder(
                BorderFactory.createMatteBorder(30, 0, 30, 0, TRANSPARENT)
        );
        add(timerPanel, BorderLayout.NORTH);
    }

    private void addManagementButtons() {
        JPanel managementButtonsPanel = new JPanel(new GridLayout(4, 1));
        managementButtonsPanel.add(createButton("Ügyfelek",
                new ShowContextMenuOnControlButtonClickListener(new ClientsControlButtonContextMenu(mainFrame)),
                new hu.elte.progtech.icafe.view.Icon("customers.png", BUTTON_HEIGHT, BUTTON_HEIGHT)
        ));
        managementButtonsPanel.add(createButton("Számítógépek",
                new ShowContextMenuOnControlButtonClickListener(new ComputersControlButtonContextMenu(mainFrame)),
                new hu.elte.progtech.icafe.view.Icon("computers.png", BUTTON_HEIGHT, BUTTON_HEIGHT)
        ));
        managementButtonsPanel.add(createButton("Befizetések",
                new ShowPaymentsListListener(mainFrame),
                new hu.elte.progtech.icafe.view.Icon("customers.png", BUTTON_HEIGHT, BUTTON_HEIGHT)
        ));
        managementButtonsPanel.add(createButton("Beállítások",
                new EditSystemSettingsListener(mainFrame),
                new hu.elte.progtech.icafe.view.Icon("settings.png", BUTTON_HEIGHT, BUTTON_HEIGHT)
        ));
        managementButtonsPanel.setBorder(
                BorderFactory.createMatteBorder(50, 0, 150, 0, TRANSPARENT)
        );
        add(managementButtonsPanel, BorderLayout.CENTER);
    }

    private void addExitButton() {
        JPanel southPanel = new JPanel();
        southPanel.add(createButton("Kilépés",
                new ExitListener(mainFrame),
                new hu.elte.progtech.icafe.view.Icon("exit.png", BUTTON_HEIGHT, BUTTON_HEIGHT)
        ));
        add(southPanel, BorderLayout.SOUTH);
    }

    private Component createButton(String label, ActionListener actionListener, hu.elte.progtech.icafe.view.Icon icon) {
        JButton button = new JButton(label);
        button.setFont(BUTTON_FONT);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(WIDTH - 10, BUTTON_HEIGHT));
        button.addActionListener(actionListener);
        return button;
    }

}
