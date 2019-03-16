package hu.valdar.progtech.backend.service;

import hu.valdar.progtech.api.entity.ArtistEntity;
import hu.valdar.progtech.api.service.ArtistService;
import hu.valdar.progtech.backend.dao.ArtistDao;
import hu.valdar.progtech.backend.dao.IEntityDao;

import java.sql.SQLException;
import java.util.List;

public class ArtistServiceImpl implements ArtistService {

    private final IEntityDao<ArtistEntity> artistEntityDao;

    public ArtistServiceImpl(){
        this.artistEntityDao = new ArtistDao();
    }

    @Override
    public int getEntityCount() throws SQLException {
        return artistEntityDao.getEntityCount();
    }

    @Override
    public List<ArtistEntity> getEntities() throws SQLException {
        return artistEntityDao.getEntities();
    }

    @Override
    public ArtistEntity getEntityById(long id) throws SQLException {
        return artistEntityDao.getEntityById(id);
    }

    @Override
    public ArtistEntity getEntityByRowIndex(int rowIndex) throws SQLException {
        return artistEntityDao.getEntityByRowIndex(rowIndex);
    }

    @Override
    public void addEntity(ArtistEntity entity) throws SQLException {
        artistEntityDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(int index) throws SQLException {
        artistEntityDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(ArtistEntity entity, int index) throws SQLException {
        artistEntityDao.updateEntity(entity, index);
    }
}
