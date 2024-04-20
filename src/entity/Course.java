package entity;

import adt.ArrayList;

public class Course {
    // Variables
    private String courseCode;
    private String courseName;
    private String faculty;
    private int creditHours;
    private String courseStatus;
    
    //store all type of tutor belong to this Course
    private ArrayList<String> tutorialList;
    private ArrayList<String> practicalList;
    private ArrayList<String> lectureList;
    
    //store all current entity
    private static ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<String> linkedCourses;

    public Course(String courseCode, String courseName, String faculty, int creditHours, String courseStatus) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.faculty = faculty;
        this.creditHours = creditHours;
        this.courseStatus = courseStatus;
        
        this.tutorialList = new ArrayList<>();
        this.practicalList = new ArrayList<>();
        this.lectureList = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public ArrayList<String> getTutorialList() {
        return tutorialList;
    }

    public void setTutorialList(ArrayList<String> tutorialList) {
        this.tutorialList = tutorialList;
    }

    public ArrayList<String> getPracticalList() {
        return practicalList;
    }

    public void setPracticalList(ArrayList<String> practicalList) {
        this.practicalList = practicalList;
    }

    public ArrayList<String> getLectureList() {
        return lectureList;
    }

    public void setLectureList(ArrayList<String> lectureList) {
        this.lectureList = lectureList;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static void setCourseList(ArrayList<Course> courseList) {
        Course.courseList = courseList;
    }
    
    
    
    
    
    @Override
    public String toString() {
        // Modify this method to include programList in the output
        return String.format("| %-10s | %-40s | %-10s | %-10s | %-10s |",courseCode,courseName,faculty,creditHours,courseStatus);
    }
}