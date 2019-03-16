package hu.valdar.progtech.backend.db.dao;

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
        entity.setName(resultSet.getString("NAME"));
        entity.setEstablishmentDate(resultSet.getDate("ESTABLISHMENT_DATE"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, BandEntity entity) throws SQLException {
        resultSet.updateString("NAME", entity.getName());
        resultSet.updateDate("ESTABLISHMENT_DATE", new java.sql.Date(entity.getEstablishmentDate().getTime()));
    }
}
