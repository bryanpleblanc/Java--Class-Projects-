package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Project 3: HybridElectricCar
 * 
 * Description:  Concrete class representing a hybrid or electric car.
 * 
 * @author Bryan LeBlanc
 *
 */
public class HybridElectricCar extends Vehicle {
    
    /**
     * HybridElectricCar constructor 
     * @param license is the license of the car
     * @param name name of the owner
     * @param tierIndex is the tier number 
     * @throws BadVehicleInformationException if vehicle is incorrect
     */
    public HybridElectricCar(String license, String name, int tierIndex) throws BadVehicleInformationException {
        super(license, name, tierIndex);
    }

    /** 
     * The vehicle picks a service bay
     * @param garage is the garage where the car will occupy
     * @throws NoAvailableBayException when there are no available service bays for the car to enter
     */
    public void pickServiceBay(Garage garage) throws NoAvailableBayException {
        boolean unoccupied = true;
        
        // traverses through garage list 
        for(int i = garage.getSize() - 1; i >= 0; i--){
            // checks if bay is occupied 
            if(!garage.getBayAt(i).isOccupied()){
                try {
                    // occupies bay at location 
                    garage.getBayAt(i).occupy(this);
                    unoccupied = false;
                    break;
                } catch (BayCarMismatchException e) {
                    continue;
                } catch (BayOccupiedException e) {
                    continue;
                }
            }
        }
        // if no bay is found 
        if(unoccupied){
            throw new NoAvailableBayException();
        }
  
    }
    
    /**
     * Returns a String representation of the HybridElectricCar
     * @return String representation of the HybridElectricCar
     */
    public String toString(){
        String toReturn = "E " + super.toString();
        return toReturn;
    }

}
