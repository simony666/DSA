/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;


public class Assignment {
    private String assignID;
    private String assignName;
    
    private static int nextID = 0;
    
    private static ListInterface<Student> assignmentList = new ArrayList<>();
    
    public Assignment() {
        
    }

    public Assignment(String assignID, String assignName) {
        this.assignID = "A" + String.format("%03d", nextID);
        this.assignName = assignName;
        nextID++;
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

    public static ListInterface<Student> getAssignmentList() {
        return assignmentList;
    }

    public static void setAssignmentList(ListInterface<Student> assignmentList) {
        Assignment.assignmentList = assignmentList;
    }
    
    @Override
    public String toString() {
        return "Assignment{" + "assignID=" + assignID + ", assignName=" + assignName + '}';
    }

    
    
    
}
