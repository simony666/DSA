package dao;

import control.TutorialGroupManagement;
import adt.*;
import entity.Programme;
import entity.TutorialGroup;

public class TGInitializer {
    
    public ListInterface<Programme> initializeProgramme() {
        
        ListInterface<Programme> programmeList = new ArrayList<>();
        
        Programme programme1 = new Programme("RIT", "Bachelor In Internet Technology");
        Programme programme2 = new Programme("RSW", "Bachelor In Internet Security");
        Programme programme3 = new Programme("RSD", "Bachelor In Software Engineering");
        
        programmeList.add(programme1);
        programmeList.add(programme2);
        programmeList.add(programme3);
        
        return programmeList;

        
       
    }
    
    public ListInterface<TutorialGroup> initializeGroup() {
        

       TutorialGroup group1 = new TutorialGroup("G1", "Group 1");
       TutorialGroup group2 = new TutorialGroup("G2", "Group 2");
       TutorialGroup group3 = new TutorialGroup("G3", "Group 3");


        TutorialGroupManagement.tutogroupList.add(group1);
        TutorialGroupManagement.tutogroupList.add(group2);
        TutorialGroupManagement.tutogroupList.add(group3);

        return TutorialGroupManagement.tutogroupList;

        
       
    }
    
}
