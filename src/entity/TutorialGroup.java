/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;
/**
 *
 * @author User
 */
public class TutorialGroup {
    private String tutorGroupID;
    private String tutorGroupName;
    
    //store all student belong to this entity
    private ArrayList<Student> studentList = new ArrayList<>();
    
    //store all current entity
    private static ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();

    public TutorialGroup() {
    }

    public TutorialGroup(String tutorGroupID, String tutorGroupName) {
        this.tutorGroupID = tutorGroupID;
        this.tutorGroupName = tutorGroupName;
    }

    public String getTutorGroupID() {
        return tutorGroupID;
    }

    public void setTutorGroupID(String tutorGroupID) {
        this.tutorGroupID = tutorGroupID;
    }

    public String getTutorGroupName() {
        return tutorGroupName;
    }

    public void setTutorGroupName(String tutorGroupName) {
        this.tutorGroupName = tutorGroupName;
    }

    public static ArrayList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public static void setTutorialGroupList(ArrayList<TutorialGroup> tutorialGroupList) {
        TutorialGroup.tutorialGroupList = tutorialGroupList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    

    @Override
    public String toString() {
        return "tutorGroupID=" + tutorGroupID + ", tutorGroupName=" + tutorGroupName + ", studentList=" + studentList;
    }

    
    
    
}