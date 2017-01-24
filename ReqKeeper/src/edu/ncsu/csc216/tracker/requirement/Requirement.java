package edu.ncsu.csc216.tracker.requirement;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;
/**
 * Project 2, Requirement
 * 
 * Class Description: Requirement represents a requirement 
 * tracked by our system. A requirement knows its requirementID,
 * state (updated from Commands propagated to it from the UI),
 * summary, estimate, developer, acceptanceTestId, and its 
 * Rejection reason. 
 * 
 * @author Bryan LeBlanc
 *
 */
public class Requirement {
    
    private int requirementId;
    private String summary;
    private String acceptanceTestId;
    private int priority; 
    private String estimate;
    private String developer;
    
    /**
     * String displayed when a requirement is in the submitted state 
     */
    public static final String SUBMITTED_NAME = "Submitted";
    /**
     * String displayed when a requirement is in the accepted state 
     */
    public static final String ACCEPTED_NAME = "Accepted";
    /**
     * String displayed when a requirement is in the rejected state 
     */
    public static final String REJECTED_NAME = "Rejected";
    /**
     * String displayed when a requirement is in the working state 
     */
    public static final String WORKING_NAME = "Working";
    /**
     * String displayed when a requirement is in the completed state 
     */
    public static final String COMPLETED_NAME = "Completed";
    /**
     * String displayed when a requirement is in the verified state 
     */
    public static final String VERIFIED_NAME = "Verified";
    /**
     * String displayed when a requirement is in the duplicate state 
     */
    public static final String DUPLICATE_NAME = "Duplicate";
    /**
     * String displayed when a requirement is in the infeasible state 
     */
    public static final String INFEASIBLE_NAME = "Infeasible";
    /**
     * Displayed when the requirement is rejected as too large 
     */
    public static final String TOO_LARGE_NAME = "Too Large";
    /**
     * Displayed when the requirement is rejected as out of scope
     */
    public static final String OUT_OF_SCOPE_NAME = "Out of Scope";
    /**
     * Displayed when the requirement is rejected as inappropriate
     */
    public static final String INAPPROPRIATE_NAME = "Inappropriate";
    /**
     * Displayed when the requirement is rejected as already implemented
     */
    public static final String ALREADY_IMPLEMENTED_NAME = "Already Implemented";
    
    private static int counter = 0;
  
    
    
    private RequirementState state;
    private Rejection rejectionReason = null;
    
    private final RequirementState submittedState = new SubmittedState();
    private final RequirementState acceptedState = new AcceptedState();
    private final RequirementState rejectedState = new RejectedState();
    private final RequirementState assignedState = new WorkingState();
    private final RequirementState completedState = new CompletedState();
    private final RequirementState verifiedState = new VerifiedState();

    /**
     * Requirement constructor. A new requirement starts in the 
     * submitted state, is assigned a Id number, and must have a summary 
     * and an acceptance test id.
     * @param summary for summary description 
     * @param acceptanceTestId for the test ID
     */
    public Requirement(String summary, String acceptanceTestId) {
        requirementId = counter;
        this.summary = summary;
        setAcceptanceTestId(acceptanceTestId);
        state = submittedState; //initializes state to submitted 
        incrementCounter(); // starts increment method
    }
    
    /**
     * Requirement constructor. Creates requirement from a xml file. 
     * Requirement id, state, summary, estimate, developer, acceptance 
     * test id, and rejection reason are assigned from file.
     * 
     * @param r file requirement 
     */
    public Requirement(Req r) {
        // same as 1st constructor
        this.requirementId = r.getId();
        this.summary = r.getSummary();
        setAcceptanceTestId(r.getTest());
        setState(r.getState());
        this.priority = r.getPriority();
        this.estimate = r.getEstimate();
        setDeveloper(r.getDeveloper());
        setRejectionReason(r.getRejection());
    }
    
    /**
     * Increments the counter by one
     */
    public void incrementCounter() {
        counter++;
    }
    
    /**
     * Returns the identification number of the requirement
     * @return requirementId requirement identification number
     */
    public int getRequirementId() {
        return requirementId;
    }
    
