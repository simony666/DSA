package entity;

import adt.ArrayList;

public class Course {
    // Variables
    private String courseID;
    private String courseCode;
    private String courseName;
    private String courseProgramLeader;
    private ArrayList<String> coverFaculty;
    private ArrayList<String> tutorialList;
    private ArrayList<String> practicalList;
    private ArrayList<String> lectureList;
    private int courseFees;

    public Course(String courseID, String courseCode, String courseName, String courseProgramLeader, int courseFees) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseProgramLeader = courseProgramLeader;
        this.courseFees = courseFees;
        this.coverFaculty = new ArrayList<>();
        this.tutorialList = new ArrayList<>();
        this.practicalList = new ArrayList<>();
        this.lectureList = new ArrayList<>();
    }

    public Course(String courseID, String courseCode, String courseName, String courseProgramLeader, int courseFees, ArrayList<String> coverList) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseProgramLeader = courseProgramLeader;
        this.courseFees = courseFees;
        this.coverFaculty = coverList;
        this.tutorialList = new ArrayList<>();
        this.practicalList = new ArrayList<>();
        this.lectureList = new ArrayList<>();
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
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

    public String getCourseProgramLeader() {
        return courseProgramLeader;
    }

    public void setCourseProgramLeader(String courseProgramLeader) {
        this.courseProgramLeader = courseProgramLeader;
    }

    public ArrayList<String> getCoverFaculty() {
        return coverFaculty;
    }

    public void setCoverFaculty(ArrayList<String> coverFaculty) {
        this.coverFaculty = coverFaculty;
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

    public int getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(int courseFees) {
        this.courseFees = courseFees;
    }
    
    
    @Override
    public String toString() {
        // Modify this method to include programList in the output
        return "Course ID: " + courseID + "\nCourse Code: " + courseCode +
                "\nCourse Name: " + courseName +
                "\nProgram Leader: " + courseProgramLeader + "\nCover Faculties: " + coverFaculty +
                "\nCourse Fees: " + courseFees + "\n" +
                "\nTutorial List: " + tutorialList +
                "\nPractical List: " + practicalList +
                "\nLecture List: " + lectureList;
    }
}