/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
/**
 *
 * @author Qin Long
 */

import adt.*;
import entity.*;
import boundary.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.MessageUI;


public class StudnetController {
    
    Scanner sc = new Scanner(System.in);
    
    private ListInterface<Student> studentList = new LinkedList<>();
    // private ListInterface<Enrollment> enrollments = new LinkedList<>();
    private ListInterface<Programme> programmeList = new LinkedList<>();
    private ListInterface<Course> courseList = new LinkedList<>();
        //private final StudentUI studentUI;
    
    
    private void viewAllStudent(){
        for (int i = 1; i <= studentList.getNumberOfEntries();i++){
            System.out.println("");
        }
    }
    
    private void addStudent(){
        System.out.println("Please enter student Name : ");
        String studentName = sc.nextLine();
        
        int studentAge = 0;
        try {
            System.out.print("Please enter age: ");
            int age = sc.nextInt();
            sc.nextLine();
            studentAge = age;

            if (age < 18 ) {
                throw new IllegalArgumentException("Invalid age");
            }
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("Invalid age")) {
                System.out.println("Invalid seat price input. Please try again(Age must greater than or equal to 18)");
            }
            
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a age.");
            sc.nextLine();
        }
        
        //Display available programs
        System.out.println("All program:");
        ListInterface<Program> programList = ProgrammeController.programList;
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
        Program program = programList.getEntry(i);
        System.out.println(i + ". " + program.getProgramCode());
        }
        
        System.out.println("Select a program");
        int selectedProgram = sc.nextInt();
        sc.nextLine();
        String selectedProgram = programList.getEntry(programNumber).getProgramCode();
        
        // faculty can auto fill base on program
        
        String courseRegistered = null;
        
        Student newStudent = new Student(studentName, studentAge, selectedProgram, faculty, courseRegistered);
        studentList.add(newStudent);

        System.out.println("Student added successfully!");
}
    

