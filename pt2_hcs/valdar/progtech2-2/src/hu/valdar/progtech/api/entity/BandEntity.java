package hu.valdar.progtech.api.entity;

import java.util.Date;

/**
 * POJO (Plain Old Java Object), melynek egy példánya az BAND tábla egy sorát reprezentálja.
 */
public class BandEntity extends AbstractEntity{

    /**
     * A zenekar neve, mely a NAME attribútumnak felel meg.
     */
    private String name;
    /**
     * Az alapítás éve, mely az ESTABLISHMENT_DATE attribútumnak felel meg.
     */
    private Date establishmentDate;

    /**
     * Oszlopok elnevezése a megjelenítéshez
     */
    public static final String[] FIELD_NAMES = {"Name", "Estabilishment date"};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    @Override
    public String toString() {
        return name;
    }
}
