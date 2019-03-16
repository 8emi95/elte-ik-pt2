/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class Blueprint {
    private Statement stmt;
    private StringBuilder query;
    private String tableName;

    Blueprint(Connection conn, String tableName) throws SQLException {
        stmt = conn.createStatement();
        query = new StringBuilder("CREATE TABLE " + tableName + " ( ");
        this.tableName = tableName;
    }

    void varchar(String column, int size, String... attribs) {
        addColumn("VARCHAR(" + size + ")", column, attribs);
    }

    void varchar(String column, String... attribs) {
        addColumn("VARCHAR(255)", column, attribs);
    }

    void increments(String column) {
        addColumn("BIGINT", column, "NOT NULL", "PRIMARY KEY");
    }

    void bigint(String column, String... attribs) {
        addColumn("BIGINT", column, attribs);
    }

    void integer(String column, String... attribs) {
        addColumn("INTEGER", column, attribs);
    }

    void bool(String column, String... attribs) {
        addColumn("BOOLEAN", column, attribs);
    }

    void timestamp(String column, String attribs) {
        addColumn("TIMESTAMP", column, attribs);
    }

    void timestamp(String column) {
        addColumn("TIMESTAMP", column, "DEFAULT CURRENT_TIMESTAMP");
    }

    void datetime(String column) {
        addColumn("TIMESTAMP", column);
    }

    void decimal(String column, int precision, int scale, String attribs) {
        String type = "DECIMAL(" + precision + ", " + scale + ")";
        addColumn(type, column, attribs);
    }

    void decimal(String column, int precision, String attribs) {
        String type = "DECIMAL(" + precision + ")";
        addColumn(type, column, attribs);
    }

    void decimal(String column, String attribs) {
        addColumn("DECIMAL", column, attribs);
    }

    private void addColumn(String type, String name, String... attribs) {
        query.append(name.toUpperCase()).append(" ").append(type);
        if (null != attribs)
            for (String a : attribs) {
                query.append(" ").append(a);
            }
        query.append(", ");
    }

    void create() throws SQLException {
        query.delete(query.length() - 2, query.length() - 1);
        query.append(")");
        String sequenceSql = " CREATE SEQUENCE " + tableName + "_ID_SEQUENCE AS BIGINT START WITH 1";
        stmt.execute(query.toString());
        stmt.execute(sequenceSql);
//        System.out.println(query.toString());
//        System.out.println(sequenceSql);
        stmt.close();
    }
}
