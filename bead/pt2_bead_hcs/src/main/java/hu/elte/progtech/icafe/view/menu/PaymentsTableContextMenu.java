/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.menu;

import hu.elte.progtech.icafe.view.listener.EditClientListener;
import hu.elte.progtech.icafe.view.listener.FulfillPaymentListener;
import hu.elte.progtech.icafe.view.listener.LoginClientListener;
import hu.elte.progtech.icafe.view.listener.LogoutClientListener;
import hu.elte.progtech.icafe.view.table.PaymentsTable;
import hu.elte.progtech.icafe.view.table.UserTable;

import java.awt.*;

public class PaymentsTableContextMenu extends AbstractContextMenu {

    public PaymentsTableContextMenu(Component component) {
        super(component);
        if (component.getClass().equals(PaymentsTable.class)) {
            PaymentsTable table = (PaymentsTable) component;
            this.add(createMenuItem("Befizetés teljesítése", new FulfillPaymentListener(table)));
        } else
            throw new IllegalArgumentException();
    }

}
