package hu.valdar.progtech.backend.db.dao;

import hu.valdar.progtech.api.entity.ArtistEntity;
import hu.valdar.progtech.api.entity.BandEntity;
import hu.valdar.progtech.backend.db.DataSource;
import hu.valdar.progtech.backend.db.connection.DBConnectionSource;

import java.sql.Connection;
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
        entity.setName(resultSet.getString("NAME"));
        entity.setAge(resultSet.getInt("AGE"));
        entity.setInstrument(resultSet.getString("INSTRUMENT"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, ArtistEntity entity) throws SQLException {
        resultSet.updateString("NAME", entity.getName());
        resultSet.updateInt("AGE", entity.getAge());
        resultSet.updateString("INSTRUMENT", entity.getInstrument());
    }
}
