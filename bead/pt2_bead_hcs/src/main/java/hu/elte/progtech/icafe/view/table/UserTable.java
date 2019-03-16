package hu.elte.progtech.icafe.view.table;

import hu.elte.progtech.icafe.api.entity.User;
import hu.elte.progtech.icafe.view.listener.ShowContextMenuOnTableRightClickListener;
import hu.elte.progtech.icafe.view.menu.ClientsTableContextMenu;
import hu.elte.progtech.icafe.view.table.model.UserTableModel;

public class UserTable extends AbstractEntityTable<User>{

    public UserTable() {
        super(new UserTableModel());
        addMouseListener(new ShowContextMenuOnTableRightClickListener(new ClientsTableContextMenu(this)));
    }

}
