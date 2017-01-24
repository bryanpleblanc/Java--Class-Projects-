package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * Project 2, RequirementTest
 * Description: Tests the Requirement class
 * 
 * @author Bryan LeBlanc
 */
public class RequirementTest {

    Requirement r;
    Command c;
    Requirement xml;
    Req req;
    int priority;
    String summary;
    String acceptanceTestId;
    String estimate;
    String developer;
    

    /**
     * Prepares a requirement for testing
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Requirement.setCounter(0);
        summary = "Summary 1";
        acceptanceTestId = "Acceptance Test ID";
        priority = 1;
        estimate = "1 day";
        developer = "Amanda";
      

        req = new Req();
        req.setId(1);
        req.setState(Requirement.SUBMITTED_NAME);
        req.setSummary(summary);
        req.setTest(acceptanceTestId);
        req.setEstimate(null);
        req.setDeveloper(null);
        req.setRejection(null);

        r = new Requirement(summary, acceptanceTestId);
        xml = new Requirement(req);

    }
    
    /**
     * Tests initial requirement getRequirementId method 
     */
    @Test
    public void testInitialRequirement() {
        assertEquals(r.getState().getStateName(), Requirement.SUBMITTED_NAME);
        assertEquals("Summary 1", r.getSummary());
        assertEquals("Acceptance Test ID", r.getAcceptanceTestId());
        assertEquals(0, r.getPriority());
        assertEquals(null, r.getEstimate());
        assertEquals(0, r.getRequirementId());
        assertEquals(null, r.getDeveloper());
        assertEquals(null, r.getRejectionReasonString());
    }
    
    /**
     * Tests setter methods
     */
    @Test
    public void testSetterMethods() {
        r.setAcceptanceTestId("new acceptance id");
        assertEquals("new acceptance id", r.getAcceptanceTestId());
        r.setDeveloper("Rick");
        assertEquals("Rick", r.getDeveloper());
    }
    
    /**
    * Test the getXMLReq method 
    */
   @Test
   public void testGetXMLReq() {
       Req req2 = xml.getXMLReq();
       assertEquals(req.getId(), req2.getId());
       assertEquals(req.getPriority(), req2.getPriority());
       assertEquals(req.getState(), req2.getState());
       assertEquals(req.getSummary(), req2.getSummary());
       assertEquals(req.getTest(), req2.getTest());
       assertEquals(req.getEstimate(), req2.getEstimate());
       assertEquals(req.getDeveloper(), req2.getDeveloper());
       assertEquals(req.getRejection(), req2.getRejection());
       
   }
   
   /**
    * Test the setCounter method
    */
   @Test
   public void testSetCounter() {
       Requirement counter = new Requirement("", "");
       assertEquals(1, counter.getRequirementId());
   }
   

   /**
   * Test Failure
   * Transition from Submitted to Accepted to Working to Completed to Working.
   */
   @Test
   public void testTSFAILURE() {
       c = new Command(CommandValue.ACCEPT, null, null, 3, "1 day", null, null);
       r.update(c);
       assertEquals(3, r.getPriority());
       c = new Command(CommandValue.ASSIGN, null, null, 0, null, "dev", null);
       r.update(c);
       c = new Command(CommandValue.COMPLETE, null, null, 0, null, null, null);
       r.update(c);
       c = new Command(CommandValue.ASSIGN, null, null, 0, null, "dev2", null);
       r.update(c);
       assertEquals("dev2", r.getDeveloper());
       
   }
   
   /**
   * Test Failure
   * Transition from Submitted to Accepted to Working to Completed to Working.
   */
   @Test
   public void testTSFAILURETwo() {
       c = new Command(CommandValue.ACCEPT, null, null, 3, "1 day", null, null);
       r.update(c);
       c = new Command(CommandValue.ASSIGN, null, null, 0, null, "dev", null);
       r.update(c);
       c = new Command(CommandValue.COMPLETE, null, null, 0, null, null, null);
       r.update(c);
       c = new Command(CommandValue.FAIL, null, null, 0, null, null, null);
       r.update(c);
       assertEquals("dev", r.getDeveloper());
       
   }
   
