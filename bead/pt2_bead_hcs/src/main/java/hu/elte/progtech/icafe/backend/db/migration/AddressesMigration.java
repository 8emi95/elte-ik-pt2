/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import java.sql.SQLException;

class AddressesMigration {
    static void run() throws SQLException {
        Migration.getInstance().run("addresses", (Blueprint table) -> {
            table.increments("id");
            table.bigint("user_id", "NOT NULL", "REFERENCES users(id)", "ON DELETE CASCADE");
            table.varchar("country", 30, "NOT NULL");
            table.varchar("city", 30, "NOT NULL");
            table.varchar("zip", 20, "NOT NULL");
            table.varchar("street_name", 30, "NOT NULL");
            table.varchar("street_suffix", 20, "NOT NULL");
            table.varchar("house_number", 20, "NOT NULL");
            table.timestamp("created_at");
            table.timestamp("updated_at");
        });
        System.out.println("Table 'addresses' created successfully!");
    }
}
