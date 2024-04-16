/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;
import java.util.Objects;


public class Assignment {
    private String assignID;
    private String assignName;
    
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    public Assignment() {
        
    }

    public Assignment(String assignID, String assignName) {
        this.assignID = assignID;
        this.assignName = assignName;
        this.studentList = new ArrayList<>();
        this.tutorialGroupList = new ArrayList<>();
    }

    public String getAssignID() {
        return assignID;
    }

    public void setAssignID(String assignID) {
        this.assignID = assignID;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public ListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(ListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignID=" + assignID + ", assignName=" + assignName + ", studentList=" + studentList + ", tutorialGroupList=" + tutorialGroupList + '}';
    }

    
    
    
}
