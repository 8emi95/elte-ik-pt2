/**
 * Created by Csaba_Hete on 2017.03.22..
 */
package hu.elte.progtech.icafe.view.panel;

import javax.swing.*;
import java.awt.*;

public class DoubleSidedPanel extends JPanel {
    private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
    private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);

    private int row;
    public DoubleSidedPanel() {
        setLayout(new GridBagLayout());
        row = 0;
    }

    public void addInput(JLabel label, JComponent component) {
        label.setHorizontalTextPosition(JLabel.LEFT);
        add(label, createGbc(0, row));
        add(component, createGbc(1, row++));
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        gbc.fill = (x == 0) ? GridBagConstraints.BOTH
                : GridBagConstraints.HORIZONTAL;

        gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }
}
