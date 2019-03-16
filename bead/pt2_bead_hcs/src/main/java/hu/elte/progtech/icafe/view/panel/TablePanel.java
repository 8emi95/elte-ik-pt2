/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.panel;

import hu.elte.progtech.icafe.view.MainFrame;

import javax.swing.*;
import java.awt.*;

import static hu.elte.progtech.icafe.view.MainFrame.DEFAULT_HEIGHT;

public class TablePanel extends JPanel {
    private static final int HEIGHT = DEFAULT_HEIGHT;
    static final int WIDTH = 900;
    private final MainFrame mainFrame;

    public TablePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setLayout(new GridLayout(1,1));
    }

    public void setTable(JTable table){
        removeAll();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        revalidate();
        repaint();
    }
}
