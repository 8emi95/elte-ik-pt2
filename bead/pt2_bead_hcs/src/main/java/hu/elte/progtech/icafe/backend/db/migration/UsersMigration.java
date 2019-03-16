/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import java.sql.SQLException;

class UsersMigration {
    static void run() throws SQLException {
        Migration.getInstance().run("users", (Blueprint table) -> {
            table.increments("id");
            table.varchar("name", 50, "NOT NULL");
            table.varchar("id_number", 20, "NOT NULL");
            table.varchar("username", 20, "NOT NULL", "UNIQUE");
            table.varchar("password", 50);
            table.integer("points", "NOT NULL DEFAULT 0");
            table.bool("present", "DEFAULT FALSE");
            table.timestamp("created_at");
            table.timestamp("updated_at");
        });
        System.out.println("Table 'users' created successfully!");
    }
}
