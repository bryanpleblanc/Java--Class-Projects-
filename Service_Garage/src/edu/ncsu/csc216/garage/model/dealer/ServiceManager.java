package edu.ncsu.csc216.garage.model.dealer;

import java.util.Scanner;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.util.SimpleIterator;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;
import edu.ncsu.csc216.garage.model.vehicle.VehicleList;

/**
 * Project 3: ServiceManager
 * 
 * Description: Concrete class that implements Manageable.
 * 
 * @author Bryan LeBlanc
 *
 */
public class ServiceManager implements Manageable {
    
    private Garage garage;
    private VehicleList vehicleList;
    
    /**
     * Constructor creates a new ServiceManager
     */
    public ServiceManager() {
        garage = new Garage();
        vehicleList = new VehicleList();
    }
    
    /**
     * Initializes the list of vehicles awaiting service. 
     * 
     * @param scan the scanner holding the vehicle data
     */
    public ServiceManager(Scanner scan) {
        garage = new Garage();
        vehicleList = new VehicleList(scan);
        
    }

    /**
     * Puts an item in the service wait list.
     * @param vehicleType type of vehicle (hybrid or regular)
     * @param license of the vehicle
     * @param name of the owner
     * @param tierIndex of the vehicle
     */
    public void putOnWaitingList(String vehicleType, String license, String name, int tierIndex) { 
        try {
            if (vehicleType.equalsIgnoreCase("e")) {
                vehicleList.add(new HybridElectricCar(license, name, tierIndex));
                
            } else {
                vehicleList.add(new RegularCar(license, name, tierIndex));
            }
        } catch (BadVehicleInformationException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Puts an item in the list of those awaiting service.
     * @param tier The item to put on waiting list
     */
    public void putOnWaitingList(Tiered tier) {
        vehicleList.add((Vehicle) tier);
        
    }

    /**
     * Returns the Tiered item meeting the given filter and position from the list of items awaiting service.
     * @param filter Filters the list of items considered
     * @param index Index in the filtered list of items
     * @return The item at the position in the list that meets the given filter
     */
    public Tiered getWaitingItem(String filter, int index) {
        return vehicleList.get(filter, index);
    }

    /**
     * Removes an item meeting the given filter from the list of items awaiting service.
     * @param filter  Filters the list of items considered for removal
     * @param index I in the filtered list of the item to be removed
     * @return  The item that was removed, or null if nothing was removed
     */
    public Tiered remove(String filter, int index) {
        return vehicleList.remove(filter, index);
    }

    /**
     * Fills the service bays with items awaiting service.
     */
    public void fillServiceBays() {
        int index = 0;
        // checks if a list is available 
        if(vehicleList != null) {
            SimpleIterator<Vehicle> cursor = vehicleList.iterator();
            Vehicle current;
            // Iterates through vehicle waiting list 
            while(cursor.hasNext() && garage.numberOfEmptyBays() > 0) {
                try {
                    // takes vehicle and adds it to service bay
                    current = (Vehicle) cursor.next();
                    current.pickServiceBay(garage);
                    // removes from vehicle waiting list 
                    vehicleList.remove("", index);
                } catch (NoAvailableBayException e) {
                    index++;
                }
            }
        }
        
    }

    /**
     * Releases the item from the given service bay.
     * @param bay  Location of the bay where the item is being serviced
     * @return  Item that was released from service, or null if the bay was empty
     */
    public Tiered releaseFromService(int bay) {
        return garage.release(bay);
    }

    /**
     * Adds a new service bay to the service area.
     */
    public void addNewBay() {
        garage.addRepairBay();
        
    }

    /**
     * A string representation of the list of items awaiting service that meet 
     *    the given filter.
     * @param filter  Determines which items are are of interest
     * @return String  String representation of the filtered list
     */
    public String printWaitList(String filter) {
        return vehicleList.filteredList(filter);
    }

    /**
     * A string representation of the list of bays in the service area.
     * @return  String representation of the service area 
     */
    public String printServiceBays() {
        String bay = "";
        // looks through garage list 
        for(int i = 0; i < garage.getSize(); i++) {
            // adds bay info and new line 
            bay += garage.getBayAt(i).toString() + "\n";
        }
        return bay;
        
    }
    

}
