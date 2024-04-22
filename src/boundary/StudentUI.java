/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author User
 */
public class StudentUI {
        Scanner sc = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("                        Student Registration Management");
        System.out.println("====================================================================================");
        System.out.println();
        System.out.println("*-----------------------------------------------------------------------------------*");
        System.out.println("|                                    Student                                        |");
        System.out.println("|-----------------------------------------------------------------------------------|");
        System.out.println("| 1. Add new students                                                               |");
        System.out.println("| 2. Remove a student                                                               |");
        System.out.println("| 3. Ammend student details                                                         |");
        System.out.println("| 4. Search students for registered courses                                         |");
        System.out.println("| 5. Add Student to a few course                                                    |");
        System.out.println("| 6. Remove student from a courses                                                  |");
        System.out.println("| 7. Fee paid for registered courses                                                |");
        System.out.println("| 8. Filters student                                                                |");
        System.out.println("| 9. View all students                                                              |");
        System.out.println("| 10. Generate summary report                                                       |");
        System.out.println("*-----------------------------------------------------------------------------------*");

        return MessageUI.getChoice();
    }

    public void displayEditStudent() {
        System.out.println("1. Edit student ID");
        System.out.println("2. Edit student name");
        System.out.println("3. Edit student age");
        System.out.println("4. Edit programme");
        System.out.println("5. Edit all details");

        System.out.print("Enter your choice: ");
    }

    public void displayTryAgain() {
        System.out.println("1. Try again");
        System.out.println("2. Back to previous page");

        System.out.print("Choose an option: ");
    }

    public void displayFilterMenu() {
        System.out.println("Filter by:");
        System.out.println("1. Student ID");
        System.out.println("2. Student Name");
        System.out.println("3. Programme");
        System.out.println("4. Intake Semester");
        System.out.println("5. Course ID");
        System.out.println("6. Course Status");

        System.out.print("Enter your choices (separated by commas): ");
    }
}
