package hu.valdar.progtech.api.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Identifiable<Long>, Serializable {

    protected static final Long serialVersionUID = 1L;

    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Két absztrakt entitást, amennyiben ugyan ahhoz a típushoz tartozik azonosnak tekintünk, amennyiben az azonosítójuk
     * megegyezik.
     *
     * @return a megadott objektum megegyezik-e az aktuális objektummal.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object.getClass().equals(this.getClass()))) {
            return false;
        }

        final Identifiable other = (Identifiable) object;
        return (this.getId() != null || other.getId() == null)
                && (this.getId() == null || this.getId().equals(other.getId()));
    }

    /**
     * A Hash Code Contract értelmében, megegyező objektumok esetén, ugyan azt a hash-t kell visszaadunk.
     * Mivel az equals-t felüldefiniáltuk, így a hashCode-ot is felül kell a fentebbi szabály teljesítése érdekében.
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--">Oracle - Java SE8 Reference</a>
     * @return a generált hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        final Long id = getId();
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[id = " + getId() + "]";
    }
}
