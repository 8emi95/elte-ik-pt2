package hu.elte.progtech.icafe.view.table;

import hu.elte.progtech.icafe.api.entity.Entity;
import hu.elte.progtech.icafe.view.table.model.EntityTableModel;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class AbstractEntityTable<T extends Entity> extends JTable{
    public AbstractEntityTable(EntityTableModel<T> tableModel) {
        super(tableModel);
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setAutoCreateRowSorter(true);
    }
}
