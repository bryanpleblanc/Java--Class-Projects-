package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Project 3: BadVehicleInformationException
 * 
 * Description: Thrown when a user attempts to add/edit a 
 * vehicle with invalid information (tier, type, name, license)
 * 
 * @author Bryan LeBlanc
 *
 */
@SuppressWarnings("serial")
public class BadVehicleInformationException extends Exception {
    
    /**
     * Default constructor for BadVehicleInformationException
     */
    public BadVehicleInformationException() {
        super("Invalid tier.");
    }

    /**
     * Passes along the specific message to the parent constructor
     * @param message The specific message 
     */
    public BadVehicleInformationException(String message) {
        super(message);
    }

}
