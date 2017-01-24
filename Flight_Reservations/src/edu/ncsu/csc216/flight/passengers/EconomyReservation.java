/**
 * 
 */
package edu.ncsu.csc216.flight.passengers;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Project 1, Part 2: EconomyReservation
 * 
 * Description: 
 * 
 * @author Bryan LeBlanc
 *
 */
public class EconomyReservation extends FlightReservation {

    /**
     * Class Constructor 
     * @param name for name 
     * @param plane for flight object
     * @param prefersWindow for prefersWindow
     */
    public EconomyReservation(String name, Flight plane, boolean prefersWindow) {
        super(name, plane, prefersWindow);
        // TODO Auto-generated constructor stub
    }

    /**
     * An abstract method that assigns a particular seat to 
     * this reservation if possible.
     * 
     * Assigns a seat to mySeat according to which child class 
     * this is [UC2/3, S5][UC4, S6]. For the economy class version, 
     * this method first checks to see if the Coach section is at 
     * capacity, returning immediately if so [UC4, S5]. If no 
     * appropriate seat is available, the mySeat data member 
     * remains null [UC2/3/4, E4].
     */
    @Override
    public void findSeat() {
        if (!myAirplane.coachAtCap()) {
            mySeat = myAirplane.reserveEconomySeat(super.wantsWindowSeat());
        } else {
            mySeat = null;
        }
        
        
    }
    /**
    * Returns the part of the string printed in the Reservations 
    * area on the GUI but minus its classification (the part that 
    * starts with the seat label) [UC6]. This string has leading 
    * blanks if necessary to pad the seat location to a field of width 4.
    * 
    * returns the entire string to be printed on the Reservations area of the GUI [UC6].
    * @return String of reservations 
    */ 
    public String stringForPrint() {
        return "Coach        " + super.stringForPrint();
    }

}
