package entity;

import adt.ArrayList;

public class Course {
    // Variables
    private String courseCode;
    private String courseName;
    private String faculty;
    private String semester;
    private String courseStatus;
    private int courseFee;
    
    //store all tutor belong to this Course, by type of courseType
    private ArrayList<Tutor> tutorialList;
    private ArrayList<Tutor> practicalList;
    private ArrayList<Tutor> lectureList;
    
    //store all current entity
    private static ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<String> linkedCourses;

    public Course(String courseCode, String courseName, String faculty, String semester, String courseStatus, int courseFee) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.faculty = faculty;
        this.semester = semester;
        this.courseStatus = courseStatus;
        this.courseFee = courseFee;
        
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(int courseFee) {
        this.courseFee = courseFee;
    }
    

    public ArrayList<Tutor> getTutorialList() {
        return tutorialList;
    }

    public void setTutorialList(ArrayList<Tutor> tutorialList) {
        this.tutorialList = tutorialList;
    }

    public ArrayList<Tutor> getPracticalList() {
        return practicalList;
    }

    public void setPracticalList(ArrayList<Tutor> practicalList) {
        this.practicalList = practicalList;
    }

    public ArrayList<Tutor> getLectureList() {
        return lectureList;
    }

    public void setLectureList(ArrayList<Tutor> lectureList) {
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
        return String.format("| %-10s | %-40s | %-10s | %-5s | %-10s | %-10s",courseCode,courseName,faculty, semester,courseStatus, courseFee);
    }
}