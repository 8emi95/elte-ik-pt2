package hu.valdar.progtech.backend.db;

import hu.valdar.progtech.backend.db.dao.ArtistDao;
import hu.valdar.progtech.backend.db.dao.BandArtistDao;
import hu.valdar.progtech.backend.db.dao.BandDao;

public class DataSource {

    private final ArtistDao artistDao;
    private final BandDao bandDao;
    private final BandArtistDao bandArtistDao;


    private DataSource(){
        this.artistDao = new ArtistDao();
        this.bandDao = new BandDao();
        this.bandArtistDao = new BandArtistDao();
    }

    public static DataSource getInstance(){
        return DataSourceHolder.INSTANCE;
    }

    private static class DataSourceHolder{
        private static final DataSource INSTANCE = new DataSource();
    }

    public ArtistDao getArtistDao() {
        return artistDao;
    }

    public BandDao getBandDao() {
        return bandDao;
    }

    public BandArtistDao getBandArtistDao() {
        return bandArtistDao;
    }
}
