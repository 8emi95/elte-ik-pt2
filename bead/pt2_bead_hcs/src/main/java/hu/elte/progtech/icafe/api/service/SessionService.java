/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.api.service;

import hu.elte.progtech.icafe.api.entity.Session;
import hu.elte.progtech.icafe.backend.dao.DataSource;
import hu.elte.progtech.icafe.backend.dao.SessionDao;

import java.sql.Timestamp;
import java.util.List;

public class SessionService implements DaoService<Session> {
    private SessionDao sessionDao;

    public SessionService() {
        sessionDao = DataSource.getInstance().getSessionDao();
    }

    @Override
    public int getEntityCount() throws Exception {
        return sessionDao.getEntityCount();
    }

    @Override
    public List<Session> getEntities() throws Exception {
        return sessionDao.getEntities();
    }

    @Override
    public Session getEntityById(long id) throws Exception {
        Session session = sessionDao.getEntityById(id);
        session.setComputer(new ComputerService().getEntityById(session.getComputerId()));
        return session;
    }

    @Override
    public Session getEntityByRowIndex(int rowIndex) throws Exception {
        return sessionDao.getEntityByRowIndex(rowIndex);
    }

    @Override
    public void addEntity(Session entity) throws Exception {
        sessionDao.addEntity(entity);
    }

    @Override
    public void deleteEntity(long index) throws Exception {
        sessionDao.deleteEntity(index);
    }

    @Override
    public void updateEntity(Session entity, long index) throws Exception {
        sessionDao.updateEntity(entity, index);
    }

    @Override
    public List<Session> getEntitiesByForeignKey(final String column, final long id) throws Exception {
        return sessionDao.getEntitiesByForeignKey(column, id);
    }

    public static Long getDiffInCeiledHours(Timestamp time1, Timestamp time2)
    {
        long milliseconds1 = time2.getTime();
        long milliseconds2 = time1.getTime();

        long diff =Math.abs( milliseconds2 - milliseconds1);
        return (long) Math.ceil((double) diff / (60 * 60 * 1000));
    }
}
