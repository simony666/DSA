
package entity;

import adt.ArrayList;
import adt.LinkedList;

public class Student{
    private String studentID;
    private String studentName;
    private int age;
    private Programme programme;
    private ArrayList<Course> courseList;
    private TutorialGroup tutorialGroup = null;
    
    //auto ID
    private static int nextID = 0;
    
    
    public Student(String studentName, int age, Programme programme) {
        this.studentID = "S" + String.format("%03d", nextID);
        nextID++;
        this.studentName = studentName;
        this.age = age;
        this.programme = programme;
        this.courseList = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    

    
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s", studentID, studentName, age, programme.getProgramCode(), programme.getFaculty());
    }
    
   
    
    
    
}
