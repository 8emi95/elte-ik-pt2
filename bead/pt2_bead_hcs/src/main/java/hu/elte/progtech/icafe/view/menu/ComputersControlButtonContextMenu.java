/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.menu;

import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.listener.CreateNewComputerListener;
import hu.elte.progtech.icafe.view.listener.ShowComputersListListener;

import java.awt.*;

public class ComputersControlButtonContextMenu extends AbstractContextMenu {

    public ComputersControlButtonContextMenu(Component component) {
        super(component);
        if (component.getClass().equals(MainFrame.class)) {
            MainFrame mainFrame = (MainFrame) component;
            this.add(createMenuItem("Számítógépek listázása", new ShowComputersListListener(mainFrame)));
            this.add(createMenuItem("Új számítógép hozzáadása", new CreateNewComputerListener(mainFrame)));
        } else
            throw new IllegalArgumentException();
    }
}
