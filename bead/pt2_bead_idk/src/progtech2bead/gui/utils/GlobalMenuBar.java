/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.gui.utils;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import progtech2bead.gui.modals.NewMovie;
import progtech2bead.gui.tables.MovieTableFrame;
import progtech2bead.gui.tables.RentalTableFrame;
import progtech2bead.services.PanicService;

/**
 *
 * @author doma
 */
public class GlobalMenuBar extends JMenuBar{

    public static JMenuBar createmenu(JFrame actualFrame) {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu system = new JMenu("System");
        system.add(new AbstractAction("Panic"){

            @Override
            public void actionPerformed(ActionEvent e) {
              new PanicService().panic();
            }
            
        });
        system.add(new AbstractAction("Exit"){

            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
            
        });
        
        JMenu movieMenu = new JMenu("Movie management");
        movieMenu.add(new AbstractAction("New Movie"){

            @Override
            public void actionPerformed(ActionEvent e) {
                new NewMovie();
            }
            
        });
        movieMenu.add(new AbstractAction("Movie table"){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(actualFrame instanceof MovieTableFrame)){
                    new MovieTableFrame();
                    actualFrame.dispose();
                }
            }
            
        });
        JMenu rentalMenu = new JMenu("Rentals");
        rentalMenu.add(new AbstractAction("Rantal table"){

            @Override
            public void actionPerformed(ActionEvent e) {
               if(!(actualFrame instanceof RentalTableFrame)){
                    new RentalTableFrame();
                    actualFrame.dispose();
                }
            }
            
        });
        
       
        menuBar.add(movieMenu);
        menuBar.add(rentalMenu);
        menuBar.add(system);
        
        return menuBar;
    }
    
}
