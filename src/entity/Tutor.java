package entity;
import adt.*;
import java.util.Objects;
import java.io.Serializable;

public class Tutor implements Serializable{
    private String tutorID;
    private String tutorName;
    private TutorialGroup tutorialGroup;
    
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    public Tutor() {}
    
    public Tutor(String tutorID) {
        this.tutorID = tutorID;
        
    }
    
    public Tutor(String tutorID, String tutorName) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.tutorialGroupList = new ArrayList<>();
    }
    
    public String getTutorID(){
        return tutorID;
    }
    
    public void setTutorID(String tutorID){
        this.tutorID = tutorID;
    }
    
    public String getTutorName(){
        return tutorName;
    }
    
    public void setTutorName(String tutorName){
        this.tutorName = tutorName;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public ListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(ListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }
    
    
    
    @Override
    public String toString(){
        return String.format("%-5s %-40s%-40s", tutorID, tutorName, getTutorialGroupList());
    }
}
