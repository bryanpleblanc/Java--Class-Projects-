#ReqKeeper (Requirements Management System)
This software enables developers to track the requirments of a project. From small to very large projects, requirements
managment systems are vital to ensure all developers know what is left to implment with their software.

###Finite State Machine by Design 
1. Submitted - software requirments are submitted into the system.
2. Accepted - developers can accept a requirement to work on.
3. Working -  in this state the developer is working to implement the requirement. 
4. Completed - once completed a requirement will then be sent to the verified state.
5. Verified - here the requirement can be reopened if it fail any tests or implementation was incorrect. 
6. Rejected - a requirement is sent here if it needs to be revised.

### XML File Interactions 
All files saved and opened as XML. The program uses a RequirmentTrackerXML 3rd party library for encoding. 

###Quick Software Screenshot 
![screen shot 2017-01-26 at 4 23 21 pm](https://cloud.githubusercontent.com/assets/25310829/22356140/016425c4-e3e4-11e6-862e-91dcb5fd9b57.png)
