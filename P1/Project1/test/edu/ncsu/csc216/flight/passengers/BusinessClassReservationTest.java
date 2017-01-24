package edu.ncsu.csc216.flight.passengers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.flight.plane.Flight;

/**
 * BusinessClassReservation class Test 
 * @author bryanleblanc
 *
 */
public class BusinessClassReservationTest {
    
    private final String testFile = "test-files/tiny-plane.txt";
    private Flight plane;
    
    
    private final String testFileOneClass = "test-files/tiny-plane-1-class.txt";
    private Flight planeOneClass;

    private BusinessClassReservation busClassRes;
    
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
    public void testBusinessClassReservationMethods() {
        
        busClassRes = new BusinessClassReservation("Passenger, One", planeOneClass , true); //1A
        busClassRes.findSeat();

        busClassRes = new BusinessClassReservation("Passenger, Two", planeOneClass, false); //1B
        busClassRes.findSeat();

        //firstClassRes = new FirstClassReservation("Passenger, Three", planeOneClass, true); //2A
        //firstClassRes.findSeat();
        
        //firstClassRes = new FirstClassReservation("Passenger, Three", planeOneClass, true); //2E
        //firstClassRes.findSeat();
        
        //firstClassRes = new FirstClassReservation("Passenger, Three", planeOneClass, true); //3A
        //firstClassRes.findSeat();
        
        boolean[][] currentSeat = planeOneClass.getSeatOccupationMap();
        assertTrue(currentSeat[0][0]);
        assertTrue(currentSeat[0][1]);
        //assertTrue(currentSeat[1][0]);
        //assertTrue(currentSeat[1][5]);
        //assertTrue(currentSeat[2][0]);
        //assertTrue(currentSeat[2][5]);
    }
    

    /**
     * Test with normal plane file 
     */
    @Test
    public void testNomalClassSeating() {
    
        busClassRes = new BusinessClassReservation("Passenger, One", plane, true); 
        busClassRes.findSeat();
        
        assertEquals("2A", busClassRes.getSeat());
        
        assertEquals("Business       2A  Passenger, One", busClassRes.stringForPrint());
    
        busClassRes = new BusinessClassReservation("Passenger, Two", plane, false); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Three", plane, false); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Four", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Five", plane, false); 
        busClassRes.findSeat();
        
        
        busClassRes = new BusinessClassReservation("Passenger, Six", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Seven", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Eight", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Nine", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Ten", plane, true); 
        busClassRes.findSeat();
        
       // should be null - Bus Class full
        
        busClassRes = new BusinessClassReservation("Passenger, Eleven", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Twelve", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Thirteen", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Fourteen", plane, true); 
        busClassRes.findSeat();
        
        busClassRes = new BusinessClassReservation("Passenger, Fithteen", plane, true); 
        busClassRes.findSeat();
        
        // Business Class Full - null
        busClassRes = new BusinessClassReservation("Passenger, sixteen", plane, true); 
        busClassRes.findSeat();
        
        boolean[][] currentSeat = plane.getSeatOccupationMap();
        
        assertFalse(currentSeat[0][0]);
        assertFalse(currentSeat[0][1]);
        assertFalse(currentSeat[0][3]);
        assertFalse(currentSeat[0][4]);
        assertFalse(currentSeat[0][5]);
        
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
