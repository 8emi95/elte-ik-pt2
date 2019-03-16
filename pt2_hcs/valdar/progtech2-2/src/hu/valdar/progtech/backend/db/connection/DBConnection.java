package hu.valdar.progtech.backend.db.connection;

import java.sql.*;
import java.util.Properties;

class DBConnection{

    /**
     * Az adatbázis elérése. (Minta Derby esetén: jdbc:derby://localhost:1527/BandViewer )
     */
    private final String url;
    /**
     * A felhasználó neve.
     */
    private final String user;
    /**
     * A felhasználóhoz tartozó jelszó.
     * (Megjegyzés: Enterprise alkalmazásban jelszót nem tárolunk Stringként biztonsági problémák miatt!)
     */
    private final String password;

    /**
     * Query, mely segítségével elkérhetjük az azonosító kiosztásához készített táblából a következő értékét.
     * (Megkerülő megoldás, a legtöbb helyen a valóságban szekvenciákat használnak a sorszámozáshoz)
     */
    private static final String SELECT_NEXT_ID_QUERY = "SELECT VALUUE FROM ID_SEQ_TABLE";

    DBConnection(Properties properties) {
        if (properties == null) {
            throw new IllegalStateException("A konfiguráció nem lehet üres!");
        }

        url = properties.getProperty("dbUrl");
        user = properties.getProperty("dbUser");
        password = properties.getProperty("dbPassword");
    }

    /**
     * Metódus, mely segítségével elkérhetjük a soron következő értéket az ID_SEQUENCE táblából.
     * @return a soronkövetkező azonosító
     * @throws SQLException adatbázis eléréssel kapcsolatos kivételek, zárt kapcsolaton próbálunk query-t futtatni,
     *  vagy a Statement nem ad vissza ResultSet objektumot.
     */
    long obtainNewId() throws SQLException {
        long id;
        try (
                final Connection connection = getConnection();
                final PreparedStatement stmt = connection.prepareStatement(SELECT_NEXT_ID_QUERY, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                final ResultSet rs = stmt.executeQuery()) {
            rs.next();
            id = rs.getLong("VALUUE") + 1L;
            rs.updateLong("VALUUE", id);
            rs.updateRow();
        }
        return id;
    }

    /**
     * Metódus mely segítségével elkérhetjük adatbázis kapcsolathoz tartozó session.
     * Ezen kapcsolaton keresztül tudunk lekérdezéseket futtatni.
     * @return adatbázis kapcsolatot tartalmazó session.
     * @throws SQLException adatbázis eléréssel kapcsolatos kivételek, vagy a megadott url null / hibás.
     */
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
