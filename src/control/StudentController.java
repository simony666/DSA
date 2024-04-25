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
import java.util.Scanner;
import utility.MessageUI;


public class StudentController {
    
    Scanner sc = new Scanner(System.in);
    StudentUI studentUI = new StudentUI(); 
    
    private ListInterface<Student> studentList = new LinkedList<>();
    // private ListInterface<Enrollment> enrollments = new LinkedList<>();
    // private ListInterface<Programme> programmeList = new LinkedList<>();
    //private ListInterface<Course> courseList = new LinkedList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
        //private final StudentUI studentUI;
    
    
    public void runStudentController() {
        int choice;
        do {
            MessageUI.clearScreen();
            choice = studentUI.getMenuChoice();
            switch (choice) {
                case 0:
                    //MessageUI.displayExitMessage();
                    MessageUI.clearScreen();
                    viewAllStudent();
                    MessageUI.pressEnter();
                    break;
                case 1:
                    MessageUI.clearScreen();
                    addStudent();
                    MessageUI.pressEnter();
                    break;
                case 2:
                    MessageUI.clearScreen();
                    removeStudent();
                    MessageUI.pressEnter();
                    break;
                case 3:
                    MessageUI.clearScreen();
                    modifyStudentDetail();
                    MessageUI.pressEnter();
                    break;
                case 4:
                    MessageUI.clearScreen();
                    searchStudentRegistedCourse();
                    MessageUI.pressEnter();
                    break;
                case 5:
                    MessageUI.clearScreen();
                    addStudentToCourse();
                    MessageUI.pressEnter();
                    break;
//                case 6:
//                    MessageUI.cls();
//                    removeStudentFromCourses();
//                    MessageUI.pressToContinue();
//                case 7:
//                    MessageUI.cls();
//                    calculateTotalFee();
//                    MessageUI.pressToContinue();
//                    break;
//                case 8:
//                    MessageUI.cls();
//                    filterStudentsAndDisplay();
//                    MessageUI.pressToContinue();
//                    break;
//                case 9:
//                    MessageUI.cls();
//                    viewAllStudents();
//                    MessageUI.pressToContinue();
//                    break;
//                case 10:
//                    MessageUI.cls();
//                    break;
//                case 11:
//                    MessageUI.cls();
//                    break;
//                case 12:
//                    MessageUI.cls();
//                    break;
//                case 13:
//                    MessageUI.cls();
//                    break;
//                case 14:
//                    MessageUI.cls();
            }
        } while (choice != 0);
    }
    
    private void viewAllStudent(){
        System.out.println("-_-_-_-_-_-_All Student List-_-_-_-_-_-_");
        for (int i = 1; i <= studentList.getNumberOfEntries();i++){
            System.out.println(i + ". " + studentList.getEntry(i));
        }
    }
    
    private void addStudent(){
        System.out.println("Please enter student Name: ");
        String studentName = sc.nextLine().trim();
   
        int studentAge = studentUI.verifyAge();
        
        //Display available programs
        System.out.println("All program:");
        ListInterface<Programme> programList = CourseMenu.programList;
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
        Programme program = programList.getEntry(i); 
        System.out.println(i + ". " + program.getProgramCode());
        }
        
        System.out.println("Select a program");
        int selectedProgramIndex = sc.nextInt();
        sc.nextLine();
        String selectedProgramme = programList.getEntry(selectedProgramIndex).getProgramCode();
        String selectedProgrammeName = programList.getEntry(selectedProgramIndex).getProgramName();
        String selectedFaculty = programList.getEntry(selectedProgramIndex).getFaculty();
        
        
//        ListInterface<Course> courseList = null;
//        
//        TutorialGroup tutorialGroup = null;
        System.out.println("Name: " + studentName + "\tAge: " + studentAge + 
                           "\tProgramme: " + selectedProgramme + "\tFaculty: " + selectedFaculty);
        boolean ans = MessageUI.comfirmationMessage();
        
