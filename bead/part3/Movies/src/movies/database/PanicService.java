/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.database;

import java.sql.Connection;
import java.util.List;
import movies.entities.Movie;

/**
 *
 * @author Emese
 */
public class PanicService {
    private Connection connection;
    private final MovieService movieService = new MovieService();
    private final RentalService rentalService = new RentalService();

    public PanicService() {
        this.connection = Database.connect();
    }

    public void panic() {
        List<Movie> piratedMovies = movieService.getPiratedMovies();
        rentalService.deleteRentalsByMovies(piratedMovies);
        movieService.deletePiratedMovies();
    }
}
