/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.menu;

import hu.elte.progtech.icafe.view.listener.EditComputerListener;
import hu.elte.progtech.icafe.view.table.ComputerTable;

import java.awt.*;

public class ComputersTableContextMenu extends AbstractContextMenu {

    public ComputersTableContextMenu(Component component) {
        super(component);
        if (component.getClass().equals(ComputerTable.class)) {
            ComputerTable table = (ComputerTable) component;
            this.add(createMenuItem("Számítógép megtekintése", new EditComputerListener(table)));
        } else
            throw new IllegalArgumentException();
    }

}
