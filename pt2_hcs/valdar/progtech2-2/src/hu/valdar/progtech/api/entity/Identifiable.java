package hu.valdar.progtech.api.entity;

/**
 * Azonosítóval rendelkező objektumok felülete
 * @param <T> Az azonosító típusa, melyet felülről a Number korlátoz.
 */
public interface Identifiable<T extends Number> {

    /**
     * A metódus (függvény) segítségével elkérhetjük az azonosítandó objektum azonosítóját.
     * @return azonosító
     */
    T getId();

    /**
     * A metódus (eljárás) segítségével beállíthatjuk az aktuális objektum azonosítóját.
     * @param id azonosító
     */
    void setId(T id);
}