        if (ans = true){
        Programme programme = new Programme (selectedProgramme, selectedProgrammeName, selectedFaculty);
        Student newStudent = new Student(studentName, studentAge, programme);
        studentList.add(newStudent);
        System.out.println("Student added successfully!");
        runStudentController();
        }else{
            System.out.println("Do you want add again? <Y/N>");
            boolean answer = MessageUI.enterComfirm();
            if (answer = true){
                addStudent();
            }else{
                MessageUI.displayExitMessage();
                runStudentController();
            }
        }
    }
    

    private void removeStudent(){
        viewAllStudent();
        System.out.println("Please select a student that you want to delete: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        
        if(selectedIndex <= studentList.getNumberOfEntries()){
            Student newStudent = studentList.getEntry(selectedIndex);
            System.out.println("Student ID：" + newStudent.getStudentID() + "\tName: " + newStudent.getStudentName() + 
                               "\tAge: " + newStudent.getAge() +  "\tProgramme: " + newStudent.getProgramme().getProgramCode() +
                               "\tFaculty: " + newStudent.getProgramme().getFaculty() + "\tCourse Registed: " + newStudent.getCourseList()+
                               "\tTutorial Group: " + newStudent.getTutorialGroup());
            boolean ans = MessageUI.comfirmationMessage();
            if (ans = true){
                studentList.remove(selectedIndex);
                System.out.println("Student deleted successfully!");
                runStudentController();
            }else{
                System.out.println("Do you want delete again? <Y/N>");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true){
                    removeStudent();
                }else{
                    MessageUI.displayExitMessage();
                    runStudentController();
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
            ListInterface<Programme> programList = CourseMenu.programList;
            for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Programme program = programList.getEntry(i); 
            System.out.println(i + ". " + program.getProgramCode());
            }
        
            System.out.println("Select a program (press Enter to keep current): ");
            String programmeInput = sc.nextLine().trim();
            Programme newProgramme = new Programme(null, null, null);
            if(!programmeInput.isEmpty()){
                int indexProgramme = Integer.parseInt(programmeInput);
                if(indexProgramme <= programList.getNumberOfEntries()){
                   String newProgrammeCode = programList.getEntry(indexProgramme).getProgramCode();
                   String newProgrammeName = programList.getEntry(indexProgramme).getProgramName();
                   String newFaculty = programList.getEntry(indexProgramme).getFaculty();
                   
                   newProgramme.setProgramCode(newProgrammeCode);
                   newProgramme.setProgramName(newProgrammeName);
                   newProgramme.setFaculty(newFaculty);
                   
                }
            }else{
                newProgramme.setProgramCode(oldStudent.getProgramme().getProgramCode());
                newProgramme.setProgramName(oldStudent.getProgramme().getProgramName());
                newProgramme.setFaculty(oldStudent.getProgramme().getFaculty());
            }
            
            System.out.println("Student ID"  + oldStudent.getStudentID() + 
                               "\tName: " + newStudentName + "\tAge: " + newStudentAge + 
                               "\tProgramme: " + newProgramme.getProgramCode() + "\tFaculty: " + newProgramme.getFaculty());
            boolean ans = MessageUI.comfirmationMessage();
        
            if (ans = true){
                Student newStudent = new Student(newStudentName, newStudentAge, newProgramme);
                studentList.replace(selectedIndex, newStudent);

                System.out.println("Student modified successfully!");
                runStudentController();
            }else{
                System.out.println("Do you want modify again? <Y/N>");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true){
                    modifyStudentDetail();
                    runStudentController();
                }else{
                    MessageUI.displayExitMessage();
                    runStudentController();
                }
            }
        }else{
            MessageUI.displayNotRelated();
            System.out.println("Do you want continue modify student detail? <Y|N>");
            boolean answer = MessageUI.enterComfirm();
            if (answer = true){
                modifyStudentDetail();
            }else{
                runStudentController();
            }
        }
    }
    
    private void searchStudentRegistedCourse(){
        viewAllStudent();
        System.out.println("Please select a student that you want search for registed courses: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        
        //check the Index and display selected student detail
        if(selectedIndex <= studentList.getNumberOfEntries()){
            Student selectedStudent = studentList.getEntry(selectedIndex);
            selectedStudent.toString();
            
            ArrayList<Course> registedCourse = selectedStudent.getCourseList();
            //for(int i = 0; i <=registedCourse.getNumberOfEntries(); i++){
                System.out.println(registedCourse.toString());
            //}   
        }else{
            MessageUI.displayNotRelated();
            System.out.println("Do you want continue search student for registed courses? <Y|N>");
            boolean answer = MessageUI.enterComfirm();
            if (answer = true){
                searchStudentRegistedCourse();
            }else{
                runStudentController();
            }
        }
    }
    
    
    private void addStudentToCourse(){
        viewAllStudent();
        System.out.println("Please select a student for register course: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        
        //check the Index and display selected student detail
        if(selectedIndex >=1 && selectedIndex <= studentList.getNumberOfEntries()){
            Student studentDetail = studentList.getEntry(selectedIndex);
            
            System.out.println("Selected student detail");
            System.out.println(studentDetail);
        
            System.out.println("List all available course");
            //ArrayList<Course> allCourse = CourseMenu.courseMap.getAllValue();
            ArrayList<Course> registedCourse = studentDetail.getCourseList();
            String faculty = studentDetail.getProgramme().getFaculty();
            ArrayList<Course> availableCourse = new ArrayList<>();
            //HashMap<String, Course> courseMap = CourseMenu.courseMap;
            LinkedList<Course> allCourse = CourseMenu.courseMap.getAllValue();
            for (int i = 1; i <= allCourse.getNumberOfEntries(); i++){
                if (allCourse.getEntry(i).getFaculty().equals(faculty)){
                    for (int j = 1; j <= registedCourse.getNumberOfEntries(); j++){
                        if (allCourse.getEntry(i).equals(registedCourse.getEntry(j))){
                            //course already exist in registered course
                            break;
                        }
                    }
                    availableCourse.add(allCourse.getEntry(i));
                } 
            }
            System.out.println("Available Course");
                System.out.println(availableCourse.toString());
            
            System.out.println("Please select a course that you want to register");
            int courseIndex = sc.nextInt();
            sc.nextLine();
            availableCourse.getEntry(courseIndex).toString();
            boolean ans = MessageUI.comfirmationMessage();
            
            if (ans = true){
                studentList.getEntry(selectedIndex).getCourseList().add(availableCourse.getEntry(courseIndex));
                System.out.println("Student registered course successfully!");
                runStudentController();
            }else{
                System.out.println("Do you want register again? <Y/N>");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true){
                    addStudentToCourse();
                }else{
                    MessageUI.displayExitMessage();
                    runStudentController();
                }
            }
        }
      
//        System.out.println("List all available courses:");
//        ArrayList<Course> registeredCourses = studentDetail.getCourseList();
//        String faculty = studentDetail.getFaculty();
//        ArrayList<Course> availableCourses = new ArrayList<>();
//
//        // Retrieve all courses from the course map
//        LinkedList<Course> allCourses = CourseMenu.courseMap.getAllValue();
//
//        // Iterate through all courses
//        for (Course course : allCourses) {
//            // Check if the course belongs to the same faculty as the student
//            if (course.getFaculty().equals(faculty)) {
//                // Check if the student is not already registered for this course
//                if (!registeredCourses.contains(course)) {
//                    availableCourses.add(course);
//                }
//            }
//        }   
    }


    private void removeFromCourse(){}
    
    
    
    
    
    
    private void calculateFee(){}
    private void filterStudent(){}
    private void generateReport(){}
    
    

    
    
    //assigment team 
    public Student getStudent(String id){
        for (int i=1;i<=this.studentList.getNumberOfEntries();i++){
            if(this.studentList.getEntry(i).getStudentID() == id){
                return this.studentList.getEntry(i);
            }
        }
        //using student id to find the student and return the student all detail(student object)
        //if no found return null
        return null;
    }
    
    public static void main(String[] args) {
        new StudentController().runStudentController();
    }
}
    

