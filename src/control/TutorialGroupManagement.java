package control;

import adt.ArrayList;
import adt.LinkedList;
import adt.ListInterface;
import java.util.Scanner;
import boundary.TutorialGroupManagementUI;
import dao.MainDao;
import entity.TutorialGroup;
import entity.Programme;
import entity.Tutor;
import entity.Student;
import utility.MessageUI;
import javax.swing.*;
import java.awt.*;

public class TutorialGroupManagement {

    private ArrayList<Programme> programmeList = new ArrayList<>();

//    private ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    private TutorialGroupManagementUI TutorialGroupManagementUI = new TutorialGroupManagementUI();

    //store all current entity
    public static ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();

    ListInterface<Student> studentList = StudentController.getStudentList();
    private String reportContent;

    public TutorialGroupManagement() {

    }

    public void entry() {

        int choice = 0;
        do {
            choice = TutorialGroupManagementUI.getTGMMenuChoice();

            switch (choice) {
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
        } while (choice != 0);
    }

    //Add tutorial group to a program.
    //1.Display existing program first.
    //2.After that, User Select one of the program.
    //3.Then, select the grps to add to program.
    //4.Last, add new tutorial grp into program List.
    //5.Print successfull added.
    public void addGroup() {
        ArrayList<Programme> programList = ProgramMenu.getProgramList();
        int totalProgramme = programList.getNumberOfEntries();
        System.out.println("================================================================");
        System.out.println("\tSelect Programme To Add Tutorial Group");
        System.out.println("================================================================");

        //Print All Exists Program.
        TutorialGroupManagementUI.displayPG();

        // Select a programme
        System.out.println("================================================================");
        System.out.printf("Enter (1-" + totalProgramme + "): ");
        int selectedIndex = scan.nextInt();
        scan.nextLine();

        // Validate selected index
        if (selectedIndex < 1 || selectedIndex > totalProgramme) {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalProgramme + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        //Select a Group. Assume 1 Program can only Have 5 TGroups. 
        if (selectedIndex >= 1 && selectedIndex <= totalProgramme) {
            Programme selectedProgramme = programList.getEntry(selectedIndex);
            System.out.println("===========================================");
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

            //add group to a programme only if it doesn't already exist
            boolean groupExists = false;
            String groupCodeToCheck = "G" + input;
            for (TutorialGroup existingGroup : selectedProgramme.getTutorialGroupList()) {
                if (existingGroup.getTutorGroupID().equals(groupCodeToCheck)) {
                    groupExists = true;
                    break;
                }
            }

            if (groupExists) {
                System.out.println("Group " + input + " already exists in " + selectedProgramme.getProgramCode() + "-" + selectedProgramme.getProgramName() + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            } else {
                //add group to the programme
                TutorialGroup addGroup = new TutorialGroup(groupCodeToCheck, "Group " + input, selectedProgramme.getProgramCode());
                selectedProgramme.getTutorialGroupList().add(addGroup);

                System.out.println("=====================");
                System.out.println("Successfully added group " + input + " to " + selectedProgramme.getProgramCode() + "-" + selectedProgramme.getProgramName() + ".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
            }

        }
    }

//    To Remove a Tutorial Group from a program
//    1.Print All Existing Programme. exp: Select a programme to remove group 
//    2.User select a Programme
//    3.Show the Tutorial Group stored in the Selected Programme
//    4.Select one of the Tutorial Group to Remove.
//    5.Print Successfull Remove.
    public void removeGroup() {
        ArrayList<Programme> programList = ProgramMenu.getProgramList();
        int totalProgramme = programList.getNumberOfEntries();
        System.out.println("================================================================");
        System.out.println("\tSelect Programme To Add Tutorial Group");
        System.out.println("================================================================");

        //Print All Exists Program.
        TutorialGroupManagementUI.displayPG();

        // Select a programme
        System.out.println("================================================================");
        System.out.printf("Enter (1-" + totalProgramme + "): ");
        int selectedIndex = scan.nextInt();
        scan.nextLine();

        // Validate selected index
        if (selectedIndex < 1 || selectedIndex > totalProgramme) {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalProgramme + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }
        if (selectedIndex >= 1 && selectedIndex <= totalProgramme) {
            Programme selectedProgramme = programList.getEntry(selectedIndex);

            // Reference of getting all Programme
            ArrayList<TutorialGroup> TGList = selectedProgramme.getTutorialGroupList();
            int totalGroup = TGList.getNumberOfEntries();
            if (totalGroup == 0) { // If no Tutorial Group Exists, Print Error.
                System.out.println("No Tutorial Group Available.");
                System.out.println("Press \"Enter\" to Exit...");
                scan.nextLine();
                return;
            } else {
                System.out.println("\n=================================");
                System.out.println("Select a Tutorial Group To Remove");
                System.out.println("=================================");

                // Loop through all Programmes
                for (int i = 1; i <= totalGroup; i++) {
                    TutorialGroup group = TGList.getEntry(i);
                    System.out.printf("%d. %s\t%s\t%s\tin %s\n", i, group.getTutorGroupID(), group.getTutorGroupName(), selectedProgramme.getProgramCode(), selectedProgramme.getProgramName());

                }
            }

            // Select a Group
            System.out.println("================================================");
            System.out.println("Enter (1-" + totalGroup + "): ");
            int selectedGroupIndex = scan.nextInt();
            scan.nextLine();

            // Validation of selectedGroup
            if (selectedGroupIndex >= 1 && selectedGroupIndex <= totalGroup) {
                TutorialGroup selectedTutorialGroup = TGList.getEntry(selectedGroupIndex);

                // Display the selected group details before removal
                System.out.println("\nSelected Group: " + selectedTutorialGroup.getTutorGroupName() + " in " + selectedTutorialGroup.getProgrammeCODE());

                // Confirm the removal
                System.out.print("Are you sure you want to remove? (yes/no): ");
                String input = scan.nextLine();

                // Remove the group from the programme
                if (input.equals("yes")) {
                    TGList.remove(selectedGroupIndex);
                    System.out.println("\nThe Group has been successfully removed from group " + " IN " + selectedTutorialGroup.getProgrammeCODE() + "!");
                    System.out.println("Press \"Enter\" to Continue...");
                    scan.nextLine();
                } else {
                    System.out.println("Removal cancelled. Please type exactly \"yes\".");
                }
            } else {
                System.out.println("\nInvalid Input! Please enter a number between 1 and " + totalGroup + ".");
            }
        }
    }

//    To List all the Tgroup in a programme.
//    1.Display all existing Programme.
//    2.Ask user to select a programme to List up Tgroup.
//    3.Display all Tgroup in the selected Programme.
    public void listGroup() {

        ArrayList<Programme> programList = ProgramMenu.getProgramList();
        int totalProgramme = programList.getNumberOfEntries();
        System.out.println("Available Programme to List Group:");

        //Print All Exists Program.
        TutorialGroupManagementUI.displayPG();

        // Select a programme
        System.out.println("================================================================");
        System.out.printf("Enter (1-" + totalProgramme + "): ");
        int selectedIndex = scan.nextInt();
        scan.nextLine();

        // Validate selected index
        if (selectedIndex < 1 || selectedIndex > totalProgramme) {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalProgramme + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }
        if (selectedIndex >= 1 && selectedIndex <= totalProgramme) {
            Programme selectedProgramme = programList.getEntry(selectedIndex);

            // Reference of getting all Programme
            ArrayList<TutorialGroup> TGList = selectedProgramme.getTutorialGroupList();
            int totalGroup = TGList.getNumberOfEntries();
            if (totalGroup == 0) { // If no Tutorial Group Exists, Print Error.
                System.out.println("No Tutorial Group Available.");
                System.out.println("Press \"Enter\" to Exit...");
                scan.nextLine();
                return;
            } else {
                // Loop through all TGroups.
                for (int i = 1; i <= totalGroup; i++) {
                    TutorialGroup group = TGList.getEntry(i);
                    System.out.printf("%d. %s\t%s\t%s\tin %s\n", i, group.getTutorGroupID(), group.getTutorGroupName(), selectedProgramme.getProgramCode(), selectedProgramme.getProgramName());
                }
            }
        }
        System.out.println("==============================");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
    }

    //To add a Student to a Tutorial Group
    //1.Print Exists Students.
    //2.Select a Students.
    //3.Select which Groups to add with the selected student.
    //4.Print Successfull Add a Student to a Tutorial Group.
    public void addStudent() {
        // Check if there are any tutorial groups available
        if (tutorialGroupList.isEmpty()) {
            System.out.println("\nNo tutorial groups available to add students to.");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        // Display initialized students
        int totalStudent = studentList.getNumberOfEntries();
        System.out.println("===============================================================================");
        System.out.println("                       Select Student to Add To Tutorial Group");
        System.out.println("===============================================================================");

        // Loop to Find and Display Students
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            System.out.println(i + "." + studentList.getEntry(i));
        }

        // User selects a Student
        System.out.println("===========================");
        System.out.printf("Enter [1-" + totalStudent + "]: ");
        int selectedIndex = scan.nextInt();
        scan.nextLine();

        // Validate selected index
        if (selectedIndex < 1 || selectedIndex > totalStudent) {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalStudent + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        // Select a Group
        if (selectedIndex >= 1 && selectedIndex <= totalStudent) {
            Student selectedStudent = studentList.getEntry(selectedIndex);

            // Check if the student is already in a tutorial group
            if (selectedStudent.getTutorialGroup() != null) {
                System.out.println("Student " + selectedStudent.getStudentName() + " is already assigned to a tutorial group.");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }

            // Get the program associated with the selected student
            Programme studentProgram = selectedStudent.getProgramme();

            // Continue with the process of adding the student to a tutorial group
            System.out.println("\nSelected Student: " + selectedStudent);
            System.out.println("===========================================");
            System.out.println("Enter the index of the Tutorial Group to add the student to: ");

            // Display tutorial groups for the student's program
            int groupCounter = 0;
            for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
                TutorialGroup group = tutorialGroupList.getEntry(i);
                if (group.getProgrammeCODE().equals(studentProgram.getProgramCode())) {
                    groupCounter++;
                    System.out.println(groupCounter + ". " + group.getTutorGroupName());
                }
            }

            if (groupCounter == 0) {
                System.out.println("No tutorial groups available for " + studentProgram.getProgramName());
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }

            System.out.println("=====================");
            System.out.printf("Enter [1-%d]: ", groupCounter);
            int input = scan.nextInt();
            scan.nextLine();

            // Validate input
            if (input < 1 || input > groupCounter) {
                System.out.println("\nInvalid Input! Please enter a number between \"1-" + groupCounter + "\".");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
                return;
            }

            // Add student to the selected tutorial group
            TutorialGroup selectedTutorialGroup = null;
            int count = 0;
            for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
                TutorialGroup group = tutorialGroupList.getEntry(i);
                if (group.getProgrammeCODE().equals(studentProgram.getProgramCode())) {
                    count++;
                    if (count == input) {
                        selectedTutorialGroup = group;
                        break;
                    }
                }
            }
            // Add student into TG
            selectedTutorialGroup.getStudentList().add(selectedStudent);
            // Set student's TG
            selectedStudent.setTutorialGroup(selectedTutorialGroup);

            System.out.println("=====================");
            System.out.println("Successfully added " + selectedStudent.getStudentName() + " to " + selectedTutorialGroup.getTutorGroupName() + ".");
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
        int totalEntries = tutorialGroupList.getNumberOfEntries();

        System.out.println("================================================");
        System.out.println("Select A Group To Remove Student");
        System.out.println("================================================");

        // Display all TGroup in tutorial groups
        for (int i = 1; i <= totalEntries; i++) {
            System.out.println(i + "." + tutorialGroupList.getEntry(i).getTutorGroupName() + " IN " + tutorialGroupList.getEntry(i).getProgrammeCODE());
        }

        System.out.println("================================================");
        System.out.print("Enter (1-" + totalEntries + "):");
        int selectedGroup = scan.nextInt();
        scan.nextLine();

        // Validate selected group
        if (selectedGroup < 1 || selectedGroup > totalEntries) {
            System.out.println("\nInvalid input. Please enter a number between 1 - 5" + ".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        TutorialGroup group = tutorialGroupList.getEntry(selectedGroup);
        LinkedList<Student> StuList = group.getStudentList();

        // Check if there are no students in the selected group
        if (StuList.isEmpty()) {
            System.out.println("There are no students in this group.");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return; // Exit the method
        }

        //Find student in a particular group.
        int totalStudent = StuList.getNumberOfEntries();  // Counter for displayed entries
        for (int j = 1; j <= totalStudent; j++) {
            Student findStudent = StuList.getEntry(j);
            System.out.println(j + ". " + group.getTutorGroupID() + "-" + group.getTutorGroupName() + "\t" + findStudent);
        }

        // Select a student
        System.out.println("================================================");
        System.out.print("Enter (1-" + totalStudent + "): ");
        int selectedEntry = scan.nextInt();   //Select the student  
        scan.nextLine();

        // Validate selected entry
        if (selectedEntry >= 1 && selectedEntry <= totalStudent) {
            TutorialGroup selectedStudent = tutorialGroupList.getEntry(selectedEntry);
            Student student = StuList.getEntry(selectedEntry);

            // Store group name to display after the student has been removed from a group
            String groupName = selectedStudent.getTutorGroupName();

            System.out.println("\nSelected student: " + student.toString() + "\t" + selectedStudent.getTutorGroupID());

            System.out.printf("Are you sure you want to remove? (yes/no): ");
            String entry = scan.nextLine();

            // Remove student from a group
            if (entry.equals("yes")) {
                StuList.remove(selectedEntry);
                System.out.println("Student has been successfully removed from " + groupName + "!");
                System.out.println("Press \"Enter\" to continue...");
                scan.nextLine();
            }
        } else {
            System.out.println("\nInvalid Input! Please enter a number between \"1-" + totalStudent + "\".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
        }
    }

    //To Change Tutorial Group for a Student.
    //1.Print all Exist Students.
    //2.User select a Student.
    //3.Select the selected student to wanted tutorial group.
    //4.Print Successful change group.
    public void changeGroup() {
        int totalStudent = studentList.getNumberOfEntries();
        // Display all existing students
        System.out.println("\n========================================");
        System.out.println("Select a Student to Change Tutorial Group");
        System.out.println("========================================");

        // Display existing students
        for (int i = 1; i <= totalStudent; i++) {
            System.out.println(i + "." + studentList.getEntry(i));
        }

        // Select a student
        System.out.println("=======================================");
        System.out.print("Enter (1-" + totalStudent + "): ");
        int selectedStudentIndex = scan.nextInt();
        scan.nextLine();

        // Validate selected student
        if (selectedStudentIndex < 1 || selectedStudentIndex > totalStudent) {
            System.out.println("Invalid student number.");
            return;
        }

        Student selectedStudent = studentList.getEntry(selectedStudentIndex);

        // Select the desired tutorial group
        System.out.println("=======================================");
        System.out.println("\nSelected Student: " + selectedStudent);
        System.out.println("Choose Tutorial Group To Change");
        System.out.println("=======================================");

        // Display available tutorial groups
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + ". Group " + i);
        }

        System.out.println("=======================================");
        System.out.print("Enter [1-5] : ");
        int selectedGroupID = scan.nextInt();
        scan.nextLine();

        // Validate selected group
        if (selectedGroupID < 1 || selectedGroupID > 5) {
            System.out.println("\nInvalid input. Please enter a number between 1 - 5" + ".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;
        }

        System.out.print("Are you sure you want to change? (yes/no) : ");
        String confirm = scan.nextLine();
        if (confirm.equals("yes")) { // If yes, change the student's tutorial group

            // Retrieve the selected tutorial group
            TutorialGroup selectedGroup = new TutorialGroup("G" + selectedGroupID, "Group " + selectedGroupID, "");

            // Update the student's tutorial group
            selectedStudent.setTutorialGroup(selectedGroup);

            // Add the student to the new tutorial group
            selectedGroup.getStudentList().add(selectedStudent);

            System.out.println("\nStudent has been successfully changed to group " + selectedGroupID + ".");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
        }
    }

////        To List all students in a tutorial group and a programme. 
////        1.Display Exists Program to Choose.
////        2.Select a Programme
////        3.Display all student in the selected Programme and TutorialGroup.
    public void listGroupAndProgramme() {
        // Display all available programmes
        System.out.println("Select Programme To List All Tutorial Groups");
        TutorialGroupManagementUI.displayPG();

        // Prompt the user to select a programme
        System.out.print("Enter (1-" + ProgramMenu.getProgramList().getNumberOfEntries() + "): ");
        int programmeIndex = scan.nextInt();
        scan.nextLine();

        // Validate the selected programme index
        if (programmeIndex < 1 || programmeIndex > ProgramMenu.getProgramList().getNumberOfEntries()) {
            System.out.println("Invalid programme number.");
            return;
        }

        // Get the selected programme
        Programme selectedProgramme = ProgramMenu.getProgramList().getEntry(programmeIndex);

        // Display all tutorial groups within the selected programme
        System.out.println("Tutorial Groups for " + selectedProgramme.getProgramName() + ":");

        // Iterate over each tutorial group to find those belonging to the selected programme
        int groupCounter = 0; // Counter to keep track of displayed groups
        for (int i = 1; i <= selectedProgramme.getTutorialGroupList().getNumberOfEntries(); i++) {
            TutorialGroup group = selectedProgramme.getTutorialGroupList().getEntry(i);
            // Check if the group belongs to the selected programme
            if (group.getProgrammeCODE().equals(selectedProgramme.getProgramCode())) {
                groupCounter++;
                System.out.println(groupCounter + ". " + group.getTutorGroupID() + "\t" + group.getTutorGroupName());
            }
        }

        // Prompt the user to select a tutorial group
        System.out.print("Enter the number of the tutorial group: ");
        int groupIndex = scan.nextInt();
        scan.nextLine();

        // Validate the selected tutorial group index
        if (groupIndex < 1 || groupIndex > groupCounter) {
            System.out.println("Invalid tutorial group number.");
            return;

        }

        // Get the selected tutorial group
        TutorialGroup selectedGroup = null;
        int selectedGroupCount = 0;
        for (int i = 1; i <= selectedProgramme.getTutorialGroupList().getNumberOfEntries(); i++) {
            TutorialGroup group = selectedProgramme.getTutorialGroupList().getEntry(i);
            if (group.getProgrammeCODE().equals(selectedProgramme.getProgramCode())) {
                selectedGroupCount++;
                if (selectedGroupCount == groupIndex) {
                    selectedGroup = group;
                    break;
                }
            }
        }

        //Check if the selectedGroup is Null
        if (selectedGroup == null) {
            System.out.println("The number of students in the Group is \"Empty\" ");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
            return;

        }

        // Display all students in the selected tutorial group
        LinkedList<Student> stuList = selectedGroup.getStudentList();
        System.out.println("Students in " + selectedGroup.getTutorGroupName() + ":");
        for (int i = 1; i <= stuList.getNumberOfEntries(); i++) {
            Student student = stuList.getEntry(i);
            System.out.println(i + ". " + student);
        }

        System.out.println("==============================");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
    }

//        //To merge Tutorial Group based on criteria: Only able to Merge Within Same Programme.
    public void mergeGroup() {
        // Get all existing tutorial groups
        int totalGroup = tutorialGroupList.getNumberOfEntries();

        // Prompt the user to select two tutorial groups to merge
        System.out.println("Select two tutorial groups to merge:");
        for (int i = 1; i <= totalGroup; i++) {
            TutorialGroup group = tutorialGroupList.getEntry(i);
            System.out.println(i + ". " + group.getTutorGroupID() + "-" + group.getTutorGroupName() + " (" + group.getProgrammeCODE() + ")");
        }

        // Select the first tutorial group
        System.out.println("=======================================");
        System.out.print("Enter the index of the first group to merge (1-" + totalGroup + "): ");
        int group1Index = scan.nextInt();
        scan.nextLine();

        // Validate the first group selection
        if (group1Index < 1 || group1Index > totalGroup) {
            System.out.println("\nInvalid input. Please enter a number between 1 and " + totalGroup + ".");
            return;
        }

        TutorialGroup group1 = tutorialGroupList.getEntry(group1Index);

        // Prompt the user to select another tutorial group
        System.out.println("Select another tutorial group to merge with the first one:");
        int availableGroupsCount = 0; // Count of available groups for selection
        for (int i = 1; i <= totalGroup; i++) {
            TutorialGroup group2 = tutorialGroupList.getEntry(i);
            if (i != group1Index && group2.getProgrammeCODE().equals(group1.getProgrammeCODE())) {
                availableGroupsCount++;
                System.out.println(availableGroupsCount + ". " + group2.getTutorGroupID() + "-" + group2.getTutorGroupName());
            }
        }

        // Select the second tutorial group
        System.out.println("=======================================");
        System.out.print("Enter the index of the second group to merge (1-" + availableGroupsCount + "): ");
        int group2Index = scan.nextInt();
        scan.nextLine();

        // Validate the second group selection
        if (group2Index < 1 || group2Index > availableGroupsCount) {
            System.out.println("\nInvalid input. Please enter a number between 1 and " + availableGroupsCount + ".");
            return;
        }

        // Find the actual index of the selected group in the tutorialGroupList
        int actualGroup2Index = 0;
        for (int i = 1, count = 0; i <= totalGroup; i++) {
            if (i != group1Index) {
                TutorialGroup group2 = tutorialGroupList.getEntry(i);
                if (group2.getProgrammeCODE().equals(group1.getProgrammeCODE())) {
                    count++;
                    if (count == group2Index) {
                        actualGroup2Index = i;
                        break;
                    }
                }
            }
        }

        TutorialGroup group2 = tutorialGroupList.getEntry(actualGroup2Index);

        // Confirm the merge
        System.out.print("Are you sure you want to merge tutorial groups " + group1.getTutorGroupID() + " and " + group2.getTutorGroupID() + "? (yes/no) ");
        String confirm = scan.nextLine();

        // Merge the two tutorial groups if the user confirms
        if (confirm.equalsIgnoreCase("yes")) {
            // Move students from group2 to group1
            for (Student student : group2.getStudentList()) {
                group1.addStudent(student);
            }

            // Clear the student list of the second group
            group2.getStudentList().clear();

            // Change the group ID and name of the second group to match the first group
            group2.setTutorGroupID(group1.getTutorGroupID());
            group2.setTutorGroupName(group1.getTutorGroupName());
            group2.getStudentList().remove(actualGroup2Index);

            System.out.println("Tutorial groups " + group1.getTutorGroupID() + " and " + group2.getTutorGroupID() + " have been merged.");
            System.out.println("Press \"Enter\" to continue...");
            scan.nextLine();
        } else {
            System.out.println("Merge cancelled.");
        }
    }

//        
//    //To Generate Summary Report (At Least 2)
//    //1.Ask user to Select what Summary they want to see
//    //2.User Select a Summary.
//    //3.If 1, print the 1 Report, if 2 print 2
//    //4.Print Report.
    public void generateReport() {
        System.out.println("=================================");
        System.out.println("Select a Summary Report");
        System.out.println("=================================");
        System.out.println("1. Programme Summary Report");
        System.out.println("2. Tutorial Group Summary Report");
        System.out.println("================================================");
        System.out.print("Enter (1-2): ");

        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                displayProgrammeSummaryReport();
                break;
            case 2:
                displayTutorialGroupSummaryReport();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
                break;
        }
    }

    public void displayProgrammeSummaryReport() {
        // Programme Summary Report
        System.out.println("===============================================================================================================================");
        System.out.println("---------------------------------------------- Programme Summary Report  ---------------------------------------------------");
        System.out.println("===============================================================================================================================");

        System.out.printf("%-4s| %-25s| %-66s| %-30s%n", "No.", "Program Code", "Programme Name", "Faculty");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        int totalProgramme = ProgramMenu.getProgramList().getNumberOfEntries();
        int totalStudents = 0;
        for (int i = 1; i <= totalProgramme; i++) {
            Programme programme = ProgramMenu.getProgramList().getEntry(i);
            int studentsInProgramme = countStudentsInProgramme(programme.getProgramCode());
            totalStudents += studentsInProgramme;
            System.out.printf("%-4d| %-25s| %-66s| %-30s%n", i, programme.getProgramCode(), programme.getProgramName(), programme.getFaculty());
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("SUM Of Student Enrolled In a Programme:");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total Programme:  " + totalProgramme);
        System.out.println("Total Students Enrolled: " + totalStudents);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
    }

    public void displayTutorialGroupSummaryReport() {
        // Tutorial Group Summary Report
        System.out.println("===============================================================================================================================");
        System.out.println("-------------------------------------------- Tutorial Group Summary Report  ---------------------------------------------------");
        System.out.println("===============================================================================================================================");

        System.out.printf("%-4s| %-25s| %-66s| %-30s%n", "No.", "Program Code", "Programme Name", "Faculty");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        int totalProgramme = ProgramMenu.getProgramList().getNumberOfEntries();
        int totalGroups = 0;
        for (int i = 1; i <= totalProgramme; i++) {
            Programme programme = ProgramMenu.getProgramList().getEntry(i);
            int groupsInProgramme = countGroupsInProgramme(programme.getProgramCode());
            totalGroups += groupsInProgramme;
            System.out.printf("%-4d| %-25s| %-66s| %-30s%n", i, programme.getProgramCode(), programme.getProgramName(), programme.getFaculty());
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("SUM Of Tutorial Group In a Programme:");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Total Programme:  " + totalProgramme);
        System.out.println("Total Tutorial Groups: " + totalGroups);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Press \"Enter\" to continue...");
        scan.nextLine();
    }

    private int countStudentsInProgramme(String programCode) {

        int totalStudents = 0;
        for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            TutorialGroup student = tutorialGroupList.getEntry(i);
            if (student.getProgrammeCODE().equals(programCode)) {
                totalStudents += student.studentList.getNumberOfEntries();
            }
        }
        return totalStudents;
    }

    private int countGroupsInProgramme(String programCode) {
        int totalGroups = 0;
        for (int i = 1; i <= ProgramMenu.getProgramList().getNumberOfEntries(); i++) {
            Programme programme = ProgramMenu.getProgramList().getEntry(i);
            if (programme.getProgramCode().equals(programCode)) {
                totalGroups += programme.getTutorialGroupList().getNumberOfEntries();
            }
        }
        return totalGroups;
    }

    public static ArrayList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public static void setTutorialGroupList(ArrayList<TutorialGroup> tutorialGroupList) {
        TutorialGroupManagement.tutorialGroupList = tutorialGroupList;
    }

    public static void main(String[] args) {
        new MainDao().generate();
        TutorialGroupManagement tgtgm = new TutorialGroupManagement();
        tgtgm.entry();
    }

}
