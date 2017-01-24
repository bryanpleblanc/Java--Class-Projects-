package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Project 3: Garage 
 * 
 * Description: Keeps track of a set of service bays
 * 
 * @author Bryan LeBlanc
 *
 */
public class Garage {
    /** Max service bays */
    public static final int MAX_ROOMS = 30;
    /** Original number of service bays */
    public static final int DEFAULT_SIZE = 8;
    private int size;
    private int counter;
    
    private ServiceBay[] bayList;
    
    /**
     * Garage constructor; creates new garage 
     */
    public Garage() {
        size = 0;
        bayList = new ServiceBay[MAX_ROOMS];
        ServiceBay.startBayNumberingAt101();
        initRooms(DEFAULT_SIZE);
    }
    
    /**
     *  Helper method: construct the garage
     * @param numBay the number of service bays initially opened
     */
    private void initRooms(int size) {
        counter = 3;
        for(int i = 0; i < size; i++) {
            addRepairBay();
        }
    }
    
    /**
     * Opens a new repair bay. 
     * When adding a bay, method shifts elements
     * Makes sure 1/3 of all bays are Hybrid.
     * 
     */
    public void addRepairBay() {
        if(size < MAX_ROOMS){
            
            if(counter % 3 == 0) {
                // creates new hybrid bay
                bayList[size] = new HybridElectricBay();
                size++;
                counter++;
            } else {
                // shifts elements to right
                for (int j = size - 1; j >= 0; j--) {
                    bayList[j + 1] = bayList[j];
                }
                //creates new regular bay
                bayList[0] = new ServiceBay();
                size++;
                counter++;
            }
        }
    
    }
    
    /**
     * Getter method that returns the number of empty bays 
     * @return number of empty bays
     */
    public int numberOfEmptyBays() {
        int emptyBays = 0;
        for(int i = 0; i < size; i++) {

            if(!bayList[i].isOccupied()) {
                emptyBays++;
            }
        }
        return emptyBays;
    }
    
    /**
     * Getter method returns the bay at the specified index
     * @param index the index of the bay
     * @return the bay at the index 
     */
    public ServiceBay getBayAt(int index) {
        return bayList[index];
    }
    
    /**
     * Getter method returns number of open bays
     * @return size of the garage
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Method to finish (aka remove) a vehicle 
     * @param index of the vehicle being removed
     * @return the Vehicle removed
     */
    public Vehicle release(int index) {
        if(bayList[index] == null || !bayList[index].isOccupied()) {
            return null;
        }
        return bayList[index].release();
    }
    
}
