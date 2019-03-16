/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowContextMenuOnControlButtonClickListener extends AbstractAction {

    private JPopupMenu menu;

    public ShowContextMenuOnControlButtonClickListener(JPopupMenu menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();
        menu.show(source, source.getMousePosition().x, source.getMousePosition().y);
    }
}
