package hu.elte.progtech.icafe.view.table;

import hu.elte.progtech.icafe.api.entity.Payment;
import hu.elte.progtech.icafe.view.listener.ShowContextMenuOnTableRightClickListener;
import hu.elte.progtech.icafe.view.menu.ComputersTableContextMenu;
import hu.elte.progtech.icafe.view.menu.PaymentsTableContextMenu;
import hu.elte.progtech.icafe.view.table.model.PaymentsTableModel;

public class PaymentsTable extends AbstractEntityTable<Payment>{

    public PaymentsTable() {
        super(new PaymentsTableModel());
        addMouseListener(new ShowContextMenuOnTableRightClickListener(new PaymentsTableContextMenu(this)));
    }
}
