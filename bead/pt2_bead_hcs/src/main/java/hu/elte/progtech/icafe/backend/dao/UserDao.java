/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserDao extends AbstractEntityDao<User> {
    private static final String TABLE = "USERS";

    public UserDao() {
        super(TABLE);
    }

    @Override
    protected User newEntity() {
        return new User();
    }

    @Override
    protected void setEntityAttributes(User entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getLong("ID"));
        entity.setName(resultSet.getString("NAME"));
        entity.setIdNumber(resultSet.getString("ID_NUMBER"));
        entity.setUsername(resultSet.getString("USERNAME"));
        entity.setPassword(resultSet.getString("PASSWORD"));
        entity.setPoints(resultSet.getInt("POINTS"));
        entity.setPresent(resultSet.getBoolean("PRESENT"));
        entity.setCreatedAt(resultSet.getTimestamp("CREATED_AT"));
        entity.setUpdatedAt(resultSet.getTimestamp("UPDATED_AT"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, User entity) throws SQLException {
        resultSet.updateString("NAME", entity.getName());
        resultSet.updateString("ID_NUMBER", entity.getIdNumber());
        resultSet.updateString("USERNAME", entity.getUsername());
        resultSet.updateString("PASSWORD", entity.getPassword());
        if (null != entity.getPoints())
            resultSet.updateInt("POINTS", entity.getPoints());
        if (null != entity.isPresent())
            resultSet.updateBoolean("PRESENT", entity.isPresent());
        if (null != entity.getCreatedAt())
            resultSet.updateTimestamp("UPDATED_AT", Timestamp.valueOf(LocalDateTime.now()));
    }
}
