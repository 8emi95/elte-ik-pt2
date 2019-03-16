/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.controllers;

import java.util.List;
import progtech2bead.entities.Movie;
import progtech2bead.services.MovieService;

/**
 *
 * @author doma
 */
public class MovieController {
    private final MovieService movieService = new MovieService();
    
    public List<Movie> getAllMovies(){
        return movieService.findall();
    }
    
    public List<Movie> filter(String title, int begin, int end){
        return movieService.multiselectByTitleAndYear(title, begin, end);
    }
   
    public void newMovie(Movie movie){
        movieService.create(movie);
    }

    public Movie getById(int movieID) {
        return movieService.findOne(movieID);
    }
   
}
