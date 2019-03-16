/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import progtech2bead.entities.Movie;
import progtech2bead.entities.Rental;
import progtech2bead.persistence.Database;

/**
 *
 * @author doma
 */
public class RentalService {
    private Connection connection;
    
    public RentalService() {
        connection = Database.connect();
    }
    
    public void create(Rental rental){
    int randomID = new Random().nextInt(998) +1;
    int count = 0;
    try {
          PreparedStatement query = connection.prepareStatement("INSERT INTO rentals (id,name,expiry_date,movies_id) VALUES ("+randomID+",?,?,?)");
          query.setString(1, rental.getForWho());
          query.setDate(2, rental.getHowLong());
          query.setInt(3, rental.getMovideID());
          query.executeUpdate();
          
          new MovieService().setAvaiblity(rental.getMovideID(), false);
      } catch (SQLIntegrityConstraintViolationException sqlException){
            create(rental, count);
        }catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }
    
    private void create(Rental rental, int count){
    int randomID = new Random().nextInt(998) +1;
    
    try {
        if(count > 4 ){
            throw new SQLException("Key duplication");
        }
          PreparedStatement query = connection.prepareStatement("INSERT INTO rentals (id,name,expiry_date,movies_id) VALUES ("+randomID+",?,?,?)");
          query.setString(1, rental.getForWho());
          query.setDate(2, rental.getHowLong());
          query.setInt(3, rental.getMovideID());
          query.executeUpdate();
          
          new MovieService().setAvaiblity(rental.getMovideID(), false);
      } catch (SQLIntegrityConstraintViolationException sqlException){
            count++;
            create(rental, count);
        }catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }

    public void checkForExpiredRentals() {
        if(getExpiredRentals() != null && !getExpiredRentals().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(),getExpiryMesage(getExpiredRentals()));
            deleteSpecificRentals(getExpiredRentals());
        } 
    }
    
    private List<Rental> getExpiredRentals(){
          List<Rental> results = null;
        ResultSet resultSet = null;
     
      try {
         
          PreparedStatement query = connection.prepareStatement("SELECT * FROM rentals WHERE ? >= expiry_date");
          query.setDate(1, new Date(new java.util.Date().getTime()));
          
         resultSet = query.executeQuery(); 
         results = new ArrayList<Rental>();
         
         while (resultSet.next()){
            results.add(new Rental(
               resultSet.getString("name"),
               resultSet.getDate("expiry_date"),
               resultSet.getInt("movies_id")));
         } 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
      
      return results;
    }
    
    public String getExpiryMesage(List<Rental> expiredRentals){
        StringBuilder builder = new StringBuilder("Expired rentals: "+'\n');
        for (Rental rental : expiredRentals){
            builder.append(String.format("Name: %s, Movie: %s", rental.getForWho(), new MovieService(connection).findOne(rental.getMovideID()))+'\n');
        }
        return builder.toString();
    }
    
    public void delete(Rental rental){
         try {
          PreparedStatement query = connection.prepareStatement("DELETE FROM rentals WHERE id = ?");
          
          query.setInt(1, rental.getId());
          query.executeUpdate();
          new MovieService().setAvaiblity(rental.getMovideID(), true);
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
    }

    private void deleteSpecificRentals(List<Rental> rentals) {
        for (Rental rental : rentals){
            delete(rental);
        }
    }

    protected void deleteRentalsByMovies(List<Movie> movies){
        for(Movie movie : movies){
            delete(findByMovieId(movie.getId()));
        }
    }
    
    private Rental findByMovieId(int movieID){
         Rental result = null;
        ResultSet resultSet = null;
     
      try {
         
          PreparedStatement query = connection.prepareStatement("SELECT * FROM rentals WHERE movies_id = ?");
          query.setInt(1, movieID);
  
         resultSet = query.executeQuery(); 
         if (resultSet.next()) {
              result = new Rental(
               resultSet.getString("name"),
               resultSet.getDate("expiry_date"),
               resultSet.getInt("movies_id"));
         }

      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      } 
      
      return result;
    }

    public List<Rental> findall() {
    List<Rental> results = null;
      ResultSet resultSet = null;
     
      try {
         PreparedStatement query = connection.prepareStatement("SELECT * FROM rentals");
         resultSet = query.executeQuery(); 
         results = new ArrayList<Rental>();
         
         while (resultSet.next()){
            results.add(new Rental(
            resultSet.getString("name"),
            resultSet.getDate("expiry_date"),
            resultSet.getInt("movies_id")));
         } 
      } catch (SQLException sqlException){
         sqlException.printStackTrace();         
      }  
      return results;
    }
}
