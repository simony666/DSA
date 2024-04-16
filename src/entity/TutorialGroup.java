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
public class TutorialGroup {
    private String tutorGroupID;
    private String tutorGroupName;
    private Student student;
   
    private ListInterface<Student> studentList = new ArrayList<>();

    public TutorialGroup() {
    }

    public TutorialGroup(String tutorGroupID, String tutorGroupName) {
        this.tutorGroupID = tutorGroupID;
        this.tutorGroupName = tutorGroupName;
        this.studentList = new ArrayList<>();
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

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "tutorGroupID=" + tutorGroupID + ", tutorGroupName=" + tutorGroupName + ", studentList=" + studentList;
    }

    
    
    
}
