package edu.ncsu.csc216.flight.plane;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for Seat Class
 * @author bryanleblanc
 *
 */
public class SeatTest {
    
    /**
     * Tests Seat methods
     */
    @Test
    public void testSeatMethods() {
        //constructor test 
        Seat seat = new Seat("1A", true, false); // tests constructor by adding a seat
        
        // test the location of the seat 
        assertEquals("1A", seat.getLocation());
        
        // occupy test
        seat.occupy(); // makes a seat occupied 
        assertTrue(seat.isOccupied()); // tests is occupied method
        
        // tests isAisle() and tests isWindow()
        assertFalse(seat.isAisleSeat());
        assertTrue(seat.isWindowSeat());
        
        // release test 
        seat.release(); // releases seat 
        assertFalse(seat.isOccupied()); // test to check if the seat was released 
        
        
     // tests constructor by adding a seat
        seat = new Seat("1D", false, false); 
        
        // tests setter methods 
        seat.setAisleSeat(true);
        seat.setWindowSeat(true);
        
        // tests isAisle() and tests isWindow()
        assertTrue(seat.isAisleSeat());
        assertTrue(seat.isWindowSeat());

        
    }

}


