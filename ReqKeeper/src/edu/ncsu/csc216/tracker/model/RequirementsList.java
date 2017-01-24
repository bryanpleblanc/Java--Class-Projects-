package edu.ncsu.csc216.tracker.model;
import java.util.ArrayList;
import java.util.List;
import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * Project 2, RequirmentsList
 * 
 * Class Description: Maintains the list of Requirements.
 * 
 * @author Bryan LeBlanc
 */
public class RequirementsList {

    /**
     * A list of requirements
     */
    private List<Requirement> requirementList;
    
    /**
     * Constructor for the RequirementsList class.  
     * RequirementsList is an array of Requirements
     */
    public RequirementsList() {
        //Initialize ArrayList
        requirementList = new ArrayList<Requirement>();
        // set counter to 0 for the new list 
        Requirement.setCounter(0);
    }
    
    /**
     * Adds a requirement to the current list given a summary and test id.
     * 
     * @param summary description of the requirement
     * @param acceptanceTestId testing id
     * @return requirementId requirement id
     */
    public int addRequirement(String summary, String acceptanceTestId) {
        // adds a new requirement
        Requirement r = new Requirement(summary, acceptanceTestId);
        // adds the requirement to the list
        requirementList.add(r);
        
        return r.getRequirementId();
    }
    
    /**
     * Read the XML file to create new requirements list.
     * Finds the larger of max and the new requirementID incremented by one
     * @param list list of requirements
     */
    public void addXMLReqs(List<Req> list) {
        int max = 0;
        for (Req r: list) {
            Requirement newReq = new Requirement(r);
            requirementList.add(newReq );
            max = Math.max(max, newReq.getRequirementId());
        }
        Requirement.setCounter(max + 1);  
    }
    

    
    /**
     * Returns the list of requirements
     * @return requirementList the list of requirements
     */
    public List<Requirement> getRequirements() {
        return requirementList;
    }
    
    /**
     * Access a requirement by its id number
     * @param id requirement id number
     * @return requirement found requirement
     */
    public Requirement getRequirementById(int id) {
        if(!requirementList.isEmpty()) {
            
            // look through each element 
            for(Requirement requirement: requirementList) {
                
                // check to see if element equals id 
                if(requirement.getRequirementId() == id){
                    return requirement;
                }
            }
        }
        return null;  
    }   
    
    /**
     * Executes command with requirement id 
     * @param id requirement id number
     * @param c command
     */
    public void executeCommand(int id, Command c) {
        Requirement requirement = getRequirementById(id);
        if(requirement != null) {
            requirement.update(c);
        }

    }
    
    /**
     * Removes requirement from List
     * @param id requirement id
     */
    public void deleteRequirementById(int id) {
        requirementList.remove(getRequirementById(id));

    }  

}   
