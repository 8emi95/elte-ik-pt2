/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies.entities;

import java.sql.Date;

/**
 *
 * @author Emese
 */
public class Movie {
    private int id;
    private String title;
    private String directors;
    private String actors;
    private int releaseYear;
    private int lengthInMins;
    private String cover;
    private boolean original;
    private int rentalNumber;
    private boolean available;

    public Movie(String title, String directors, String actors, int releaseYear, int lengthInMins, String cover, boolean original) {
        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.lengthInMins = lengthInMins;
        this.cover = cover;
        this.original = original;
        this.rentalNumber = 0;
        this.available = false;
    }

    public Movie(int id, String title, String directors, String actors, int releaseYear, int lengthInMins, String cover, boolean original, int rentalNumber, boolean available) {
        this.id = id;
        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.releaseYear = releaseYear;
        this.lengthInMins = lengthInMins;
        this.cover = cover;
        this.original = original;
        this.rentalNumber = rentalNumber;
        this.available = available;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLengthInMins() {
        return lengthInMins;
    }

    public void setLengthInMins(int lengthInMins) {
        this.lengthInMins = lengthInMins;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public int getRentalNumber() {
        return rentalNumber;
    }

    public void setRentalNumber(int rentalNumber) {
        this.rentalNumber = rentalNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return title;
    }

    public Rental rent(String name, Date rentDate) {
        setAvailable(false);
        return new Rental(this.id, name, rentDate);
    }
}
