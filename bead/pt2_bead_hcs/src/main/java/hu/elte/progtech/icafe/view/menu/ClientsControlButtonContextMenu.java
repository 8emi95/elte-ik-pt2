/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.menu;

import hu.elte.progtech.icafe.view.MainFrame;
import hu.elte.progtech.icafe.view.listener.CreateNewClientListener;
import hu.elte.progtech.icafe.view.listener.ShowClientsListListener;

import java.awt.*;

public class ClientsControlButtonContextMenu extends AbstractContextMenu {

    public ClientsControlButtonContextMenu(Component component) {
        super(component);
        if (component.getClass().equals(MainFrame.class)) {
            MainFrame mainFrame = (MainFrame) component;
            this.add(createMenuItem("Ügyfelek listázása", new ShowClientsListListener(mainFrame)));
            this.add(createMenuItem("Új ügyfél hozzáadása", new CreateNewClientListener(mainFrame)));
        } else
            throw new IllegalArgumentException();
    }
}
