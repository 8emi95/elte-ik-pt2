/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db;

import hu.elte.progtech.icafe.backend.configuration.AppConfiguration;

import java.sql.*;
import java.util.Properties;


public class DbHelper {
    private static DbConnection connection;

    private DbHelper() {
        Properties config = AppConfiguration.getConfiguration();
        connection = new DbConnection(
                config.getProperty("dbUrl"),
                config.getProperty("dbUser"),
                config.getProperty("dbPwd")
        );
    }

    /**
     * Metódus, mely segítségével elkérhetjük a soron következő értéket a megadott szekvenciából.
     *
     * @param sequenceName a szekvencia neve
     * @return a soronkövetkező azonosító
     * @throws SQLException adatbázis eléréssel kapcsolatos kivételek, zárt kapcsolaton próbálunk query-t futtatni,
     *                      vagy a Statement nem ad vissza ResultSet objektumot.
     */
    public long obtainNewId(String sequenceName) throws SQLException {
        long id;
        final Connection connection = getConnection();
        final PreparedStatement stmt = connection.prepareStatement(getNextIdQuery(sequenceName));
        final ResultSet rs = stmt.executeQuery();
        rs.next();
        id = rs.getLong(1);
//        System.out.println("obtained id from " + sequenceName + ": " + id);
        return id;
    }


    /**
     * Metódus, melynek segítségével elérhető az osztály egyetlen pédánya
     *
     * @return az osztály egyetlen példánya
     */
    public static DbHelper getInstance() {
        return DBConnectionInstanceHolder.INSTANCE;
    }

    /**
     * Metódus, mely elkészíti a queryt, mely segítségével elkérhetjük az azonosító kiosztásához készített szekvencia
     * következő értékét.
     *
     * @param sequenceName a szekvencia neve
     * @return az elkészített query
     */
    private String getNextIdQuery(String sequenceName) {
        return "SELECT ID FROM ( VALUES NEXT VALUE FOR " + sequenceName + " ) S(ID)";
    }

    private static class DBConnectionInstanceHolder {
        private static final DbHelper INSTANCE = new DbHelper();
    }

    /**
     * Metódus mely segítségével elkérhetjük adatbázis kapcsolathoz tartozó session.
     * Ezen kapcsolaton keresztül tudunk lekérdezéseket futtatni.
     *
     * @return adatbázis kapcsolatot tartalmazó session.
     * @throws SQLException adatbázis eléréssel kapcsolatos kivételek, vagy a megadott url null / hibás.
     */
    public Connection getConnection() throws SQLException {
        return connection.getConnection();
    }

    /**
     * Metódus az összes nyitott adatbázis kapcsolat bezárására
     */
    public static void closeAllConnections() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se) {
            if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
                // we got the expected exception
                System.out.println("Derby shut down normally");
                // Note that for single database shutdown, the expected
                // SQL state is "08006", and the error code is 45000.
            } else {
                // if the error code or SQLState is different, we have
                // an unexpected exception (shutdown failed)
                System.err.println("Derby did not shut down normally");
                printSQLException(se);
            }
        }
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }
}
