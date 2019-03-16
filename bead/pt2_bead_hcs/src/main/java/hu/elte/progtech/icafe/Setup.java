package hu.elte.progtech.icafe;

import hu.elte.progtech.icafe.backend.controller.ICafeApp;
import hu.elte.progtech.icafe.backend.db.migration.Migrator;
import hu.elte.progtech.icafe.backend.db.seed.Seeder;

import java.io.IOException;

/**
 * Created by Csaba_Hete on 2017.03.18..
 */
public class Setup {
    public static void main(String[] args) {
        Migrator.migrate();
//        Seeder.seed();
        try {
            ICafeApp.prepareForFirstUse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
