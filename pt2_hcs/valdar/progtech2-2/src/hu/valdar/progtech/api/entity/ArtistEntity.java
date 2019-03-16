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
     * Oszlopok elnevezése a megjelenítéshez
     */
    public static final String[] FIELD_NAMES = {"Name", "Age", "Instrument"};

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

    @Override
    public String toString() {
        return name;
    }
}
