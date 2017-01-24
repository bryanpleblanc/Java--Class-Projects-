package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Project 3: NoAvailableBayException 
 * 
 * Description: Thrown when a vehicle attempts to choose a service bay
 * but all appropriate service bays are occupied.
 * 
 * @author Bryan LeBlanc
 *
 */
@SuppressWarnings("serial")
public class NoAvailableBayException extends Exception {
    

    /**
     * Passes along the specific message to the parent constructor
     * @param message The specific message from the offended code
     */
    public NoAvailableBayException(String message) {
        super(message);
    }

    /**
     * Default constructor for NoAvailableBayException
     */
    public NoAvailableBayException() {
        super("No Available Bay");
    }

}
