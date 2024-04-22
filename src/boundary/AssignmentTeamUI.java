 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ArrayList;
import adt.LinkedList;
import control.AssignmentTeamCrtl;
import control.CourseMenu;
import entity.AssignmentTeam;
import entity.Course;
import entity.Student;
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
            if (choice<1 || choice >10){
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
            System.out.println("\nPlease Enter Team Index or \"-1\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == -1){
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
        AssignmentTeam team = getModifyTeam();
        if (team == null){
            //cancel modify
            return null;
        }
        
        //team is the team need to verify now
        return getModifyPart(team);
    }
    
    private AssignmentTeam getModifyTeam(){
        AssignmentTeam team = null;
        int choice = 0;
        while (choice == 0){
            System.out.println("===================================");
            System.out.println("=     Modify Assignment Team      =");
            System.out.println("===================================");
            int result = displayATList();
            System.out.println("\nPlease Enter Team Index need to modify or \"-1\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == -1){
                System.out.println("Modification of assignment team cancelled.");
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
    
    private AssignmentTeam getModifyPart(AssignmentTeam team){
        int choice = 0;
        while (choice == 0){
            System.out.println("===================================");
            System.out.println("=     Modify Assignment Team      =");
            System.out.println("===================================");
            System.out.println("Selected Team: "+team.toString());
            System.out.println("");
            System.out.println("1. Name");
            System.out.println("2. Tutorial Group");
            System.out.println("3. Course");
            System.out.println("4. Student Limit");
            
            System.out.println("\nPlease Enter which part need to modify or \"-1\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == -1){
                System.out.println("Modification of assignment team cancelled.");
                return null;
            }else if (choice < 0 || choice > 4){
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        switch (choice){
            case 1:
                //Name
                System.out.println("\nPlease Enter New Assignment Team Name or \"cancel\" to cancel:");
                String name = scanner.nextLine().trim();
                if (name.equals("cancel")){
                    System.out.println("Modification of assignment team cancelled.");
                    return null;
                }
                team.setAssignName(name);
                System.out.println("Assignment Team Name Changed!");
                break;
            case 2:
                //Tutorial Group
                break;
            case 3:
                //Course
                break;
            case 4:
                //Student Limit
                int limit = 0;
                while (limit == 0){
                    System.out.println("\nPlease Enter New Assignment Team Name or \"-1\" to cancel:");
                    limit = scanner.nextInt();
                    scanner.nextLine();

                    if (limit == -1){
                        System.out.println("Modification of assignment team cancelled.");
                        return null;
                    }else if (choice < 0){
                        MessageUI.clearScreen();
                        MessageUI.displayInvalidChoiceMessage();
                        choice = 0;
                    }
                }
                boolean success = team.setLimit(limit);
                if (success){
                    System.out.println("Assignment Team limit Changed!");
                }else{
                    System.out.println("Assignment Team limit Cannot Change!");
                }
                break;
        }
        return team;
    }
    
    public int displayATList(){
        LinkedList<AssignmentTeam> list = new AssignmentTeamCrtl().getAssignmentList();
        int result = MessageUI.displayList(list);
        if (result == 0){
            MessageUI.displayEmpty();
        }
        return result;
    }
    
    public int displayATMemberList(AssignmentTeam at){
        System.out.println("Member For " + at.getAssignName());
        ArrayList<Student> list = at.getStudentList();
        int result = MessageUI.displayList(list);
        if (result == 0){
            MessageUI.displayEmpty();
        }
        return result;
    }

    public AssignmentTeam getTeam() {
        AssignmentTeam team = null;
        int choice = 0;
        while (choice == 0){
            int result = displayATList();
            System.out.println("\nPlease Select A Team or \"-1\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == -1){
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
}
