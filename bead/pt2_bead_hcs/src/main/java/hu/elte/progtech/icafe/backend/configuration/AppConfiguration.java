/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfiguration {

    private static final String CONFIG_FILE = "icafe.app.conf";

    private static final String DB_DEFAULT_URL = "jdbc:derby:icafeDB;create=true";
    private static final String DB_DEFAULT_USER = "root";
    private static final String DB_DEFAULT_PWD = "";

    private static final String DEFAULT_CURRENCY = "HUF";
    private static final String DEFAULT_VAT_RATE = "27";
    private static final String DEFAULT_DISCOUNT_PER_POINTS = "150";
    private static final String DEFAULT_MAX_DISCOUNT = "10";
    private static final String DEFAULT_NET_PRICE = "1200";

    private static Properties configuration = null;

    private AppConfiguration() {
    }

    public static Properties getConfiguration() {
        if (null == configuration) {
            configuration = new Properties();
            try {
                configuration.load(new FileInputStream(CONFIG_FILE));
            } catch (IOException ioe) {
                System.err.println("AppConfiguration file not found or corrupted!");
                System.err.println("Trying to rebuild with default settings...");
                try {
                    setDefaultConfiguration();
                } catch (IOException e) {
                    System.err.println("ERROR: Can't rebuild configuration file!");
                }
            }
        }
        return configuration;
    }

    public static void setDefaultConfiguration() throws IOException {
        if (null == configuration) {
            configuration = new Properties();
        }
        configuration.setProperty("currency", DEFAULT_CURRENCY);
        configuration.setProperty("vatRate", DEFAULT_VAT_RATE);
        configuration.setProperty("discountPerPoints", DEFAULT_DISCOUNT_PER_POINTS);
        configuration.setProperty("maxDiscount", DEFAULT_MAX_DISCOUNT);
        configuration.setProperty("netPrice", DEFAULT_NET_PRICE);

        configuration.setProperty("dbUrl", DB_DEFAULT_URL);
        configuration.setProperty("dbUser", DB_DEFAULT_USER);
        configuration.setProperty("dbPwd", DB_DEFAULT_PWD);

        storeConfiguration();
    }

    public static synchronized void storeConfiguration() throws IOException {
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            configuration.store(out, null);
            System.out.println("AppConfiguration file rebuilded!");
        } catch (IOException e) {
            System.err.println("Cannot rebuild configuration file!");
            throw e;
        }
    }
}
