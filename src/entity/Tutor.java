package entity;
import adt.*;
import java.util.Objects;
import java.io.Serializable;

public class Tutor{
    private String tutorID;
    private String tutorName;
    
    private static int nextID = 0;
    
    private ListInterface<Tutor> tutorList = new ArrayList<>();
    
    
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

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(ListInterface<Tutor> tutorList) {
        this.tutorList = tutorList;
    }
    
    @Override
    public String toString(){
        return String.format("%-5s %-40s", tutorID, tutorName);
    }
}
