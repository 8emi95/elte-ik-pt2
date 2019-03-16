package hu.valdar.progtech.api.entity;

/**
 * POJO (Plain Old Java Object), melynek egy példánya az ARTIST tábla egy sorát reprezentálja.
 */
public class ArtistEntity extends AbstractEntity{

    /**
     * A zenész neve, mely a NAME attribútumnak felel meg.
     */
    private String name;
    /**
     * A zenész kora, mely az AGE attribútumnak felel meg.
     */
    private int age;
    /**
     * A zenész hangszere, mely az INSTRUMENT attribútumnak felel meg.
     */
    private String instrument;
    /**
     * A zenész bandája, mely a BAND_ID attribútum feloldásának felel meg.
     * Míg adatbázis oldalon idegen kulcsot használunk, úgy Java-ban a konkrét entitást szokás felvenni.
     */
    private BandEntity band;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public BandEntity getBand() {
        return band;
    }

    public void setBand(BandEntity band) {
        this.band = band;
    }
}
