/**
 * Created by Csaba_Hete on 2017.05.10..
 */
package hu.elte.progtech.icafe.view.table.model;

import hu.elte.progtech.icafe.api.entity.Entity;
import hu.elte.progtech.icafe.api.service.DaoService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractEntityTableModel<T extends Entity> extends AbstractTableModel {

    protected final DaoService<T> service;
    private final String[] columnNames;
    protected List<T> entities;

    public AbstractEntityTableModel(String[] columnNames, DaoService<T> service) {
        this.service  = service;
        this.columnNames = columnNames;
        entities = new ArrayList<>();
        reloadEntities();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public T getEntityAtRow(int rowIndex) {
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
        T entity = getEntityAtRow(rowIndex);
        return getAttributeOfEntity(entity, columnIndex);
    }

    public final void reloadEntities() {
        new SwingWorker<List<T>, Void>() {
            @Override
            protected List<T> doInBackground() throws Exception {
                return service.getEntities();
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

    protected abstract Object getAttributeOfEntity(T entity, int columnIndex);

    protected abstract void displayError(SQLException sqlException);
}
