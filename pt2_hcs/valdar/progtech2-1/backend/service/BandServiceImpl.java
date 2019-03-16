package hu.valdar.progtech.backend.service;


import hu.valdar.progtech.api.entity.BandEntity;
import hu.valdar.progtech.api.service.BandService;
import hu.valdar.progtech.backend.dao.BandDao;
import hu.valdar.progtech.backend.dao.IEntityDao;

import java.sql.SQLException;
import java.util.List;

public class BandServiceImpl implements BandService{

    private final IEntityDao<BandEntity> bandEntityDao;

    public BandServiceImpl(){
        this.bandEntityDao = new BandDao();
    }

    @Override
    public BandEntity getEntityByRowIndex(int rowIndex) throws SQLException {
        return bandEntityDao.getEntityByRowIndex(rowIndex);
    }

    @Override
    public int getEntityCount() throws SQLException {
        return bandEntityDao.getEntityCount();
    }

    @Override
    public List<BandEntity> getEntities() throws SQLException {
        return bandEntityDao.getEntities();
    }

    @Override
    public BandEntity getEntityById(long id) throws SQLException {
        return bandEntityDao.getEntityById(id);
    }

    @Override
    public void addEntity(BandEntity entity) throws SQLException {
        bandEntityDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(int index) throws SQLException {
        bandEntityDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(BandEntity entity, int index) throws SQLException {
        bandEntityDao.updateEntity(entity, index);
    }

}
