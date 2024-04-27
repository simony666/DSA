package boundary;

import adt.ArrayList;
import adt.HashMap;
import adt.KeyValuePair;
import adt.LinkedList;
import control.AssignmentTeamCrtl;
import control.CourseMenu;
import control.StudentController;
import control.TutorialGroupManagement;
import entity.AssignmentTeam;
import entity.Course;
import entity.Student;
import entity.TutorialGroup;
import java.util.Iterator;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Yong Choy Mun
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
        int choice = -1;
        while(choice == -1){
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
            System.out.println("0. Exit");
            System.out.println("\nPlease Enter Menu Index(0-9):");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice<0 || choice >9){
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = -1;
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
        
        System.out.println("Limit Set to " + limit);
        System.out.println("");
        
        //select Tutorial group
        System.out.println("Tutorial Group List");
        int choice = 0;
        ArrayList<TutorialGroup> TGList = TutorialGroupManagement.getTutorialGroupList();
        while(choice == 0){
            int total = MessageUI.displayList(TGList);
            System.out.println("Please Select A Tutorial Group For this Team [1-"+total +"] or \"-1\" to cancel");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            if(choice == -1){
                System.out.println("Creation of assignment team cancelled.");
                return null;
            }else if (choice<=0 || choice > total){
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        TutorialGroup tg = TGList.getEntry(choice);
        

        //select course
        //TODO: get course from a list
        System.out.println("Avaiable Course List");
        LinkedList<Course> CList = CourseMenu.courseMap.getAllValue();
        choice = 0;
        while(choice == 0){
            int total = MessageUI.displayList(CList);
            System.out.println("Please Select A Course For this Team [1-"+total +"] or \"-1\" to cancel");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
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
            System.out.println("Selected Team: "+team.getAssignName());
            System.out.println("");
            System.out.println("1. Name");
            System.out.println("2. Tutorial Group");
            System.out.println("3. Course");
            System.out.println("4. Student Limit");
            
            System.out.println("\nPlease Enter which part need to modify or \"-1\" to cancel:");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            
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
                }else if(name.isBlank()){
                    System.out.println("Team Name Cannot Be Null");
                    return null;
                    
                            
                }
                team.setAssignName(name);
                System.out.println("Assignment Team Name Changed!");
                break;
            case 2:
                //Tutorial Group
                if (team.getStudentCount()>0){
                    MessageUI.clearScreen();
                    System.out.println("Unable to change! Student List Must Be Empty!");
                    return null;
                }
                ArrayList<TutorialGroup> TGList = TutorialGroupManagement.getTutorialGroupList();
                int TGchoice = 0;
                while (TGchoice == 0){
                    int result = MessageUI.displayList(TGList);
                    System.out.println("\nPlease Enter Team Index or \"-1\" to cancel:");
                    TGchoice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("");

                    if (TGchoice == -1){
                        System.out.println("Modification of Tutorial Group for assignment team cancelled.");
                        return null;
                    }else if (TGchoice < 0 || TGchoice > result){
                        MessageUI.clearScreen();
                        MessageUI.displayInvalidChoiceMessage();
                        choice = 0;
                    }
                }
                TutorialGroup TG = TGList.getEntry(TGchoice);
                team.setTutorialGroup(TG);
                System.out.println("Assignment Team's Tutorial Group Changed!");
                break;
            case 3:
                if (team.getStudentCount()>0){
                    MessageUI.clearScreen();
                    System.out.println("Unable to change! Student List Must Be Empty!");
                    return null;
                }
                //Course
                
                LinkedList<Course> CList = CourseMenu.courseMap.getAllValue();
                int Cchoice = 0;
                while (Cchoice == 0){
                    int total = MessageUI.displayList(CList);
                    System.out.println("Please Select A Course For this Team [1-"+total +"] or \"-1\" to cancel");
                    Cchoice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("");
                    
                    if(Cchoice == -1){
                        System.out.println("Modification of Course for assignment team cancelled.");
                        return null;
                    }else if (Cchoice<=0 || Cchoice >=total){
                        //set choice to invalid to display again
                        MessageUI.clearScreen();
                        MessageUI.displayInvalidChoiceMessage();
                        choice = 0;
                    }
                }
                Course course = CList.getEntry(Cchoice);
                team.setCourse(course);
                System.out.println("Assignment Team's Course Changed!");
                break;
            case 4:
                //Student Limit
                int limit = 0;
                while (limit == 0){
                    System.out.println("\nCurrent Limit: "+team.getLimit());
                    System.out.println("\nPlease Enter New Limit or \"-1\" to cancel:");
                    limit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("");

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
    
    public void Report1(){
        //Team Count By Student Count
        LinkedList<AssignmentTeam> Team = new AssignmentTeamCrtl().getAssignmentList();
        HashMap<String, Integer> result = new HashMap<>();
        
        for (int i = 1; i<= Team.getNumberOfEntries();i++){
            String key = String.valueOf(Team.getEntry(i).getStudentCount());
            int count = result.getOrDefault(key,0);
            result.put(key, ++count);
        }
        
        System.out.printf("|%-15s|%-13s|%5s", "-".repeat(15), "-".repeat(13), "\n");
        System.out.printf("| %13s | %-11s |%5s", "Student Count", "Team Count", "\n");
        System.out.printf("|%13s|%-11s|%5s", "-".repeat(13), "-".repeat(11), "\n");
        for (KeyValuePair<String, Integer> entry : result.getAllEntries()) {
            System.out.printf("|%8s User | %-11s |%5s", entry.getKey(), String.valueOf(entry.getValue()), "\n");
        }
        System.out.printf("|%-13s|%-11s|%5s", "-".repeat(13), "-".repeat(11), "\n");
        MessageUI.pressEnter();
        
    }
    
    public void Report2(){
        //Team Count By Student Count
        LinkedList<AssignmentTeam> Team = new AssignmentTeamCrtl().getAssignmentList();
        HashMap<String, Integer> result = new HashMap<>();
        
        for (int i = 1; i<= Team.getNumberOfEntries();i++){
            String key = Team.getEntry(i).getCourse().getCourseName();
            int count = result.getOrDefault(key,0);
            result.put(key, ++count);
        }
        System.out.printf("|%-40s|%-11s|%5s", "-".repeat(40), "-".repeat(11), "\n");
        System.out.printf("|%-40s|%-11s|%5s", "Course", "Team Count", "\n");
        System.out.printf("|%-40s|%-11s|%5s", "-".repeat(40), "-".repeat(11), "\n");
        for (KeyValuePair<String, Integer> entry : result.getAllEntries()) {
            System.out.printf("|%-40s|%-11s|%5s", entry.getKey(), String.valueOf(entry.getValue()), "\n");
        }
        System.out.printf("|%-40s|%-11s|%5s", "-".repeat(40), "-".repeat(11), "\n");
        MessageUI.pressEnter();
        
    }
    
    
    
    //reuse func
    
    public int displayATList(){
        LinkedList<AssignmentTeam> list = new AssignmentTeamCrtl().getAssignmentList();
        System.out.printf("%3s  [%-5s] - %-20.20s <%-2s> (Member Count,Course)\n","" ,"ID","Name","Group");
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

    public Student getStudent() {
        Student stu = null;
        StudentController StuCrtl = new StudentController();
        
        System.out.println("Please Enter Student's ID or \"cancel\" to exit:");
        while (stu == null){
            String studentID = scanner.nextLine().trim();

            if (studentID.equals("cancel")){
                return null;
            }
            stu = StuCrtl.getStudent(studentID);
            if (stu == null){
                MessageUI.clearScreen();
                System.out.println("Please Enter A Valid Student's ID or \"cancel\" to exit");
            }else{
                break;
            }
        }
        return stu;
    }
}
