package hu.valdar.progtech.frontend.model.table;

import hu.valdar.progtech.api.entity.BandEntity;
import hu.valdar.progtech.backend.db.DataSource;

import java.util.Date;

public class BandTableModel extends EntityTableModel<BandEntity>{

    public BandTableModel() {
        super(BandEntity.FIELD_NAMES, DataSource.getInstance().getBandDao());
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Date.class;
            default:
                return null;
        }
    }

    @Override
    protected Object getAttributeOfEntity(BandEntity band, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return band.getName();
            case 1:
                return band.getEstablishmentDate();
            default:
                return null;
        }
    }

    @Override
    protected void setEntityAttributes(int columnIndex, BandEntity band, Object aValue) {
        switch (columnIndex) {
            case 0:
                band.setName((String) aValue);
                break;
            case 1:
                band.setEstablishmentDate((Date) aValue);
                break;
        }
    }

    @Override
    public void addNewEntity() {
        Date date = new Date(new java.util.Date().getTime());
        BandEntity band = new BandEntity();
        band.setName("New band");
        band.setEstablishmentDate(date);
        addNewEntity(band);
    }

}
