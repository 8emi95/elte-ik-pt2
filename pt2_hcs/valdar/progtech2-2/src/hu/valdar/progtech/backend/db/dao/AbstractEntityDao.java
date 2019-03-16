package hu.valdar.progtech.backend.db.dao;

import hu.valdar.progtech.api.entity.AbstractEntity;
import hu.valdar.progtech.backend.db.connection.DBConnectionSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityDao<E extends AbstractEntity> implements IEntityDao<E> {

    private final String fullSelectSqlString;
    private final String selectCountSqlString;
    private final String selectByIdSqlString;

    AbstractEntityDao(String tableName) {
        fullSelectSqlString = "SELECT * FROM " + tableName;
        selectCountSqlString = "SELECT COUNT(*) AS CNT FROM " + tableName;
        selectByIdSqlString = fullSelectSqlString + " WHERE ID = ";
    }


    private void doOnResultSet(String sql, int resultSetType, int resultSetConcurrency, RunnableOnResultSet todo) throws SQLException {
        try (Connection connection = DBConnectionSource.getInstance().getConnection();
             Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
             ResultSet rs = statement.executeQuery(sql)) {
            todo.run(rs);
        }
    }

    protected interface RunnableOnResultSet {
        void run(ResultSet resultSet) throws SQLException;
    }

    @Override
    public int getEntityCount() throws SQLException {
        final IntegerHolder count = new IntegerHolder();
        doOnResultSet(selectCountSqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            rs.next();
            count.setIntValue(rs.getInt("CNT"));
        });
        return count.getIntValue();
    }

    @Override
    public List<E> getEntities() throws SQLException {
        final List<E> entities = new ArrayList<>();
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            while (rs.next()) {
                E entity = newEntity();
                entity.setId(rs.getLong("ID"));
                setEntityAttributes(entity, rs);
                entities.add(entity);
            }
        });
        return entities;
    }

    @Override
    public E getEntityById(long id) throws SQLException {
        final E entity = newEntity();
        doOnResultSet(selectByIdSqlString + id, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            rs.next();
            entity.setId(rs.getLong("ID"));
            setEntityAttributes(entity, rs);
        });
        return entity;
    }

    @Override
    public E getEntityByRowIndex(final int rowIndex) throws SQLException {
        final E entity = newEntity();
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            rs.absolute(rowIndex + 1);
            entity.setId(rs.getLong("ID"));
            setEntityAttributes(entity, rs);
        });
        return entity;
    }

    @Override
    public void addEntity(final E entity) throws SQLException {
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, (ResultSet rs) -> {
            rs.moveToInsertRow();
            rs.updateLong("ID", DBConnectionSource.getInstance().obtainNewId());
            getEntityAttributes(rs, entity);
            rs.insertRow();
        });
    }

    @Override
    public void deleteEntity(final int index) throws SQLException {
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, (ResultSet rs) -> {
            rs.absolute(index + 1);
            rs.deleteRow();
        });
    }

    @Override
    public void updateEntity(final E entity, final int index) throws SQLException {
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, (ResultSet rs) -> {
            rs.absolute(index + 1);
            rs.updateLong("ID", entity.getId().intValue());
            getEntityAttributes(rs, entity);
            rs.updateRow();
        });
    }

    protected abstract E newEntity();

    protected abstract void setEntityAttributes(E entity, ResultSet resultSet) throws SQLException;

    protected abstract void getEntityAttributes(ResultSet resultSet, E entity) throws SQLException;

    private class IntegerHolder{
        private int intValue;

        int getIntValue() {
            return intValue;
        }

        void setIntValue(int intValue) {
            this.intValue = intValue;
        }
    }

}
