package hu.valdar.progtech.frontend.model.table;

import hu.valdar.progtech.api.entity.AbstractEntity;
import hu.valdar.progtech.backend.db.dao.IEntityDao;
import hu.valdar.progtech.frontend.MainFrame;

import java.sql.SQLException;

public abstract class EntityTableModel<E extends AbstractEntity> extends AbstractEntityTableModel<E> {

    public EntityTableModel(String[] columnNames, IEntityDao<E> dao) {
        super(columnNames, dao);
    }

    @Override
    protected void displayError(SQLException sqlException) {
        MainFrame.showError(sqlException.getMessage());
    }
}
