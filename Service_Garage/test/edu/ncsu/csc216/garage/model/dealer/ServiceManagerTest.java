package edu.ncsu.csc216.garage.model.dealer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Class Tests ServiceManager methods
 * 
 * @author Bryan LeBlanc
 *
 */
public class ServiceManagerTest {
    
    ServiceManager sm;
    Vehicle regular;
    Vehicle hybrid;

    /**
     * Standard setUp method 
     * @throws BadVehicleInformationException 
     */
    @Before
    public void setUp() throws BadVehicleInformationException {
        regular = new RegularCar("R-Lice", "R-Name", 0);
        hybrid = new HybridElectricCar("H-Lice", "H-Name", 1);
        sm = new ServiceManager();
    }
    
    /**
     * Tests ServiceManager constructor 
     */
    @Test
    public void testServiceManager() {
        //System.out.println(sm.printServiceBays());
        //System.out.println(sm.printWaitList(""));
        assertTrue(sm.printServiceBays().contains("108: EMPTY"));
        assertTrue(sm.printWaitList(null).contains(""));
    }
    
    /**
     * Test putOnWaitingList method
     */
    @Test
    public void testPutOnWaitingList() {
        sm.putOnWaitingList("R", "License3", "Name3", 3);
        sm.putOnWaitingList("R", "License2", "Name2", 2);
        sm.putOnWaitingList("R", "License1", "Name1", 1);
        sm.putOnWaitingList("R", "License0", "Name0", 0);
        //System.out.println(sm.getWaitingItem("", 0));
        //System.out.println(sm.getWaitingItem("", 1));
        //System.out.println(sm.getWaitingItem("", 2));
        //System.out.println(sm.getWaitingItem("", 3));
        
        assertTrue(sm.printWaitList("").contains("Name3"));
        assertTrue(sm.printWaitList("").contains("Name2"));
        assertTrue(sm.printWaitList("").contains("Name1"));
        assertTrue(sm.printWaitList("").contains("Name0"));
    }
    
    /**
     * Test putOnWaitingList method with only tiered parameter 
     * Also tests getWaitingListItem
     */
    @Test
    public void testPutOnWaitingListTiered() {
        sm.putOnWaitingList(regular);
        sm.putOnWaitingList(hybrid);
        assertEquals(hybrid, sm.getWaitingItem("", 0));
        assertEquals(regular, sm.getWaitingItem("", 1));
    }
    
    /**
     * Tests remove method 
     */
    @Test
    public void testRemove() {
        sm.putOnWaitingList(regular);
        sm.putOnWaitingList(hybrid);
        assertEquals(hybrid, sm.getWaitingItem("", 0));
        assertEquals(regular, sm.getWaitingItem("", 1));
        assertEquals(hybrid, sm.remove("", 0));
        assertEquals(regular, sm.remove("", 0));
    }
    
    /**
     * Tests FillServiceBays method 
     * Also tests printServiceBay
     */
    @Test
    public void testFillServiceBays() {
        assertFalse(sm.printServiceBays().contains("R-Name"));
        assertFalse(sm.printServiceBays().contains("H-Name"));
        
        sm.putOnWaitingList(regular);
        sm.putOnWaitingList(hybrid);
        sm.fillServiceBays();
        assertTrue(sm.printServiceBays().contains("R-Name"));
        assertTrue(sm.printServiceBays().contains("H-Name"));
    }
    
    /**
     * Tests release method 
     * Also tests printServiceBay
     */
    @Test
    public void testRelease() {
        assertFalse(sm.printServiceBays().contains("R-Name"));
        assertFalse(sm.printServiceBays().contains("H-Name"));
        
        sm.putOnWaitingList(regular);
        sm.putOnWaitingList(hybrid);
        sm.fillServiceBays();
        assertTrue(sm.printServiceBays().contains("R-Name"));
        assertTrue(sm.printServiceBays().contains("H-Name"));
        
        //System.out.println(sm.printServiceBays());
        assertEquals(regular, sm.releaseFromService(0));
        assertFalse(sm.printServiceBays().contains("R-Name"));
        
        assertEquals(hybrid, sm.releaseFromService(7));
        assertFalse(sm.printServiceBays().contains("H-Name"));
    }
    
    /**
     * Tests adding new service bay 
     * Also tests printServiceBay
     */
    @Test
    public void testAddBay() {
        assertFalse(sm.printServiceBays().contains("109"));
        sm.addNewBay();
        assertTrue(sm.printServiceBays().contains("109"));
        
        assertFalse(sm.printServiceBays().contains("E10"));
        sm.addNewBay();
       //System.out.println(sm.printServiceBays());
        assertTrue(sm.printServiceBays().contains("E10"));
        sm.addNewBay();
        sm.addNewBay();
        sm.addNewBay();
        assertTrue(sm.printServiceBays().contains("111"));
        assertTrue(sm.printServiceBays().contains("112"));
        assertTrue(sm.printServiceBays().contains("E13"));
    }

    
}
