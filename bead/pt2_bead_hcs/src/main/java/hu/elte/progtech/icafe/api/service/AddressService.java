/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Address;
import hu.elte.progtech.icafe.backend.dao.AddressDao;
import hu.elte.progtech.icafe.backend.dao.DataSource;

import java.util.List;

public class AddressService implements DaoService<Address> {
    private AddressDao addressDao;

    public AddressService() {
        addressDao = DataSource.getInstance().getAddressDao();
    }

    @Override
    public int getEntityCount() throws Exception {
        return addressDao.getEntityCount();
    }

    @Override
    public List<Address> getEntities() throws Exception {
        return addressDao.getEntities();
    }

    @Override
    public Address getEntityById(long id) throws Exception {
        return addressDao.getEntityById(id);
    }

    @Override
    public Address getEntityByRowIndex(int rowIndex) throws Exception {
        return addressDao.getEntityByRowIndex(rowIndex);
    }

    @Override
    public void addEntity(Address entity) throws Exception {
        addressDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(long index) throws Exception {
        addressDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(Address entity, long index) throws Exception {
        addressDao.updateEntity(entity, index);
    }

    @Override
    public List<Address> getEntitiesByForeignKey(final String column, final long id) throws Exception{
        return addressDao.getEntitiesByForeignKey(column, id);
    }
}
