/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

import movies.entities.Movie;
import movies.entities.Rental;

/**
 *
 * @author Emese
 */
public class ServiceTest {
    Movie movie1 = new Movie(
        "The Hunger Games",
        "Gary Ross",
        "Jennifer Lawrence",
        2012,
        142,
        "https://ia.media-imdb.com/images/M/MV5BMjA4NDg3NzYxMF5BMl5BanBnXkFtZTcwNTgyNzkyNw@@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
        true);
    Movie movie2 = new Movie(
        "White Chicks",
        "Keenen Ivory Wayans",
        "Marlon Wayans, Shawn Wayans",
        2004,
        109,
        "https://ia.media-imdb.com/images/M/MV5BMTY3OTg2OTM3OV5BMl5BanBnXkFtZTYwNzY5OTA3._V1_.jpg",
        false);
    Movie movie3 = new Movie(
        "Lissi und der wilde Kaiser",
        "Michael Herbig",
        "Lotte Ledl, Waldemar Kobus",
        2007,
        82,
        "https://ia.media-imdb.com/images/M/MV5BMTUxMjc5NzQ2M15BMl5BanBnXkFtZTcwODQ3NzU4MQ@@._V1_.jpg",
        true);
    Movie movie4 = new Movie(
        "Deadpool",
        "Tim Miller",
        "Ryan Reynolds",
        2016,
        108,
        "https://ia.media-imdb.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SY1000_CR0,0,666,1000_AL_.jpg",
        false);
    Movie movie5 = new Movie(
        "Bad Moms",
        "Jon Lucas",
        "Mila Kunis, Kathryn Hahn, Kristen Bell, Lilly Singh",
        2016,
        100,
        "https://ia.media-imdb.com/images/M/MV5BMjIwNzE5MTgwNl5BMl5BanBnXkFtZTgwNjM4OTA0OTE@._V1_SY1000_CR0,0,675,1000_AL_.jpg",
        true);


    @Test
    public void testAddMovie() {
        MovieService instance = new MovieService();
        instance.deleteAllMovies();
        instance.addMovie(movie1);
        int result = instance.listMovies().size();
        assertTrue(result == 1);
    }


    @Test
    public void testListMovies() {
        MovieService instance = new MovieService();
        instance.deleteAllMovies();
        instance.addMovie(movie3);
        instance.addMovie(movie4);
        List<String> expResultString = new ArrayList<>();
        expResultString.add(movie3.getTitle());
        expResultString.add(movie4.getTitle());
        List<Movie> result = instance.listMovies();
        List<String> resultString = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            resultString.add(result.get(i).getTitle());
        }
        assertEquals(expResultString, resultString);
    }

 
    @Test
    public void testDeleteAllMovies() {
        MovieService instance = new MovieService();
        instance.deleteAllMovies();
        List<Movie> result = instance.listMovies();
        assertTrue(result.isEmpty());
    }

 
    @Test
    public void testDeleteMovieByID() {
        MovieService instance = new MovieService();
        instance.deleteAllMovies();
        instance.addMovie(movie3);
        instance.addMovie(movie4);
        instance.deleteMovieByID(instance.findMovieByTitle("Deadpool").getID());
        List<String> expResultString = new ArrayList<>();
        expResultString.add(movie3.getTitle());
        List<Movie> result = instance.listMovies();
        List<String> resultString = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            resultString.add(result.get(i).getTitle());
        }
        assertEquals(expResultString, resultString);
    }


    @Test
    public void testDeleteAllRentals() {
        RentalService instance = new RentalService();
        List<Rental> result = instance.listRentals();
        instance.deleteAllRentals(result);
        assertTrue(result.isEmpty());
    }


    @Test
    public void testListRentals() throws ParseException {
        MovieService movieServiceInstance = new MovieService();
        movieServiceInstance.deleteAllMovies();
        movieServiceInstance.addMovie(movie3);
        movieServiceInstance.addMovie(movie4);
        movieServiceInstance.addMovie(movie5);

        RentalService rentalServiceInstance = new RentalService();
        List<Rental> rentalList = rentalServiceInstance.listRentals();
        rentalServiceInstance.deleteAllRentals(rentalList);
        Rental rental1 = new Rental(movie3.getID(),
                                    "Dixie Normous",
                                    new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-31").getTime()));
        Rental rental2 = new Rental(movie4.getID(),
                                    "Anurag Dikshit",
                                    new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-31").getTime()));
        rentalServiceInstance.addRental(rental1);
        rentalServiceInstance.addRental(rental2);

        int[] expResultInt = new int[100];
        expResultInt[0] = movie3.getID();
        expResultInt[1] = movie4.getID();

        List<Rental> result = rentalServiceInstance.listRentals();
        int[] resultInt = new int[100];
        for (int i = 0; i < result.size(); ++i) {
            resultInt[i] = result.get(i).getMovieID();
        }

        assertArrayEquals(expResultInt, resultInt);
        //assertEquals(expResultInt, resultInt);
    }


    @Test
    public void testAddRental() throws ParseException {
        MovieService movieServiceInstance = new MovieService();
        movieServiceInstance.deleteAllMovies();
        movieServiceInstance.addMovie(movie3);
        movieServiceInstance.addMovie(movie4);

        RentalService rentalServiceInstance = new RentalService();
        List<Rental> rentals = rentalServiceInstance.listRentals();
        rentalServiceInstance.deleteAllRentals(rentals);
        Rental rental1 = new Rental(movie3.getID(),
                                    "Dixie Normous",
                                    new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-31").getTime()));
        rentalServiceInstance.addRental(rental1);

        int result = rentalServiceInstance.listRentals().size();
        assertTrue(result == 1); // 0?
    }

/*
    @Test
    public void testFindMovieByID() {
        MovieService instance = new MovieService();
        instance.deleteAllMovies();
        instance.addMovie(movie3);
        instance.addMovie(movie4);
        instance.findMovieByID(instance.findMovieByTitle("Deadpool").getID());
        int expResultInt = movie4.getID();
        List<Movie> result = instance.listMovies();
        int resultInt = 0;
        for (int i = 0; i < result.size(); ++i) {
            if (result.get(i).getTitle().equals("Deadpool"))
            resultInt = result.get(i).getID();
        }
        assertEquals(expResultInt, resultInt);
    }
*/
/*
    @Test
    public void testDeletePiratedMovies() {
        MovieService instance = new MovieService();
        instance.addMovie(movie3);
        instance.addMovie(movie4); // this should be deleted
        instance.deletePiratedMovies();
    }
*/
/*
    @Test
    public void testSetAvaiblity() {
        int movieID = 0;
        boolean avaiblity = false;
        MovieService instance = new MovieService();
        instance.setAvaiblity(movieID, avaiblity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
/*
    @Test
    public void testGetPiratedMovies() {
        MovieService instance = new MovieService();
        List<Movie> expResult = null;
        List<Movie> result = instance.getPiratedMovies();
        assertEquals(expResult, result);
    }
*/
/*
    @Test
    public void testDeleteRentalByID() {
        Rental rental = null;
        RentalService instance = new RentalService();
        instance.deleteRentalByID(rental);
    }
*/
/*
    @Test
    public void testDeleteRentalsByMovies() {
        List<Movie> movies = null;
        RentalService instance = new RentalService();
        instance.deleteRentalsByMovies(movies);
    }
*/
}
