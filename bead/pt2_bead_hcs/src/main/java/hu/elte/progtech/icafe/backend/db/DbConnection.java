/**
 * Created by Csaba_Hete on 2017.03.26..
 */
package hu.elte.progtech.icafe.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DbConnection {
    private Connection connection;

    private final String url;
    private final String user;
    private final String pwd;

    DbConnection(String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    Connection getConnection() throws SQLException {
        if(null == connection || connection.isClosed()){
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.printf("Connected to database at %s%n", url);
        }
        return connection;
    }
}
