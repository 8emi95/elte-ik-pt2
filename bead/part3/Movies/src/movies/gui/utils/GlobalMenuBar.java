/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.gui.utils;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import movies.database.PanicService;
import movies.gui.NewMovie;
import movies.gui.tables.MovieTableFrame;
import movies.gui.tables.RentalTableFrame;

/**
 *
 * @author Emese
 */
public class GlobalMenuBar extends JMenuBar {
    public static JMenuBar createMenu(JFrame actualFrame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu movieMenu = new JMenu("Movies");
        movieMenu.add(new AbstractAction("New Movie") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewMovie();
            }
        });
        movieMenu.add(new AbstractAction("Movie table") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(actualFrame instanceof MovieTableFrame)) {
                    new MovieTableFrame();
                    actualFrame.dispose();
                }
            }
        });

        JMenu rentalMenu = new JMenu("Rentals");
        rentalMenu.add(new AbstractAction("Rental table") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(actualFrame instanceof RentalTableFrame)) {
                    new RentalTableFrame();
                    actualFrame.dispose();
                }
            }
        });

        JMenu help = new JMenu("Help");
        help.add(new AbstractAction("Panic") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanicService().panic();
            }
        });
        help.add(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(movieMenu);
        menuBar.add(rentalMenu);
        menuBar.add(help);

        return menuBar;
    }
}
