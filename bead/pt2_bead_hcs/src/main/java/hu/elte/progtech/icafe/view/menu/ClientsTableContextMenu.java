/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.menu;

import hu.elte.progtech.icafe.view.listener.EditClientListener;
import hu.elte.progtech.icafe.view.listener.LoginClientListener;
import hu.elte.progtech.icafe.view.listener.LogoutClientListener;
import hu.elte.progtech.icafe.view.table.UserTable;

import java.awt.*;

public class ClientsTableContextMenu extends AbstractContextMenu {

    public ClientsTableContextMenu(Component component) {
        super(component);
        if (component.getClass().equals(UserTable.class)) {
            UserTable table = (UserTable) component;
            this.add(createMenuItem("Ügyfél megtekintése", new EditClientListener(table)));
            this.add(createMenuItem("Ügyfél beléptetése", new LoginClientListener(table)));
            this.add(createMenuItem("Ügyfél kiléptetése", new LogoutClientListener(table)));
        } else
            throw new IllegalArgumentException();
    }

}
