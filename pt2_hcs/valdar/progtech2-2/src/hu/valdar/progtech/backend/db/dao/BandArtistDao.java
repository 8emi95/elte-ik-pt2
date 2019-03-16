package hu.valdar.progtech.backend.db.dao;

import hu.valdar.progtech.api.entity.ArtistEntity;
import hu.valdar.progtech.api.entity.BandArtistEntity;
import hu.valdar.progtech.api.entity.BandEntity;
import hu.valdar.progtech.backend.db.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BandArtistDao extends AbstractEntityDao<BandArtistEntity>{

    public BandArtistDao() {
        super("BAND_ARTIST");
    }

    @Override
    protected BandArtistEntity newEntity() {
        return new BandArtistEntity();
    }

    @Override
    protected void setEntityAttributes(BandArtistEntity entity, ResultSet resultSet) throws SQLException {
        try {
            ArtistEntity artist = DataSource.getInstance().getArtistDao()
                    .getEntityById(resultSet.getLong("ARTIST_ID"));
            entity.setArtist(artist);
        } catch (SQLException ex){
            throw new SQLException("Nem talalhato a megadott ID-val ArtistEntity!", ex);
        }

        try {
            BandEntity band = DataSource.getInstance().getBandDao()
                    .getEntityById(resultSet.getLong("BAND_ID"));
            entity.setBand(band);
        } catch (SQLException ex){
            throw new SQLException("Nem talalhato a megadott ID-val BandEntity!", ex);
        }


    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, BandArtistEntity entity) throws SQLException {
        resultSet.updateLong("ARTIST_ID", entity.getArtist().getId());
        resultSet.updateLong("BAND_ID", entity.getBand().getId());
    }
}
