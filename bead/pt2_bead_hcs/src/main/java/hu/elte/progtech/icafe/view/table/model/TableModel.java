/**
 * Created by Csaba_Hete on 2017.03.21..
 */
package hu.elte.progtech.icafe.view.table.model;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private String[] columnNames = new String[] {};
    private Object[][] data = new Object[][] {};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setColumnNames(String[] columnNames) {
        if (null != columnNames){
            this.columnNames = columnNames;
            fireTableStructureChanged();
        }
    }

    public void setData(Object[][] data) {
        if (null != data){
            this.data = data;
            fireTableRowsUpdated(0, data.length);
        }
    }
}
