package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Test for FirtClassReservation class
 * @author bryanleblanc
 *
 */
public class FirstClassReservationTest {
    
    private final String testFile = "test-files/tiny-plane.txt";
    private Flight plane;
    
    private final String testFileOneClass = "test-files/tiny-plane-1-class.txt";
    private Flight planeOneClass;
    private FirstClassReservation firstClassRes;

    
    /**
     * Sets up the tests.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        planeOneClass = new Flight(testFileOneClass);
        plane = new Flight(testFile);

    }
    

    /**
     * Tests Flight Reservation Methods
     */
    @Test
    public void testFirstClassFlightReservationMethods() {
        
        firstClassRes = new FirstClassReservation("Passenger, One", planeOneClass , true); //1A
        firstClassRes.findSeat();
        assertEquals("1A", firstClassRes.getSeat());

        firstClassRes = new FirstClassReservation("Passenger, Two", planeOneClass, false); //1B
        firstClassRes.findSeat();

        assertEquals("1B", firstClassRes.getSeat());
        
        firstClassRes = new FirstClassReservation("Passenger, Three", planeOneClass, true); //2A
        firstClassRes.findSeat();
        

        
        boolean[][] currentSeat = planeOneClass.getSeatOccupationMap();
        
        assertTrue(currentSeat[0][0]);
        assertTrue(currentSeat[0][1]);
        //assertTrue(currentSeat[1][0]);
        //assertTrue(currentSeat[1][5]);
        //assertTrue(currentSeat[2][0]);
        //assertTrue(currentSeat[2][5]);
    }
    /**
     * Test method with normal plane file 
     */
    @Test
    public void testNomalClassSeating() {
        
        firstClassRes = new FirstClassReservation("Passenger, One", plane, true); //1A
        firstClassRes.findSeat();

        firstClassRes = new FirstClassReservation("Passenger, Two", plane, false); //1B
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Three", plane, false); //1C
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Four", plane, true); //1E
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Five", plane, false); //1D
        firstClassRes.findSeat();
        
        // should be null - first class full
        firstClassRes = new FirstClassReservation("Passenger, Six", plane, true); //2A
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Seven", plane, true); //2E
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Eight", plane, true); //3A
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Nine", plane, true); //3E
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Ten", plane, true); //2B
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Eleven", plane, true); //2C
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Twelve", plane, true); //2D
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Thirteen", plane, true); //3B
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Fourteen", plane, true); //3C
        firstClassRes.findSeat();
        
        firstClassRes = new FirstClassReservation("Passenger, Fithteen", plane, true); //3D
        firstClassRes.findSeat();
        
        // Business Class Full - null
        firstClassRes = new FirstClassReservation("Passenger, sixteen", plane, true); //6E
        firstClassRes.findSeat();
        
        boolean[][] currentSeat = plane.getSeatOccupationMap();
        
        assertTrue(currentSeat[0][0]);
        assertTrue(currentSeat[0][1]);
        assertTrue(currentSeat[0][3]);
        assertTrue(currentSeat[0][4]);
        assertTrue(currentSeat[0][5]);
        
        assertTrue(currentSeat[1][0]);
        assertTrue(currentSeat[1][1]);
        assertTrue(currentSeat[1][3]);
        assertTrue(currentSeat[1][4]);
        assertTrue(currentSeat[1][5]);
        
        assertTrue(currentSeat[2][0]);
        assertTrue(currentSeat[2][1]);
        assertTrue(currentSeat[2][3]);
        assertTrue(currentSeat[2][4]);
        assertTrue(currentSeat[2][5]);
        
        assertTrue(currentSeat[3][5]);
        
        
    }
    
    

}