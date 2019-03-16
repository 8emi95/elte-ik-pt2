/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author doma
 */
public class Movie {
 
    private int id;
    private String title;
    private boolean origin;
    private int releaseYear;
    private boolean available;
    private String actors;
    private String directors;

    public Movie(String title, boolean origin, int releaseYear, String actors, String directors) {
        this.title = title;
        this.origin = origin;
        this.releaseYear = releaseYear;
        this.available = true;
        this.actors = actors;
        this.directors = directors;
        this.available = true;
    }
    
    public Movie(int id, String title, boolean origin, int releaseYear, String actors, String directors, boolean available) {
        this.id = id;
        this.title = title;
        this.origin = origin;
        this.releaseYear = releaseYear;
        this.available = true;
        this.actors = actors;
        this.directors = directors;
        this.available = available;
    }   
    
    public Rental rent(String forWho, Date howLong){
        setAvailable(false);
        return new Rental (forWho, howLong, this.id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOriginal() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isAvailable() {
        return available;
    }

    private void setAvailable(boolean available) {
        this.available = available;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return title;
    }
    
}
