 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ArrayList;
import adt.KeyValuePair;
import adt.LinkedList;
import control.AssignmentTeamCrtl;
import control.CourseMenu;
import entity.AssignmentTeam;
import entity.Course;
import entity.TutorialGroup;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author yongc
 */
public class AssignmentTeamUI {
    //create assignment teams for a tutor group
    //remove assignment team
    //amend assignment team details
    //add student to assignment teams
    //remove student from assignment teams
    //merge assignment team based on criteria
    //list assignment teams for a tutorial group
    //list students under and assignment team
    //2 summary report
    
    //tutorial group has many assignment teams
    //a team has many students, Limit the size
    
    private Scanner scanner = new Scanner(System.in);
    //private AssignmentTeamCrtl ATCrtl = new AssignmentTeamCrtl();
    
    public int getMenuChoice(){
        int choice = 0;
        while(choice == 0){
            System.out.println("===================================");
            System.out.println("=         Assignment Team         =");
            System.out.println("===================================");
            System.out.println("1. Create Assignment Team");
            System.out.println("2. Remove Assignment Team");
            System.out.println("3. Modify Assignment Team");
            System.out.println("4. Add Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Merge Assignment Team");
            System.out.println("7. Display Assignment Team");
            System.out.println("8. Display Assignment Team Member");
            System.out.println("9. Report");
            System.out.println("10. Exit");
            System.out.println("\nPlease Enter Menu Index(1-10):");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice<=0 || choice >=10){
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        return choice;
    }
    
    public AssignmentTeam getNewTeam(){
        System.out.println("===================================");
        System.out.println("=     Create Assignment Team      =");
        System.out.println("===================================");
        System.out.println("Please Enter Assignment Team Name or \"cancel\" to cancel");
        String name = scanner.nextLine().trim();
        System.out.println("");
        
        if (name.equalsIgnoreCase("cancel")) {
            System.out.println("Creation of assignment team cancelled.");
            return null;
        }
        
        System.out.println("Please Enter Team Limit (default: 5) or \"cancel\" to cancel");
        String limitInput = scanner.nextLine().trim();
        System.out.println("");
        
        int limit = 5;
        if (limitInput.equalsIgnoreCase("cancel")) {
            System.out.println("Creation of assignment team cancelled.");
            return null;
        }else if(!limitInput.isEmpty()){
            limit = Integer.parseInt(limitInput);
        }
        
        System.out.println("Limit Set to" + limit);
        System.out.println("");
        
        //select Tutorial group
        int choice = 0;
        ArrayList<TutorialGroup> TGList = TutorialGroup.getTutorialGroupList();
        while(choice == 0){
            int total = MessageUI.displayList(TGList);
            System.out.println("Please Select A Tutorial Group For this Team [1-"+total +"] or \"-1\" to cancel");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == -1){
                System.out.println("Creation of assignment team cancelled.");
                return null;
            }else if (choice<=0 || choice >=total){
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        TutorialGroup tg = TGList.getEntry(choice);
        

        //select course
        //TODO: get course from a list
        LinkedList<Course> CList = CourseMenu.courseMap.getAllValue();
        while(choice == 0){
            int total = MessageUI.displayList(CList);
            System.out.println("Please Select A Course For this Team [1-"+total +"] or \"-1\" to cancel");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == -1){
                System.out.println("Creation of assignment team cancelled.");
                return null;
            }else if (choice<=0 || choice >=total){
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        Course course = CList.getEntry(choice);
        
        // Create AssignmentTeam object with the provided name
        AssignmentTeam team = new AssignmentTeam(name,tg,course,limit);
        return team;
    }
    
    public AssignmentTeam getRemoveTeam(){
        int choice = 0;
        AssignmentTeam team = null;
        while (choice == 0){
            System.out.println("===================================");
            System.out.println("=     Remove Assignment Team      =");
            System.out.println("===================================");
            int result = displayATList();
            System.out.println("\nPlease Enter Team Index or \"999\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 999){
                return team;
            }else if (choice < 0 || choice > result){
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }else{
                team = new AssignmentTeamCrtl().getAssignmentList().getEntry(choice);
            }
            
        }
        return team;
    }
    public AssignmentTeam modifyTeam(){
        //name
        //tutotialgroup
        //course
        //limit
        AssignmentTeam team = null;
        int choice = 0;
        while (choice == 0){
            System.out.println("===================================");
            System.out.println("=     Modify Assignment Team      =");
            System.out.println("===================================");
            int result = displayATList();
            System.out.println("\nPlease Enter Team Index Need to modify or \"999\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 999){
                return false;
            }else if (choice < 0 || choice > result){
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }else{
                team = new AssignmentTeamCrtl().getAssignmentList().getEntry(choice);
            }
            
        }
        
    }
    
    public int displayATList(){
        ArrayList<AssignmentTeam> list = new AssignmentTeamCrtl().getAssignmentList();
        return MessageUI.displayList(list);
    }
}
