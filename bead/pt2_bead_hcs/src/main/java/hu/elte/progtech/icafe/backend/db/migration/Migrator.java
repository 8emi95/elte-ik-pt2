/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import hu.elte.progtech.icafe.backend.db.DbHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class Migrator {

    public static void migrate() {
        try {
            Connection conn = DbHelper.getInstance().getConnection();
            conn.setAutoCommit(false);
            ComputersMigration.run();
            UsersMigration.run();
            AddressesMigration.run();
            SessionsMigration.run();
            PaymentsMigration.run();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Error during migrations");
        }
    }
}
