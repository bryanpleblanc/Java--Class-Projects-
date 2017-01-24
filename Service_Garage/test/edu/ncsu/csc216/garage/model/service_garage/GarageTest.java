package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Class tests Garage Class methods
 * 
 * @author Bryan LeBlanc
 *
 */
public class GarageTest {
    
    Garage garage;
    
    /**
     * Sets up variables
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        garage = new Garage();
    }
    
    /**
     * Test garage constructor 
     */
    @Test
    public void testGarage() {
        assertEquals(8, garage.numberOfEmptyBays());

    }
    
    /**
     * Test BayOccupiedException()
     * Test BayCarMismatchException()
     * Test NoAvailableBayException()
     */
    public void testExceptions() {
        Exception bcme = new BayCarMismatchException();
        assertEquals("Bay Car Mismatch Exception", bcme.getLocalizedMessage());
        
        Exception boe = new BayOccupiedException();
        assertEquals("Bay Occupied Exception", boe.getLocalizedMessage());
        
        Exception nabe = new NoAvailableBayException();
        assertEquals("No Available Bay", nabe.getLocalizedMessage());
    }
    
    /**
     * Test add method
     */
    @Test
    public void testAddBay() {
        garage.addRepairBay();
        assertEquals(9, garage.numberOfEmptyBays());
        
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        assertEquals(12, garage.numberOfEmptyBays());
        
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        assertEquals(18, garage.numberOfEmptyBays());
        Vehicle vehicle;
        try {
            vehicle = new RegularCar("License", "Name", 0);
            try {
                garage.getBayAt(0).occupy(vehicle);
            } catch (BayOccupiedException e) {
                fail("Should not have thrown an exception");
            } catch (BayCarMismatchException e) {
                fail("Should not have thrown an exception");
            }
        } catch (BadVehicleInformationException e) {
            fail("Should not have thrown an exception");
        }
        
        assertEquals(17, garage.numberOfEmptyBays());
        
    }
    
    /**
     * Test getter for size 
     */
    @Test
    public void testGetSize() {
        assertEquals(8, garage.getSize());
        
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        garage.addRepairBay();
        assertEquals(14, garage.getSize());
    }
    
    /**
     * Test getter method for bay at index 
     */
    @Test
    public void testGetBayAt() {
        assertEquals(false, garage.getBayAt(0).isOccupied());
        
    }
    
    /**
     * Test release method 
     */
    @Test
    public void testRelease() {
        Vehicle vehicle;
        try {
            vehicle = new RegularCar("License", "Name", 0);
            try {
                garage.getBayAt(0).occupy(vehicle);
            } catch (BayOccupiedException e) {
                fail("Should not have thrown an exception");
            } catch (BayCarMismatchException e) {
                fail("Should not have thrown an exception");
            }
        } catch (BadVehicleInformationException e) {
            fail("Should not have thrown an exception");
        }
        
        assertTrue(garage.getBayAt(0).isOccupied());
        garage.release(0);
        assertFalse(garage.getBayAt(0).isOccupied());
    }
    

}
