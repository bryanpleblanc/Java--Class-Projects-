package edu.ncsu.csc216.flight.plane;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for Flight class
 * @author bryanleblanc
 *
 */
public class FlightTest {

    private final String testFile = "test-files/tiny-plane.txt";
    private Flight flight;
    
    
    /**
     * Sets up the tests.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        
        
    }
    
    /**
     * Tests Flight constructor
     */
    @Test
    public void testFlight() {
        flight = new Flight(testFile);
        String[][] seatMap = flight.getSeatMap();
        // ensuring seat map is formatted correctly 
        // Also testing getSeatMap() method
        assertEquals("1A", seatMap[0][0]);
        assertEquals("1B", seatMap[0][1]);
        assertEquals(null, seatMap[0][2]);
        assertEquals("1C", seatMap[0][3]);
        assertEquals("1D", seatMap[0][4]);
        assertEquals("1E", seatMap[0][5]);
        assertEquals("11E", seatMap[5][5]);
    }
    /**
     * Tests reserveBusinessSeat() method
     */
    @Test
    public void testReserveBusinessSeat() {
        flight = new Flight(testFile);
        assertEquals("2A", flight.reserveBusinessSeat(true));
        assertEquals("2E", flight.reserveBusinessSeat(true)); // window seats full at the first row
        assertEquals("2B", flight.reserveBusinessSeat(false));
        assertEquals("2C", flight.reserveBusinessSeat(false));
        assertEquals("3A", flight.reserveBusinessSeat(true)); 
        assertEquals("3E", flight.reserveBusinessSeat(true)); // ALL window seats full
        assertEquals("2D", flight.reserveBusinessSeat(true)); // takes the first available unoccupied seat 
        assertEquals("3B", flight.reserveBusinessSeat(false));
        assertEquals("3C", flight.reserveBusinessSeat(false));
        assertEquals("3D", flight.reserveBusinessSeat(true)); // All business seats taken
        assertEquals(null, flight.reserveBusinessSeat(true)); // tries to add a seat in a full class 

    }
    
    /**
     * Tests FirstClassSeat() method
     */
    @Test
    public void testFirstClassSeat() {
        flight = new Flight(testFile);
        assertEquals("1A", flight.reserveFirstClassSeat(true));
        assertEquals("1E", flight.reserveFirstClassSeat(true)); // window seats full in first class
        assertEquals("1B", flight.reserveFirstClassSeat(false));
        assertEquals("1C", flight.reserveFirstClassSeat(true));
        assertEquals("1D", flight.reserveFirstClassSeat(true)); // all first class seats full
        assertEquals(null, flight.reserveFirstClassSeat(true)); // finds a seat in full first class
        
    }
    
    /**
     * Tests EconomySeat() method
     */
    @Test
    public void testEconomySeat() {
        flight = new Flight(testFile);
        assertEquals("6E", flight.reserveEconomySeat(true)); // window
        assertEquals("10A", flight.reserveEconomySeat(true)); // window 
        assertEquals("10B", flight.reserveEconomySeat(false)); // aisle
        assertEquals("10C", flight.reserveEconomySeat(false)); //aisle
        assertEquals("11B", flight.reserveEconomySeat(false)); //aisle
        assertEquals("11C", flight.reserveEconomySeat(false)); //aisle
        assertEquals("6C", flight.reserveEconomySeat(false)); //aisle
        assertEquals("6D", flight.reserveEconomySeat(false)); //aisle
        assertEquals("10D", flight.reserveEconomySeat(false)); //aisle
        assertEquals("10E", flight.reserveEconomySeat(true)); //Window
        assertEquals("11A", flight.reserveEconomySeat(true)); //Window

    }

    /**
     * Tests ReleaseSeat() method
     * 
     */
    @Test
    public void testReleaseSeat() {
        flight = new Flight(testFile);
        
        
        assertEquals("1A", flight.reserveFirstClassSeat(true)); // add seat
        assertEquals("1E", flight.reserveFirstClassSeat(true)); // window seats full in first class
        assertEquals("1B", flight.reserveFirstClassSeat(false));
        assertEquals("1C", flight.reserveFirstClassSeat(true));
        
        // removes 2 seats that were occupied
        flight.releaseSeat("1A"); 
        flight.releaseSeat("1C"); 
        
        // try to remove seat that is not occupied
        flight.releaseSeat("1D"); 
        
        boolean[][] currentSeat = flight.getSeatOccupationMap(); // gets seat occupation map
        
        // runs the tests
        assertFalse(currentSeat[0][0]); // false if seat is unoccupied
        assertTrue(currentSeat[0][1]); // true if the seat is occupied
        assertFalse(currentSeat[0][3]);
        assertFalse(currentSeat[0][4]);
        assertTrue(currentSeat[0][5]);
    }
    
    //public void testHelperMethods() {
      //  flight = new Flight(testFile);
        //assertEquals(0, flight.getStartFirstClass());
    //}
}
