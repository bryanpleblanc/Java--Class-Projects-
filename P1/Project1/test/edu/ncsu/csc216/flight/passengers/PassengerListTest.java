package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;



/**
 * Test for PassengerList Class
 * @author bryanleblanc
 *
 */
public class PassengerListTest {

    private final String testFile = "test-files/tiny-plane.txt";
    private PassengerList passengerList;
    private FirstClassReservation firstClassRes;
    private Flight plane; 
    /**
     * Sets up the tests.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
     
      passengerList = new PassengerList();
      plane = new Flight(testFile); 
    }
    
    /**
     * Tests method add 
     */
    @Test
    public void testAddPassengerList() {
        //add(FlightReservation fR)
        //FlightReservation(String name, Flight plane, boolean prefersWindow) 
        firstClassRes = new FirstClassReservation("Davidson, Bob", plane, true);
        passengerList.add(firstClassRes);
        firstClassRes = new FirstClassReservation("Flint, Robert", plane, true);
        passengerList.add(firstClassRes);

        assertEquals("First Class  none  Davidson, Bob\nFirst Class  none  Flint, Robert\n", passengerList.report());

    }
    
    /**
     * Tests method remove
     */
    @Test
    public void testRemovePassengerList() {
        firstClassRes = new FirstClassReservation("Davidson, Bob", plane, true);
        passengerList.add(firstClassRes);
        firstClassRes = new FirstClassReservation("Flint, Robert", plane, true);
        passengerList.add(firstClassRes);
        
        assertEquals("First Class  none  Davidson, Bob\nFirst Class  none  Flint, Robert\n", passengerList.report());
        
        passengerList.removePassenger(1);
        assertEquals("First Class  none  Davidson, Bob\n", passengerList.report());
        
        // tests if index is less than or equal to zero
        try {
            passengerList.removePassenger(-1);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed");             
        }
    }
    
    /**
     * Tests method report
     */
    @Test
    public void testReport() {
        firstClassRes = new FirstClassReservation("Davidson, Bob", plane, true);
        passengerList.add(firstClassRes);
        firstClassRes = new FirstClassReservation("Flint, Robert", plane, true);
        passengerList.add(firstClassRes);
        // add white space
        firstClassRes = new FirstClassReservation("Mint, Sarah  ", plane, true);
        passengerList.add(firstClassRes);
        
        assertEquals("First Class  none  Davidson, Bob\nFirst Class  none  Flint, Robert\nFirst Class  none  Mint, Sarah\n", passengerList.report());

    }
}
