/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.AssignmentTeamUI;
import dao.AssignmentTeamDao;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author yongc
 */
public class AssignmentTeam {
    private Scanner scanner = new Scanner(System.in);
    private final AssignmentTeamUI ui = new AssignmentTeamUI();
    
    public void entry(){
        int choice = ui.getMenuChoice();
        switch(choice){
            case 1 -> System.out.println("case 1");
            case 2 -> System.out.println("case 2");
            default -> System.out.println("Something went wrong!");
        }
        //1. Create Assignment Team"
        //2. Remove Assignment Team");
        //3. Modify Assignment Team");
        //4. Add Student");
        //5. Remove Student");
        //6. Merge Assignment Team");
        //7. Display Assignment Team");
        //8. Display Assignment Team Member");
        //9. Report");
    }
    
    public void createTeamCrtl(){
        
    }
    
    public void addStudentCrtl(){
        //getStudent(Student stu);
        
        //ask assigment id / display all assignment if enter 999 / filter 
        //
        //ask student id
        //get from getstudent controller
        //if student exist
    }
    
    public static void main(String[] args){
        new AssignmentTeamDao().generate();
        new AssignmentTeam().entry();
    }
}