    /**
     * Returns the name of the current state the requirement is in
     * @return state name of the current state the requirement is in
     */
    public RequirementState getState() {
        return state;
    }
    
    /**
     * Sets a requirement's state to match the given name
     * @param stateName name of the requirement state
     */
    private void setState(String stateName) {
        
        if (stateName.equalsIgnoreCase(SUBMITTED_NAME)) {
            state = submittedState;
        } else if (stateName.equalsIgnoreCase(ACCEPTED_NAME)) {
            state = acceptedState;
        } else if (stateName.equalsIgnoreCase(REJECTED_NAME)) {
            state = rejectedState;
        } else if (stateName.equalsIgnoreCase(WORKING_NAME)) {
            state = assignedState;
        } else if (stateName.equalsIgnoreCase(COMPLETED_NAME)) {
            state = completedState;
        } else if (stateName.equalsIgnoreCase(VERIFIED_NAME)) {
            state = verifiedState;
        } else {
            throw new IllegalArgumentException("Invalid State");
        }
        
    }
    
    /**
     * Returns the priority number of the requirement 
     * @return priority number of the priority 
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * Returns the estimate from the state
     * @return estimate for the time to complete the requirement
     */
    public String getEstimate() {
        return estimate;
    }
    
    /**
     * Returns the reason for rejection of the requirement
     * @return rejection for rejection of the requirement
     */
    public Rejection getRejectionReason() {
        return rejectionReason;
    }
    
    /**
     * Returns the name of the reason for rejection of the requirement
     * @return name for rejection of the requirement
     */
    public String getRejectionReasonString() {
        if (rejectionReason == null) {
            return null;
        }
        return rejectionReason.name();
    }
    
    /**
     * Sets the rejection reason to the parameter reason
     * @param reason for rejecting the requirement
     */
    private void setRejectionReason(String reason) {
        if (state == rejectedState) {
            if (ALREADY_IMPLEMENTED_NAME.equalsIgnoreCase(reason)) {
                rejectionReason = Rejection.ALREADY_IMPLEMENTED;
            } else if (DUPLICATE_NAME.equalsIgnoreCase(reason)) {
                rejectionReason = Rejection.DUPLICATE;
            } else if (INFEASIBLE_NAME.equalsIgnoreCase(reason)) {
                rejectionReason = Rejection.INFEASIBLE;
            } else if (TOO_LARGE_NAME.equalsIgnoreCase(reason)) {
                rejectionReason = Rejection.TOO_LARGE;
            } else if (OUT_OF_SCOPE_NAME.equalsIgnoreCase(reason)) {
                rejectionReason = Rejection.OUT_OF_SCOPE;
            } else if (INAPPROPRIATE_NAME.equalsIgnoreCase(reason)) {
                rejectionReason = Rejection.INAPPROPRIATE;
            } else {
                throw new IllegalArgumentException("Invalid Rejection Reason");
            }
        } else {
            rejectionReason = null;
        }
    
    }
    
    /**
     * Helper method for the State classes
     * Steps to reject a requirement 
     * @param reason for rejection (from Command class)
     * @throws UnsuportedOperationException
     */
    private void reject(Rejection reason) {
        rejectionReason = reason;
        if (rejectionReason != null) {
            this.rejectionReason = reason;
            priority = 0;
            estimate = null;
            developer = null;
            setState(REJECTED_NAME);
        } else {
            throw new UnsupportedOperationException("Invalid Rejection Reason");
        }
        
    }
    
    /**
     * Returns the name of the developer
     * @return developer name of the developer
     */
    public String getDeveloper() {
        return developer;
    }
    
    /**
     * Returns a brief description of the requirement
     * @return summary description of the requirement
     */
    public String getSummary() {
        return summary;
    }
    
    /**
     * Returns the acceptance test Id
     * @return accetanceTestId acceptance test id
     */
    public String getAcceptanceTestId() {
        return acceptanceTestId;
    }
    
    /**
     * Assigns the acceptance test Id to the given acceptanceTestId
     * @param acceptanceTestId acceptance test id
     */
    public void setAcceptanceTestId(String acceptanceTestId) {
        this.acceptanceTestId = acceptanceTestId;
    }
    
