/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

/**
 * Project 1, Part 2: Seat
 * 
 * Description: Concrete class representing a seat on an airplane
 * (or other type of vehicle, theater seat, etc).
 * 
 * @author Bryan LeBlanc
 *
 */
public class Seat {
    
    /** row and letter, such as 12J */
    private String location;
    private boolean occupied;
    private boolean windowSeat;
    private boolean aisleSeat;
    
    /**
     * Seat constructor
     * @param location from location
     * @param windowSeat from windowSeat
     * @param aisleSeat from aisleSeat
     */
    public Seat(String location, boolean windowSeat , boolean aisleSeat) {
        this.location = location;
        this.windowSeat = windowSeat;
        this.aisleSeat = aisleSeat;
        
    }
    
    /**
     * Checks if a seat is occupied 
     * @return boolean
     */
    public boolean isOccupied() {
        return occupied;
    }
    
    /**
     * Checks if window seat
     * @return boolean 
     */
    public boolean isWindowSeat() {
        return windowSeat;
    }
    
    /**
     * Sets window seat value
     * @param windowSeat from windowSeat
     */
    public void setWindowSeat(boolean windowSeat) {
        this.windowSeat = windowSeat;
    }
    
    /**
     * Checks if aisle seat
     * @return boolean 
     */
    public boolean isAisleSeat() {
        return aisleSeat;
    }
    
    /**
     * Sets aisle seat value
     * @param aisleSeat from aisleSeat
     */
    public void setAisleSeat(boolean aisleSeat) {
        this.aisleSeat = aisleSeat;
    }
    

    /**
     * Makes a seat occupied
     */
    public void occupy() {
        occupied = true;
    }
    
    /**
     * Makes a seat unoccupied 
     */
    public void release() {
        occupied = false;
        
    }
    
    /**
     * Gets the location of the seat 
     * @return location 
     */
    public String getLocation() {
        if(location != null) {
            return location.trim();
        }
        return null;
        
    }
    

}
