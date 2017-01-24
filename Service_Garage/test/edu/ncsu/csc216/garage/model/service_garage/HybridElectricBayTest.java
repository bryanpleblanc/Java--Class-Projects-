package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Class tests HybridElectricBay Class methods 
 * 
 * @author Bryan LeBlanc
 *
 */
public class HybridElectricBayTest {

    HybridElectricBay hybrid;
    HybridElectricBay bay;
    
    /**
     * Standard Setup method 
     * @throws Exception
     */
    @Before 
    public void setUp() throws Exception {
        hybrid = new HybridElectricBay();
    }
    
    /**
     * Test HybridElectricBay constructor 
     */
    @Test
    public void testHybridElectricBay() {
        ServiceBay.startBayNumberingAt101();
        bay = new HybridElectricBay();
        assertEquals("E01", bay.getBayID());
        assertFalse(bay.isOccupied());
    }
    
    /**
     * Test Occupy method
     */
    @Test
    public void testOccupy() {
        // adding vehicle to unoccupied bay 
        Vehicle vehicle;
        assertFalse(hybrid.isOccupied()); 
        try {
            vehicle = new HybridElectricCar("License", "Name", 0);
            try {
                hybrid.occupy(vehicle);
            } catch (BayCarMismatchException | BayOccupiedException e) {
                e.printStackTrace();
            }
        } catch (BadVehicleInformationException e) {
            e.printStackTrace();
        }
        assertTrue(hybrid.isOccupied());  
        
        // tests trying to add a vehicle to an occupied bay
        Vehicle vehicle2;
        assertTrue(hybrid.isOccupied()); 
        try {
            vehicle2 = new RegularCar("License2", "Name2", 0);
            try {
                hybrid.occupy(vehicle2);
                fail("Should have thrown BayOccupiedException.");
            } catch (BayCarMismatchException | BayOccupiedException e) {
                assertEquals("Bay Occupied Exception", e.getLocalizedMessage());
            }
        } catch (BadVehicleInformationException e) {
            e.printStackTrace();
        }
        assertTrue(hybrid.isOccupied());  
    }
    /**
     * Test Occupy method with wrong bay type
     */
    @Test
    public void testOccupyBayMisMatch() {
        Vehicle regular;
        assertFalse(hybrid.isOccupied()); 
        try {
            regular = new RegularCar("License", "Name", 0);
            try {
                hybrid.occupy(regular);
                fail("Should have thrown BayCarMismatchException.");
            } catch (BayCarMismatchException | BayOccupiedException e) {
                assertEquals("Bay Car Mismatch Exception", e.getLocalizedMessage());
            }
        } catch (BadVehicleInformationException e) {
            e.printStackTrace();
        }
        assertFalse(hybrid.isOccupied());  

    }
}
