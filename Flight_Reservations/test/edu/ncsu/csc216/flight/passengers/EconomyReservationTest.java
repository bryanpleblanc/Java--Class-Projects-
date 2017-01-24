package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * Test for EconomyReservation class
 * @author bryanleblanc
 *
 */
public class EconomyReservationTest {
    private final String testFile = "test-files/tiny-plane.txt";
    private Flight plane; 
    
    private final String testFileOneClass = "test-files/tiny-plane-1-class.txt";
    private Flight planeOneClass;

    private EconomyReservation econRes;

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
    public void testEconomyReservation() {
        
        econRes = new EconomyReservation("Passenger, One", planeOneClass , true); //1A
        econRes.findSeat();

        econRes = new EconomyReservation("Passenger, Two", planeOneClass, false); //1B
        econRes.findSeat();

        
        boolean[][] currentSeat = planeOneClass.getSeatOccupationMap();
        assertTrue(currentSeat[0][0]);
        assertTrue(currentSeat[0][1]);

    }
    
    /**
     *  Test with normal flight file
     */
    @Test
    public void testNomalClassSeating() {
    
        econRes = new EconomyReservation("Passenger, One", plane, true); 
        econRes.findSeat();
        
        assertEquals("6E", econRes.getSeat());
        
        assertEquals("Coach          6E  Passenger, One", econRes.stringForPrint());
    
        econRes = new EconomyReservation("Passenger, Two", plane, false); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Three", plane, false); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Four", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Five", plane, false); 
        econRes.findSeat();
        
        
        econRes = new EconomyReservation("Passenger, Six", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Seven", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Eight", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Nine", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Ten", plane, true); 
        econRes.findSeat();
        
        
        econRes = new EconomyReservation("Passenger, Eleven", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Twelve", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Thirteen", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Fourteen", plane, true); 
        econRes.findSeat();
        
        econRes = new EconomyReservation("Passenger, Fithteen", plane, true); 
        econRes.findSeat();
        
        // Business Class Full - null
        econRes = new EconomyReservation("Passenger, sixteen", plane, true); 
        econRes.findSeat();
        
        boolean[][] currentSeat = plane.getSeatOccupationMap();
        
        assertFalse(currentSeat[0][0]);
        assertFalse(currentSeat[0][1]);
        assertFalse(currentSeat[0][3]);
        assertFalse(currentSeat[0][4]);
        assertFalse(currentSeat[0][5]);
        
        assertFalse(currentSeat[1][0]);
        assertFalse(currentSeat[1][1]);
        assertFalse(currentSeat[1][3]);
        assertFalse(currentSeat[1][4]);
        assertFalse(currentSeat[1][5]);
        
        assertFalse(currentSeat[2][0]);
        assertFalse(currentSeat[2][1]);
        assertFalse(currentSeat[2][3]);
        assertFalse(currentSeat[2][4]);
        assertFalse(currentSeat[2][5]);
        
        assertTrue(currentSeat[3][5]);
    }

}
