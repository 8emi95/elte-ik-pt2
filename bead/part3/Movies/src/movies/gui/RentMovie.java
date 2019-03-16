/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import movies.entities.Rental;

/**
 *
 * @author Emese
 */
public class RentMovie extends AbstractGUI {
    private int movieID;

    private final JTextField NAME_FIELD = new JTextField();
    private final JTextField RENT_DATE_FIELD = new JTextField("YYYY-MM-DD");

    public RentMovie(int movieID) {
        setTitle("Rent Movie");
        this.movieID = movieID;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        GridLayout layout = new GridLayout(7, 0);
        layout.setVgap(10);
        JPanel info = new JPanel(layout);
        info.setPreferredSize(new Dimension(300, 300));

        JButton rentButton = new JButton("Rent");
        rentButton.addActionListener((ActionEvent evt) -> {
            String errors = getErrors();
            if (hasErrors()) {
                showMessageDialog(null, errors);
            } else {
                registerNewRental();
                this.dispose();
            }
        });

        info.add(new JLabel("Name: "));
        info.add(NAME_FIELD);
        info.add(new JLabel("Rent Date: "));
        info.add(RENT_DATE_FIELD);
        info.add(rentButton);
        add(info);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    String getErrors() {
        StringBuilder errors = new StringBuilder();
        if (NAME_FIELD.getText().isEmpty()) {
            errors.append("Missing name!\n");
        }
        if (RENT_DATE_FIELD.getText().isEmpty()) {
            errors.append("Missing rent date!\n");
        }
        if (!RENT_DATE_FIELD.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            errors.append("Incorrect rent date!\n");
        }
        return errors.toString();
    }

    boolean hasErrors() {
        return !getErrors().isEmpty();
    }

    private void registerNewRental() {
        rentalService.addRental(new Rental(movieID, NAME_FIELD.getText(), new Date(getFieldDate().getTime())));
    }

    private java.util.Date getFieldDate() {
        String rawDate = RENT_DATE_FIELD.getText();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format1.parse(rawDate);

        } catch (ParseException ex) {
            Logger.getLogger(RentMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.util.Date();
    }

    private String getMovieTitleByID(int movieID) {
        return movieService.findMovieByID(movieID).getTitle();
    }
}
