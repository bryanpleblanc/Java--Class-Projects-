/**
 * 
 */
package edu.ncsu.csc216.flight.plane;

import java.io.File;
import java.util.Scanner;

/**
 * Project 1, Part 2: Flight
 * 
 * Description: Concrete class, corresponding to an airplane flight, 
 * that does the actual seat management. Flight implements 
 * SeatingManager and it has a seating chart named map, which is a 
 * two dimensional array of Seats.
 * 
 * @author Bryan LeBlanc
 *
 */
public class Flight implements SeatingManager {
    
    /** Sets coach capacity to 80% */
    private static final int COACH_CAP = 80;
    
    private String seatLabels;
    private int numRows;
    private int numColumns;
    //private int startFirstClass;
    //private int startBusiness;
    //private int startEconomy;
    private int mostRecentEconomyRow;
    //private int economySeatsReserved;
    //private int economyCapacity;
    
    private int fileFirstClass;
    private int fileStartBusiness;
    private int fileStartEconomy;
    
    
    private Seat[][] map;
    
    /**
     * Flight constructor takes file name as a parameter and process file
     * line by line and element by element and saves it into a 2D array
     * made of seat objects. 
     * @param fileName from fileName
     */
    public Flight(String fileName) {
        Scanner in = null;
        try {
            in = new Scanner(new File(fileName));
            
            
            // read in file and process here
            
            String stringRows = in.nextLine();
            numRows = Integer.parseInt(stringRows);
            
            seatLabels = in.nextLine();
            
            String stringAisles = in.nextLine();
            int aisles = Integer.parseInt(stringAisles);
            
            String stringFirstClassRows = in.nextLine();
            fileFirstClass = Integer.parseInt(stringFirstClassRows);
            
            String stringBusinessClassRows = in.nextLine();
            fileStartBusiness = Integer.parseInt(stringBusinessClassRows);
            
            String stringCoachRows = in.nextLine();
            fileStartEconomy = Integer.parseInt(stringCoachRows);
            
            // calculate columns 
            numColumns = aisles + seatLabels.length();
            
            // 2D array 
            map = new Seat [numRows][numColumns];
            
            int rowIndex = 0;
            
            // look through each line
            while (in.hasNextLine()) {
                int columnIndex = 0;
                String row = in.nextLine();
                Scanner columnSeat = new Scanner(row);
                
                // look through each line element 
                while (columnSeat.hasNext()) {
                    String location = columnSeat.next();
                    if(location.equals("XXX")) {
                        location = null;
                    }
                    Seat seat = new Seat(location, false, false);
                    
                    map[rowIndex][columnIndex] = seat;
                    columnIndex++;
                    
                }
                rowIndex++;
                columnSeat.close(); // just added 
            }
            

            // finding left window seats
            for (int i = 0; i < numRows; i++) { 
                
                 // left window seat
                Seat currentSeat = map[i][0];
                Seat rightSeat = map[i][1];
                if (currentSeat.getLocation() != null) {
                    currentSeat.setWindowSeat(true);
                }
                
                if (rightSeat.getLocation() == null) {
                     currentSeat.setAisleSeat(true);
                 }
                
                // right window seat
               currentSeat = map[i][numColumns - 1];
               Seat leftSeat = map[i][numColumns - 2];
               if (currentSeat.getLocation() != null){
                   currentSeat.setWindowSeat(true);
               }
               if (leftSeat.getLocation() == null) {
                    currentSeat.setAisleSeat(true);
                }
            }
         // finding non-window seats
            for (int i = 0; i < numRows; i++) { 
                for (int k = 1; k < numColumns - 1; k++) {
                    
                    // non window seats
                   Seat currentSeat = map[i][k];
                   Seat leftSeat = map[i][k - 1];
                   Seat rightSeat = map[i][k + 1];
                   if (currentSeat.getLocation() != null && 
                           (leftSeat.getLocation() == null || rightSeat.getLocation() == null)) {
                   
                        currentSeat.setAisleSeat(true);
                    }
                }

            }
            mostRecentEconomyRow = getStartEconomy();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        in.close();
        
    }
    
    /**
     * Reserve a seat for a business class reservation, applying the seat<br/>
     *    preference if possible. The chosen seat is marked occupied.
     * @param prefersWindow indicates seating preference
     * @return the reserved seat or null if no such seat is available
     */
    public String reserveBusinessSeat(boolean prefersWindow) {
        if (fileStartBusiness == fileStartEconomy) {
            return reserveEconomySeat(prefersWindow);
        }
        // Checks for unoccupied window or aisle seats
        for (int i = getStartBusiness(); i < getStartEconomy(); i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                //checks if seat is unoccupied and a window seat
                if (prefersWindow && !currentSeat.isOccupied() && currentSeat.isWindowSeat()) {
                    currentSeat.occupy();
                    return currentSeat.getLocation();
                  //checks if seat is unoccupied and an aisle seat
                } else if (!prefersWindow && !currentSeat.isOccupied() && currentSeat.isAisleSeat()) {
                    currentSeat.occupy();
                    return currentSeat.getLocation();
                }
            }
        } 
        // If no window seats are available 
        for (int i = getStartBusiness(); i < getStartEconomy(); i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                if (currentSeat.getLocation() != null && !currentSeat.isOccupied()) {
                    currentSeat.occupy();
                    return currentSeat.getLocation();
                }
            }
        }
        // if no seats are available 
        return null;
    }
        
    
    /**
     * Reserve a seat for a first class reservation, applying the seat<br/>
     *    preference if possible. The chosen seat is marked occupied.
     * @param prefersWindow indicates seating preference
     * @return the reserved seat or null if no such seat is available
     */
    public String reserveFirstClassSeat(boolean prefersWindow) {
        if (fileFirstClass == fileStartBusiness) {
            return reserveBusinessSeat(prefersWindow);
        }
        // Checks for unoccupied window or aisle seats
        for (int i = getStartFirstClass(); i < getStartBusiness(); i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                //checks if seat is unoccupied and a window seat
                if (prefersWindow  && !currentSeat.isOccupied() && currentSeat.isWindowSeat()) {
                    currentSeat.occupy();
                    return currentSeat.getLocation();
                  //checks if seat is unoccupied and an aisle seat
                } else if (!prefersWindow && !currentSeat.isOccupied() && currentSeat.isAisleSeat()) {
                    currentSeat.occupy();
                    return currentSeat.getLocation();
                }
            }
        } 
        // If no window seats are available 
        for (int i = getStartFirstClass(); i < getStartBusiness(); i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                if (currentSeat.getLocation() != null && !currentSeat.isOccupied()) {
                    currentSeat.occupy();
                    return currentSeat.getLocation();
                }
            }
        }
        // if no seats are available or null
        return null;
    }
    
    
    /**
     * Reserve a seat for an economy class reservation, applying the seat<br/> 
     *   preference if possible. The chosen seat is marked occupied.
     * @param prefersWindow indicates seating preference
     * @return the reserved seat or null if no such seat is available
     */
    public String reserveEconomySeat(boolean prefersWindow) {
        // Checks for unoccupied window or aisle seats
        for (int i = mostRecentEconomyRow; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                
                Seat currentSeat = map[i][j];
                
                //checks if seat is unoccupied and if its a window seat
                if (prefersWindow && currentSeat.getLocation() != null && !currentSeat.isOccupied() && currentSeat.isWindowSeat()) {
                    currentSeat.occupy();
                    mostRecentEconomyRow = i;
                    return currentSeat.getLocation();
                  //checks if seat is unoccupied and an aisle seat
                } else if (!prefersWindow && currentSeat.getLocation() != null && !currentSeat.isOccupied() && currentSeat.isAisleSeat()) {
                    currentSeat.occupy();
                    mostRecentEconomyRow = i;
                    return currentSeat.getLocation();
                }
                
            }
        } 
        
        for (int i = getStartEconomy(); i < mostRecentEconomyRow; i++) {
            for (int j = 0; j < numColumns; j++) {
                
                Seat currentSeat = map[i][j];
                
                //checks if seat is unoccupied and if its a window seat
                if (prefersWindow && currentSeat.getLocation() != null && !currentSeat.isOccupied() && currentSeat.isWindowSeat()) {
                    currentSeat.occupy();
                    mostRecentEconomyRow = i;
                    return currentSeat.getLocation();
                  //checks if seat is unoccupied and an aisle seat
                } else if (!prefersWindow && currentSeat.getLocation() != null && !currentSeat.isOccupied() && currentSeat.isAisleSeat()) {
                    currentSeat.occupy();
                    mostRecentEconomyRow = i;
                    return currentSeat.getLocation();
                }
                
            }
        } 
        // If no window or aisle seats are available 
        for (int i = mostRecentEconomyRow; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                if (currentSeat.getLocation() != null && !currentSeat.isOccupied()) {
                    currentSeat.occupy();
                    mostRecentEconomyRow = i;
                    return currentSeat.getLocation();
                }
            }
        }
        
        // If no window or aisle seats are available 
        for (int i = getStartEconomy(); i < mostRecentEconomyRow; i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                if (currentSeat.getLocation() != null && !currentSeat.isOccupied()) {
                    currentSeat.occupy();
                    mostRecentEconomyRow = i;
                    return currentSeat.getLocation();
                }
            }
        }
        // if no seats are available 
        return null;
    }
    
    /**
     * Get a seat map, where a seat in position [row, col] is represented by <br/>
     *    its row and seat label or by null if there is no seat at the position. <br/>
     * @return the seat map showing seat labels
     */
    public String[][] getSeatMap() {
        String[][] mapLocation = new String[numRows][numColumns];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                mapLocation[i][j] = map[i][j].getLocation();
            }
        }
        return mapLocation;
    }
    
    /**
     * Get a map showing which seats are occupied. <br/>
     *   A map element is true if its [row, col] correspond to an occupied seat <br/>
     *   or false if the seat is not occupied or if there is no seat at that position.
     * @return the seat map showing which seats are occupied
     */
    public boolean[][] getSeatOccupationMap(){ 
        boolean[][] mapOccupied = new boolean[numRows][numColumns];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                mapOccupied[i][j] = map[i][j].isOccupied();
            }
        }
        return mapOccupied;
    }
    
    /**
     * A boolean method that returns true if the Coach section is too full to allow 
     * more seat assignments for economy reservations. A check on the cap is required 
     * prior to attempting to assign a seat for an economy reservation 
     * @return boolean 
     */
    public boolean coachAtCap() {
        int coachSeatCount = 0;
        int occupiedCoachSeatCount = 0;
        for (int i = getStartEconomy(); i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Seat currentSeat = map[i][j];
                if (currentSeat.getLocation() != null) {
                    coachSeatCount++;
                    if (currentSeat.isOccupied()) {
                        occupiedCoachSeatCount++;
                    }
                }
            }
        }
        int percentage = (occupiedCoachSeatCount * 100) / coachSeatCount;
        if (percentage >= COACH_CAP) {
            return true;
        }   
        return false;
    }
    
    /**
     * Makes the seat at the location given by its parameter unoccupied. 
     * Required to delete a reservation
     * @param seatLocation from seatLocation
     */
    public void releaseSeat(String seatLocation) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (map[i][j].getLocation() != null) {
                    if (map[i][j].getLocation().equals(seatLocation)) {
                        map[i][j].release();
                    }
                }
            }
        }
        
    }
    
    /**
     * Gets the index of the first first-class row
     * @return index of the first-class row
     */
    private int getStartFirstClass() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {

                String loc = map[i][j].getLocation();
                if (loc != null) {
                    String row = loc.substring(0, loc.length() - 1);
                    if (Integer.parseInt(row) == fileFirstClass) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * Gets the index of the first business class row
     * @return index of the business class row
     */
    private int getStartBusiness() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {

                String loc = map[i][j].getLocation();
                if (loc != null) {
                    String row = loc.substring(0, loc.length() - 1);
                    if (Integer.parseInt(row) == fileStartBusiness) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }
        
    /**
     * Gets the index of the first economy class row
     * @return index of the economy class row
     */
    private int getStartEconomy() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {

                String loc = map[i][j].getLocation();
                if (loc != null) {
                    String row = loc.substring(0, loc.length() - 1);
                    if (Integer.parseInt(row) == fileStartEconomy) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }


}