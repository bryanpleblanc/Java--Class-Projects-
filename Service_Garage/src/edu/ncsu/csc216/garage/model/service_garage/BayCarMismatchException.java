package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Project 3: BayCarMismatchException 
 * 
 * Description: Thrown when a vehicle attempts to choose a 
 * service bay that is empty but is of the wrong type for 
 * that particular vehicle.
 * 
 * @author Bryan LeBlanc
 *
 */
@SuppressWarnings("serial")
public class BayCarMismatchException extends Exception {
    
    /**
     * Passes along the specific message to the parent constructor
     * @param message The specific message from the offended code
     */
    public BayCarMismatchException(String message) { 
        super(message);
    }
    
    /**
     * Default constructor for BayCarMismatchException
     */
    public BayCarMismatchException() {
        super("Bay Car Mismatch Exception");
    }

}
