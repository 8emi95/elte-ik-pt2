package hu.elte.progtech.icafe.view.table.model;

import hu.elte.progtech.icafe.api.entity.Entity;
import hu.elte.progtech.icafe.api.service.DaoService;

public abstract class EntityTableModel<E extends Entity> extends AbstractEntityTableModel<E> {

    public EntityTableModel(String[] columnNames, DaoService<E> service) {
        super(columnNames, service);
    }
}
