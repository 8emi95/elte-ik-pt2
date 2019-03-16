package hu.elte.progtech.icafe.view.table;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.view.listener.ShowContextMenuOnTableRightClickListener;
import hu.elte.progtech.icafe.view.menu.ComputersTableContextMenu;
import hu.elte.progtech.icafe.view.table.model.ComputersTableModel;

public class ComputerTable extends AbstractEntityTable<Computer>{

    public ComputerTable() {
        super(new ComputersTableModel());
        addMouseListener(new ShowContextMenuOnTableRightClickListener(new ComputersTableContextMenu(this)));
    }

}
