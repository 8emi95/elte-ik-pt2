/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Random;
import progtech2bead.entities.Movie;
import progtech2bead.persistence.Database;

/**
 *
 * @author doma
 */
public class MovieService{

    private Connection connection;

    public MovieService(Connection connection) {
        this.connection = connection;
    }
    
    public MovieService() {
        connection = Database.connect();
    }

    public List<Movie> findall() {
      List<Movie> results = null;
      ResultSet resultSet = null;
     
      try {
         PreparedStatement query = connection.prepareStatement("SELECT * FROM movies");
         resultSet = query.executeQuery(); 
         results = new ArrayList<Movie>();
         
         while (resultSet.next()){
            results.add(new Movie(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getBoolean("original"),
            resultSet.getInt("release_year"),
            resultSet.getString("actors"),
            resultSet.getString("directors"),
            resultSet.getBoolean("available")));
         } 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      }  
      return results;
    }
    
    public List<Movie> selectByTitle(String title){
        return multiselectByTitleAndYear(title, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public List<Movie> selectByDateRange(int begin, int end){
        return multiselectByTitleAndYear(null, begin, end);
    }
    
    public List<Movie> multiselectByTitleAndYear(String title, int begin, int end){
        List<Movie> results = null;
        ResultSet resultSet = null;
     
      try {
         
          PreparedStatement query = connection.prepareStatement("SELECT * FROM movies WHERE title = ? AND release_year BETWEEN ? AND ?");
          query.setString(1, title);
          query.setInt(2, begin);
          query.setInt(3, end);
          
         resultSet = query.executeQuery(); 
         results = new ArrayList<Movie>();
         
         while (resultSet.next()){
            results.add(new Movie(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getBoolean("original"),
            resultSet.getInt("release_year"),
            resultSet.getString("actors"),
            resultSet.getString("directors"),
            resultSet.getBoolean("available")));
         } 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
      
      return results;
     }

   
    public Movie findOne(int id) {
        Movie result = null;
        ResultSet resultSet = null;
     
      try {
          PreparedStatement query = connection.prepareStatement("SELECT * FROM movies WHERE id = ?");
          query.setInt(1, id);
  
         resultSet = query.executeQuery(); 
         if(resultSet.next()){
            result = new Movie(
            id,
          resultSet.getString("title"),
          resultSet.getBoolean("original"),
          resultSet.getInt("release_year"),
          resultSet.getString("actors"),
           resultSet.getString("directors"),
           resultSet.getBoolean("available"));  
         }
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
      
      return result;
    }

    public void create(Movie movie) {
        int randomID = new Random().nextInt(998) +1;
        int count =0;
      try {
        PreparedStatement query = connection.prepareStatement("INSERT INTO movies (id, title,original,release_year,actors,directors,available) VALUES ("+randomID+",?,?,?,?,?,true)");
        query.setString(1, movie.getTitle());
        query.setBoolean(2, movie.isOriginal());
        query.setInt(3, movie.getReleaseYear());
        query.setString(4, movie.getActors());
        query.setString(5, movie.getDirectors());
         query.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException sqlException){
            create(movie, count);
        }catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }
    
    public void create(Movie movie, int count) {
        int randomID = new Random().nextInt(998) +1;
      try {
        if(count > 4 ){
            throw new SQLException("Key duplication");
        }
        PreparedStatement query = connection.prepareStatement("INSERT INTO movies (id, title,original,release_year,actors,directors,available) VALUES ("+randomID+",?,?,?,?,?,true)");
        query.setString(1, movie.getTitle());
        query.setBoolean(2, movie.isOriginal());
        query.setInt(3, movie.getReleaseYear());
        query.setString(4, movie.getActors());
        query.setString(5, movie.getDirectors());
         query.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException sqlException){
            count++;
            create(movie, count);
        }catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }

    public void delete(int id) {
      try {
        PreparedStatement query = connection.prepareStatement("DELETE FROM movies WHERE id = "+id);       
        query.executeUpdate(); 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }

    protected void setAvaiblity(int movieID, boolean avaiblity) {
        try {
          PreparedStatement query = connection.prepareStatement("UPDATE movies SET available ="+avaiblity+" WHERE id = ?");
          query.setInt(1, movieID);
         query.executeUpdate(); 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
        
    }

    protected List<Movie> getPiratedMovies() {
    List<Movie> results = null;
      ResultSet resultSet = null;
     
      try {
         PreparedStatement query = connection.prepareStatement("SELECT * FROM movies WHERE original = false");
         resultSet = query.executeQuery(); 
         results = new ArrayList<Movie>();
         
         while (resultSet.next()){
            results.add(new Movie(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getBoolean("original"),
            resultSet.getInt("release_year"),
            resultSet.getString("actors"),
            resultSet.getString("directors"),
            resultSet.getBoolean("available")));
         } 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      }  
      return results;
    }
    
    protected void deletePiratedMovies(){
        for(Movie movie : getPiratedMovies()){
            delete(movie);
        }
    }

    private void delete(Movie movie) {
         try {
          PreparedStatement query = connection.prepareStatement("DELETE FROM rentals WHERE id = ?");
          
          query.setInt(1, movie.getId());
          query.executeUpdate();
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }
}
