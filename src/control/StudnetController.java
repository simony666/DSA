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
    // private ListInterface<Programme> programmeList = new LinkedList<>();
    private ListInterface<Course> courseList = new LinkedList<>();
        //private final StudentUI studentUI;
    
    
    private void viewAllStudent(){
        for (int i = 1; i <= studentList.getNumberOfEntries();i++){
            System.out.println(i + ". " + studentList.getEntry(i));
        }

        System.out.println("Press 'Enter' to continue.");
        sc.nextLine();
        
    }
    
    private void addStudent(){
        System.out.println("Please enter student Name: ");
        String studentName = sc.nextLine().trim();
   
        StudentUI studentUI = new StudentUI(); 
        int studentAge = studentUI.verifyAge();
        
        //Display available programs
        System.out.println("All program:");
        ListInterface<Program> programList = ProgrammeController.programList;
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
        Program program = programList.getEntry(i); 
        System.out.println(i + ". " + program.getProgramCode());
        }
        
        System.out.println("Select a program");
        int selectedProgramIndex = sc.nextInt();
        sc.nextLine();
        Programme selectedProgram = programList.getEntry(selectedProgramIndex).getProgramCode();
        
        String selectedFaculty = programList.getEntry(selectedProgramIndex).getFaculty();
        
//        ListInterface<Course> courseList = null;
//        
//        TutorialGroup tutorialGroup = null;
        System.out.println("Name: " + studentName + "\tAge: " + studentAge + 
                           "\tProgramme: " + selectedProgram + "\tFaculty: " + selectedFaculty);
        boolean ans = MessageUI.comfirmationMessage();
        
        if (ans = true){
        Student newStudent = new Student(studentName, studentAge, selectedProgram);
        studentList.add(newStudent);

        System.out.println("Student added successfully!");
        }else{
            System.out.println("Do you want add again? (Y/N)");
            boolean answer = MessageUI.enterComfirm();
            if (answer = true){
                addStudent();
            }else{
                //return to Previous page
            }
        }
    }
    

    private void deleteStudent(){
        viewAllStudent();
        System.out.println("Please select a student that you want to delete: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        
        if(selectedIndex <= studentList.getNumberOfEntries()){
            Student newStudent = studentList.getEntry(selectedIndex);
            System.out.println("Student ID：" + newStudent.getStudentID() + "\tName: " + newStudent.getStudentName() + 
                               "\tAge: " + newStudent.getAge() +  "\tProgramme: " + newStudent.getProgramme() +
                               "\tFaculty: " + newStudent.getFaculty() + "\tCourse Registed: " + newStudent.getCourseList()+
                               "\tTutorial Group: " + newStudent.getTutorialGroup());
            boolean ans = MessageUI.comfirmationMessage();
            if (ans = true){
                studentList.remove(selectedIndex);
                System.out.println("Student deleted successfully!");
            }else{
                System.out.println("Do you want add again? (Y/N)");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true){
                    deleteStudent();
                }else{
                    //return to Previous page
                }
            }
            
        }
    }
    
    private void modifyStudentDetail(){
        viewAllStudent();
        System.out.println("Please select a student that you want to modify: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        
        //check the Index and display selected student detail
        if(selectedIndex <= studentList.getNumberOfEntries()){
            Student oldStudent = studentList.getEntry(selectedIndex);
            oldStudent.toString();
            
            //modify name
            System.out.println("Please enter student Name (press Enter to keep current): ");
            String studentName = sc.nextLine().trim();
            
            String newStudentName = null;
            if(studentName.isEmpty()){
                newStudentName = oldStudent.getStudentName();
            }else{
                newStudentName = studentName;
            }
            
            //modify age
            System.out.print("Please enter student age (press Enter to keep current): ");
            String ageInput = sc.nextLine().trim();
            int newStudentAge = 0;
            if (!ageInput.isEmpty()) {
                try {
                    int intAge = Integer.parseInt(ageInput);
                    if (intAge >= 18) {
                        newStudentAge = intAge;
                    } else {
                        System.out.println("Invalid input. Please try again(Age must greater than or equal to 18)");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value for age.");
                }
            }else {
                newStudentAge = oldStudent.getAge();
            }
            
            //modify programme
            System.out.println("All program:");
            ListInterface<Program> programList = ProgrammeController.programList;
            for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Program program = programList.getEntry(i); 
            System.out.println(i + ". " + program.getProgramCode());
            }
        
            System.out.println("Select a program (press Enter to keep current): ");
            String programmeInput = sc.nextLine().trim();
            Programme newProgramme = null;
            String newFaculty = null;
            if(!programmeInput.isEmpty()){
                int indexProgramme = Integer.parseInt(programmeInput);
                if(indexProgramme <= programList.getNumberOfEntries()){
                   newProgramme = programList.getEntry(indexProgramme).getProgramCode();
                   newFaculty = programList.getEntry(indexProgramme).getFaculty();
                }
            }else{
                newProgramme = oldStudent.getProgramme();
                newFaculty = oldStudent.getFaculty();
            }
            
            System.out.println("Name: " + newStudentName + "\tAge: " + newStudentAge + 
                           "\tProgramme: " + newProgramme + "\tFaculty: " + newFaculty);
            boolean ans = MessageUI.comfirmationMessage();
        
            if (ans = true){
                Student newStudent = new Student(newStudentName, newStudentAge, newProgramme);
                studentList.add(newStudent);

                System.out.println("Student added successfully!");
            }else{
                System.out.println("Do you want add again? (Y/N)");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true){
                    addStudent();
                }else{
                    //return to Previous page
                }
            }
        }else{
            MessageUI.displayNotRelated();
            System.out.println("Do you want continue modify student detail?");
            boolean answer = MessageUI.enterComfirm();
            if (answer = true){
                addStudent();
            }else{
                //return to Previous page
            }
        }
    }
    
    
    
    
    
    
    
    private void registeredCourse(){}
    private void selectCourse(){}
    private void removeFromCourse(){}
    private void calculateFee(){}
    private void filterStudent(){}
    private void generateReport(){}
    
    

    
    
    //assigment team 
    private void getStudent(){
        //using student id to find the student and return the student all detail(student object)
        //if no found return null
    }
}
    

