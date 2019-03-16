package hu.valdar.progtech.backend.dao;

import hu.valdar.progtech.api.entity.BandEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BandDao extends AbstractEntityDao<BandEntity>{

    public BandDao() {
        super("BAND");
    }

    @Override
    protected BandEntity newEntity() {
        return new BandEntity();
    }

    @Override
    protected void setEntityAttributes(BandEntity entity, ResultSet resultSet) throws SQLException {
        //TODO: Implementálás
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, BandEntity entity) throws SQLException {
        //TODO: Implementálás
    }
}
