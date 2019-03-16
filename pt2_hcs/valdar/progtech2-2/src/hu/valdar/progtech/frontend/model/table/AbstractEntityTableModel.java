package hu.valdar.progtech.frontend.model.table;

import hu.valdar.progtech.api.entity.AbstractEntity;
import hu.valdar.progtech.backend.db.dao.IEntityDao;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractEntityTableModel<E extends AbstractEntity> extends AbstractTableModel {

    protected final String[] columnNames;
    protected final int refreshInterval;
    protected final Timer refreshTimer;
    protected List<E> entities;
    protected IEntityDao<E> dao;

    public AbstractEntityTableModel(String[] columnNames, IEntityDao<E> dao) {
        this.dao = dao;
        this.columnNames = columnNames;
        this.refreshInterval = 60 * 1000;
        entities = new ArrayList<>();
        refreshTimer = new Timer(refreshInterval, (ActionEvent e) -> reloadEntities());
        reloadEntities();
        refreshTimer.start();

    }

    protected E getEntityAtRow(int rowIndex) {
        return entities.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return entities.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        E entity = getEntityAtRow(rowIndex);
        return getAttributeOfEntity(entity, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public void setValueAt(Object aValue, final int rowIndex, int columnIndex) {
        final E entity = getEntityAtRow(rowIndex);
        setEntityAttributes(columnIndex, entity, aValue);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                dao.updateEntity(entity, rowIndex);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    reloadEntities();
                } catch (InterruptedException | ExecutionException ex) {
                    displayError((SQLException) ex.getCause());
                }
            }

        }.execute();
    }

    protected void addNewEntity(final E entity) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                dao.addEntity(entity);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    reloadEntities();
                } catch (InterruptedException | ExecutionException ex) {
                    displayError((SQLException) ex.getCause());
                }
            }

        }.execute();
    }

    public void deleteEntity(final int rowIndex) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                dao.deleteEntity(rowIndex);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    reloadEntities();
                } catch (InterruptedException | ExecutionException ex) {
                    displayError((SQLException) ex.getCause());
                }
            }

        }.execute();
    }

    protected final void reloadEntities() {
        new SwingWorker<List<E>, Void>() {
            @Override
            protected List<E> doInBackground() throws Exception {
                return dao.getEntities();
            }

            @Override
            protected void done() {
                try {
                    entities = get();
                    fireTableDataChanged();
                } catch (InterruptedException | ExecutionException ex) {
                    displayError((SQLException) ex.getCause());
                }
            }

        }.execute();
    }

    protected abstract void displayError(SQLException sqlException);

    protected abstract Object getAttributeOfEntity(E entity, int columnIndex);

    protected abstract void setEntityAttributes(int columnIndex, final E entity, Object aValue);

    public abstract void addNewEntity();
}
