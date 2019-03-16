package hu.valdar.progtech.frontend.model.table;

import hu.valdar.progtech.api.entity.ArtistEntity;
import hu.valdar.progtech.api.entity.BandArtistEntity;
import hu.valdar.progtech.api.entity.BandEntity;
import hu.valdar.progtech.backend.db.DataSource;

import java.sql.SQLException;

public class BandArtistTableModel extends EntityTableModel<BandArtistEntity>{

    public BandArtistTableModel() {
        super(BandArtistEntity.FIELD_NAMES, DataSource.getInstance().getBandArtistDao());
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ArtistEntity.class;
            case 1:
                return BandEntity.class;
            default:
                return null;
        }
    }

    @Override
    protected Object getAttributeOfEntity(BandArtistEntity bandArtist, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return bandArtist.getArtist();
            case 1:
                return bandArtist.getBand();
            default:
                return null;
        }
    }

    @Override
    protected void setEntityAttributes(int columnIndex, BandArtistEntity bandArtist, Object aValue) {
        switch (columnIndex) {
            case 0:
                bandArtist.setArtist((ArtistEntity) aValue);
                break;
            case 1:
                bandArtist.setBand((BandEntity) aValue);
                break;
        }
    }

    @Override
    public void addNewEntity() {
        try{
            if(DataSource.getInstance().getArtistDao().getEntityCount() == 0){
                throw new SQLException("There are no artist yet!");
            }

            if(DataSource.getInstance().getBandDao().getEntityCount() == 0){
                throw new SQLException("There are no band yet!");
            }

            BandArtistEntity bandArtistEntity = new BandArtistEntity();
            bandArtistEntity.setArtist(DataSource.getInstance().getArtistDao().getEntityByRowIndex(0));
            bandArtistEntity.setBand(DataSource.getInstance().getBandDao().getEntityByRowIndex(0));
            addNewEntity(bandArtistEntity);
        } catch (SQLException ex){
            displayError(ex);
        }
    }
}
