/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import java.sql.SQLException;

class SessionsMigration {
    static void run() throws SQLException {
        Migration.getInstance().run("sessions", (Blueprint table) -> {
            table.increments("id");
            table.bigint("user_id", "NOT NULL", "REFERENCES users(id)", "ON DELETE RESTRICT");
            table.bigint("computer_id", "NOT NULL", "REFERENCES computers(id)", "ON DELETE RESTRICT");
            table.timestamp("start_time");
            table.datetime("end_time");
        });
        System.out.println("Table 'sessions' created successfully!");
    }
}
