
package entity;

import adt.ArrayList;
import adt.ArrayList;

public class Student{
    private String studentID;
    private String studentName;
    private int age;
    private Programme programme;
    private Course course;
    private String courseRegistered;
    
    //auto ID
    private static int nextID = 0;
    
    //store all current entity
    private static ArrayList<Student> studentList = new ArrayList<>();
    
    public Student(String studentName) {
        this.studentID = "S" + String.format("%03d", nextID);
        nextID++;
        this.studentName = studentName;
        this.age = age;
        this.programme = programme;
        this.course = course;
        this.courseRegistered = courseRegistered;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseRegistered() {
        return courseRegistered;
    }

    public void setCourseRegistered(String courseRegistered) {
        this.courseRegistered = courseRegistered;
    }
    
    

    
    
    
    
    
    
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-20s %-30s", studentID, studentName, age, programme.getProgName(), course.getCoverFaculty(), courseRegistered);
    }
    
    
    
}
