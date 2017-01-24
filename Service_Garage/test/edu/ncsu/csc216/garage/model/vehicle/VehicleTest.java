package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class test Vehicle Class methods 
 * 
 * @author Bryan LeBlanc
 *
 */
public class VehicleTest {
    
    String license1;
    String license2;
    String name1;
    String name2;
    int tier1;
    int tier2;
    Vehicle regular;
    Vehicle hybrid;
    
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
        
        
        license2 = "FAST";
        tier2 = 3;
        name2 = "Doe, John";
        regular = new RegularCar(license2, name2, tier2);          
    }
    
    /**
     * Tests Vehicle constructor 
     * Vehicle(String license, String name, int tierIndex)
     * @throws BadVehicleInformationException
     */
    @Test
    public void testVehicle() throws BadVehicleInformationException {
        // tests blank license 
        try {
            new RegularCar(" ", name2, tier2);
            fail("Did not throw BadVehicleInformationException");
        } catch (BadVehicleInformationException e) {
            assertEquals("License cannot be blank.", e.getLocalizedMessage());
        }
        
        // test null name
        try {
            new RegularCar(license2, null, tier2);
            fail("Did not throw BadVehicleInformationException");
        } catch (BadVehicleInformationException e) {
            assertEquals("Owner name cannot be blank.", e.getLocalizedMessage());
        }
        
        // test blank name
        try {
            new RegularCar(license2, " ", tier2);
            fail("Did not throw BadVehicleInformationException");
        } catch (BadVehicleInformationException e) {
            assertEquals("Owner name cannot be blank.", e.getLocalizedMessage());
        }
        
        
        // test null license 
        try {
            new HybridElectricCar(null, name2, tier2);
            fail("Did not throw BadVehicleInformationException");
        } catch (BadVehicleInformationException e) {
            assertEquals("License cannot be blank.", e.getLocalizedMessage());
        }
        //String license = "  LIC 012   ";
        //System.out.println(license.trim());
        // test "  LIC 012   ", "   abc   ", 3
        try {
            new HybridElectricCar("  LIC 012   ", "   abc   ", tier2);
            fail("Did not throw BadVehicleInformationException");
        } catch (BadVehicleInformationException e) {
            assertEquals("Invalid tier.", e.getLocalizedMessage());
        }
        
        RegularCar rc = new RegularCar("    License     ", "    Name    ", tier2);
        assertEquals("License", rc.getLicense());
        assertEquals("Name", rc.getName());
        assertEquals(3, rc.getTier());
    }
    
    
    /**
     * Tests meetsFilter() method
     */
    @Test
    public void testMeetsFilter() {
        assertTrue(regular.meetsFilter("Doe"));
        assertTrue(regular.meetsFilter("DOE"));
        assertTrue(regular.meetsFilter(null));
        assertTrue(regular.meetsFilter("    "));
        assertTrue(regular.meetsFilter(""));
        assertFalse(regular.meetsFilter("Jerry"));

    }
    
    /**
     * Tests the toString method
     * format: <type> <tier> <license> <owner name>
     */
    @Test
    public void testToString() {
        assertTrue(regular.toString().startsWith("R"));
        assertTrue(hybrid.toString().startsWith("E"));
        assertTrue(regular.toString().contains(license2));
        
        assertEquals("R Platinum  FAST      Doe, John", regular.toString());
        assertEquals("E None      VA-121A   LeBlanc, Bryan", hybrid.toString());

    }
    
    /**
     * Test getName, getLicense, getTier
     */
    @Test
    public void testGetterMethods() {
        assertEquals("VA-121A", hybrid.getLicense());
        assertEquals("LeBlanc, Bryan", hybrid.getName());
        assertEquals(0, hybrid.getTier());

    }
    
    /**
     * Tests compareToTier method
     */
    @Test
    public void testCompareToTier() {
        assertEquals(-1, hybrid.compareToTier(regular));
        assertEquals(0, hybrid.compareToTier(hybrid));
        assertEquals(1, regular.compareToTier(hybrid));
        assertEquals(1, regular.compareToTier(null));
        
    }

}
