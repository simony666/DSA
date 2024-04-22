package control;

import java.util.Scanner;
import adt.*;
import boundary.TutorialGroupManagementUI;
import dao.*;
import entity.*;
import utility.MessageUI;
import java.util.Iterator;

public class TutorialGroupManagement {
    
    private static Scanner scan = new Scanner(System.in);
    private TGInitializer tginitializer = new TGInitializer();
    private TutorialGroupManagementUI TutorialGroupManagementUI = new TutorialGroupManagementUI();
    //public static ListInterface<TutorialGroup> tutogroupList = new ArrayList<>();

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
                    listGroup();
                    break;
                case 4: // Adding students to a tutorial group
                    addStudent();
                    break;
                case 5: // Remove a student from a tutorial group
                   removeStudent();
                    break;
                case 6: // Change the tutorial group for a student
                    changeGroup();
                    break;
                case 7: // List all students in a tutorial group and a programme
                    listGroupAndProgramme();
                    break;
                case 8: // Merge tutorial groups based on criteria
                    mergeGroup();
                    break;
                case 9: //Generate summary reports (at least 2)
                    generateReport();
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
    //5.Print successfull added.
    //Progress Now: Need to Wait for the Programme Control's (ProgrammeList).
    public void addGroup() {
    int totalProgramme = Programme.getProgrammeList().getNumberOfEntries(); 
    System.out.println("=======================================");
    System.out.println("Select Programme To Add Tutorial Group");
    System.out.println("=======================================");
    
    // Display all existing programme
    for (int i = 0; i < Programme.getProgrammeList().getNumberOfEntries(); i++) {
        System.out.println((i + 1) + ". " + Programme.getProgrammeList().getEntry(i).toString());
    }

    // Select a programme
    System.out.println("=======================================");
    System.out.printf("Enter [1-" + totalProgramme + "]: ");
    int selectedIndex = scan.nextInt();
    scan.nextLine();
    
    // Validate selected index
    if (selectedIndex < 1 || selectedIndex > totalProgramme) {
        System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalProgramme + "\".");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
        return;
    }
    