    /**
     * Assigns the developer to the given developer
     * @param developer name of developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }
    
    /**
     * Accepts a command from the requirement's state
     * @param c from Command (gui/user)
     */
    public void update(Command c) {
        state.updateState(c);
    }
    
    /**
     * Returns information from the requirement in the xml file
     * @return r from xml file  
     */
    public Req getXMLReq() {
        Req r = new Req();
        r.setPriority(this.getPriority());
        r.setId(this.getRequirementId());
        r.setState(state.getStateName());
        r.setSummary(this.getSummary());
        r.setTest(this.getAcceptanceTestId());
        r.setEstimate(this.getEstimate());
        r.setDeveloper(this.getDeveloper());
        r.setRejection(this.getRejectionReasonString());
        return r;
        
    }
    
    /**
     * Sets the size of the requirement list.
     * 
     * Note: Used when creating a new RequirementList and
     * loading a RequirementList
     * 
     * @param x size of the requirement list
     */
    public static void setCounter(int x) {
        counter = x;
    }
    
    /**
     * Represents the submitted state.
     * 
     * New requirements start as submitted, and may become submitted after going
     * through the rejected state.  From this state a requirement may be
     * accepted with an estimate, or rejected with a reason.
     */
    private class SubmittedState implements RequirementState {
        
        /**
         * Constructor for a requirement in the submitted state
         * 
         * A requirement in the submitted state must have a reqId, 
         * a summary, and an acceptanceTestId.  The estimate, developer,
         * and rejection reason are all null
         * 
         * The constructor is empty to prevent instantiation by other classes
         */
        private SubmittedState() {
        }
        
        /**
         * Update the {@link Requirement} based on the given {@link Command}.
         * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
         * is not a valid action for the given state.  
         * @param c {@link Command} describing the action that will update the {@link Requirement}'s
         * state.
         * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
         * for the given state.
         */
        public void updateState(Command c) {
            if (c.getCommand() == CommandValue.ACCEPT) {
                setState(ACCEPTED_NAME);
                 priority = c.getPriority();
                //setPriority(c.getPriority());
                estimate = c.getEstimate();
            } else if (c.getCommand() == CommandValue.REJECT) {
                reject(c.getRejectionReason());
            } else {
                throw new UnsupportedOperationException("Invalid Command");
            }
        }   
        
        /**
         * Returns the name of the current state represented as a String
         * @return SUBMITTED_NAME String representing the Submitted State
         */
        public String getStateName() {
            return SUBMITTED_NAME;
        }
    
    }   
    
    /**
     * Represents the accepted state.
     * Requirements become accepted when a submitted requirement is
     * given an estimate.  An accepted requirement can either be rejected 
     * with a reason, or assigned with a developer name/Id
     */
    private class AcceptedState implements RequirementState {
        
        /**
         * Constructor for the accepted state.
         */
        private AcceptedState() {
        }
        
        /**
         * Update the {@link Requirement} based on the given {@link Command}.
         * @param c {@link Command} describing the action that will update the {@link Requirement}'s
         * state.
         * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
         * for the given state.
         */
        public void updateState(Command c) {
            if (c.getCommand() == CommandValue.ASSIGN) {
                setState(WORKING_NAME);
                developer = c.getDeveloperId();
            } else if (c.getCommand() == CommandValue.REJECT) {
                reject(c.getRejectionReason());
            } else {
                throw new UnsupportedOperationException("Invalid Command");
            }
        }

        /**
         * Returns the String value for the accepted state
         * @return ACCEPTED_NAME the String value for the accepted state
         */
        public String getStateName() {
            return ACCEPTED_NAME;
        }
    
    }   
    
    /**
     * Represents the working state.
     * Requirements that have been submitted become working
     * and are given a developer.
     * Working requirements can either be rejected with a reason, 
     * or completed.
     */
    private class WorkingState implements RequirementState {
        
        /**
         * Constructor for the working state. 
         */
        private WorkingState() {
        }
        
