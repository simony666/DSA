
package entity;

import adt.ArrayList;
import adt.ListInterface;

public class Student{
    private String studentID;
    private String studentName;
    
    private static int nextID = 0;
    
    private static ListInterface<Student> studentList = new ArrayList<>();
    
    public Student(String studentID, String studentName) {
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

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        Student.studentList = studentList;
    }
    
    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", studentName=" + studentName + '}';
    }
    
    
    
    
}
