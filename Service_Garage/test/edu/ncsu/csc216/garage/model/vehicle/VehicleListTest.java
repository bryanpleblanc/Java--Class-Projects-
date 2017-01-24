package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Class tests VehicleList Class methods 
 * 
 * @author Bryan LeBlanc
 *
 */
public class VehicleListTest {
    
    String license1;
    String license2;
    String name1;
    String name2;
    int tier1;
    int tier2;
    Vehicle regular;
    Vehicle regular2;
    Vehicle regular3;
    Vehicle hybrid;
    
    VehicleList list;
    Scanner scan;
    
    /**
     * Sets up testing variables 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        
        list = new VehicleList();
        license1 = "VA-121A";
        tier1 = 0;
        name1 = "LeBlanc, Bryan";
        hybrid = new HybridElectricCar(license1, name1, tier1);
        
        
        license2 = "FAST";
        tier2 = 3;
        name2 = "Doe, John";
        regular = new RegularCar(license2, name2, tier2);  
        regular2 = new RegularCar("NC-451", "Dunn, Mike", 1);
        regular3 = new RegularCar("NC-890", "Hunt, Amanda", 2);
                
        
        
    }
    
    /**
     * Tests Vehicle constructor 
     * Vehicle(String license, String name, int tierIndex)
     * @throws BadVehicleInformationException
     */
    @Test
    public void testLoadFile() throws BadVehicleInformationException {
        try {
            scan = new Scanner(new File("cars.txt"));
            list = new VehicleList(scan);
            
            assertEquals(27, list.filteredList("").split("\n").length);
        } catch (FileNotFoundException e) {
            fail("Should not have thrown an exception");
        }
        
        System.out.println(list.filteredList(null));
        
    }
    
    /**
     * Tests Vehicle constructor 
     * Vehicle(String license, String name, int tierIndex)
     * @throws BadVehicleInformationException
     */
    @Test
    public void testLoadFile2() throws BadVehicleInformationException {
        try {
            scan = new Scanner(new File("cars2.txt"));
            list = new VehicleList(scan);
            assertEquals(4, list.filteredList("").split("\n").length);
        } catch (FileNotFoundException e) {
            fail("Should not have thrown an exception");
        }
        
        System.out.println(list.filteredList(null));
        
    }
    

    /**
     * Tests add vehicle 
     * @throws BadVehicleInformationException
     */
    @Test
    public void testAdd() throws BadVehicleInformationException {
        list.add(regular);
        assertTrue(list.filteredList(null).contains("Doe"));
        list.add(hybrid);
        assertTrue(list.filteredList(null).contains("LeBlanc"));
        list.add(regular2);
        assertTrue(list.filteredList(null).contains("Dunn"));
        list.add(regular3);
        assertTrue(list.filteredList(null).contains("Hunt"));
        //System.out.println(list.filteredList(null));
        
        assertEquals(hybrid, list.get("LeBlanc", 0));
        assertEquals(regular, list.get("Doe", 0));
        assertEquals(regular2, list.get("Dunn", 0));
        assertEquals(regular3, list.get("Hunt", 0));

    }
    
    /**
     * Test Get() method 
     */
    @Test
    public void testGet() {
        list.add(regular);
        list.add(hybrid);
        list.add(regular2);
        list.add(regular3);
        
        assertEquals(hybrid, list.get(null, 3));
        assertEquals(regular, list.get(null, 0));
        assertEquals(regular2, list.get(null, 2));
        assertEquals(regular3, list.get(null, 1));
        
        assertEquals(hybrid, list.get("", 3));
        assertEquals(regular, list.get("", 0));
        assertEquals(regular2, list.get("", 2));
        assertEquals(regular3, list.get("", 1));

    }
    
    /**
     * Test Remove() method 
     */
    @Test
    public void testRemove() {
        list.add(regular);
        list.add(hybrid);
        list.add(regular2);
        list.add(regular3);
        
        assertEquals(hybrid, list.get(null, 3));
        assertEquals(regular, list.get(null, 0));
        assertEquals(regular2, list.get(null, 2));
        assertEquals(regular3, list.get(null, 1));
        
        list.remove(null, 3);
        assertEquals(null, list.get(null, 3));
        assertEquals(regular, list.get(null, 0));
        assertEquals(regular2, list.get(null, 2));
        assertEquals(regular3, list.get(null, 1));
        
        list.remove(null, 0);
        assertEquals(null, list.get(null, 3));
        assertEquals(null, list.get(null, 2));
        assertEquals(regular2, list.get(null, 1));
        assertEquals(regular3, list.get(null, 0));

    }
    
    /**
     * Test FilteredList() method
     */
    @Test
    public void testFilteredList() {
        list.add(regular);
        list.add(hybrid);
        list.add(regular2);
        list.add(regular3);
        
        assertTrue(list.filteredList(null).contains("LeBlanc"));
        assertTrue(list.filteredList(null).contains("Doe"));
        assertTrue(list.filteredList(null).contains("Hunt"));
        assertTrue(list.filteredList(null).contains("Dunn"));
    }


}
