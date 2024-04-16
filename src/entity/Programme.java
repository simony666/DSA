/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Programme {
    private String progID;
    private String progName;
    
    private ListInterface<Courses> coursesList = new ArrayList<>();
    private ListInterface<TutorialGroup> TutorialGroupList = new ArrayList<>();

    public Programme() {
    }

    public Programme(String progID, String progName) {
        this.progID = progID;
        this.progName = progName;
        this.coursesList = new ArrayList<>();
        this.TutorialGroupList = new ArrayList<>();
    }

    public String getProgID() {
        return progID;
    }

    public void setProgID(String progID) {
        this.progID = progID;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public ListInterface<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ListInterface<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    public ListInterface<TutorialGroup> getTutorialGroupList() {
        return TutorialGroupList;
    }

    public void setTutorialGroupList(ListInterface<TutorialGroup> TutorialGroupList) {
        this.TutorialGroupList = TutorialGroupList;
    }

    @Override
    public String toString() {
        return "Programme{" + "progID=" + progID + ", progName=" + progName + ", coursesList=" + coursesList + ", TutorialGroupList=" + TutorialGroupList + '}';
    }

    
    
    
}
