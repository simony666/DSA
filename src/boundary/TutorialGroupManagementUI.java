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
        System.out.println("8. Merge tutorial groups based on criteria"); // (not sure, maybe is search tutors)
        System.out.println("9. Generate Reports");
        System.out.println("0. Quit");
        System.out.print("Enter choice (1-9): ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    
    }
    
    
}
