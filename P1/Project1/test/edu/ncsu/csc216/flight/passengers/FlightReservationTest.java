

package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Test FlightReservation class
 * @author bryanleblanc
 *
 */
public class FlightReservationTest {

    private final String testFile = "test-files/tiny-plane.txt";
    //private PassengerList passengerList;
    private FirstClassReservation firstClassRes;
    private FirstClassReservation firstClassResBlankName;

    private Flight plane; 
    /**
     * Sets up the tests.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
      //passengerList = new PassengerList();
      plane = new Flight(testFile); 

    }
    
    /**
     * Tests Flight Reservation Methods
     */
    @Test
    public void testFlightReservationMethods() {
        //add(FlightReservation fR)
        //FlightReservation(String name, Flight plane, boolean prefersWindow) 
        firstClassRes = new FirstClassReservation("Davidson, Bob", plane, true);
        firstClassRes.findSeat();
        
        
        // tests if passenger name is blank
        try {
            firstClassResBlankName = new FirstClassReservation("", plane, true);
            firstClassRes.findSeat();
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
        // tests if passenger name is blank
        try {
            firstClassResBlankName = new FirstClassReservation(null, plane, true);
            firstClassRes.findSeat();
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
        // Test getName() method
        assertEquals("Davidson, Bob", firstClassRes.getName());
        
        
        
        // test getSeat() method 
        assertEquals("1A", firstClassRes.getSeat());
        
        // test getVehicle() method 
        assertEquals(plane, firstClassRes.getVehicle());
        
        //test wantsWindowSeat() method
        assertTrue(firstClassRes.wantsWindowSeat());
        
        // test stringForPrint() method
        assertEquals("First Class    1A  Davidson, Bob", firstClassRes.stringForPrint());
        
        //test compareTo() method
        assertEquals(0, firstClassRes.compareTo(firstClassRes));
        
        //test compareTo() method
        assertEquals(0, firstClassRes.compareTo(firstClassRes));
        
        //test compareTo() method
        assertTrue(plane.getSeatOccupationMap()[0][0]); // test if occupied
        firstClassRes.cancelReservation(); // cancel reservation method call 
        assertFalse(plane.getSeatOccupationMap()[0][0]); // test if not occupied

    }
}

