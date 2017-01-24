package edu.ncsu.csc216.flight.plane;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for GateAgent Class
 * @author bryanleblanc
 *
 */
public class GateAgentTest {
    
    private final String testFile = "test-files/tiny-plane.txt";
    private GateAgent gateAgent;
    /**
     * Sets up the tests.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
      //create gate agent object using constructor 
        gateAgent = new GateAgent(testFile);
        
    }
    
    /**
     * Tests GateAgent constructor, getSeatMap(), and addPassenger() 
     */
    @Test
    public void testGetSeatMap() {
        //addPassenger(int reservationType, String passengerName, boolean windowSeat)
        // Reservation type (0 = first class, 1 = business class, 2 = economy)
        gateAgent.addPassenger(0, "LeBlanc, Bryan", true);
        gateAgent.addPassenger(1, "Doe, John", true);
        gateAgent.addPassenger(2, "Doe, Jane", true);

        assertEquals("1A", gateAgent.getSeatMap()[0][0]);
        assertTrue(gateAgent.getSeatOccupationMap()[0][0]);
        
        assertEquals("2A", gateAgent.getSeatMap()[1][0]);
        assertTrue(gateAgent.getSeatOccupationMap()[1][0]);
        
        assertEquals("6E", gateAgent.getSeatMap()[3][5]);
        assertTrue(gateAgent.getSeatOccupationMap()[3][5]);
        
        // tests if passenger is blank
        try {
            gateAgent.addPassenger(0, "", true);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
        // tests if passenger is null
        try {
            gateAgent.addPassenger(0, null, true);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("Passed"); 
            
        }

    }
    
    /**
     * Tests GetSeatOccupationMap()
     */
    @Test
    public void testGetSeatOccupationMap() {
      //gateAgent.getSeatOccupationMap();
        
        gateAgent.addPassenger(0, "LeBlanc, Bryan", true);
        gateAgent.addPassenger(0, "John, Smith", true);

        assertTrue(gateAgent.getSeatOccupationMap()[0][0]);
        assertFalse(gateAgent.getSeatOccupationMap()[0][1]);
        assertFalse(gateAgent.getSeatOccupationMap()[0][2]);
        assertFalse(gateAgent.getSeatOccupationMap()[0][3]);
        assertTrue(gateAgent.getSeatOccupationMap()[0][5]);

    }
    
    /**
     * Tests PrintReservations()
     */
    @Test
    public void testPrintReservations() {
      // adds 3 passengers 
      gateAgent.addPassenger(0, "Mellow, Wright", true);
      gateAgent.addPassenger(0, "Addium, Adam", true);
      gateAgent.addPassenger(0, "Zeffer, Smith", true);

      // test to make sure it print correctly and is sorted properly
      assertEquals("First Class    1E  Addium, Adam\nFirst Class    1A  Mellow, Wright\nFirst Class    1B  Zeffer, Smith\n", gateAgent.printReservations());
        
    }
    
    /**
     * Tests RemovePassenger()
     */
    @Test
    public void testRemovePassenger() {
       // adds 3 passengers 
      gateAgent.addPassenger(0, "Mellow, Wright", true);
      gateAgent.addPassenger(0, "Addium, Adam", true);
      gateAgent.addPassenger(0, "Zeffer, Smith", true);
      
      // test to remove passenger
      gateAgent.removePassenger(0);
      assertEquals("First Class    1A  Mellow, Wright\nFirst Class    1B  Zeffer, Smith\n", gateAgent.printReservations());
      
        
    }
        

        
    
}
