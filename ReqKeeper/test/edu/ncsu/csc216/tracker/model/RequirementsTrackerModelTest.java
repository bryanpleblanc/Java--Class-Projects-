package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;

/**
 * Tests the RequirementsTrackerModel class
 * @author Bryan LeBlanc
 */
public class RequirementsTrackerModelTest {

    private RequirementsTrackerModel rtm;
   

    /**
     * Sets up variables for use in testing
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        rtm = RequirementsTrackerModel.getInstance();
        rtm.createNewRequirementsList();
        rtm.addRequirement("summary", "acceptanceTestId");
        rtm.addRequirement("summary 1", "acceptanceTestId 1");
       
    }
    
    /**
     * Tests the saveRequirementsToFile method 
     * If filename is null.
     */
    @Test
    public void testSaveRequirementstoFile() {
        try {
            rtm.saveRequirementsToFile(null);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
             System.out.println("Passed");            
        }
    }
    
    /**
     * Tests the saveRequirementsToFile method, loadRequirementToFile method, 
     * and getAcceptanceTestId method
     * 
     */
    @Test
    public void testSaveandLoadRequirementstoFileTwo() {
        // check if requirements were added 
        assertEquals("acceptanceTestId", rtm.getRequirementById(0).getAcceptanceTestId());
        assertEquals("acceptanceTestId 1", rtm.getRequirementById(1).getAcceptanceTestId());
        rtm.saveRequirementsToFile("testSave.xml");
        rtm.loadRequirementsFromFile("testSave.xml");
        // ensure file was saved and loaded correctly
        assertEquals("acceptanceTestId", rtm.getRequirementById(0).getAcceptanceTestId());
        assertEquals("acceptanceTestId 1", rtm.getRequirementById(1).getAcceptanceTestId());

    }
    
    /**
     * Tests the loadRequirementsFromFile method 
     * If filename is null.
     */
    @Test
    public void testloadRequirementsFromFile() {
        try {
            rtm.loadRequirementsFromFile(null);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
             System.out.println("Passed");            
        }
    }
    
    /**
     * Test failure from teachers tests 
     */
    @Test
    public void testLoadFileTSFAILURE() {
        rtm.loadRequirementsFromFile("exp_req_completed.xml");
        assertEquals(1, rtm.getRequirementById(2).getPriority());
        
    }
    /**
     * Test CreateNewRequirementsList() and getRequirementListAsArray()
     */
    @Test
    public void testCreateNewRequirementsList() {
        Object[][] list = rtm.getRequirementListAsArray();
        assertEquals(0, list[0][0]);
        assertEquals("Submitted", list[0][1]);
        assertEquals("summary", list[0][2]);
        
        assertEquals(1, list[1][0]);
        assertEquals("Submitted", list[1][1]);
        assertEquals("summary 1", list[1][2]);
        
    }
    
    /**
     * Test deleteRequirementById method 
     */
    @Test
    public void testDeleteRequirementById()  {
        assertEquals("acceptanceTestId", rtm.getRequirementById(0).getAcceptanceTestId());
        assertEquals("acceptanceTestId 1", rtm.getRequirementById(1).getAcceptanceTestId());
        
        rtm.deleteRequirementById(0);
        Object[][] list = rtm.getRequirementListAsArray();
        assertEquals(1, list.length);
        assertEquals("summary 1", list[0][2]);
    }
    
    /**
     * Tests the executeCommand method 
     */
    @Test
    public void testExecuteCommand() {
        rtm.addRequirement("summary 2", "acceptanceTestId 2");
        Command c = new Command(CommandValue.ACCEPT, "summary 2", "acceptanceTestId 2", 1, "1 day", null, null);
        rtm.executeCommand(2, c);
        Object[][] list = rtm.getRequirementListAsArray();
        assertEquals(Requirement.ACCEPTED_NAME, rtm.getRequirementById(2).getState().getStateName());
        assertEquals("summary 2", list[2][2]);
        assertEquals("acceptanceTestId 2", rtm.getRequirementById(2).getAcceptanceTestId());

    }
}
