package edu.ncsu.csc216.tracker.model;
import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.xml.*;

/**
 * Project 2, RequirementsTrackerModel
 * 
 * Class Description: Maintains the RequirementsList and handles 
 * CommandValues from the GUI. It also implements the Singleton 
 * design pattern. This means that only one instance of the 
 * RequirementsTrackerModel can ever be created. The Singleton 
 * pattern ensures that all parts of the RequirementsTrackerGUI 
 * are interacting with the same RequirementsTrackerModel at all times.
 * 
 * Note: RequirementsTrackerModel works with the XML files that contain 
 * the Requirements in a file when the application is not in use. Therefore, 
 * RequirementsTrackerModel works closely with the RequirementReader
 * and RequirementWriter classes in the RequirementsTrackerXML.jar. 
 * Separation of RequirementsList from RequirementsTrackerModel means 
 * each class can have a very specific abstraction: RequirementsList 
 * maintains the List of Requirements and RequirementsTrackerModel 
 * controls the creation and modification of (potentially many) 
 * RequirementsLists.
 * 
 * @author Bryan LeBlanc
 * 
 */
public class RequirementsTrackerModel {

    /**
     * The tracker model, designed so there will be only one model (singleton design).
     * The model can hold multiple lists of requirements
     */
    private static RequirementsTrackerModel singleton = null;
    
    
    /**
     * A list of requirement lists
     */
    private RequirementsList requirementList;
    
    /**
     * Constructor for the RequirementsTrackerModel class.  Implements a 
     * singleton design and is separated from RequirementsList so that 
     * there will be only one model tracking any number of lists.
     */
    private RequirementsTrackerModel() {
        requirementList = new RequirementsList();
    }
    
    /**
     * Returns the RequirementsTrackerModel
     * @return singleton the RequirementTrackerModel
     */
    public static RequirementsTrackerModel getInstance() { 
             if (singleton == null) {
                 singleton = new RequirementsTrackerModel();
             }
             return singleton; 

    }
    

    /**
     * Saves list of requirements to file
     * @param fileName name of the new file
     */
    public void saveRequirementsToFile(String fileName) {
        if(fileName == null) {
            throw new NullPointerException();
        }
        try {
            RequirementsWriter file = new RequirementsWriter(fileName);
            for (Requirement r: requirementList.getRequirements()) {
                    file.addReq(r.getXMLReq());
                   
            }
            file.marshal();
        } catch (RequirementIOException e) {
            throw new IllegalArgumentException("Unable to save requirement file.");
        }

    }
    
    /**
     * Loads a requirements list from .xml file
     * @param fileName from file
     */
    public void loadRequirementsFromFile(String fileName) {
        if(fileName == null) {
            throw new NullPointerException();
        }
        try {
            RequirementsReader file = new RequirementsReader(fileName);
            requirementList.addXMLReqs(file.getReqs());
        } catch (RequirementIOException e) {
            throw new IllegalArgumentException("Unable to load requirement file.");
        }
    }
    
    /**
     * Create new RequirementsList
     */
    public void createNewRequirementsList() {
        requirementList = new RequirementsList();
    }
    
    /**
     * Returns RequirementsList as a 2D array of objects
     * for ReqKeeper GUI Listings 
     * @return RequirementList 2D array of requirements
     */
    public Object[][] getRequirementListAsArray() {
        int count = 0; 
        // finds number of requirements for number of rows 
        int listRows = requirementList.getRequirements().size();
        Object[][] twoDList = new Object[listRows][3];
        for (Requirement r: requirementList.getRequirements()) {
            twoDList[count][0] = r.getRequirementId();
            twoDList[count][1] = r.getState().getStateName();
            twoDList[count][2] = r.getSummary();
            count++;
        }
        return twoDList;
    }
    
    /**
     * Returns Requirement by id number
     * @param id requirement id number
     * @return Requirement by requirement id 
     */
    public Requirement getRequirementById(int id) {
        return requirementList.getRequirementById(id);
    }
    
    /**
     * Carries out the given command on the given Requirement
     * @param id The index number of the desired Requirement
     * @param c The command to be executed
     */
    public void executeCommand(int id, Command c) {
        requirementList.getRequirementById(id).update(c);

    }
    
    /**
     * Discards the specified Requirement from the RequirementsList
     * @param id The index number of the desired Requirement
     */
    public void deleteRequirementById(int id) {
        requirementList.deleteRequirementById(id);

    }
    
    /**
     * Creates a new Requirement and adds it to the RequirementsList
     * @param summary Brief description of the Requirement
     * @param acceptanceTestId Required for subsequent testing
     */
    public void addRequirement(String summary, String acceptanceTestId) {
        requirementList.addRequirement(summary, acceptanceTestId);
        
    }

}
