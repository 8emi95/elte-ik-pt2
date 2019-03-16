/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import progtech2bead.entities.Movie;

/**
 *
 * @author doma
 */
public class Database {
   private static final String URL = "jdbc:derby://localhost:1527/bead";
   private static final String USERNAME = "domus";
   private static final String PASSWORD = "asd123";
   
   public static Connection connect(){
       try {
         return DriverManager.getConnection(URL, USERNAME, PASSWORD);
      }catch (SQLException sqlException){
         sqlException.printStackTrace();
         System.exit(1);
      }
       return null;
   }
   
   public static void close(Connection connection){
       try {
         connection.close();
      } catch (SQLException sqlException){
         sqlException.printStackTrace();
      } 
   }

}
