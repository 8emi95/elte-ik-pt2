/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Entity;

import java.util.List;

public interface DaoService<E extends Entity> {
    int getEntityCount() throws Exception;
    List<E> getEntities() throws Exception;
    E getEntityById(long id) throws Exception;
    E getEntityByRowIndex(int rowIndex) throws Exception;
    void addEntity(E entity) throws Exception;
    void deleteEntity(long index) throws Exception;
    void updateEntity(E entity, long index) throws Exception;
    List<E> getEntitiesByForeignKey(final String column, final long id) throws Exception;
}
