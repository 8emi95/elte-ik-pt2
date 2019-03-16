/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.gui;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import movies.entities.Movie;

/**
 *
 * @author Emese
 */
public class NewMovie extends AbstractGUI {
    private final JTextField TITLE_FIELD = new JTextField();
    private final JTextField DIRECTORS_FIELD = new JTextField();
    private final JTextField ACTORS_FIELD = new JTextField();
    private final JTextField RELEASE_YEAR_FIELD = new JTextField();
    private final JTextField LENGTH_IN_MINS_FIELD = new JTextField();
    private final JTextField COVER_FIELD = new JTextField();
    private final JCheckBox ORIGIN_CHECK_BOX = new JCheckBox();

    private List<Movie> movies;

    @Override
    String getErrors() {
        StringBuilder errors = new StringBuilder();
        if (TITLE_FIELD.getText().isEmpty()) {
            errors.append("Missing title!\n");
        }
        if (RELEASE_YEAR_FIELD.getText().isEmpty()) {
            errors.append("Missing year!\n");
        }
        if (!RELEASE_YEAR_FIELD.getText().matches("[1-2][0-9]{3}")) {
            errors.append("Incorrect release year!\n");
        }
        if (!isReleaseYearPossible()) {
            errors.append("Release year must after 1887!\n");
        }
        return errors.toString();
    }

    @Override
    boolean hasErrors() {
        return !getErrors().isEmpty();
    }

    public NewMovie() {
        setTitle("New Movie");

        JButton newMovieButton = new JButton("Add Movie");
        newMovieButton.addActionListener((ActionEvent evt) -> {
            String errors = getErrors();
            if (hasErrors()) {
                showMessageDialog(null, errors);
            } else {
                registerNewMovie();
                this.dispose();
            }
        });

        info.add(new JLabel("Title: "));
        info.add(TITLE_FIELD);
        info.add(new JLabel("Directors (separate with comma):"));
        info.add(DIRECTORS_FIELD);
        info.add(new JLabel("Actors (separate with comma):"));
        info.add(ACTORS_FIELD);
        info.add(new JLabel("Release Year: "));
        info.add(RELEASE_YEAR_FIELD);
        info.add(new JLabel("Length (minutes): "));
        info.add(LENGTH_IN_MINS_FIELD);
        info.add(new JLabel("Cover (URL): "));
        info.add(COVER_FIELD);
        info.add(new JLabel("Original: "));
        info.add(ORIGIN_CHECK_BOX);

        info.add(newMovieButton);
        add(info);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerNewMovie() {
        movieService.addMovie(new Movie(  TITLE_FIELD.getText(),
                                        DIRECTORS_FIELD.getText(),
                                        ACTORS_FIELD.getText(),
                                        Integer.parseInt(RELEASE_YEAR_FIELD.getText()),
                                        Integer.parseInt(LENGTH_IN_MINS_FIELD.getText()),
                                        COVER_FIELD.getText(),
                                        ORIGIN_CHECK_BOX.isSelected()));
    }

    private boolean isReleaseYearPossible() {
        return Integer.parseInt(RELEASE_YEAR_FIELD.getText()) >= 1888;
    }
}
