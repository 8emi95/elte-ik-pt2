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
public class Rental {
    private int id;
    private int movieID;
    private String name;
    private Date rentDate;
    private Date returnDate;

    public Rental(int movieID, String name, Date rentDate) {
        this.movieID = movieID;
        this.name = name;
        this.rentDate = rentDate;
    }

    public Rental(int id, int movieID, String name, Date rentDate, Date returnDate) {
        this.id = id;
        this.movieID = movieID;
        this.name = name;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
         this.id = id;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
