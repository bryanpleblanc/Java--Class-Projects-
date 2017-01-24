package edu.ncsu.csc216.tracker.requirement.enums;

/**
 * Project 2, CommandValue
 * 
 * Class Description: Possible command values for the GUI buttons
 * 
 * @author Bryan LeBlanc
 * 
 * Note: To access a value in the enumeration, use the 
 * enumeration name followed by the value (for example, 
 * CommandValue.ACCEPT). Enumerated types are essentially 
 * a name given to an integer value. Therefore, you can 
 * use primitive comparison operators (== and !=) to compare 
 * enumerated type variables (of the same enumerated type - 
 * don't try to compare a CommandValue with a Rejection). 
 * Enumerated types can also be the type of a variable. For 
 * example, Requirement's rejectionReason field is of type 
 * Rejection. 
 */
public enum CommandValue { 
    /**
     * Command value accept
     */
    ACCEPT, 
    /**
     * Command value reject
     */
    REJECT, 
    /**
     * Command value revise
     */
    REVISE, 
    /**
     * Command value assign
     */
    ASSIGN, 
    /**
     * Command value complete
     */
    COMPLETE, 
    /**
     * Command value pass
     */
    PASS, 
    /**
     * Command value fail 
     */
    FAIL 
}
