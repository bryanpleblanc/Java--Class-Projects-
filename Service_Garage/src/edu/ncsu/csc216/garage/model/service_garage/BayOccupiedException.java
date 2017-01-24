package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Project 3: BayOccupiedException 
 * 
 * Description: Thrown when a vehicle attempts to choose a service bay that is occupied.
 * 
 * @author Bryan LeBlanc
 *
 */
@SuppressWarnings("serial")
public class BayOccupiedException extends Exception {

    /**
     * Passes along the specific message to the parent constructor
     * @param message The specific message from the offended code
     */
    public BayOccupiedException(String message) {
        super(message);
    }

    /**
     * Default constructor for the exception 
     */
    public BayOccupiedException() {
        super("Bay Occupied Exception");
    }
}
