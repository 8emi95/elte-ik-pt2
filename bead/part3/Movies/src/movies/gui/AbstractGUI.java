/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import movies.database.MovieService;
import movies.database.RentalService;

/**
 *
 * @author Emese
 */
public abstract class AbstractGUI extends JFrame {
    final JPanel info;
    final RentalService rentalService = new RentalService();
    final MovieService movieService = new MovieService();


    public AbstractGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GridLayout layout = new GridLayout(10, 0);
        layout.setVgap(10);
        this.info = new JPanel(layout);
        info.setPreferredSize(new Dimension(400, 400));
    }

    abstract String getErrors();

    abstract boolean hasErrors();
}
