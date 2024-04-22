/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;


public class AssignmentTeam {
    //auto ID
    private static int nextID = 0;
    
    //attribute
    private String assignID;
    private String assignName;
    private int limit = 5;//member limit of each assignment
    private ArrayList<Student> studentList; //store all student under this assignment team
    private TutorialGroup tutorialGroup;
    private Course course;
    //this.assignID = "A" + String.format("%03d", nextID);
    //nextID++;

    public AssignmentTeam(String assignName, TutorialGroup tutorialGroup, Course course) {
        this.assignID = "AT" + String.format("%03d", nextID);
        nextID++;
        this.assignName = assignName;
        this.tutorialGroup = tutorialGroup;
        this.course = course;
        this.studentList = new ArrayList<>(limit);
    }
    public AssignmentTeam(String assignName, TutorialGroup tutorialGroup, Course course, int limit) {
        this(assignName,tutorialGroup,course);
        this.studentList = new ArrayList<>(limit);
    }
    
    
    
    //own method
    
    public boolean addStudent(Student stu){
        return this.studentList.add(stu);
    }
    public boolean removeStudent(Student stu){
        int index = this.studentList.indexof(stu);
        Student result = this.studentList.remove(index);
        return result != null;
    }
    
    public int getStudentCount() {
        return studentList.getNumberOfEntries();
    }
    
    
    public String getAssignID() {
        return assignID;
    }

    public void setAssignID(String assignID) {
        this.assignID = assignID;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    //Getter && Setter
    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public String toString() {
        return "[" + assignID + "] - " + assignName + " <"+tutorialGroup.getTutorGroupID()+">" +
                    "(Member Count: "+ String.valueOf(this.getStudentCount()) + 
                    ",Course: "+ course.getCourseName() +
                    ")";
    }

    
    
    
}
