/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import java.sql.SQLException;

class PaymentsMigration {
    static void run() throws SQLException {
        Migration.getInstance().run("payments", (Blueprint table) -> {
            table.increments("id");
            table.bigint("user_id", "NOT NULL", "REFERENCES users(id)", "ON DELETE RESTRICT");
            table.bigint("session_id", "NOT NULL", "REFERENCES sessions(id)", "ON DELETE RESTRICT");
            table.bigint("quantity", "NOT NULL");
            table.varchar("unit", 10, "NOT NULL", "DEFAULT 'óra'");
            table.decimal("net_price", 7, 2, "NOT NULL");
            table.decimal("vat_rate", 4, 1, "NOT NULL");
            table.decimal("discount", 4, 1, "NOT NULL");
            table.decimal("gross_amount", 7, 2, "NOT NULL");
            table.varchar("currency", 3);
            table.bool("fulfilled", "DEFAULT FALSE");
            table.timestamp("created_at");
            table.timestamp("updated_at");
        });
        System.out.println("Table 'payments' created successfully!");
    }
}
