/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import boundary.AssignmentTeamUI;
import dao.AssignmentTeamDao;
import entity.AssignmentTeam;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author yongc
 */
public class AssignmentTeamCrtl {

    private Scanner scanner = new Scanner(System.in);
    private final AssignmentTeamUI ui = new AssignmentTeamUI();

    private static ArrayList<AssignmentTeam> assignmentList = new ArrayList<>(10);//store all current entity

    public void entry() {
        int choice = ui.getMenuChoice();
        switch (choice) {
            case 1:
                createTeamCrtl();
                break;
            case 2:
                RemoveTeamCrtl();
                break;
            case 7:
                ListTeamCrtl();
            default:
                System.out.println("Something went wrong!");
                break;
        }
        //1. Create Assignment Team"
        //2. Remove Assignment Team");
        //3. Modify Assignment Team");
        //4. Add Student");
        //5. Remove Student");
        //6. Merge Assignment Team");
        //7. Display Assignment Team");
        //8. Display Assignment Team Member");
        //9. Report");\
        //10. Exit");
    }

    public void createTeamCrtl() {
        //create New Entity, add into list
        AssignmentTeam newTeam = ui.getNewTeam();
        if (newTeam != null){
            addAT(newTeam);
            System.out.println("Assignment team with student limit to '" + String.valueOf(newTeam.getLimit()) + "' student created successfully.");
        }
        
        //alway redirect back to main menu
        entry();
    }
    public void RemoveTeamCrtl() {
        //create New Entity, add into list
        AssignmentTeam oldTeam = ui.getRemoveTeam();
        if (oldTeam != null){
            removeAT(oldTeam);
            System.out.println("Assignment team "+oldTeam.getAssignName()+" remove successfully.");
        }
        
        //alway redirect back to main menu
        entry();
    }
    public void MergeTeamCrtl() {
        //create New Entity, add into list
        AssignmentTeam oldTeam = ui.getRemoveTeam();
        if (oldTeam != null){
            removeAT(oldTeam);
            System.out.println("Assignment team "+oldTeam.getAssignName()+" remove successfully.");
        }
        
        //alway redirect back to main menu
        entry();
    }
    
    public void ListTeamCrtl() {
        ui.displayATList();
        
        System.out.println("Press Enter to continue");
        scanner.nextLine();
        MessageUI.clearScreen();
        
        
        //alway redirect back to main menu
        entry();
    }

    public void addStudentCrtl() {
        //getStudent(Student stu);

        //ask assigment id / display all assignment if enter 999 / filter 
        //
        //ask student id
        //get from getstudent controller
        //if student exist
    }
    
    public boolean addAT(AssignmentTeam at){
        return AssignmentTeamCrtl.assignmentList.add(at);
    }
    
    public boolean removeAT(AssignmentTeam at){
        int index = assignmentList.indexof(at);
        if(index >= 0){
            return AssignmentTeamCrtl.assignmentList.remove(index) != null;
        }
        return false;  
    }

    public ArrayList<AssignmentTeam> getAssignmentList() {
        return AssignmentTeamCrtl.assignmentList;
    }

    public void setAssignmentList(ArrayList<AssignmentTeam> assignmentList) {
        AssignmentTeamCrtl.assignmentList = assignmentList;
    }

    public static void main(String[] args) {
        new AssignmentTeamDao().generate();
        AssignmentTeamCrtl crtl = new AssignmentTeamCrtl();
        //AssignmentTeamUI ui = new AssignmentTeamUI();
        crtl.entry();
        //ui.displayATList();
    }
}
