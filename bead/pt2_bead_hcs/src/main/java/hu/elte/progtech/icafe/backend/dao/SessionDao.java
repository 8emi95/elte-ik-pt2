/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.Computer;
import hu.elte.progtech.icafe.api.entity.Session;
import hu.elte.progtech.icafe.api.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDao extends AbstractEntityDao<Session> {
    private static final String TABLE = "SESSIONS";

    public SessionDao() {
        super(TABLE);
    }

    @Override
    protected Session newEntity() {
        Session session = new Session();
        session.setComputer(new Computer());
        session.setUser(new User());
        return session;
    }

    @Override
    protected void setEntityAttributes(Session entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getLong("ID"));
        entity.setUserId(resultSet.getLong("USER_ID"));
        entity.setComputerId(resultSet.getLong("COMPUTER_ID"));
        entity.setStartTime(resultSet.getTimestamp("START_TIME"));
        entity.setEndTime(resultSet.getTimestamp("END_TIME"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, Session entity) throws SQLException {
        resultSet.updateLong("USER_ID", entity.getUserId());
        resultSet.updateLong("COMPUTER_ID", entity.getComputerId());
        if (null != entity.getStartTime())
            resultSet.updateTimestamp("END_TIME", entity.getEndTime());
    }
}
