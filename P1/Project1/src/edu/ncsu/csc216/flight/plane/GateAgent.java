/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import edu.ncsu.csc216.flight.passengers.BusinessClassReservation;
import edu.ncsu.csc216.flight.passengers.EconomyReservation;
import edu.ncsu.csc216.flight.passengers.FirstClassReservation;
import edu.ncsu.csc216.flight.passengers.PassengerList;

/**
 * Project 1, Part 2: GateAgent 
 * 
 * Description: Concrete class representing the gate agent 
 * (or desk agent) in the problem domain. GateAgent has a 
 * PassengerList named manifest and a SeatingManager named plane.
 * 
 * SeatingManager and a PassengerList, to which it delegates the work of most of its behaviors
 * 
 * @author Bryan LeBlanc
 *
 */
public class GateAgent {
    
    // Is this correct?
    private SeatingManager plane;
    
    private PassengerList manifest;
    
    
    /**
     * Gate Agent Constructor
     * @param seatChartFileName for filename
     */
    public GateAgent(String seatChartFileName) {
        plane = new Flight(seatChartFileName);
        manifest = new PassengerList();
    }
    

    /**
     * Simply calls the removePasenger() method in PassengerList
     * Removes the passenger (FlightReservation) at the 
     * given index.
     * @param index for index 
     */
    public void removePassenger(int index) {
        manifest.removePassenger(index);
        
    }
    

    /**
     * Adds a passenger reservation to the manifest. The 
     * first parameter is an int, which indicates the reservation
     * type (0 = first class, 1 = business class, 2 = economy), 
     * the second parameter is a string representing the passenger's 
     * name, and the third parameter is a boolean indicating seat 
     * type preference (true = prefers window, false = prefers aisle).
     * This method also attempts to find a seat for this reservation.
     * See [UC2/3/4]. If the name is null or all whitespace, this 
     * method throws an IllegalArgumentException 
     * 
     * NOTE: 
     * When addPassenger() is invoked, it must create a new FlightReservation 
     * of the appropriate type. The actual parameters it uses are a string for
     * the passenger's name, a Flight (which it has through its SeatingManager), 
     * and a boolean to indicate whether the passenger prefers a window seat.
     *  
     * @param reservationType from reservationType
     * @param passengerName from passengerName
     * @param windowSeat from windowSeat
     */
    public void addPassenger(int reservationType, String passengerName, boolean windowSeat) {
        if(passengerName == null || passengerName.length() <= 0) {
            throw new IllegalArgumentException("Passenger name cannot be blank");
        }
        if (reservationType == 0) {
                FirstClassReservation fR = new FirstClassReservation(passengerName, (Flight)plane, windowSeat);
                fR.findSeat();
                manifest.add(fR);
            
        } else if (reservationType == 1) {
                BusinessClassReservation fR = new BusinessClassReservation(passengerName, (Flight)plane, windowSeat);
                fR.findSeat();
                manifest.add(fR); 

        } else if (reservationType >= 2) {
                EconomyReservation fR = new EconomyReservation(passengerName, (Flight)plane, windowSeat);
                fR.findSeat();
                manifest.add(fR); 
        }
    }
    
    /**
     * Simply call getSeatMap() method in Flight 
     * Returns 2D array of seat map 
     * @return String 2D array
     */
    public String[][] getSeatMap() {
        return plane.getSeatMap();
        
    }
    
    /**
     * Simply call getSeatOccupationMap() method in Flight 
     * Returns 2D array of occupation seat map 
     * @return boolean 2D array
     */
    public boolean[][] getSeatOccupationMap() {
        return plane.getSeatOccupationMap();
    }
    
    /**
     * Simply calls PassengerList.report() to determine its return value.
     * @return a printed array in report form 
     */
    public String printReservations() {
        return manifest.report();
        
        
        
    }

}
