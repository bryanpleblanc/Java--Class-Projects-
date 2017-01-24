package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Class tests RegularCar Class methods 
 * 
 * @author Bryan LeBlanc
 *
 */
public class RegularCarTest {
    
    String license2;
    String name2;
    int tier2;
    RegularCar regular;
    
    /**
     * Sets up testing variables 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        license2 = "FAST";
        tier2 = 3;
        name2 = "Doe, John";
        regular = new RegularCar(license2, name2, tier2);
    }
    
    /**
     * Tests pickServiceBay method 
     */
    @Test
    public void testPickServiceBay() {
        Garage garage = new Garage();
        try {
            regular.pickServiceBay(garage);
            assertTrue(garage.getBayAt(0).isOccupied());
            assertEquals(garage.numberOfEmptyBays(), 7);
        } catch (NoAvailableBayException e) {
            fail("Should not have thrown exception");
        }
    }
    
    
    /**
     * Tests toString method
     */
    @Test
    public void testToString() {
        assertEquals("R Platinum  FAST      Doe, John", regular.toString());
    }

}
