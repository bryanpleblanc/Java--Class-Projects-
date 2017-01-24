package edu.ncsu.csc216.flight.passengers;

import java.util.ArrayList;


/**
 * Project 1, Part 2: PassengerList
 * 
 * Description: Concrete class that represents the passenger
 * manifest for a flight. PassengerList has all of its 
 * FlightReservations in a variable named list.
 * 
 * @author Bryan LeBlanc
 *
 */
public class PassengerList {
    
    
    //private FlightReservation flightRes;
    
    /** This type can hold an entire collection of elements of type FlightReservation */
    private ArrayList<FlightReservation> list;
    
    /**
     * Class Constructor 
     */
    public PassengerList() {
        list = new ArrayList<FlightReservation>();
        
    }
    
    /**
     *  Adds a FlightReservation to the list in its proper 
     *  place according to the passenger name [UC6].
     * @param fR for flightReservation 
     */
    public void add(FlightReservation fR) {
        for (int i = 0; i < list.size(); i++) {
            if (fR.compareTo(list.get(i)) < 0) {
                list.add(i, fR);
                return;
            }
        }
        list.add(fR);
    }
    
    /**
     * Removes the passenger (FlightReservation) at the 
     * given index. Throws an IllegalArgumentException if 
     * the index is out of range [UC7].
     * 
     * @param index of the passenger in the array
     */
    public void removePassenger(int index) {
        if (index < 0 || index >= list.size()) {
            throw new IllegalArgumentException("Index is out of range");
        }
        FlightReservation fR = list.get(index);
        
        fR.cancelReservation();

        list.remove(index);
        

    }
    
    /**
     * Returns a newline-separated list of strings representing
     * FlightReservations [UC6].
     * 
     * @return string of flight reservations with a new line character
     */
    public String report() {
        String reportList = "";
        for (FlightReservation x: list) {
            reportList += x.stringForPrint() + "\n";
        }
        return reportList;
        
    }

}

    