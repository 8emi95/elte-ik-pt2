/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.Computer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ComputerDao extends AbstractEntityDao<Computer> {
    private static final String TABLE = "COMPUTERS";

    public ComputerDao() {
        super(TABLE);
    }

    @Override
    protected Computer newEntity() {
        return new Computer();
    }

    @Override
    protected void setEntityAttributes(Computer entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getLong("ID"));
        entity.setName(resultSet.getString("NAME"));
        entity.setCpu(resultSet.getString("CPU"));
        entity.setMotherboard(resultSet.getString("MOTHERBOARD"));
        entity.setMemory(resultSet.getString("MEMORY"));
        entity.setVga(resultSet.getString("VGA"));
        entity.setMassStorage(resultSet.getString("MASS_STORAGE"));
        entity.setOs(resultSet.getString("OS"));
        entity.setBusy(resultSet.getBoolean("BUSY"));
        entity.setCreatedAt(resultSet.getTimestamp("CREATED_AT"));
        entity.setUpdatedAt(resultSet.getTimestamp("UPDATED_AT"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, Computer entity) throws SQLException {
        resultSet.updateString("NAME", entity.getName());
        resultSet.updateString("CPU", entity.getCpu());
        resultSet.updateString("MOTHERBOARD", entity.getMotherboard());
        resultSet.updateString("MEMORY", entity.getMemory());
        resultSet.updateString("VGA", entity.getVga());
        resultSet.updateString("MASS_STORAGE", entity.getMassStorage());
        resultSet.updateString("OS", entity.getOs());
        if (null != entity.isBusy())
            resultSet.updateBoolean("BUSY", entity.isBusy());
        if (null != entity.getCreatedAt())
            resultSet.updateTimestamp("UPDATED_AT", Timestamp.valueOf(LocalDateTime.now()));
    }
}
