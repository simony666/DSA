
package entity;

import adt.ArrayList;
import adt.ArrayList;

public class Student{
    private String studentID;
    private String studentName;
    
    //auto ID
    private static int nextID = 0;
    
    //store all current entity
    private static ArrayList<Student> studentList = new ArrayList<>();
    
    public Student(String studentName) {
        this.studentID = "S" + String.format("%03d", nextID);
        this.studentName = studentName;
        nextID++;
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

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Student.nextID = nextID;
    }

    public static ArrayList<Student> getStudentList() {
        return studentList;
    }

    public static void setStudentList(ArrayList<Student> studentList) {
        Student.studentList = studentList;
    }
    
    
    
    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", studentName=" + studentName + '}';
    }
    
    
    
    
}
