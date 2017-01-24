package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Project 3: HybridElectricBay
 * 
 * Description: Concrete class that extends ServiceBay and represents 
 * a service bay that can accommodate hybrid/electric vehicles only.
 * 
 * @author Bryan LeBlanc
 *
 */
public class HybridElectricBay extends ServiceBay {
    
    /**
     * Constructs hybrid bay. Calls super class constructor.
     */
    public HybridElectricBay() {
        super("E");
    }
    
    /**
     * Occupies bay with parameter vehicle 
     * @param v vehicle occupying bay
     * @throws BayCarMismatchException when a regular car tries to enter a hybrid bay
     * @throws BayOccupiedException when a car tries to occupy and occupied bay
     */
    public void occupy(Vehicle v) throws BayCarMismatchException, BayOccupiedException {
        if(super.isOccupied()){
            throw new BayOccupiedException();
        }
        if (v instanceof RegularCar) {
            throw new BayCarMismatchException();
        }
        super.occupy(v);
        
    }

}
