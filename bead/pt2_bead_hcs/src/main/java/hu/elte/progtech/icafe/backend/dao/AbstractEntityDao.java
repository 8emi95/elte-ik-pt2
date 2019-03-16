/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.dao;

import hu.elte.progtech.icafe.api.entity.Entity;
import hu.elte.progtech.icafe.backend.db.DbHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityDao<E extends Entity> implements Dao<E> {

    private final String fullSelectSqlString;
    private final String selectCountSqlString;
    private final String tableName;
    private final String selectByIdSqlString;


    AbstractEntityDao(String tableName) {
        fullSelectSqlString = "SELECT * FROM " + tableName;
        selectCountSqlString = "SELECT COUNT(*) AS CNT FROM " + tableName;
        this.tableName = tableName;
        selectByIdSqlString = fullSelectSqlString + " WHERE ID = ";
    }

    protected interface RunnableOnResultSet {
        void run(ResultSet resultSet) throws SQLException;
    }

    private void doOnResultSet(String sql, int resultSetType, int resultSetConcurrency, RunnableOnResultSet todo) throws SQLException {
        Connection connection = DbHelper.getInstance().getConnection();
        try (Statement statement = connection.createStatement(resultSetType, resultSetConcurrency);
             ResultSet rs = statement.executeQuery(sql)) {
            todo.run(rs);
        }
    }

    @Override
    public int getEntityCount() throws SQLException {
        final IntegerHolder count = new IntegerHolder();
        doOnResultSet(selectCountSqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            rs.next();
            count.setValue(rs.getInt("CNT"));
        });
        return count.getValue();
    }

    @Override
    public List<E> getEntities() throws SQLException {
        final List<E> entities = new ArrayList<>();
        getEntities(entities, fullSelectSqlString);
        return entities;
    }

    @Override
    public E getEntityById(long id) throws SQLException {
        final E entity = newEntity();
        doOnResultSet(selectByIdSqlString + id, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            rs.next();
            setEntityAttributes(entity, rs);
        });
        return entity;
    }

    @Override
    public List<E> getEntitiesByForeignKey(String column, long id) throws SQLException {
        final List<E> entities = new ArrayList<>();
        String sql = fullSelectSqlString + String.format(" WHERE %s = %d", column, id);
        getEntities(entities, sql);
        return entities;
    }

    private void getEntities(List<E> entities, String sql) throws SQLException {
        doOnResultSet(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            while (rs.next()) {
                E entity = newEntity();
                setEntityAttributes(entity, rs);
                entities.add(entity);
            }
        });
    }

    @Override
    public E getEntityByRowIndex(final int rowIndex) throws SQLException {
        final E entity = newEntity();
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, (ResultSet rs) -> {
            rs.absolute(rowIndex + 1);
            setEntityAttributes(entity, rs);
        });
        return entity;
    }

    @Override
    public long addEntity(final E entity) throws SQLException {
        long id = DbHelper.getInstance().obtainNewId(tableName + "_ID_SEQUENCE");
        doOnResultSet(fullSelectSqlString, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, (ResultSet rs) -> {
            rs.moveToInsertRow();
            rs.updateLong("ID", id);
            getEntityAttributes(rs, entity);
            rs.insertRow();
        });
        return id;
    }

    @Override
    public void deleteEntity(final long id) throws SQLException {
        doOnResultSet(selectByIdSqlString + id, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, (ResultSet rs) -> {
            rs.next();
            rs.deleteRow();
        });
    }

    @Override
    public void updateEntity(final E entity, final long id) throws SQLException {
        doOnResultSet(selectByIdSqlString + id, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, (ResultSet rs) -> {
            rs.next();
            getEntityAttributes(rs, entity);
            rs.updateRow();
        });
    }

    protected abstract E newEntity();

    protected abstract void setEntityAttributes(E entity, ResultSet resultSet) throws SQLException;

    protected abstract void getEntityAttributes(ResultSet resultSet, E entity) throws SQLException;

    private class IntegerHolder {
        private int value;

        int getValue() {
            return value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }
}
