package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Class tests ServiceBay Class methods
 * 
 * @author Bryan LeBlanc
 *
 */
public class ServiceBayTest {
    
    ServiceBay serviceBay;
    ServiceBay bay;
    Garage garage;
    Vehicle hybrid;
    
    /**
     * Sets up variables for use in testing
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        garage = new Garage();
        serviceBay = new ServiceBay();
        
    }
    
    /**
     * Tester GetBayID method 
     */
    @Test
    public void testGetBayIDPreffix() {
        ServiceBay.startBayNumberingAt101();
        bay = new ServiceBay("E");
        assertEquals("E01", bay.getBayID());
    }
    /**
     * Tester GetBayID method 
     */
    @Test
    public void testGetBayID() {
        assertEquals("108", garage.getBayAt(0).getBayID());
        assertEquals("106", garage.getBayAt(1).getBayID());
        assertEquals("105", garage.getBayAt(2).getBayID());
        assertEquals("103", garage.getBayAt(3).getBayID());
        assertEquals("102", garage.getBayAt(4).getBayID());
        assertEquals("E01", garage.getBayAt(5).getBayID());
        assertEquals("E04", garage.getBayAt(6).getBayID());
        assertEquals("E07", garage.getBayAt(7).getBayID());
    }

    /**
     * Tester isOccupied method 
     */
    @Test
    public void testIsOccupied() {
        assertFalse(garage.getBayAt(0).isOccupied());
        assertFalse(garage.getBayAt(1).isOccupied());
        assertFalse(garage.getBayAt(2).isOccupied());
        assertFalse(garage.getBayAt(3).isOccupied());
        assertFalse(garage.getBayAt(4).isOccupied());
        assertFalse(garage.getBayAt(5).isOccupied());
        assertFalse(garage.getBayAt(6).isOccupied());
        assertFalse(garage.getBayAt(7).isOccupied());

    }
    
    /**
     * Test Occupy method 
     * @throws BadVehicleInformationException 
     * @throws BayCarMismatchException 
     * @throws BayOccupiedException 
     */
    @Test
    public void testOccupy() throws BadVehicleInformationException, BayOccupiedException, BayCarMismatchException {
        hybrid = new  RegularCar("License", "Name", 0);
        assertFalse(serviceBay.isOccupied());
        serviceBay.occupy(hybrid);
        assertTrue(serviceBay.isOccupied());
    }
    
    /**
     * Test Release method 
     * @throws BadVehicleInformationException 
     * @throws BayCarMismatchException 
     * @throws BayOccupiedException 
     */
    @Test
    public void testRelease() throws BadVehicleInformationException, BayOccupiedException, BayCarMismatchException {
        hybrid = new RegularCar("License", "Name", 0);
        serviceBay.occupy(hybrid);
        assertEquals(hybrid, serviceBay.release());
        
    }
    
    /**
     * Test toString method
     */
    @Test
    public void testToString() {
        try {
            hybrid = new RegularCar("License", "Name", 0);
        } catch (BadVehicleInformationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertFalse(serviceBay.isOccupied());
        try {
            serviceBay.occupy(hybrid);
        } catch (BayOccupiedException | BayCarMismatchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue(serviceBay.isOccupied());
        assertEquals("109: License  Name", serviceBay.toString());
        //System.out.println(serviceBay.toString());
    }
    

}
