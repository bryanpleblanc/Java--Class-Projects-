package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Project 3: RegularCar
 * 
 * Description: Concrete class representing an ordinary 
 * gasoline or diesel vehicle.
 * 
 * @author bryanleblanc
 *
 */
public class RegularCar extends Vehicle {
    

    
    /**
     * RegularCar constructor 
     * @param license is the license of the car
     * @param name is the name of the owner of the car
     * @param tierIndex is the tier index of the car
     * @throws BadVehicleInformationException if vehicle is incorrect
     */
    public RegularCar(String license, String name, int tierIndex) throws BadVehicleInformationException {
        super(license, name, tierIndex);

    }

    /** 
     * The vehicle picks a service bay
     * @param garage is the garage where the car will occupy
     * @throws NoAvailableBayException when there are no available service bays for the car to enter
     */
    public void pickServiceBay(Garage garage) throws NoAvailableBayException{
        boolean unoccupied = true;
        
        if(unoccupied){
            // traverses through garage list 
            for(int i = 0; i < garage.getSize(); i++){
                // checks if hev bay is available
                if(garage.getBayAt(i).getBayID().charAt(0) != 'E'){
                    try {
                        // occupies bay at list location 
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
        }
        // if no bay is available 
        if(unoccupied){
            throw new NoAvailableBayException();
        }
    
    }
    
    /**
     * Returns a String representation of the RegularCar
     * @return String representation of the RegularCar
     */
    public String toString(){
        String toReturn = "R " + super.toString();
        return toReturn;
    }

}
