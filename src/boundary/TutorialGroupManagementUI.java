package boundary;

import adt.*;
import control.*;
import entity.*;
import java.util.Scanner;

public class TutorialGroupManagementUI {
    
    
    Scanner scanner = new Scanner(System.in);
    
    public int getTGMMenuChoice() {
        System.out.println("========================================");
        System.out.println("     Tutorial Group Management System   ");
        System.out.println("========================================");
        System.out.println("1. Add a tutorial group to a programme");
        System.out.println("2. Remove a tutorial group from a programme");
        System.out.println("3. List all tutorial groups for a programme");
        System.out.println("4. Adding students to a tutorial group");
        System.out.println("5. Remove a student from a tutorial group");
        System.out.println("6. Change the tutorial group for a student");
        System.out.println("7. List all students in a tutorial group and a programme");
        System.out.println("8. Merge tutorial groups based on criteria");
        System.out.println("9. Generate Reports");
        System.out.println("0. Quit");
        System.out.println("========================================");
        System.out.print("Enter choice (0-9): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    //Display Exists Tutotial Group.
    public void displayTG(){
        for (int i = 1; i <= TutorialGroupManagement.getTutorialGroupList().getNumberOfEntries(); i++) {
               TutorialGroup group = TutorialGroupManagement.getTutorialGroupList().getEntry(i);
               System.out.println(i + ". " + group.getTutorGroupID() + "-" + group.getTutorGroupName());
           }
    }
    //Display Exists Programs
    public void displayPG() {
        // Display all existing programme
    for (int i = 1; i <= Programme.getProgramList().getNumberOfEntries(); i++) {
        System.out.println(i + ". " + Programme.getProgramList().getEntry(i));
        }
    }
    
    //Display Exists Students.
    public void displayStudent() {
        for(int i = 1; i <= StudentController.getStudentList().getNumberOfEntries(); i++) {
            System.out.println(i + "." + StudentController.getStudentList().getEntry(i));
        }
    }
    

   
    
}
    
    

