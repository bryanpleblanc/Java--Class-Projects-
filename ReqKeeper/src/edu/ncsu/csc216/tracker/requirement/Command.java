package edu.ncsu.csc216.tracker.requirement;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;

/**
 * The Command class creates objects that encapsulate user actions (or
 * transitions) that cause the state of a Requirement to update
 * 
 * @author Bryan LeBlanc
 */
public class Command {

    /**
     * Contains a brief description of the requirement
     */
    private String summary;
    
    /**
     * For subsequent test tracking in a separate system
     */
    private String acceptanceTestId;
    
    /**
     * The developer's name/id
     */
    private String developerId;
    
    /**
     * Time estimated for completion of the requirement
     */
    private String estimate;
    
    /**
     *  The priority of the requirement represented numerically
     */
    private int priority;

    /**
     * Possible user actions which trigger state transitions
     */
    private CommandValue c;
    
    /**
     * Reason the requirement was rejected
     */
    private Rejection r;
    

    /**
     * Possible user actions which trigger state transitions
     * 
     * Throws IllegalArgumentException if:
     * A Command with a null CommandValue parameter.
     * A Command with a CommandValue of ACCEPT and a null estimate, empty string estimate, or priority that is not between 1 and 3, inclusive.
     * A Command with a CommandValue of REJECT and a null Rejection.
     * A Command with a CommandValue of ASSIGN and either a null developer id or an empty string for the developer id.
     * A Command with a CommandValue of REVISE and a null summary, empty string summary, a null acceptance test, or an empty string acceptance test.
     * @param c command from user 
     * @param summary description of the requirement 
     * @param acceptanceTestId id for testing
     * @param priority numerical priority
     * @param estimate estimated time to complete
     * @param developerId name of the developer
     * @param r rejected reason
     * @throws IllegalArgumentException if parameters are equal to null
     */
    public Command(CommandValue c, String summary, String acceptanceTestId, int priority, 
            String estimate, String developerId, Rejection r) {

        if (c == null) {
            throw new IllegalArgumentException();
        } else if (c == CommandValue.ACCEPT && (estimate == null 
                || estimate.equalsIgnoreCase("") || priority <= 0 || priority > 3)) {
                    throw new IllegalArgumentException();      
        } else if(c == CommandValue.REJECT && r == null) {
            throw new IllegalArgumentException();
        }  else if ((c == CommandValue.ASSIGN) && (developerId == null || developerId.equalsIgnoreCase(""))) {
            throw new IllegalArgumentException();
        } else if ((c == CommandValue.REVISE) && (summary == null || summary.equalsIgnoreCase("")
                || acceptanceTestId == null || acceptanceTestId.equalsIgnoreCase(""))) {
            throw new IllegalArgumentException();
        }
        this.c = c;
        this.summary = summary;
        this.acceptanceTestId = acceptanceTestId;
        this.priority = priority;
        this.estimate = estimate;
        this.developerId = developerId;
        this.r = r;
    }
    
    /**
     * Returns the summary of the Requirement
     * @return summary description of the requirement
     */
    public String getSummary() {
        return summary;
    }
    
    /**
     * Returns the acceptanceTestId
     * @return acceptanceTestId for test tracking in a separate system
     */
    public String getAcceptanceTestId() {
        return acceptanceTestId;
    }
    
    /**
     * Returns the command value from the user
     * @return c Possible user actions which trigger state transitions
     */
    public CommandValue getCommand() {
        return c;
    }
    
    /**
     * Returns the time estimate for the requirement
     * @return estimate time estimated for completion of the requirement
     */
    public String getEstimate() {
        return estimate;
    }
    
    /**
     * Returns the priority number of the requirement 
     * @return priority number of the priority 
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * Returns the developer name/Id
     * @return developerId The developer's name/id
     */
    public String getDeveloperId() {
        return developerId;
    }
    
    /**
     * Returns the rejection reason from the user
     * @return r Reason the requirement was rejected
     */
    public Rejection getRejectionReason() {
        return r;
    }
    
}   
