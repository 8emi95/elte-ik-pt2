package hu.valdar.progtech.backend.dao;

import hu.valdar.progtech.api.entity.ArtistEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistDao extends AbstractEntityDao<ArtistEntity>{

    private static final String TABLE_NAME = "ARTIST";

    public ArtistDao() {
        super(TABLE_NAME);
    }

    @Override
    protected ArtistEntity newEntity() {
        return new ArtistEntity();
    }

    @Override
    protected void setEntityAttributes(ArtistEntity entity, ResultSet resultSet) throws SQLException {
        //TODO: Implementálás
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, ArtistEntity entity) throws SQLException {
        //TODO: Implementálás
    }
}
