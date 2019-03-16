/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.db.seed;

import hu.elte.progtech.icafe.backend.db.DbHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Seeder {
    public static void seed() {
        try (Scanner scanner = new Scanner(new File("src/seed.sql"));
             Connection connection = DbHelper.getInstance().getConnection();
             Statement stmt = connection.createStatement()) {
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String sql = scanner.next().trim();
                if (sql.equals("") || sql.startsWith("/*")) {
                    continue;
                }
                System.out.print(sql.replaceAll("\\s+", " "));
                System.out.print(": ");
                if (!sql.toUpperCase().startsWith("SELECT")) {
                    try {
                        stmt.execute(sql);
                        System.out.println("OK");
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Az sql file nem talalhato! :" + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Hiba tortent az SQL kapcsolat soran: " + ex.getMessage());
        }
    }
}
