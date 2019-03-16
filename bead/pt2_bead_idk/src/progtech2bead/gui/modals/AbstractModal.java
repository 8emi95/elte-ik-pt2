/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.gui.modals;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import progtech2bead.controllers.MovieController;
import progtech2bead.controllers.RentalController;

/**
 *
 * @author doma
 */
public abstract class AbstractModal extends JFrame{
    final JPanel info;
    final MovieController movieController = new MovieController();
    final RentalController rentalController = new RentalController();
    
    public AbstractModal(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GridLayout layout = new GridLayout(7, 0);
        layout.setVgap(10);
        this.info = new JPanel(layout);
        info.setPreferredSize(new Dimension(300, 300));
    }
    
    abstract String getErrors();
    
    abstract boolean hasErrors();
    
}
