/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.backend.dao.ComputerDao;
import hu.elte.progtech.icafe.backend.dao.DataSource;

import java.util.List;
import java.util.Optional;

public class ComputerService implements DaoService<Computer> {
    private ComputerDao computerDao;

    public ComputerService() {
        computerDao = DataSource.getInstance().getComputerDao();
    }

    @Override
    public int getEntityCount() throws Exception {
        return computerDao.getEntityCount();
    }

    @Override
    public List<Computer> getEntities() throws Exception {
        return computerDao.getEntities();
    }

    @Override
    public Computer getEntityById(long id) throws Exception {
        return computerDao.getEntityById(id);
    }

    @Override
    public Computer getEntityByRowIndex(int rowIndex) throws Exception {
        return computerDao.getEntityByRowIndex(rowIndex);
    }

    @Override
    public void addEntity(Computer entity) throws Exception {
        checkNameAvailability(entity);
        computerDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(long index) throws Exception {
        computerDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(Computer entity, long index) throws Exception {
        checkNameAvailability(entity);
        computerDao.updateEntity(entity, index);
    }

    @Override
    public List<Computer> getEntitiesByForeignKey(final String column, final long id) throws Exception {
        return computerDao.getEntitiesByForeignKey(column, id);
    }

    private void checkNameAvailability(Computer entity) throws Exception {
        List<Computer> entities = computerDao.getEntities();
        if (entities.stream().anyMatch(o -> o.getName().equals(entity.getName()) && !o.getId().equals(entity.getId()))) {
            throw new Exception("Már létezik ilyen nevű számítógép!");
        }
    }

    public Computer getFirstFree() throws Exception {
        List<Computer> computers = computerDao.getEntities();
        Optional<Computer> free = computers.stream().filter(o -> !o.isBusy()).findFirst();
        return free.isPresent() ? free.get() : null;
    }

    public void setBusy(Computer computer) throws Exception {
        computer.setBusy(true);
        computerDao.updateEntity(computer, computer.getId());
    }

    public void setFree(Computer computer) throws Exception {
        computer.setBusy(false);
        computerDao.updateEntity(computer, computer.getId());
    }
}
