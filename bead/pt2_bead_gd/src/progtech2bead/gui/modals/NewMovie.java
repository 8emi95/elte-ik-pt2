/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.gui.modals;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import progtech2bead.controllers.MovieController;
import progtech2bead.entities.Movie;

/**
 *
 * @author doma
 */
public class NewMovie extends AbstractModal{
    private final JTextField TITLE_FIELD = new JTextField();
    private final JTextField ACTORS_FIELD = new JTextField();
    private final JTextField DIRECTORS_FIELD = new JTextField();
    private final JTextField RELEASE_YEAR_FIELD = new JTextField();
    private final JCheckBox ORIGIN_CHECK_BOX  = new JCheckBox();
   
    private List<Movie> movies;

    @Override
    String getErrors(){
    
        StringBuilder errors = new StringBuilder();
        if(TITLE_FIELD.getText().isEmpty()){
            errors.append("Missing title!\n");
        }
        if(RELEASE_YEAR_FIELD.getText().isEmpty()){
            errors.append("Missing year!\n");
        }
        if(!RELEASE_YEAR_FIELD.getText().matches("^[0-9]{4}$")){
            errors.append("Release year must be numeric!\n");
        }
        if(!isReleaseYearInThePastOrMoment()){
            errors.append("Release year must be in the past or the actual year!\n");
        }
        return errors.toString();
    }
    
    @Override
    boolean hasErrors(){
        return !getErrors().isEmpty();
    }
    
    
    public NewMovie(){
        setTitle("New movie");
        
        JButton create = new JButton("Add movie");
        create.addActionListener((ActionEvent evt) -> {
            String errors = getErrors();
            if(hasErrors()){
               showMessageDialog(null, errors);
            }else{
                registerNewMovie();
                this.dispose();
            }
        });
        
        info.add(new JLabel("Title: "));
        info.add(TITLE_FIELD);
        info.add(new JLabel("Actors: (comma separated)"));
        info.add(ACTORS_FIELD);
        info.add(new JLabel("Directors: (comma separated)"));
        info.add(DIRECTORS_FIELD);
        info.add(new JLabel("Release year: "));
        info.add(RELEASE_YEAR_FIELD);
        info.add(new JLabel("Original: "));
        info.add(ORIGIN_CHECK_BOX);
        
        info.add(create);
        add(info);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }    

    private void registerNewMovie() {
        movieController.newMovie(new Movie(TITLE_FIELD.getText(), ORIGIN_CHECK_BOX.isSelected(),
                getReleaseYear(), ACTORS_FIELD.getText(), DIRECTORS_FIELD.getText()));
    }

    private int getReleaseYear() {
        return Integer.parseInt(RELEASE_YEAR_FIELD.getText());
    }

    private boolean isReleaseYearInThePastOrMoment() {
        return getReleaseYear() <= new Date().getYear() + 1900;
    }
}
