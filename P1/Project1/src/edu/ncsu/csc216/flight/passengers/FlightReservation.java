package edu.ncsu.csc216.flight.passengers;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Project 1, Part 2: FlightReservation
 * 
 * Description: Abstract class representing a reservation 
 * for an airplane flight. FlightReservation has a Flight 
 * named myAirplane.
 * 
 * NOTE:
 * FlightReservation is an abstract class that holds all of
 * the data required for any type of reservation. It has 
 * four instance variables (a string for the passenger's 
 * name, a a boolean for seat preference, a string for the 
 * seat label, and a Flight for the airplane). Since mySeat 
 * is protected, it is directly accessible to any of the 
 * FlightReservation child classes as well as to PassengerList,
 * which resides in the same package. Note that FlightReservation 
 * also has a private instance variable of type Flight.
 * 
 * @author Bryan LeBlanc
 *
 */
public abstract class FlightReservation {
    /** seat */
    protected String mySeat;
    private String name;
    private boolean prefersWindow;
    /** Flight object */
    protected Flight myAirplane;
    
    
    /**
     * The FlightReservation constructor has three parameters that 
     * initialize the passenger name, the plane, and the seat 
     * preference. It contains all the code to initialize any instance 
     * of its child classes. Any attempt to create a FlightReservation 
     * with a null name or a name with only whitespace characters results
     * in an IllegalArgumentException [UC2/3/4, E1]. Any attempt to create 
     * a FlightReservation with a null Flight also results in an IllegalArgumentException.
     * @param name from name
     * @param plane from plane
     * @param prefersWindow from prefersWindow
     */
    public FlightReservation(String name, Flight plane, boolean prefersWindow) {
        if (name == null || name.trim().length() <= 0) {
            throw new IllegalArgumentException("Name can't be null or blank");
        } else if (plane == null) {
            throw new IllegalArgumentException("Plane can't be blank");
        } 
        this.name = name.trim();
        this.myAirplane = plane;
        this.prefersWindow = prefersWindow;
    }    
    
    /**
     * Gets the passenger name
     * @return passenger name 
     */
    public String getName() {
        if(name == null){
            throw new IllegalArgumentException();
        }
        return name;

    }
    
    /**
     * Gets the seat 
     * @return seat 
     */
    public String getSeat() {
        if(mySeat != null) {
            return mySeat.trim();
        }
        return null;
    }
    
    /**
     * Getter for flight object 
     * @return Flight object
     */
    public Flight getVehicle() {
        return myAirplane;
    }
    
    /**
     * True if the seating preference is for a window seat, 
     * false if it is for an aisle seat [UC 2/3/4].
     * @return ture if passenger prefers window 
     */
    public boolean wantsWindowSeat() {
        return prefersWindow;
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
    abstract void findSeat();
    
    /**
     * Returns the part of the string printed in the Reservations 
     * area on the GUI but minus its classification (the part that 
     * starts with the seat label) [UC6]. This string has leading 
     * blanks if necessary to pad the seat location to a field of width 4.
     * 
     * returns the entire string to be printed on the Reservations area of the GUI [UC6].
     * @return a formatted string of reservations 
     */
    public String stringForPrint() {
       if(mySeat == null) {
           return "none  " + name;
        }
        // 2 spaces before mySeat and 2 spaces after 
        //return "  " + mySeat + "  " + name;
       // String.format explanation:
       // %1 is the first argument (myseat) - %4s add a 4 character left padded string
        String str =  String.format("%1$4s", mySeat) +  "  " + name;
        return str;
    }
    
    

    /**
     * Makes a case insensitive comparison on names [UC6].
     * @param fR for flightReservation 
     * @return compareTo from compareTo
     */
    public int compareTo(FlightReservation fR) {
        return name.compareToIgnoreCase(fR.getName());
        
    }
    
    /**
     * Makes the seat for this reservation (if there is one) unoccupied [UC7][S3].
     */
    public void cancelReservation() {
        myAirplane.releaseSeat(mySeat);
        
    }
        

}
