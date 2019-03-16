/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.controller;

import hu.elte.progtech.icafe.backend.configuration.AppConfiguration;
import hu.elte.progtech.icafe.backend.db.DbHelper;

import java.io.IOException;
import java.sql.SQLException;

public class ICafeApp implements AutoCloseable {
    public static void prepareForFirstUse() throws IOException {
        AppConfiguration.setDefaultConfiguration();
    }

    public void close() {
        DbHelper.closeAllConnections();
    }

    public void init() throws SQLException {
        DbHelper.getInstance().getConnection();
    }
}
