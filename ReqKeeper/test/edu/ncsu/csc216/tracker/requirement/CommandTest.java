package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;

/**
 * Project 2, CommandTest 
 * Description: Tests the Command class
 * 
 * @author Bryan LeBlanc
 */
public class CommandTest {
    
    /** Command variable */
    private Command c;


    /**
     * Sets up strings and a command for use in testing
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        c = new Command(CommandValue.ACCEPT, "summary", "acceptanceTestId", 1, 
                "estimate", "developerId", Rejection.DUPLICATE);

    }
    
    /**
     * Test getter methods 
     */
    @Test
    public void testGetterMethods() {
        // test command
        assertEquals(CommandValue.ACCEPT, c.getCommand());
        // test summary
        assertEquals("summary", c.getSummary());
        // test acceptance test id
        assertEquals("acceptanceTestId", c.getAcceptanceTestId());
        // test priority 
        assertEquals(1, c.getPriority());
        //test estimate
        assertEquals("estimate", c.getEstimate());
        // test developer id
        assertEquals("developerId", c.getDeveloperId());
        // test rejection
        assertEquals(Rejection.DUPLICATE, c.getRejectionReason());
 
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With null CommandValue
     */
    @Test
    public void testCommandConstructorOne() {
        
        // tests CommandValue with null parameter 
        CommandValue nullCommand = null;
        try {
            c = new Command(nullCommand, "summary", "acceptanceTestId", 1, 
                    "estimate", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Accept and null estimate
     */
    @Test
    public void testCommandConstructorTwo() {
        
        try {
            c = new Command(CommandValue.ACCEPT, "summary", "acceptanceTestId", 1, 
                    null, "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Accept and blank estimate
     */
    @Test
    public void testCommandConstructorThree() {
        
        try {
            c = new Command(CommandValue.ACCEPT, "summary", "acceptanceTestId", 1, 
                    "", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Accept and blank estimate and priority less than 1
     */
    @Test
    public void testCommandConstructorFour() {
        
        try {
            c = new Command(CommandValue.ACCEPT, "summary", "acceptanceTestId", 0, 
                    "", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Accept and blank estimate and priority greater than 3
     */
    @Test
    public void testCommandConstructorFive() {
        
        try {
            c = new Command(CommandValue.ACCEPT, "summary", "acceptanceTestId", 4, 
                    "", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Rejection and null rejection
     */
    @Test
    public void testCommandConstructorSix() {
        Rejection rejection = null;
        try {
            c = new Command(CommandValue.REJECT, "summary", "acceptanceTestId", 1, 
                    "estimate", "developerId", rejection);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Assign and a null developer 
     */
    @Test
    public void testCommandConstructorSeven() {
        try {
            c = new Command(CommandValue.ASSIGN, "summary", "acceptanceTestId", 1, 
                    "estimate", null, Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Assign and a blank developer 
     */
    @Test
    public void testCommandConstructorEight() {
        try {
            c = new Command(CommandValue.ASSIGN, "summary", "acceptanceTestId", 1, 
                    "estimate", "", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Revise and null summary and null acceptancetestid
     */
    @Test
    public void testCommandConstructorNine() {
        try {
            c = new Command(CommandValue.REVISE, null, null, 1, 
                    "estimate", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Revise and blank summary and blank acceptancetestid
     */
    @Test
    public void testCommandConstructorTen() {
        try {
            c = new Command(CommandValue.REVISE, "", "", 1, 
                    "estimate", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test constructor for IllegalArgumentException's
     * With CommandValue Revise and blank summary and null acceptancetestid
     */
    @Test
    public void testCommandConstructorEleven() {
        try {
            c = new Command(CommandValue.REVISE, "", null, 1, 
                    "estimate", "developerId", Rejection.DUPLICATE);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
             System.out.println("Passed");            
        }
        
    }
    
    /**
     * Test enum CommandValue and Rejection
     */
    @Test
    public void testEnums() {
        Rejection[] list = Rejection.values();
        assertEquals(list[0], Rejection.ALREADY_IMPLEMENTED);
        assertEquals(Rejection.ALREADY_IMPLEMENTED, Rejection.valueOf("ALREADY_IMPLEMENTED"));
        
        CommandValue[] list2 = CommandValue.values();
        assertEquals(list2[0], CommandValue.ACCEPT);
        assertEquals(CommandValue.ACCEPT, CommandValue.valueOf("ACCEPT"));
        
    }
}
