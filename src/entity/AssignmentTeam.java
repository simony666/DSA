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
    private ArrayList<Student> studentList = new ArrayList<>(limit); //store all student under this assignment team
    
    //static artribute
    private static ArrayList<AssignmentTeam> assignmentList = new ArrayList<>(10);//store all current entity
    private static int limit = 5;//member limit of each assignment
    

    public AssignmentTeam(String assignName){
        this.assignID = "A" + String.format("%03d", nextID);
        this.assignName = assignName;
        AssignmentTeam.assignmentList.add(this);
        nextID++;
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
    
    
    //Getter && Setter

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

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public static ArrayList<AssignmentTeam> getAssignmentList() {
        return assignmentList;
    }

    public static void setAssignmentList(ArrayList<AssignmentTeam> assignmentList) {
        AssignmentTeam.assignmentList = assignmentList;
    }

    public static int getLimit() {
        return limit;
    }

    public static void setLimit(int limit) {
        AssignmentTeam.limit = limit;
    }
    

    
    @Override
    public String toString() {
        return "AssignmentTeam{" + "assignID=" + assignID + ", assignName=" + assignName + '}';
    }

    
    
    
}
