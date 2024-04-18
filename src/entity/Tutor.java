package entity;
import adt.*;
import java.util.Objects;
import java.io.Serializable;

public class Tutor{
    private String tutorID;
    private String tutorName;
    
    private static int nextID = 0;
    
    //store all current entity
    private static ArrayList<Tutor> tutorList = new ArrayList<>();
    
    
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

    public static ArrayList<Tutor> getTutorList() {
        return tutorList;
    }

    public static void setTutorList(ArrayList<Tutor> tutorList) {
        Tutor.tutorList = tutorList;
    }

    
    
    @Override
    public String toString(){
        return String.format("%-5s %-40s", tutorID, tutorName);
    }
}
