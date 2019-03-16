package hu.valdar.progtech.api.entity;

public class BandArtistEntity extends AbstractEntity{

    /**
     * A zenész bandája, mely az ARTIST_ID attribútum feloldásának felel meg.
     * Míg adatbázis oldalon idegen kulcsot használunk, úgy Java-ban a konkrét entitást szokás felvenni.
     */
    private ArtistEntity artist;

    /**
     * A zenész bandája, mely a BAND_ID attribútum feloldásának felel meg.
     * Míg adatbázis oldalon idegen kulcsot használunk, úgy Java-ban a konkrét entitást szokás felvenni.
     */
    private BandEntity band;

    public ArtistEntity getArtist() {
        return artist;
    }

    public static final String[] FIELD_NAMES = {"Artist", "Band"};

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public BandEntity getBand() {
        return band;
    }

    public void setBand(BandEntity band) {
        this.band = band;
    }
}
