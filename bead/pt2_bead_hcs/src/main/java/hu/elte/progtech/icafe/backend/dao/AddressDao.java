/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AddressDao extends AbstractEntityDao<Address> {
    private static final String TABLE = "ADDRESSES";

    public AddressDao() {
        super(TABLE);
    }

    @Override
    protected Address newEntity() {
        return new Address();
    }

    @Override
    protected void setEntityAttributes(Address entity, ResultSet resultSet) throws SQLException {
        entity.setId(resultSet.getLong("ID"));
        entity.setUserId(resultSet.getLong("USER_ID"));
        entity.setCountry(resultSet.getString("COUNTRY"));
        entity.setCity(resultSet.getString("CITY"));
        entity.setZip(resultSet.getString("ZIP"));
        entity.setStreetName(resultSet.getString("STREET_NAME"));
        entity.setStreetSuffix(resultSet.getString("STREET_SUFFIX"));
        entity.setHouseNumber(resultSet.getString("HOUSE_NUMBER"));
        entity.setCreatedAt(resultSet.getTimestamp("CREATED_AT"));
        entity.setUpdatedAt(resultSet.getTimestamp("UPDATED_AT"));
    }

    @Override
    protected void getEntityAttributes(ResultSet resultSet, Address entity) throws SQLException {
        resultSet.updateLong("USER_ID", entity.getUserId());
        resultSet.updateString("COUNTRY", entity.getCountry());
        resultSet.updateString("CITY", entity.getCity());
        resultSet.updateString("ZIP", entity.getZip());
        resultSet.updateString("STREET_NAME", entity.getStreetName());
        resultSet.updateString("STREET_SUFFIX", entity.getStreetSuffix());
        resultSet.updateString("HOUSE_NUMBER", entity.getHouseNumber());
        if (null != entity.getCreatedAt())
            resultSet.updateTimestamp("UPDATED_AT", Timestamp.valueOf(LocalDateTime.now()));
    }
}
