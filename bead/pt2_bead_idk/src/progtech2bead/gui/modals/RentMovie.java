/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.gui.modals;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import progtech2bead.entities.Rental;

/**
 *
 * @author doma
 */
public class RentMovie extends AbstractModal{
    private int movieID;
    
    private final JTextField FOR_WHO_FIELD = new JTextField();
    private final JTextField HOW_LONG_FIELD = new JTextField("yyyy-MM-dd");

    public RentMovie(int movieID) {
        setTitle("Rent movie");
        this.movieID = movieID;
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GridLayout layout = new GridLayout(7, 0);
        layout.setVgap(10);
        JPanel  info = new JPanel(layout);
        info.setPreferredSize(new Dimension(300, 300));
        
        JButton create = new JButton("Rent");
        create.addActionListener((ActionEvent evt) -> {
            String errors = getErrors();
            if(hasErrors()){
               showMessageDialog(null, errors);
            }else{
                registerNewRental();
                this.dispose();
            }
        });
        info.add(new JLabel("For who: "));
        info.add(FOR_WHO_FIELD);
        info.add(new JLabel("Expiry date: "));
        info.add(HOW_LONG_FIELD);
        info.add(create);
        add(info);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    String getErrors() {
        StringBuilder errors = new StringBuilder();
        if(FOR_WHO_FIELD.getText().isEmpty()){
            errors.append("Missing name!\n");
        }
        if(HOW_LONG_FIELD.getText().isEmpty()){
            errors.append("Missing expiry date!\n");
        }
        if(!HOW_LONG_FIELD.getText().matches("\\d{4}-\\d{2}-\\d{2}")){
            errors.append("Missing expiry date!\n");
        }
        if(getFieldDate().compareTo(new java.util.Date()) < 0){
            errors.append("Release year must be in the future!\n");
        }
        return errors.toString(); 
    }

    boolean hasErrors() {
        return !getErrors().isEmpty();
    }

    private void registerNewRental() {
        rentalController.newRental(new Rental(FOR_WHO_FIELD.getText(), new Date(getFieldDate().getTime()), movieID));
    }
    private java.util.Date getFieldDate(){
        String rawDate = HOW_LONG_FIELD.getText();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format1.parse(rawDate);
            
        } catch (ParseException ex) {
            Logger.getLogger(RentMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.util.Date();
    }
    
    private String getMovieTitleByID(int movieID) {
        return movieController.getById(movieID).getTitle();
    }
    
}
