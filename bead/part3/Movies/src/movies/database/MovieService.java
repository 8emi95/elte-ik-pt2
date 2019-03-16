/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import movies.entities.Movie;

/**
 *
 * @author Emese
 */
public class MovieService {
    private Connection connection;

    public MovieService(Connection connection) {
        this.connection = connection;
    }

    public MovieService() {
        connection = Database.connect();
    }

    public List<Movie> listMovies() {
        List<Movie> results = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM movie");
            resultSet = query.executeQuery();
            results = new ArrayList<Movie>();

            while (resultSet.next()) {
                results.add(new Movie(  resultSet.getInt("id"),
                                        resultSet.getString("title"),
                                        resultSet.getString("directors"),
                                        resultSet.getString("actors"),
                                        resultSet.getInt("release_year"),
                                        resultSet.getInt("length_in_mins"),
                                        resultSet.getString("cover"),
                                        resultSet.getBoolean("original"),
                                        resultSet.getInt("rental_number"),
                                        resultSet.getBoolean("available")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }

    public Movie findMovieByID(int id) {
        Movie result = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM movie WHERE id = ?");
            query.setInt(1, id);

            resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = new Movie( id,
                                    resultSet.getString("title"),
                                    resultSet.getString("directors"),
                                    resultSet.getString("actors"),
                                    resultSet.getInt("release_year"),
                                    resultSet.getInt("length_in_mins"),
                                    resultSet.getString("cover"),
                                    resultSet.getBoolean("original"),
                                    resultSet.getInt("rental_number"),
                                    resultSet.getBoolean("available"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }
    
    

    public Movie findMovieByTitle(String title) { // for test
        Movie result = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM movie WHERE title = ?");
            query.setString(1, title);

            resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = new Movie( resultSet.getInt("id"),
                                    title,
                                    resultSet.getString("directors"),
                                    resultSet.getString("actors"),
                                    resultSet.getInt("release_year"),
                                    resultSet.getInt("length_in_mins"),
                                    resultSet.getString("cover"),
                                    resultSet.getBoolean("original"),
                                    resultSet.getInt("rental_number"),
                                    resultSet.getBoolean("available"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public void addMovie(Movie movie) {
        int randomID = new Random().nextInt(998) + 1;
        int count = 0;
        try {
            PreparedStatement query = connection.prepareStatement(
                "INSERT INTO movie (id, title, directors, actors, release_year, length_in_mins, cover, original, rental_number, available) VALUES (" +
                randomID +
                ", ?, ?, ?, ?, ?, ?, ?, ?, true)");
            query.setString(1, movie.getTitle());
            query.setString(2, movie.getDirectors());
            query.setString(3, movie.getActors());
            query.setInt(4, movie.getReleaseYear());
            query.setInt(5, movie.getLengthInMins());
            query.setString(6, movie.getCover());
            query.setBoolean(7, movie.isOriginal());
            query.setInt(8, movie.getRentalNumber());
            query.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException sqlException) {
            create(movie, count);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void create(Movie movie, int count) {
        int randomID = new Random().nextInt(998) + 1;
        try {
            if (count > 4) {
                throw new SQLException("Key duplication");
            }
            PreparedStatement query = connection.prepareStatement(
                "INSERT INTO movie (id, title, directors, actors, release_year, length_in_mins, cover, original, rental_number, available) VALUES (" +
                randomID +
                ", ?, ?, ?, ?, ?, ?, ?, ?, true)");
            query.setString(1, movie.getTitle());
            query.setString(2, movie.getDirectors());
            query.setString(3, movie.getActors());
            query.setInt(4, movie.getReleaseYear());
            query.setInt(5, movie.getLengthInMins());
            query.setString(6, movie.getCover());
            query.setBoolean(7, movie.isOriginal());
            query.setInt(8, movie.getRentalNumber());
            query.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException sqlException) {
            count++;
            create(movie, count);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void deleteMovieByID(int id) {
        try {
            PreparedStatement query = connection.prepareStatement("DELETE FROM movie WHERE id = " + id);
            query.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public void deleteAllMovies() {
        try {
            PreparedStatement query = connection.prepareStatement("DELETE FROM movie");
            query.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    protected void setAvaiblity(int movieID, boolean avaiblity) {
        try {
            PreparedStatement query = connection.prepareStatement("UPDATE movie SET available =" + avaiblity + " WHERE id = ?");
            query.setInt(1, movieID);
            query.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    protected List<Movie> getPiratedMovies() {
        List<Movie> results = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM movie WHERE original = false");
            resultSet = query.executeQuery();
            results = new ArrayList<Movie>();

            while (resultSet.next()) {
                results.add(new Movie(  resultSet.getInt("id"),
                                        resultSet.getString("title"),
                                        resultSet.getString("directors"),
                                        resultSet.getString("actors"),
                                        resultSet.getInt("release_year"),
                                        resultSet.getInt("length_in_mins"),
                                        resultSet.getString("cover"),
                                        resultSet.getBoolean("original"),
                                        resultSet.getInt("rental_number"),
                                        resultSet.getBoolean("available")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return results;
    }

    protected void deletePiratedMovies() {
        for (Movie movie : getPiratedMovies()) {
            delete(movie);
        }
    }

    private void delete(Movie movie) {
        try {
            PreparedStatement query = connection.prepareStatement("DELETE FROM rental WHERE id = ?"); // movie?
            query.setInt(1, movie.getID());
            query.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
