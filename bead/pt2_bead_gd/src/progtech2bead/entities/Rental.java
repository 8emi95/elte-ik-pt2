/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.entities;

import java.sql.Date;

/**
 *
 * @author doma
 */
public class Rental {
    
    private int id;
    private String forWho;
    private Date howLong;
    private int movideID;

    public Rental(String forWho, Date howLong, int movideID) {
        this.forWho = forWho;
        this.howLong = howLong;
        this.movideID = movideID;
    }

    public int getId() {
        return id;
    }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public Date getHowLong() {
        return howLong;
    }

    public void setHowLong(Date howLong) {
        this.howLong = howLong;
    }

    public int getMovideID() {
        return movideID;
    }

    public void setMovideID(int movideID) {
        this.movideID = movideID;
    }
    
    
    
}
