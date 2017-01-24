package edu.ncsu.csc216.tracker.requirement.enums;

/**
 * Project 2, Rejection
 * 
 * Class Description: Possible reasons for rejection 
 * of a requirement
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
public enum Rejection { 
    /**
     * Rejection for already implemented
     */
    ALREADY_IMPLEMENTED, 
    /**
     * Rejection for duplicate
     */
    DUPLICATE, 
    /**
     * Rejection for infeasible
     */
    INFEASIBLE, 
    /**
     * Rejection for too large
     */
    TOO_LARGE, 
    /**
     * Rejection for out of scope
     */
    OUT_OF_SCOPE, 
    /**
     * Rejection for inappropriate
     */
    INAPPROPRIATE 
}
