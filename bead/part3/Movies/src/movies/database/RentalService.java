/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import movies.entities.Movie;
import movies.entities.Rental;

/**
 *
 * @author Emese
 */
public class RentalService {
    private Connection connection;

    public RentalService() {
        connection = Database.connect();
    }

    public void addRental(Rental rental) {
        int randomID = new Random().nextInt(998) + 1;
        int count = 0;
        try {
            PreparedStatement query = connection.prepareStatement(
                "INSERT INTO rental (id, movie_ID, name, rent_date, return_date) VALUES (" +
                randomID +
                ", ?, ?, ?, ?)");
            query.setInt(1, rental.getMovieID());
            query.setString(2, rental.getName());
            query.setDate(3, rental.getRentDate());
            query.setNull(4, java.sql.Types.DATE);
            query.executeUpdate();

            new MovieService().setAvaiblity(rental.getMovieID(), false);
        } catch (SQLIntegrityConstraintViolationException sqlException) {
            create(rental, count);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void create(Rental rental, int count) {
        int randomID = new Random().nextInt(998) + 1;

        try {
            if (count > 4) {
                throw new SQLException("Key duplication");
            }
            PreparedStatement query = connection.prepareStatement(
                "INSERT INTO rental (id, movie_ID, name, rent_date, return_date) VALUES (" +
                randomID +
                ", ?, ?, ?, ?)");
            query.setInt(1, rental.getMovieID());
            query.setString(2, rental.getName());
            query.setDate(3, rental.getRentDate());
            query.setNull(4, java.sql.Types.DATE);
            // query.setDate(4, "NULL");
            query.executeUpdate();

            new MovieService().setAvaiblity(rental.getMovieID(), false);
        } catch (SQLIntegrityConstraintViolationException sqlException) {
            count++;
            create(rental, count);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteAllRentals(List<Rental> rentals) {
        try {
            PreparedStatement query = connection.prepareStatement("DELETE FROM rental");
            query.executeUpdate();
            for (int i = 0; i < rentals.size(); ++i) {
                new MovieService().setAvaiblity(rentals.get(i).getMovieID(), true);
            }
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteRentalByID(Rental rental) {
        try {
            PreparedStatement query = connection.prepareStatement("DELETE FROM rental WHERE id = ?");
            query.setInt(1, rental.getID());
            query.executeUpdate();
            new MovieService().setAvaiblity(rental.getMovieID(), true);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    protected void deleteRentalsByMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            deleteRentalByID(findRentalByMovieID(movie.getID()));
        }
    }

    private Rental findRentalByMovieID(int movieID) {
        Rental result = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM rental WHERE movie_id = ?");
            query.setInt(1, movieID);
            resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = new Rental(resultSet.getInt("movie_id"),
                                    resultSet.getString("name"),
                                    resultSet.getDate("rent_date"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public List<Rental> listRentals() {
        List<Rental> results = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM rental");
            resultSet = query.executeQuery();
            results = new ArrayList<Rental>();

            while (resultSet.next()) {
                results.add(new Rental( resultSet.getInt("id"),
                                        resultSet.getInt("movie_id"),
                                        resultSet.getString("name"),
                                        resultSet.getDate("rent_date"),
                                        resultSet.getDate("return_date")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }
}