   /**
    * Tests the updateState Submitted method
    */
   @Test
   public void testUpdateStateSubmitted() {
       assertEquals(Requirement.SUBMITTED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.ACCEPT, null, null, 1, "day", null, null);
       r.update(c);
       assertEquals(1, r.getPriority());
       assertEquals("day", r.getEstimate());
       assertEquals(Requirement.ACCEPTED_NAME, r.getState().getStateName());
   }
   
   
   /**
    * Tests the updateState Accepted method
    */
   @Test
   public void testUpdateStateAccepted() {
       testUpdateStateSubmitted();

       assertEquals(Requirement.ACCEPTED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.ASSIGN, null, null, 0, null, "Bryan LeBlanc", null);
       assertEquals(1, r.getPriority());
       assertEquals("day", r.getEstimate());
       r.update(c);
       assertEquals(Requirement.WORKING_NAME, r.getState().getStateName());
   }
   
   /**
    * Tests the updateState Working method
    */
   @Test
   public void testUpdateStateWorking() {
       testUpdateStateAccepted();
       assertEquals(Requirement.WORKING_NAME, r.getState().getStateName());
       c = new Command(CommandValue.COMPLETE, null, null, 0, null, null, null);
       r.update(c);
       
       assertEquals(Requirement.COMPLETED_NAME, r.getState().getStateName());       
   }
   
   /**
    * Tests the updateState Completed method 
    */
   @Test
   public void testUpdateStateCompleted() {
       testUpdateStateWorking();
       assertEquals(Requirement.COMPLETED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.PASS, null, null, 0, null, null, null);
       r.update(c);
       assertEquals(Requirement.VERIFIED_NAME, r.getState().getStateName());        
   }
   
   /**
    * Tests the updateState Completed method 
    */
   @Test
   public void testUpdateStateCompletedTwo() {
       testUpdateStateWorking();
       assertEquals(Requirement.COMPLETED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.FAIL, null, null, 0, null, null, null);
       r.update(c);
       assertEquals(Requirement.WORKING_NAME, r.getState().getStateName());        
   }
   
   /**
    * Tests the updateState Completed method 
    */
   @Test
   public void testUpdateStateCompletedThree() {
       testUpdateStateWorking();
       assertEquals(Requirement.COMPLETED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.ASSIGN, null, null, 0, null, "Amanda", null);
       r.update(c);
       assertEquals(Requirement.WORKING_NAME, r.getState().getStateName());        
   }
   
   /**
    * Tests the updateState Verified method 
    */
   @Test
   public void testUpdateStateVerified() {
       testUpdateStateCompleted();
       assertEquals(Requirement.VERIFIED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.ASSIGN, null, null, 0, null, "Amanda", null);
       r.update(c);
       assertEquals(Requirement.WORKING_NAME, r.getState().getStateName());            
   }
   
   /**
    * Tests the updateState Verified method 
    */
   @Test
   public void testUpdateStateVerifiedTwo() {
       testUpdateStateCompleted();
       assertEquals(Requirement.VERIFIED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.REJECT, null, null, 0, null, null, Rejection.DUPLICATE);
       r.update(c);
       assertEquals(Requirement.REJECTED_NAME, r.getState().getStateName());            
   }
   
   /**
    * Tests the updateState Rejected method 
    */
   @Test
   public void testUpdateStateRejected() {
       testUpdateStateVerifiedTwo();
       assertEquals(Requirement.REJECTED_NAME, r.getState().getStateName());
       c = new Command(CommandValue.REVISE, "summary", "acceptanceTestId", 0, null, null , null);
       r.update(c);
       assertEquals(Requirement.SUBMITTED_NAME, r.getState().getStateName());
       
   }
    
    


}
