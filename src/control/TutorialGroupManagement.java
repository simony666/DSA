package control;

import java.util.Scanner;
import adt.*;
import boundary.TutorialGroupManagementUI;
import dao.*;
import entity.*;
import utility.MessageUI;

public class TutorialGroupManagement {
    
    Scanner scan = new Scanner(System.in);
       
    
    private TGInitializer tginitializer = new TGInitializer();
    private TutorialGroupManagementUI TutorialGroupManagementUI = new TutorialGroupManagementUI();
    public static ListInterface<TutorialGroup> tutogroupList = new ArrayList<>();
    
//    public TutorialGroupManagement() {
//        if(TutorialGroup..tutogroupList.isEmpty()) {
//            TutorialGroup.tutogroupList = tginitializer.initializeGroup();
//        }
//    }

    public void runTutorialGroupManagement() {
        int choice = 0;
        do{
            choice = TutorialGroupManagementUI.getTGMMenuChoice();
     
            switch(choice){
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1: // Add a tutorial group to a programme
                    addGroup();
                    break;
                case 2: // Remove a tutorial group from a programme
                    removeGroup();
                    break;
                case 3: // List all tutorial groups for a programme
                    break;
                case 4: // Adding students to a tutorial group
                    //addStudentToTutorialGroup();
                    break;
                case 5: // Remove a student from a tutorial group
                   // removeStudentFromTutorialGroup();
                    break;
                case 6: // Change the tutorial group for a student
                    
                    break;
                case 7: // List all students in a tutorial group and a programme
                    
                    break;
                case 8: // Merge ttutorial groups based on criteria
                    
                    break;
                case 9: //Generate summary reports (at least 2)
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        }while(choice != 0);
    }
    
    //Add tutorial group to a program.
    //1.Display existing program first.
    //2.After that, User Select one of the program.
    //3.Then, select the grps to add to program.
    //4.Last, add new tutorial grp into program List.
    //Progress Now: Need to Wait for the Programme Control's (ProgrammeList).
    public void addGroup() {
    int totalProgramme = Programme.programmeList.getNumberOfEntries(); 
    System.out.println("=======================================");
    System.out.println("Select Programme To Add Tutorial Group");
    System.out.println("=======================================");
    
    // Display all existing programme
    for (int i = 0; i < Programme.programmeList.getNumberOfEntries(); i++) {
        System.out.println((i + 1) + ". " + Programme.programmeList.getEntry(i).getProgName());
    }

    // Select a programme
    System.out.println("=======================================");
    System.out.printf("Enter (1-" + totalProgramme + "): ");
    int selectedIndex = scan.nextInt();
    scan.nextLine();
    
    //Select a Group
    if(selectedIndex >= 1 && selectedIndex <= totalProgramme) {
        Programme selectedProgramme = Programme.programmeList.getEntry(selectedIndex);
        System.out.println("\nSelected Programme: " + selectedProgramme);
        System.out.println("=====================");
        System.out.println("Choose Tutorial Group");
        System.out.println("1. Group 1");
        System.out.println("2. Group 2");
        System.out.println("3. Group 3");
        System.out.println("4. Group 4");
        System.out.println("5. Group 5");
        System.out.println("=====================");
        System.out.println("Enter [1-5]: ");
        int input = scan.nextInt();
        scan.nextLine();
        
        //add group to a programme
        Programme addGroup = new Programme("G" + input, "Group" + input);
        Programme.programmeList.add(addGroup);
        
        System.out.println("=====================");
        System.out.println("Successfully added group to " + selectedProgramme.getProgID() + selectedProgramme.getProgName() + ".");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
        } else {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalProgramme + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
        }
    }
    
    
    //To Remove a Tutorial Group from a program
    //1.Print All Existing Programme. exp: Select a programme to remove group 
    //2.User select a Programme
    //3.Show the Tutorial Group stored in the Selected Programme
    //4.Select one of the Tutorial Group to Remove.
    //5.Print Successfull Remove.
    public static void removeGroup() {
      
    //Reference of getting all Programme     
    int totalProgramme = Programme.programmeList.getNumberOfEntries();
    if (totalProgramme == 0) {
        System.out.println("No Programme Available.");
        return;
    } else {
        System.out.println("\n=======================================");
        System.out.println("Select Programme To Remove Tutorial Group");
        System.out.println("=======================================");
        
        /*display all Information by comparing programme ID in the Tgroup entity and Programme entity
        this allow all Programme information that is not in the group entity to be shown : Programme Name*/
        //loop all Programme
        for (int i = 1; i <= Programme.programmeList.getNumberOfEntries(); i++) {
            Programme programme = Programme.programmeList.getEntry(i);
            
            //loop all Tutorial Group
            for (int j = 1; j <= tutogroupList.getNumberOfEntries(); j++) {
                TutorialGroup group = tutogroupList.getEntry(j);
                
                //if program id in the group and programme match then display the programme information
                if (programme.getProgID().equals(group.getProgrammeId())) {
                System.out.println(i + ". " + programme.getProgID() + "\t" + programme.getProgName()+ "\t" + group);
                break;
                }
            }
        }
        
        //Select a Group
        System.out.println("================================================");
        System.out.printf("Enter (1-" + totalEntries + "): ");
        int selectedGroup = scan.nextInt();       
        scan.nextLine();
        
        if(selectedGroup >= 1 && selectedGroup <= totalProgramme){
            Programme selectProgramme = Programme.programmeList.getEntry(selectedGroup);
            
            //store Programme name to display after the Group has been removed from a programme
            String programmeName = selectProgramme.getProgName();
            
            //find group
            for (int j = 1; j <= tutogroupList.getNumberOfEntries(); j++) {
                TutorialGroup findGroup = tutogroupList.getEntry(j);
                if (selectProgramme.getProgID().equals(findGroup.getProgrammeId())) {
                    //display programme information
                    System.out.println("\nSelected Group: " + selectProgramme.getProgID()+ "-" + selectProgramme.getProgName()+ "-" + findGroup);
                    break;
                }
            }   
            
            System.out.printf("Are you sure you want to remove? (yes/no): ");
            String input = scan.nextLine();
            
             //remove group from a programme
            if (input.equals("yes")) {
                Programme.programmeList.remove(selectedGroup);
                System.out.println("The Group has been successfully removed from "+ programmeName + "!");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
            }
        } else {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalEntries + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            }     
        }
   }    
    
    //To List all the Tgroup in a programme.
    //1.Display all existing Programme.
    //2.Ask user to select a programme to List up Tgroup.
    //3.Display all Tgroup in the selected Programme.
    public void listGroup() {
        int totalProgramme = Programme.getProgrammeList().getNumberOfEntries();
        System.out.println("\n=======================================");
        System.out.println("Select Programme To List Tutorial Group");
        System.out.println("=======================================");   
        
        //Display all existing Programme
        for(int i = 0; i < Programme.getProgrammeList().getNumberOfEntries(); i++) {
            System.out.println((i+1) + "." + Programme.getProgrammeList().getEntry(i).getProgID() + "-" + Programme.getProgrammeList().getEntry(i).getProgName());
        }
        
        //Select a Programme
        System.out.println("=======================================");
        System.out.printf("Enter 1-" + totalProgramme + ":");
        int input = scan.nextInt();
        scan.nextLine();
        

        
        //validate input
        if(input >= 1 && input <= totalProgramme ) {
            Programme selectedProgramme = Programme.getProgrammeList().getEntry(input - 1);
            System.out.println("\nTutorial Group In " + selectedProgramme.getProgID() + "-" + selectedProgramme.getProgName());
            ListInterface<TutorialGroup> tgroup = selectedProgramme.
           
        }
        
        
        
        
    }
   
    
    public ListInterface<TutorialGroup> getTutogroupList() {
        return tutogroupList;
    }

    public void setTutogroupList(ListInterface<TutorialGroup> tutogroupList) {
        this.tutogroupList = tutogroupList;
    }
    
    
    public static void main (String[]args) {
        TutorialGroupManagement tgtgm = new TutorialGroupManagement();
        tgtgm.runTutorialGroupManagement();
    }

        
}
    
    

    
    
        

 
    