    //Select a Group
    if(selectedIndex >= 1 && selectedIndex <= totalProgramme) {
        Programme selectedProgramme = Programme.getProgrammeList().getEntry(selectedIndex);
        System.out.println("\nSelected Programme: " + selectedProgramme);
        System.out.println("===========================================");
        System.out.println("Enter a index of Tutorial Group To Add: ");
        System.out.println("1. Group 1");
        System.out.println("2. Group 2");
        System.out.println("3. Group 3");
        System.out.println("4. Group 4");
        System.out.println("5. Group 5");
        System.out.println("=====================");
        System.out.printf("Enter [1-5]: ");
        int input = scan.nextInt();
        scan.nextLine();
        
        // Validate input
        if (input < 1 || input > 5) {
            System.out.println("\nInvalid Input! Please enter a number between \"1-5\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }
        
        // Check if the selected tutorial group already exists in the selected programme
        for (int i = 0; i < TutorialGroup.getTutorialGroupList().getNumberOfEntries(); i++) {
            TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(i);
            if (group.getTutorGroupID().equals("G" + input)) {
                System.out.println("\nError! Tutorial Group " + input + " already exists in " + selectedProgramme.getProgName() + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }
        }
        
        //add group to a programme
        Programme addGroup = new Programme("G" + input, "Group" + input);
        Programme.getProgrammeList().add(addGroup);
        
        System.out.println("=====================");
        System.out.println("Successfully added group" + input + " to " + selectedProgramme.getProgID() + selectedProgramme.getProgName() + ".");
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
    public void removeGroup() {
      
    //Reference of getting all Programme     
    int totalProgramme = Programme.getProgrammeList().getNumberOfEntries();
    if (totalProgramme == 0) { //If no Programme Exists, Print Error.
        System.out.println("No Programme Available.");
        
    } else {
        System.out.println("\n=================================");
        System.out.println("Select a Tutorial Group To Remove");
        System.out.println("=================================");
        
        /*display all Information by comparing programme ID in the Tgroup entity and Programme entity
        this allow all Programme information that is not in the group entity to be shown : Programme Name*/
        //loop all Programme
        for (int i = 1; i <= Programme.getProgrammeList().getNumberOfEntries(); i++) {
            Programme programme = Programme.getProgrammeList().getEntry(i);
            
            //loop all Tutorial Group
            for (int j = 1; j <= TutorialGroup.getTutorialGroupList().getNumberOfEntries(); j++) {
                TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(j);
                
                //if program id in the group and programme match then display the programme information
                if (programme.getProgID().equals(group.getProgrammeId())) {
                System.out.println(i + ". " + group + "in" + programme.getProgID() +"-"+ programme.getProgName());
                break;
                }
            }
        }
        
        //Select a Group
        System.out.println("================================================");
        System.out.printf("Enter (1-" + totalProgramme + "): ");
        int selectedGroup = scan.nextInt();       
        scan.nextLine();
        
        //Validation of selectedGroup
        if(selectedGroup >= 1 && selectedGroup <= totalProgramme){
            Programme selectProgramme = Programme.getProgrammeList().getEntry(selectedGroup);
            
            //store Programme name to display after the Group has been removed from a programme
            String programmeName = selectProgramme.getProgName();
            
            //find group
            for (int j = 1; j <= TutorialGroup.getTutorialGroupList().getNumberOfEntries(); j++) {
                TutorialGroup findGroup = TutorialGroup.getTutorialGroupList().getEntry(j);
                if (selectProgramme.getProgID().equals(findGroup.getProgrammeId())) {
                    //display programme information
                    System.out.println("\nSelected Group: " + findGroup + "in" + selectProgramme.getProgID()+ "-" + selectProgramme.getProgName());
                    break;
                }
            }   
            
            System.out.printf("Are you sure you want to remove? (yes/no): ");
            String input = scan.nextLine();
            
             //remove group from a programme
            if (input.equals("yes")) {
                Programme.getProgrammeList().remove(selectedGroup);
                System.out.println("The Group has been successfully removed from "+ programmeName + "!");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
            }else{
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
            }
        } else {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalProgramme + "\".");
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
        System.out.println("\n=========================================");
        System.out.println("Select a Programme To List Tutorial Group");
        System.out.println("=========================================");   
        
        //Display all existing Programme
        for(int i = 0; i < Programme.getProgrammeList().getNumberOfEntries(); i++) {
            System.out.println((i+1) + "." + Programme.getProgrammeList().getEntry(i).getProgName());
        }
        
        //Select a Programme
        System.out.println("=======================================");
        System.out.printf("Enter 1-" + totalProgramme + ":");
        int input = scan.nextInt();
        scan.nextLine();
        

        //validate input
        if(input >= 1 && input <= totalProgramme ) {
            Programme selectedProgramme = Programme.getProgrammeList().getEntry(input - 1);
            System.out.println("\nTutorial Group In " + selectedProgramme.getProgName() + ":");
           
            //Loop all through the Tgroup in the selected Programme       
                for(int j = 0; j < TutorialGroup.getTutorialGroupList().getNumberOfEntries(); j++) {
                    TutorialGroup findGroup = TutorialGroup.getTutorialGroupList().getEntry(j);
                    
                    if(findGroup.getProgrammeId().equals(selectedProgramme.getProgID())) {
                        System.out.println((j + 1) +  "." + findGroup.getTutorGroupID() + "-" + findGroup.getTutorGroupName());
                    }
                }
            }else{
                System.out.println("Invalid input. Please enter a number between 1 " + totalProgramme + ".");
            }
    }
        
    //To add a Student to a Tutorial Group
    //1.Print Exists Students.
    //2.Select a Students.
    //3.Select which Groups to add with the selected student.
    //4.Print Successfull Add a Student to a Tutorial Group.
    public void addStudent(){
         int totalStudent = Programme.getProgrammeList().getNumberOfEntries(); 
        System.out.println("\n=======================================");
        System.out.println("Select Student Add To Tutorial Group");
        System.out.println("=======================================");
        
        //Loop to Find Students
        for(int i = 0; i < Student.getStudentList().getNumberOfEntries(); i++) {
            System.out.println((i + 1) + "." + Student.getStudentList().getEntry(i).toString());
        }
        
        //User select a Student
        System.out.println("===========================");
        System.out.printf("Enter [1-" + totalStudent + ":" );
        int selectedIndex = scan.nextInt();
        scan.nextLine();
        
        // Validate selected index
        if (selectedIndex < 1 || selectedIndex > totalStudent) {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalStudent + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
        }
        
        //Select a Group
        if(selectedIndex >= 1 && selectedIndex <= totalStudent) {
            Student selectedStudent = Student.getStudentList().getEntry(selectedIndex);
            System.out.println("\nSelected Student: " + selectedStudent);
            System.out.println("===========================================");
            System.out.println("Enter a index of Tutorial Group To Add: ");
            System.out.println("1. Group 1");
            System.out.println("2. Group 2");
            System.out.println("3. Group 3");
            System.out.println("4. Group 4");
            System.out.println("5. Group 5");
            System.out.println("=====================");
            System.out.printf("Enter [1-5]: ");
            int input = scan.nextInt();
            scan.nextLine();

            // Validate input
            if (input < 1 || input > 5) {
                System.out.println("\nInvalid Input! Please enter a number between \"1-5\".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }
            
            //add student to a tutorial group
           TutorialGroup addStudent = new TutorialGroup("G" + input, "Group" + input, selectedStudent.getStudentID());
           TutorialGroup.getTutorialGroupList().add(addStudent);

           System.out.println("=====================");
           System.out.println("Successfully added" + selectedStudent.getStudentName() + " to " + "Group" + input + ".");
           System.out.println("Press \"Enter\" to continue...");
           scan.nextLine();
           } else {
               System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalStudent + "\".");
               System.out.println("Press \"Enter\" to continue...");
               scan.nextLine();
           }
    }
    
    //To remove a student from a tutorial group
    //1.Print all existing Tutorial Group.
    //2.Print the exist tutorial group.
    //3.select a student to remove.
    //4.print successful removed.
    public void removeStudent() {
        int totalGroup = TutorialGroup.getTutorialGroupList().getNumberOfEntries();
        
        // Display all existing tutorial groups
        System.out.println("\n========================================");
        System.out.println("Select a Tutorial Group To Remove Student");
        System.out.println("========================================");
        for (int i = 1; i <= totalGroup; i++) {
            TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(i);
            System.out.println(i + ". " + group.toString());
        }

        // Select a tutorial group
        System.out.println("=======================================");
        System.out.printf("Enter (1-" + totalGroup + "): ");
        int selectedGroup = scan.nextInt();
        scan.nextLine();

        // Validate selected group
        if (selectedGroup < 1 || selectedGroup > totalGroup) {
            System.out.println("\nInvalid input. Please enter a number between 1 and " + totalGroup + ".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        // Display existing students in the selected tutorial group
        TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(selectedGroup - 1);
        System.out.println("\nStudents in " + group.getTutorGroupID() + "-" + group.getTutorGroupName() + ":");
        for (int i = 0; i < group.getStudentList().getNumberOfEntries(); i++) {
            Student student = group.getStudentList().getEntry(i);
            System.out.println((i + 1) + ". " + student.toString());
        }

        // Select a student to remove
        System.out.println("=======================================");
        System.out.printf("Enter (1-" + group.getStudentList().getNumberOfEntries() + "): ");
        int selectedStudent = scan.nextInt();
        scan.nextLine();

        // Validate selected student
        if (selectedStudent < 1 || selectedStudent > group.getStudentList().getNumberOfEntries()) {
            System.out.println("\nInvalid input. Please enter a number between 1 and " + group.getStudentList().getNumberOfEntries() + ".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        // Remove the selected student from the tutorial group
        group.getStudentList().remove(selectedStudent - 1);
        System.out.println("\nStudent has been successfully removed from " + group.getTutorGroupID() + "-" + group.getTutorGroupName() + "!");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
    }

    //To Change Tutorial Group for a Student.
    //1.Print all Exist Students.
    //2.User select a Student.
    //3.Select the selected student to wanted tutorial group.
    //4.Print Successful change group.
    public void changeGroup() {
        
        int totalStudent = Student.getStudentList().getNumberOfEntries();
        // Display all existing students
        System.out.println("\n========================================");
        System.out.println("Select a Student to Change Tutorial Group");
        System.out.println("========================================");
        for (int i = 0; i < totalStudent; i++) {
            System.out.println((i + 1) + ". " + Student.getStudentList().getEntry(i).toString());
        }

        // Select a student
        System.out.println("=======================================");
        System.out.printf("Enter (1-" + totalStudent + "): ");
        int selectedStudent = scan.nextInt();
        scan.nextLine();

        // Validate selected student
        if (selectedStudent >= 1 || selectedStudent <= totalStudent) {
            Student student = Student.getStudentList().getEntry(selectedStudent);
            
            // Select the desired tutorial group
            System.out.println("=======================================");
            System.out.println("\nSelected Student: " + student.toString());
            System.out.println("Choose Tutorial Group To Change");
            System.out.println("=======================================");
            System.out.println("1. Group 1");
            System.out.println("2. Group 2");
            System.out.println("3. Group 3");
            System.out.println("4. Group 4");
            System.out.println("5. Group 5");
            System.out.println("=======================================");
            System.out.printf("Enter [1-5] : ");
            int selectedGroupID = scan.nextInt();
            scan.nextLine();

            // Validate selected group
            if (selectedGroupID < 1 || selectedGroupID > 5) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 5" + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }

            System.out.printf("Are you sure you want to change? (yes/no) :");
            String confirm = scan.nextLine();
            if(confirm.equals(confirm)) { //If yes Change the student's tutorial group

                TutorialGroup changeGroup = new TutorialGroup("G" + selectedGroupID,"Group " + selectedGroupID, student.getStudentID());
                TutorialGroup.getTutorialGroupList().replace(selectedStudent, changeGroup);
                System.out.println("\nStudent has been successfully changed to " + selectedGroupID + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();        
                }
        }else{
            System.out.println("\nInvalid input. Please enter a number between 1 and " + totalStudent + ".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
        } 
    }
    
    //To List all students in a tutorial group and a programme.
        public void listGroupAndProgramme() {
           // Display all existing tutorial groups
           System.out.println("\n========================================");
           System.out.println("Select a Tutorial Group to List Students");
           System.out.println("========================================");
           TutorialGroupManagementUI.displayTG();

           // Select a tutorial group
           System.out.println("=======================================");
           System.out.printf("Enter (1-" + TutorialGroup.getTutorialGroupList().getNumberOfEntries() + "): ");
           int selectedGroup = scan.nextInt();
           scan.nextLine();

           // Validate selected group
           if (selectedGroup < 1 || selectedGroup > TutorialGroup.getTutorialGroupList().getNumberOfEntries()) {
               System.out.println("\nInvalid input. Please enter a number between 1 and " + TutorialGroup.getTutorialGroupList().getNumberOfEntries() + ".");
               System.out.println("Press \"Enter\" to continue...");
               scan.nextLine();
               return;
           }

           // Display the list of students in the selected tutorial group
           TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(selectedGroup - 1);
           System.out.println("\nStudents in " + group.getTutorGroupID() + "-" + group.getTutorGroupName() + ":");
           for (int i = 0; i < group.getStudentList().getNumberOfEntries(); i++) {
               Student student = group.getStudentList().getEntry(i);
               System.out.println((i + 1) + ". " + student.toString());
           }

           // Display the list of students in the selected programme
           System.out.println("\nStudents in Programme " + group.getProgrammeId() + ":");
           Programme programme = Programme.getProgrammeList().getEntry(selectedGroup);
           for (int i = 0; i < programme.getStudentList().getNumberOfEntries(); i++) {
               Student student = programme.getStudentList().getEntry(i);
               System.out.println((i + 1) + ". " + student.toString());
           }

           System.out.println("Press \"Enter\" to continue...");
           scan.nextLine();
        }
        
        //To merge Tutorial Group based on criteria: Only able to Merge Within Same Programme.
        public void mergeGroup() {
            // Get all existing tutorial groups
            int totalGroup = TutorialGroup.getTutorialGroupList().getNumberOfEntries();

            // Prompt the user to select two tutorial groups to merge
            System.out.println("Select two tutorial groups to merge:");
            for (int i = 0; i < totalGroup; i++) {
                TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(i);
                System.out.println((i + 1) + ". " + group.getTutorGroupID() + "-" + group.getTutorGroupName() + " (" + group.getProgrammeId()+ ")");
            }

            // Select the first tutorial group
            System.out.println("=======================================");
            System.out.printf("Enter (1-%d): ", totalGroup);
            int group1Index = scan.nextInt();
            scan.nextLine();

            // Validate the first group selection
            if (group1Index < 1 || group1Index > totalGroup) {
                System.out.println("\nInvalid input. Please enter a number between 1 and " + totalGroup + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }

            TutorialGroup group1 = TutorialGroup.getTutorialGroupList().getEntry(group1Index - 1);

            // Prompt the user to select another tutorial group
            System.out.println("Select another tutorial group to merge with the first one:");
            for (int i = 0; i < totalGroup; i++) {
                TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(i);
                if (group.getProgrammeId().equals(group1.getProgrammeId())) { // Only show groups within the same programme
                    if (group.getTutorGroupID().equals(group1.getTutorGroupID())) continue; // Avoid selecting the same group
                    System.out.println((i + 1) + ". " + group.getTutorGroupID() + "-" + group.getTutorGroupName());
                }
            }

            // Select the second tutorial group
            System.out.println("=======================================");
            System.out.printf("Enter (1-%d): ", totalGroup - group1Index + 1);
            int group2Index = scan.nextInt();
            scan.nextLine();

            // Validate the second group selection
            if (group2Index < 1 || group2Index > (totalGroup - group1Index + 1)) {
                System.out.println("\nInvalid input. Please enter a number between 1 and " + (totalGroup - group1Index + 1) + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }

            TutorialGroup group2 = TutorialGroup.getTutorialGroupList().getEntry(group1Index + group2Index - 2);

            // Confirm the merge
            System.out.println("Are you sure you want to merge tutorial groups " + group1.getTutorGroupID() + " and " + group2.getTutorGroupID() + "? (yes/no)");
            String confirm = scan.nextLine();

            // Merge the two tutorial groups if the user confirms
            if (confirm.equalsIgnoreCase("yes")) {

                // Clear the student list of the second group
                group2.getStudentList().clear();

                // Change the group ID and name of the second group to match the first group
                group2.setTutorGroupID(group1.getTutorGroupID());
                group2.setTutorGroupName(group1.getTutorGroupName());

                System.out.println("Tutorial groups " + group1.getTutorGroupID() + " and " + group2.getTutorGroupID() + " have been merged.");
            } else {
                System.out.println("Merge cancelled.");
        }
    }
        
    //To Generate Summary Report (At Least 2)
    //1.Ask user to Select what Summary they want to see
    //2.User Select a Summary.
    //3.If 1, print the 1 Report, if 2 print 2
    //4.Print Report.
    public void generateReport() {
        
    System.out.println("==============================");
    System.out.println("\tSelect a Summary Report");
    System.out.println("==============================");
    System.out.println("1. Tutorial Group Report");
    System.out.println("2. Programme Report");
    //get entry
    System.out.println("==============================");
    System.out.print("Enter (1-2): ");
    int entry = scan.nextInt();
    scan.nextLine();
    
    //Validate entry
    if(entry < 1 || entry > 2) {
        System.out.println("\nInvalid input. Please enter a number between 1 - 2.");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
        return;
    }

     if(entry == 1) {
         // Get all existing tutorial groups
        int totalGroup = TutorialGroup.getTutorialGroupList().getNumberOfEntries();

        // Print the header
        System.out.println("Tutorial Group Summary Report");
        System.out.println("--------------------------------");

        // Iterate through all the tutorial groups
        for (int i = 0; i < totalGroup; i++) {
            TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(i);

            // Print the group details
            System.out.println("Group " + (i + 1) + ":");
            System.out.println("Group ID: " + group.getTutorGroupID());
            System.out.println("Group Name: " + group.getTutorGroupName());
            System.out.println("Number of Students: " + group.getStudentList().getNumberOfEntries());
            System.out.println();
            }
        }else if (entry == 2 ){
            // Get all existing tutorial groups
            int totalGroup = TutorialGroup.getTutorialGroupList().getNumberOfEntries();

            // Print the header
            System.out.println("Tutorial Group Summary Report");
            System.out.println("--------------------------------");

            // Iterate through all the tutorial groups
            for (int i = 0; i < totalGroup; i++) {
                TutorialGroup group = TutorialGroup.getTutorialGroupList().getEntry(i);

                // Print the group details
                System.out.println("Programme: " + group.getProgrammeId());
                System.out.println("Group " + (i + 1) + ":");
                System.out.println("Group ID: " + group.getTutorGroupID());
                System.out.println("Group Name: " + group.getTutorGroupName());
                System.out.println("Number of Students: " + group.getStudentList().getNumberOfEntries());
                System.out.println("Tutor Name: " + Tutor.getTutorList().getNumberOfEntries());
                System.out.println();
                    }
            }        
    }    
          
    public static void main (String[]args) {
        TutorialGroupManagement tgtgm = new TutorialGroupManagement();
        tgtgm.runTutorialGroupManagement();
    }
  }      

    
    

    
    
        

 
    

