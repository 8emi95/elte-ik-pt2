package hu.valdar.progtech.frontend.model.combobox;

import hu.valdar.progtech.api.entity.AbstractEntity;
import hu.valdar.progtech.backend.db.dao.IEntityDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractEntityComboBoxModel<E extends AbstractEntity> extends DefaultComboBoxModel<E> {

    private List<E> entities;
    private final int refreshInterval;
    private final Timer refreshTimer;
    private final IEntityDao<E> dao;

    public AbstractEntityComboBoxModel(IEntityDao<E> dao) {
        this.dao = dao;
        entities = new ArrayList<>();
        this.refreshInterval = 60 * 1000;
        this.refreshTimer = new Timer(refreshInterval, (ActionEvent e) -> {
            refreshEntities();
        });
        refreshTimer.start();
        refreshEntities();
    }

    @Override
    public int getSize() {
        return entities.size();
    }

    @Override
    public E getElementAt(int index) {
        return entities.get(index);
    }

    private void refreshEntities() {
        new SwingWorker<List<E>, Void>() {

            @Override
            protected List<E> doInBackground() throws Exception {
                List<E> entities = new ArrayList<>();
                int entityCount = dao.getEntityCount();
                for (int rowIndex = 0; rowIndex < entityCount; rowIndex++) {
                    entities.add(dao.getEntityByRowIndex(rowIndex));
                }
                return entities;
            }

            @Override
            protected void done() {
                try {
                    entities = get();
                    fireContentsChanged(this, 0, getSize());
                } catch (InterruptedException | ExecutionException ex) {
                    displayError((SQLException) ex.getCause());
                }
            }
        }.execute();
    }

    protected abstract void displayError(SQLException sqlException);
}
