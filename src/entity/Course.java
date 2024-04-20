package entity;

import adt.ArrayList;
import adt.ListInterface;

public class Course {
    // Variables
    private String courseID;
    private String courseCode;
    private String courseName;
    private String courseProgramLeader;
    private int courseFees;
    private ArrayList<String> coverFaculty;
    
    //store all tutor belong to this Course, by type of courseType
    private ArrayList<Tutor> tutorialList;
    private ArrayList<Tutor> practicalList;
    private ArrayList<Tutor> lectureList;
    
    //store all current entity
    private static ArrayList<Course> courseList = new ArrayList<>();

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
  
    public int getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(int courseFees) {
        this.courseFees = courseFees;
    }

    public ArrayList<String> getCoverFaculty() {
        return coverFaculty;
    }

    public void setCoverFaculty(ArrayList<String> coverFaculty) {
        this.coverFaculty = coverFaculty;
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
        return "Course ID: " + courseID + "\nCourse Code: " + courseCode +
                "\nCourse Name: " + courseName +
                "\nProgram Leader: " + courseProgramLeader + "\nCover Faculties: " + coverFaculty +
                "\nCourse Fees: " + courseFees + "\n" +
                "\nTutorial List: " + tutorialList +
                "\nPractical List: " + practicalList +
                "\nLecture List: " + lectureList;
    }
}