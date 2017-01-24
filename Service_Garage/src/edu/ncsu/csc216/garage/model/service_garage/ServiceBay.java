package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Project 3: 
 * 
 * Description: Concrete class representing a service bay in the garage. 
 * Service bays of this type are considered "regular," and can handle 
 * both regular and hybrid/electric vehicles. Each service bay has an 
 * ID and, if not empty, a vehicle.
 * 
 * @author Bryan LeBlanc 
 *
 */
public class ServiceBay {
    
    private boolean occupied;
    private Vehicle myVehicle;
    private String bayID;
    private static int nextNumber;
    
    /**
     * Method sets the next bay number to 101 
     */
    public static void startBayNumberingAt101() {
        nextNumber = 1;
    }
    

    /**
     * ServiceBay constructor; creates a bay with a prefix
     * @param prefix to use in the bayID
     */
    public ServiceBay(String prefix) {
        if(prefix == null || prefix.trim().length() <= 0) {
            if (nextNumber < 10) {
                bayID = "1" + "0" + nextNumber;
            } else {
                bayID = "1" + nextNumber;
            }
            nextNumber++;
            occupied = false;
        } else {
            prefix = prefix.trim();
            if (prefix.length() > 1) {
                prefix = prefix.substring(0, 1);
            }
            if(nextNumber < 10) {
                bayID = prefix + "0" + nextNumber;
            } else {
                bayID = prefix + nextNumber;
            }
            nextNumber++;
            occupied = false;
        }
    
    }
    
    /**
     * ServiceBay constructor; creates a new bay
     */
    public ServiceBay() {
        this("1");
    }
    
    /**
     * Getter method returns the ID of the service bay
     * @return bay ID in the service bay
     */
    public String getBayID() {
        return bayID;
    }
    
    /**
     * Getter method returns if bay is occupied
     * @return true if the bay is occupied
     */
    public boolean isOccupied() {
        return occupied;
    }
    

    /**
     * Method releases (removed) a vehicle from the service bay
     * @return Vehicle removed from bay
     */
    public Vehicle release() {
        Vehicle v = myVehicle;
        if(!occupied) {
            return null;
        } 
        
        occupied = false;
        myVehicle = null;
        return v;
    }
    
    /**
     * Makes a service bay occupied
     * @param vehicle stored in the bay
     * @throws BayOccupiedException if the bay is already occupied
     * @throws BayCarMismatchException if the car tries to enter a bay to which it does not correspond
     */
    public void occupy(Vehicle vehicle) throws BayOccupiedException, BayCarMismatchException {
        if(occupied) {
            throw new BayOccupiedException();
        }
        myVehicle = vehicle;
        occupied = true;
    }
    

    /**
     * Returns a string representation of the service bay
     * @return the string representation of the service bay
     */
    public String toString() {
        if(!occupied) {
            return getBayID()  + ": EMPTY";
        }
        return getBayID() + ": " + String.format("%-9s", myVehicle.getLicense()) + myVehicle.getName();
        //102: NC1234   Doe, John 
        

    }
        
    
    

}