        /**
         * Update the {@link Requirement} based on the given {@link Command}.
         * @param c {@link Command} describing the action that will update the {@link Requirement}'s
         * state.
         * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
         * for the given state.
         */
        public void updateState(Command c) {
            if (c.getCommand() == CommandValue.COMPLETE) {
                setState(COMPLETED_NAME);
            } else if (c.getCommand() == CommandValue.REJECT) {
                reject(c.getRejectionReason());
            } else {
                throw new UnsupportedOperationException("Invalid Command");
            }
        
        }
        
        /**
         * Returns the String value of the working state
         * @return WORKING_NAME the String value of the working state
         */
        public String getStateName() {
            return WORKING_NAME;
        }
    
    }   
    
    /**
     * Represents the completed state.
     * Requirements become completed when implemented by the developer.
     * A completed requirement can pass testing and become Verified, 
     * fail testing and become reopened, or rejected with a rejection reason.
     */
    private class CompletedState implements RequirementState {
        
        /**
         * Constructor for the completed state. 
         */
        private CompletedState() {
        }
        
        /**
         * Update the {@link Requirement} based on the given {@link Command}. 
         * @param c {@link Command} describing the action that will update the {@link Requirement}'s
         * state.
         * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
         * for the given state.
         */
        public void updateState(Command c) {
            if (c.getCommand() == CommandValue.PASS) {
                setState(VERIFIED_NAME);
            } else if (c.getCommand() == CommandValue.FAIL) {
                setState(WORKING_NAME);
                //developer = null;
            } else if (c.getCommand() == CommandValue.ASSIGN) {
                setState(WORKING_NAME);
                developer = c.getDeveloperId();
            } else if (c.getCommand() == CommandValue.REJECT) {
                reject(c.getRejectionReason());
            } else {
                throw new UnsupportedOperationException("Invalid Command.");
            }
        }


        /**
         * Returns the String value for the completed state
         * @return COMPLETED_NAME the String value for the completed state
         */
        public String getStateName() {
            return COMPLETED_NAME;
        }
    
    }   
    
    /**
     * Represents the verified state.
     * Requirements become verified when the testing is passed.
     * A verified requirement may be rejected with a reason or reopened if 
     * it fails later testing.
     */
    private class VerifiedState implements RequirementState {
        
        /**
         * Constructor for the verified state.  
         * 
         * A verified requirement must have a developer must have values.  
         */
        private VerifiedState() {
        }
        
        /**
         * Update the {@link Requirement} based on the given {@link Command}. 
         * @param c {@link Command} describing the action that will update the {@link Requirement}'s
         * state.
         * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
         * for the given state.
         */
        public void updateState(Command c) {
            if (c.getCommand() == CommandValue.ASSIGN) {
                setState(WORKING_NAME);
                developer = c.getDeveloperId();  
            } else if (c.getCommand() == CommandValue.REJECT) {
                reject(c.getRejectionReason());
            } else {
                throw new UnsupportedOperationException("Invalid Command.");
            }

        }

        /**
         * Returns the String value for the verified state
         * @return VERIFIED_NAME the String value for the verified state
         */
        public String getStateName() {
            return VERIFIED_NAME;
        }
    
    }   
    
    /**
     * Represents the rejected state.
     * 
     * A requirement becomes rejected from any other state and is 
     * given a rejection reason. 
     */
    private class RejectedState implements RequirementState {
        
        /**
         * Constructor for the rejected state.
         *  The rejected requirement must can have a summary and 
         *  acceptance test Id.
         */
        private RejectedState() {
        }
        
        /**
         * Update the {@link Requirement} based on the given {@link Command}. 
         * @param c {@link Command} describing the action that will update the {@link Requirement}'s
         * state.
         * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
         * for the given state.
         */
        public void updateState(Command c) {
            if (c.getCommand() == CommandValue.REVISE) {
                    setState(SUBMITTED_NAME);
                    acceptanceTestId = c.getAcceptanceTestId();
                    summary = c.getSummary();
                    rejectionReason = null;

            } else {
                throw new UnsupportedOperationException("Invalid Command");
            }
        }

        /**
         * Returns the String value for the rejected state
         * @return REJECTED_NAME, the String for rejected state
         */
        public String getStateName() {
            return REJECTED_NAME;
        }
    
    }   

}
