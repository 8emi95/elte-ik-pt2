package hu.valdar.progtech.frontend.model.combobox;

import hu.valdar.progtech.api.entity.AbstractEntity;
import hu.valdar.progtech.backend.db.dao.IEntityDao;
import hu.valdar.progtech.frontend.MainFrame;

import java.sql.SQLException;

public class EntityComboBoxModel<E extends AbstractEntity> extends AbstractEntityComboBoxModel<E>{

    public EntityComboBoxModel(IEntityDao<E> dao) {
        super(dao);
    }

    @Override
    protected void displayError(SQLException sqlException) {
        MainFrame.showError(sqlException.getMessage());
    }
}
