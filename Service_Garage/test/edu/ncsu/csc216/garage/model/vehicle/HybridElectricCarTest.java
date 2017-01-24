package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Class tests HybridElectricCar Class methods
 *  
 * @author Bryan LeBlanc
 *
 */
public class HybridElectricCarTest {
    
    String license1;
    String name1;
    int tier1;
    HybridElectricCar hybrid;
    
    /**
     * Sets up testing variables 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        license1 = "VA-121A";
        tier1 = 0;
        name1 = "LeBlanc, Bryan";
        hybrid = new HybridElectricCar(license1, name1, tier1);
    }
    
    /**
     * Tests pickServiceBay method 
     */
    @Test
    public void testPickServiceBay() {
        Garage garage = new Garage();
        try {
            hybrid.pickServiceBay(garage);
            assertTrue(garage.getBayAt(7).isOccupied());
            assertEquals(7, garage.numberOfEmptyBays());
        } catch (NoAvailableBayException e) {
            fail("Should not have thrown exception");
        }
    }
    
    /**
     * Tests toString method
     */
    @Test
    public void testToString() {
        assertEquals("E None      VA-121A   LeBlanc, Bryan", hybrid.toString());
    }

}
