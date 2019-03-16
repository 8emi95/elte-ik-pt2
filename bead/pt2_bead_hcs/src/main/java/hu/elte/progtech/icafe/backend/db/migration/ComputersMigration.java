/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.migration;

import java.sql.SQLException;

class ComputersMigration {
    static void run() throws SQLException {
        Migration.getInstance().run("computers",(Blueprint table)->{
            table.increments("id");
            table.varchar("name",10, "NOT NULL","UNIQUE");
            table.varchar("cpu",50);
            table.varchar("motherboard",50);
            table.varchar("memory",50);
            table.varchar("vga",50);
            table.varchar("mass_storage",50);
            table.varchar("os",50);
            table.bool("busy", "DEFAULT FALSE");
            table.timestamp("created_at");
            table.timestamp("updated_at");
        });
        System.out.println("Table 'computers' created successfully!");
    }
}
