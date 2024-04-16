/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;
import java.util.Objects;

public class Courses {
    private String coursesID;
    private String coursesName;
    private String coursesType;
    
    private ListInterface<Tutor> tutorList = new ArrayList<>();
    
    public Courses() {
        
    }

    public Courses(String coursesID, String coursesName, String coursesType) {
        this.coursesID = coursesID;
        this.coursesName = coursesName;
        this.coursesType = coursesType;
    }

    

    public String getCoursesID() {
        return coursesID;
    }

    public void setCoursesID(String coursesID) {
        this.coursesID = coursesID;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(ListInterface<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public String getCoursesType() {
        return coursesType;
    }

    public void setCoursesType(String coursesType) {
        this.coursesType = coursesType;
    }

    @Override
    public String toString() {
        return "coursesID=" + coursesID + ", coursesName=" + coursesName + ", coursesType=" + coursesType + ", tutorList=" + tutorList ;
    } 
    
}
