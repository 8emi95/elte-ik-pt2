/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T extends Entity> {
    int getEntityCount() throws SQLException;
    List<T> getEntities() throws SQLException;
    T getEntityById(final long id) throws SQLException;
    List<T> getEntitiesByForeignKey(final String column, final long id) throws SQLException;
    T getEntityByRowIndex(final int rowIndex) throws SQLException;
    long addEntity(final T model) throws SQLException;
    void updateEntity(final T model, final long id) throws SQLException;
    void deleteEntity(final long id) throws SQLException;
}
