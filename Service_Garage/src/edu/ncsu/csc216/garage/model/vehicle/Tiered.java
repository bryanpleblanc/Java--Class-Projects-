package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Project 3: Tiered
 * 
 * Description: Interface that describes behaviors of 
 * objects that can be ordered by tiers (0, 1, 2 and 
 * so on, with higher tiers having higher numbers).
 * 
 * @author Bryan LeBlanc
 *
 */
public interface Tiered {
    
    /**
     * Compare the tier status of this object with another. 
     * Returns 0 if the two match, a negative number if the 
     * tier status of this object is less than the other's, 
     * a positive number if the tier status of this object is 
     * greater. Required for ordering. [UC1, S5], [UC2, S4]
     * 
     * @param t the tiered vehicle to compare to
     * @return 0 if the two match, a negative number if 
     * the tier status of this object is less than the other's, 
     * a positive number if the tier status of this object is greater. 
     */
    int compareToTier(Tiered t);

    /**
     * Getter method to return the tier of the object
     * @return tier of the object
     */
    int getTier();

}
