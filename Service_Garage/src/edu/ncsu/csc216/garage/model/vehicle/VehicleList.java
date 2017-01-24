package edu.ncsu.csc216.garage.model.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.garage.model.util.SimpleIterator;

/**
 * Project 3: VehicleList
 * 
 * Description: List of vehicles waiting for service.
 * 
 * @author Bryan LeBlanc
 *
 */
public class VehicleList {
    
    private Node head;
    
    /**
     * Constructor of custom "LinkedList" of vehicles
     * If anything is wrong with the file elements Vehicle 
     * should catch and throw BadVehicleInformationException
     * @param scan scanner used to read in new values 
     */
    public VehicleList(Scanner scan) {
        if(scan == null) {
            return;
        }
        while(scan.hasNextLine()) {
            String vehicleType = "";
            int tier = 0;
            String license = "";
            String name = "";
            String row = scan.nextLine();
            @SuppressWarnings("resource")
            Scanner rowElement = new Scanner(row);
            
            if(rowElement.hasNext()) {
                vehicleType = rowElement.next();
            } 
            if(rowElement.hasNextInt()) {
                tier = rowElement.nextInt();
            } 
            if(rowElement.hasNext()) {
                license = rowElement.next();
            } 
            if (rowElement.hasNext()) {
                name = rowElement.nextLine();
            } 
            try {
                 if (vehicleType.equalsIgnoreCase("e")) {
                     add(new HybridElectricCar(license, name, tier));
                     //license, String name, int tierIndex) 
                 } else if (vehicleType.equalsIgnoreCase("r")) {
                     add(new RegularCar(license, name, tier));
                 } 
            } catch (BadVehicleInformationException e) {
                continue;
            }

        }

    }
    

    /**
     * Default constructor of a new "LinkedList" of vehicles
     */
    public VehicleList() {
        head = null;
    }
    
    /**
     * Retrieves an Iterator for the VehicleList
     * @return iterator for the VehicleList
     */
    public SimpleIterator<Vehicle> iterator() {
        return new Cursor();
    }
    
    /**
     * Removes the vehicle that appears in the filtered list 
     * at the given index 
     * @param filter that sorts the list
     * @param index of the vehicle removed
     * @return the vehicle removed
     */
    public Vehicle remove(String filter, int index) {
        // checks for empty list or index less than zero
        if (head == null || index < 0) {
            return null;
        } 
        Node current = head;
        Node previous = null;
        int count = 0; 
        
        while (current != null) {
            if (current.v.meetsFilter(filter)) {
                // checks if found correct list index 
                if (count == index) {
                    // first item 
                    if (previous == null) {
                        head = current.next;
                    // any other item
                    } else {
                        previous.next = current.next;
                    }
                    return current.v;
                }
                count++;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }
    
    /**
     * Adds a vehicle to the wait list
     * @param vehicle the vehicle to add to the list
     */
    public void add(Vehicle vehicle) {
        if (head == null) { 
            head = new Node(vehicle, null);
        } else if (vehicle.getTier() > head.v.getTier()) {
            head = new Node(vehicle, head);
        } else { 
            Node current = head.next;
            Node previous = head;
            while (current != null && vehicle.getTier() <= current.v.getTier()) {
               previous = current;
               current = current.next;
            }
            previous.next = new Node(vehicle, current);
        }
    }
    
    /**
     * Gets the vehicle at the provided index from the filter
     * @param filter that sorts the list
     * @param index of the vehicle 
     * @return the vehicle found
     */
    public Vehicle get(String filter, int index) {
        if (head == null || index < 0) {
            return null;
        } 
        List<Vehicle> filtered = new ArrayList<Vehicle>();
        int count = 0;
        Node current = head;
        while(current != null) {
            Vehicle vehicle = (Vehicle) current.v;
            if (vehicle.meetsFilter(filter)) {
                filtered.add(count, vehicle);
                count++;
            }
            current = current.next;
            
        }
        
        for (int i = 0; i < filtered.size(); i++) {
            if(i == index) {
                return filtered.get(i);
            }
        }
        return null;

    }
    
    /**
     * String representation of all vehicles that meet the filter. 
     * Each substring corresponding to a vehicle is terminated by a newline. [UC3, S3]
     * 
     * @param filter value to the list of vehicles 
     * @return string representation of vehicles which meet the filter value
     */
    public String filteredList(String filter) {
        if (head == null) {
            return "";
        }
        String filtered = "";
        Node current = head;
        while(current != null) {
            //TODO
            Vehicle vehicle = (Vehicle) current.v;
            if (vehicle.meetsFilter(filter)) {
                filtered += vehicle.toString() + "\n";
            }
            current = current.next;
        }
        return filtered;
    }
    
    /**
     * Uses two pointers to nodes. One points to the Node you are checking, 
     * the other points to the Node before it. Using the parameters, it finds 
     * a Node it wants and returns the Node before it.
     * 
     * The parameters are a filter and a position.  Basically you are traversing the 
     * filtered list with two pointers, with the first pointer looking for a vehicle 
     * at a certain position.  The second pointer is trailing the first one, so it is 
     * pointing to the trailing node.  
     * @param filter that sorts the list
     * @param index of the vehicle 
     * @return Node before node
     */
    @SuppressWarnings("unused")
    private Node findTrailingNode(String filter, int index) {
        if (head == null || index < 0) {
            return null;
        } 
        Node current = head;
        Node previous = null;
        int count = 0; 
        while (current != null) {
            if (current.v.meetsFilter(filter)) {
                // checks if found correct list index 
                if (count == index) {
                    return previous;
                }
                count++;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }
    
    /**
     * The inner Class of the VehicleList
     * 
     * @author Bryan LeBlanc
     */
    private class Node {

        Node next;
        Vehicle v;

        /**
         * The basic constructor for any time a new Node is added and 
         * @param data is the data of the Node
         * @param next is the Node following the current
         */
        public Node(Vehicle v, Node next){
            this.v = v;
            this.next = next;
        }
    }
    
    /**
     * Private inner class for creating an iterator for more efficient 
     * traversal of the linked list
     * @author Bryan LeBlanc 
     */
    private class Cursor implements SimpleIterator<Vehicle> {

        /**
         * Node to be used as an iterator
         */
        private Node cursor;

        /**
         * Constructor for Cursor creates a new reference pointing to the front of the list
         */
        private Cursor() {
            cursor = head;
        }

        /**
         * Lets the calling code know if there is a valid next element in the linked list of vehicles
         * @return Whether or not the Cursor has a next item, or you have reached the end of the list
         */
        public boolean hasNext() {
            return cursor != null;
        }

        /**
         * gives the  next vehicle and increments the cursor along the linked list of vehicles
         * @return v The vehicle part of the node
         */
        public Vehicle next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            Vehicle v = cursor.v;  //grabs the data
            cursor = cursor.next; //iterates
            return v;   //returns the data
        }
        
    }

}
