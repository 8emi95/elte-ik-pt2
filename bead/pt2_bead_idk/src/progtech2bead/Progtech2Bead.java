/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead;

import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import progtech2bead.entities.Movie;
import progtech2bead.gui.tables.MovieTableFrame;
import progtech2bead.persistence.Database;
import progtech2bead.services.MovieService;
import progtech2bead.threads.MovieExpiryCheck;
    
/**
 *
 * @author doma
 */
public class Progtech2Bead {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new MovieTableFrame();
       new MovieExpiryCheck().execute();
    }
    
}
