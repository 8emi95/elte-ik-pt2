package hu.valdar.progtech.backend.dao;

import hu.valdar.progtech.api.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Az entitásokkal végezhető műveletekhez kapcsolódó közös felület.
 * @param <E> entitás típusa, melyet felülről az AbstractEntity típus korlátoz.
 */
public interface IEntityDao<E extends AbstractEntity> {

    /**
     * Metódus melynek a segítségével lekérdezhetjük, hogy hány sor található az adatbázisban, az adott entitásnak megfelelő táblában.
     * @return darabszám
     */
    int getEntityCount() throws SQLException;

    /**
     * Metódus, amely visszaadja az adott táblához tartozó összes sort entitásként.
     */
    List<E> getEntities() throws SQLException;

    /**
     * Metódus, amely segítségével visszaadhatjuk entitásként az azonosítóhoz tartozó sor az adatbázisból, amennyiben létezik.
     * @param id azonosító
     */
    E getEntityById(long id) throws SQLException;

    /**
     * Metódus, amely segítségével visszaadhatjuk entitásként az adott indexhez tartozó sor az adatbázisból, amennyiben létezik.
     * @param rowIndex sor index.
     */
    E getEntityByRowIndex(int rowIndex) throws SQLException;

    /**
     * Metódus, melynek segítségével új sort tudunk szúrni az entitásnak megfelelő táblába.
     * @param entity Entitás, amely tartalmazza az adatbázisba menteni kívánt értékeket.
     */
    void addEntity(E entity) throws SQLException;

    /**
     * Metódus, melynek segítségével törölni tudunk egy sort az entitásnak megfelelő táblából.
     * @param index sor index
     */
    void deleteEntity(int index) throws SQLException;

    /**
     * Metódus, melynek segítségével módosítani tudunk egy létező sort az adatbázisban.
     * @param entity Módosítani kívánt entitás, amely tartalmazza a módosított adatokat.
     * @param index Sor index, ahol a módosítani kívánt entitás elhelyezkedik.
     */
    void updateEntity(E entity, int index) throws SQLException;

}
