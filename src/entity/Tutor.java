package entity;
import adt.*;
import java.util.Objects;
import java.io.Serializable;

public class Tutor{
    private String tutorID;
    private String tutorName;
    
    private static int nextID = 1;
    
    //store all course belong to this tutor, by type of courseType
    private ArrayList<Course> tutorialList = new ArrayList<>();
    private ArrayList<Course> practicalList = new ArrayList<>();
    private ArrayList<Course> lectureList = new ArrayList<>();
    
    
    //store all current entity
    private static ArrayList<Tutor> tutorList = new ArrayList<>();
    
    //store all tutorialGroup belong to this entity
    private static ArrayList<TutorialGroup> TutorialGroupList = new ArrayList<>();
    
    public Tutor(String tutorID, String tutorName) {
        this.tutorID = "T" + String.format("%03d", nextID);
        this.tutorName = tutorName;
        nextID++;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Tutor.nextID = nextID;
    }

    public ArrayList<Course> getTutorialList() {
        return tutorialList;
    }

    public void setTutorialList(ArrayList<Course> tutorialList) {
        this.tutorialList = tutorialList;
    }

    public ArrayList<Course> getPracticalList() {
        return practicalList;
    }

    public void setPracticalList(ArrayList<Course> practicalList) {
        this.practicalList = practicalList;
    }

    public ArrayList<Course> getLectureList() {
        return lectureList;
    }

    public void setLectureList(ArrayList<Course> lectureList) {
        this.lectureList = lectureList;
    }
    
    

    public static ArrayList<Tutor> getTutorList() {
        return tutorList;
    }

    public static void setTutorList(ArrayList<Tutor> tutorList) {
        Tutor.tutorList = tutorList;
    }

    public static ArrayList<TutorialGroup> getTutorialGroupList() {
        return TutorialGroupList;
    }

    public static void setTutorialGroupList(ArrayList<TutorialGroup> TutorialGroupList) {
        Tutor.TutorialGroupList = TutorialGroupList;
    }

    
    
    @Override
    public String toString(){
        return String.format("%-5s %-40s", tutorID, tutorName);
    }
}
