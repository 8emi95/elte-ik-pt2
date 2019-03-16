/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progtech2bead.controllers;

import java.util.List;
import progtech2bead.entities.Rental;
import progtech2bead.services.RentalService;

/**
 *
 * @author doma
 */
public class RentalController {
    private final RentalService rentalService = new RentalService();
    
    public List<Rental> getAllRentals(){
        return rentalService.findall();
    }
    
    public void newRental(Rental rental){
        rentalService.create(rental);
    }

    public void bringBack(Rental rental) {
        rentalService.delete(rental);
    }
}
