package edu.ncsu.csc216.garage.model.vehicle;


import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Project 3: Vehicle
 * 
 * Description: Abstract class representing a vehicle 
 * appropriate for servicing in the garage.
 * 
 * @author Bryan LeBlanc
 *
 */
public abstract class Vehicle implements Tiered {
    

    private String license;
    private String name;
    private int tierIndex;
    /** Names for number tiers */ 
    public static final String[] CUSTOMER_TIER = {"None", "Silver", "Gold", "Platinum"};
    /** Max character size for the license */
    public static final int MAX_LICENSE_SIZE = 8;
    
    /**
     * Constructor that creates a vehicle out of a license, owner name, and tier status.
     * 
     * @throws BadVehicleInformationException if the license of owner are not valid
     * @param license containing no whitespace of the vehicle's license plate number
     * @param name containing at least one non-whitespace character representing the owner's name
     * @param tierIndex representing the vehicle's preferred service tier status
     */
    public Vehicle(String license, String name, int tierIndex) throws BadVehicleInformationException {
        setLicense(license);
        setName(name);
        setTier(tierIndex);
    }
    
    /**
     * The vehicle picks a service bay
     * @throws NoAvailableBayException if there is not a bay open
     * @param garage with service bays for selection
     */
    public abstract void pickServiceBay(Garage garage) throws NoAvailableBayException;
    
    /**
     * Returns true if the filter is a prefix to the owner's name
     * The check case is insensitive. A filter of null or the null string
     * (e.g. "") would return true
     * @param filter value to match the prefix 
     * @return true if filter matches prefix 
     */
    public boolean meetsFilter(String filter) {
        if (filter == null) {
            return true;
        }
        filter = filter.trim().toLowerCase();
        if (filter.equals("") || filter.isEmpty()) {
            return true;
        } else if (name.toLowerCase().startsWith(filter)) {
            return true;
        }
        return false;

    }
    
    /**
     * String representation of the vehicle
     * @return string of the vehicle
     */
    public String toString() {
      //R Gold      NC123456  Doe, Jane
        return String.format("%-10s", CUSTOMER_TIER[getTier()]) + String.format("%-10s", getLicense()) + getName();
    }
    
    /**
     * Getter method for the owners name 
     * @return name of the vehicle's owner (Last, First)
     */
    public String getName() {
        return name;
    }
    
    /**
     * Getter method for the vehicles license
     * @return license plate number with no whitespace 
     */
    public String getLicense() {
        return license;
    }
    
    /**
     * Getter method returns the value of the vehicle's tier status 
     * 0-None, 1-Silver, 2-Gold, and 3-Platinum
     * @return vehicle's tier status
     */
    public int getTier() {
        return tierIndex;
    }
    
    /**
     * Compare the tier status of this object with another.
     * 
     * Returns 0 if the two match, a negative number if the tier status of 
     * this object is less than the other's, and a positive number if the tier 
     * status of this object is greater than the other's. 
     * Required for ordering.
     * 
     * Example: 3 (Platinum) - 1 (Silver) = 2 (positive)
     * 
     * @param tiered to be compared against
     * @return result the result of the comparison
     */
    public int compareToTier(Tiered tiered) { 
        if (tiered == null) {
            return 1;
        }
        if (tierIndex == tiered.getTier()) {
            return 0;
        } 
        if (tierIndex < tiered.getTier()) {
            return -1;
        } 
        return 1;
 
    }
    

    /**
     * Sets the Tier index number 
     * @param tierIndex number designated to the vehicle tier 
     * @throws BadVehicleInformationException if vehicle information is incorrect 
     */
    public void setTier(int tierIndex) throws BadVehicleInformationException {
        if (tierIndex < 0 || tierIndex > 3) {
            throw new BadVehicleInformationException();
        } else {
            this.tierIndex = tierIndex;
        }
    }
    
    /**
     * Sets the owners name
     * @param name
     */
    private void setName(String name) throws BadVehicleInformationException {
        if (name == null) {
            throw new BadVehicleInformationException("Owner name cannot be blank.");
        }
        name = name.trim();
        if (name.isEmpty()) {
            throw new BadVehicleInformationException("Owner name cannot be blank.");
        } else {
        this.name = name;
        }
    }
    
    /**
     * Sets the license plate number 
     * @param license
     */
    private void setLicense(String license) throws BadVehicleInformationException {
        if (license == null) {
            throw new BadVehicleInformationException("License cannot be blank.");
        }

        //String newLicense = "";
        license = license.trim();
        //traverses through license string 
        for (int i = 0; i < license.length(); i++) {
            // checks for spaces after trim function
            if (license.charAt(i) == ' ') {
                throw new BadVehicleInformationException();
                //newLicense += license.charAt(i);
            }
        } 
        if (license.length() > MAX_LICENSE_SIZE) {
            throw new BadVehicleInformationException("License cannot be more than 8 characters.");
        } else if (license.isEmpty()) {
            throw new BadVehicleInformationException("License cannot be blank.");
        } else {
            this.license = license;
        }
    }

}
