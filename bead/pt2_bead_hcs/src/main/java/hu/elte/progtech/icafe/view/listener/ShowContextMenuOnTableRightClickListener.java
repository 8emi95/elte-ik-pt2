/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowContextMenuOnTableRightClickListener extends MouseAdapter {

    private JPopupMenu menu;

    public ShowContextMenuOnTableRightClickListener(JPopupMenu menu) {
        this.menu = menu;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getSource() instanceof JTable) {
            JTable table = (JTable) me.getSource();
            int row = table.rowAtPoint(new Point(me.getX(), me.getY()));
            if (me.isPopupTrigger() && row != -1) {
                table.clearSelection();
                table.setRowSelectionInterval(row, row);
                menu.show(table, me.getX(), me.getY());
            }
        } else
            throw new IllegalArgumentException();
    }

}
