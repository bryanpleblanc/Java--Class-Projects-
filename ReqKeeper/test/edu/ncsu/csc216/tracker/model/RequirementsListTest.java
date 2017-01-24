package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * Tests the RequirementsList class
 * @author Bryan LeBlanc
 */
public class RequirementsListTest {

    /** Instance of RequirementsList to be tested */
    private RequirementsList rl;
    
    /** Requirement to be used in testing RequirementList */
    private Requirement r;
    
    /**
     * Sets up variables and objects for testing
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        rl = new RequirementsList();
        r = new Requirement("summary", "acceptance test id");

    }

    /**
     * Tests the addRequirement method of the RequirementsList class
     * verifies that the requirement added in setUp is as expected
     */
    @Test
    public void testAddRequirement() {
        rl.addRequirement("summary", "acceptanceTestId");
        List<Requirement> list = rl.getRequirements();
        Requirement test = list.get(0);
        assertEquals("summary", test.getSummary());
        assertEquals("acceptanceTestId", test.getAcceptanceTestId());

    }
    
    /**
    *  Tests the addXMLReqs method 
    */
   @Test
   public void testAddXMLReqs() {
       Req req = r.getXMLReq();
       List<Req> list = new ArrayList<Req>();
       list.add(req);
       rl.addXMLReqs(list);
       assertEquals("summary", rl.getRequirementById(0).getSummary());
       assertEquals("acceptance test id", rl.getRequirementById(0).getAcceptanceTestId());
   }
   
   /**
    *  Tests the excuteCommand method
    */
   @Test
   public void testExecuteCommand() {
       Command c = new Command(CommandValue.REJECT, null, null, 0, null, null, Rejection.DUPLICATE );
       rl.addRequirement("summary", "test");
       rl.executeCommand(1, c);
       
       Requirement test = rl.getRequirementById(1);
       assertEquals(Requirement.REJECTED_NAME, test.getState().getStateName());
       assertEquals("summary", test.getSummary());
       assertEquals("test", test.getAcceptanceTestId());
       assertEquals(null, test.getEstimate());
       assertEquals(null, test.getDeveloper());

   }
   
   /**
    *  Tests the deleteRequirementsById method
    */
   @Test
   public void testDeleteRequirementById() {
       rl.addRequirement("summary", "test");
       assertEquals(1, rl.getRequirements().size());
       
       rl.deleteRequirementById(1);
       assertEquals(0, rl.getRequirements().size());
   }
}
