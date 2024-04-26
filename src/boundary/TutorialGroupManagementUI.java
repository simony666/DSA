package boundary;

import adt.*;
import control.*;
import entity.*;
import java.awt.Font;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;


public class TutorialGroupManagementUI {
    
    
    Scanner scanner = new Scanner(System.in);
    
public int getTGMMenuChoice() {
    System.out.println("╔═════════════════════════════════════════════════╗");
    System.out.println("║     Tutorial Group Management System      ║");
    System.out.println("╠═════════════════════════════════════════════════╣");
    System.out.println("║ 1. Add a tutorial group to a programme");
    System.out.println("║ 2. Remove a tutorial group from a programme");
    System.out.println("║ 3. List all tutorial groups for a programme");
    System.out.println("║ 4. Add students to a tutorial group");
    System.out.println("║ 5. Remove a student from a tutorial group");
    System.out.println("║ 6. Change the tutorial group for a student");
    System.out.println("║ 7. List all students in a tutorial group and a programme");
    System.out.println("║ 8. Merge tutorial groups based on criteria");
    System.out.println("║ 9. Generate Reports");
    System.out.println("║ 0. Quit");
    System.out.println("╚═════════════════════════════════════════════════╝");
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
    for (int i = 1; i <= ProgramMenu.getProgramList().getNumberOfEntries(); i++) {
        System.out.println(i + ". " + ProgramMenu.getProgramList().getEntry(i));
        }
    }
    
    //Display Exists Students.
    public void displayStudent() {
        for(int i = 1; i <= StudentController.getStudentList().getNumberOfEntries(); i++) {
            System.out.println(i + "." + StudentController.getStudentList().getEntry(i));
        }
    }
    
        public void generateReportWindow(String reportContent){
        // Create a JFrame (window)
        JFrame frame = new JFrame("Report Generate");

        // Create a JTextArea (text area) to display text
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Make the text area non-editable

        // Set a monospaced font for the text area
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        textArea.setFont(font);

        // Add the text area to a JScrollPane (scrollable pane)
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the scroll pane to the frame
        frame.getContentPane().add(scrollPane);

        // Set the size and close operation of the frame
        frame.setSize(1200, 800); // Set the size of the window
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close operation

        // Display the frame (window)
        frame.setVisible(true);

        // Example: Set teext in the text area
        String outputText = reportContent;
        textArea.setText(outputText);
        }
    

   
    
}
    
    

