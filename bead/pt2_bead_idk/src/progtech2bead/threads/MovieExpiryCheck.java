/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import progtech2bead.services.RentalService;

/**
 *
 * @author doma
 */
public class MovieExpiryCheck extends SwingWorker<Void, Void>{

    @Override
    protected Void doInBackground() throws Exception {
        while(true){
            new RentalService().checkForExpiredRentals();
            Thread.sleep(3000);
        }
    }
}
