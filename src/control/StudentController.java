package control;
/**
 *
 * @author Qin Long
 */

import adt.*;
import entity.*;
import boundary.*;
import dao.MainDao;
import java.util.Scanner;
import utility.MessageUI;


public class StudentController {
    
    Scanner sc = new Scanner(System.in);
    StudentUI studentUI = new StudentUI(); 
    CourseUI courseUI = new CourseUI();
    
    public static LinkedList<Student> studentList = new LinkedList<>();
    // private ListInterface<Enrollment> enrollments = new LinkedList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    
    
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
                    searchStudentRegisteredCourse();
                    MessageUI.pressEnter();
                    break;
                case 5:
                    MessageUI.clearScreen();
                    addStudentToCourse();
                    MessageUI.pressEnter();
                    break;
                case 6:
                    MessageUI.clearScreen();
                    removeFromCourse();
                    MessageUI.pressEnter();
                case 7:
                    MessageUI.clearScreen();
                    //calculateFee();
                    MessageUI.pressEnter();
                    break;
                case 8:
                    MessageUI.clearScreen();
                    filterStudent();
                    MessageUI.pressEnter();
                    break;
                case 9:
                    MessageUI.clearScreen();
                    generateReport();
                    MessageUI.pressEnter();
                    break;
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
        studentUI.header();
        for (int i = 1; i <= studentList.getNumberOfEntries();i++){
            System.out.println("\n" + i + ". " + studentList.getEntry(i).toString());
        }
    }
    
    private void addStudent(){
        System.out.print("Please enter student Name: ");
        String studentName = sc.nextLine().trim();
   
        int studentAge = studentUI.verifyAge();
        
        //Display available programs
        System.out.println("All program: ");
        ListInterface<Programme> programList = CourseMenu.programList;
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
        Programme program = programList.getEntry(i); 
        System.out.println(i + ". " + program.getProgramCode());
        }
        
        System.out.print("Select a program: ");
        int selectedProgramIndex = sc.nextInt();
        sc.nextLine();
        String selectedProgramme = programList.getEntry(selectedProgramIndex).getProgramCode();
        String selectedProgrammeName = programList.getEntry(selectedProgramIndex).getProgramName();
        String selectedFaculty = programList.getEntry(selectedProgramIndex).getFaculty();
        
        System.out.printf("| Name: %-10s | Age: %-20s | Programme: %-10s | Faculty: %-10s |",
                studentName, studentAge, selectedProgramme, selectedFaculty);
        boolean ans = MessageUI.comfirmationMessage();
        
        if (ans == true){
        Programme programme = new Programme (selectedProgramme, selectedProgrammeName, selectedFaculty);
        Student newStudent = new Student(studentName, studentAge, programme);
        studentList.add(newStudent);
        System.out.println("Student added successfully!");
        runStudentController();
        }else{
            System.out.println("Do you want add again? <Y/N>");
            boolean answer = MessageUI.enterComfirm();
            if (answer == true){
                addStudent();
            }else{
                MessageUI.displayExitMessage();
                runStudentController();
            }
        }
    }
    

    private void removeStudent(){
        if (studentList.getNumberOfEntries() >= 1){
            viewAllStudent();
            System.out.println("\nPlease select a student that you want to delete: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();
        
            if(selectedIndex >= 0 && selectedIndex <= studentList.getNumberOfEntries()){
                Student newStudent = studentList.getEntry(selectedIndex);
                System.out.println("Student ID: " + newStudent.getStudentID() + "\nName: " + newStudent.getStudentName() + 
                                "\nAge: " + newStudent.getAge() +  "\nProgramme: " + newStudent.getProgramme().getProgramCode() +
                                "\nFaculty: " + newStudent.getProgramme().getFaculty());
                System.out.print("Course:");
                for (int i = 1; i <= newStudent.getCourseList().getNumberOfEntries(); i++){
                    System.out.print(" " +newStudent.getCourseList().getEntry(i).getCourseCode());
                }
                System.out.println("\nTutorial Group: " + newStudent.getTutorialGroup());
                boolean ans = MessageUI.comfirmationMessage();
                if (ans == true){
                    studentList.remove(selectedIndex);
                    System.out.println("Student deleted successfully!");
                    runStudentController();
                }else{
                    System.out.println("Do you want delete again? <Y/N>");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer == true){
                        removeStudent();
                    }else{
                        runStudentController();
                    }
                }
            }else{
                MessageUI.displayInvalidIndexMessage();
            }
        }else{
            MessageUI.displayEmpty();
        }
    }
    
    private void modifyStudentDetail(){
        if (studentList.getNumberOfEntries() >= 1){
            viewAllStudent();
            System.out.print("Please select a student that you want to modify: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();
        
            //check the Index and display selected student detail
            if(selectedIndex <= studentList.getNumberOfEntries()){
                Student oldStudent = studentList.getEntry(selectedIndex);
                oldStudent.toString();
            
                //modify name
                System.out.print("Please enter student Name (press Enter to keep current): ");
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
            
                System.out.println("Student ID: "  + oldStudent.getStudentID() + 
                                "\nName: " + newStudentName + "\nAge: " + newStudentAge + 
                                "\nProgramme: " + newProgramme.getProgramCode() + "\nFaculty: " + newProgramme.getFaculty());
                boolean ans = MessageUI.comfirmationMessage();
        
                if (ans == true){
                    Student newStudent = new Student(newStudentName, newStudentAge, newProgramme);
                    studentList.replace(selectedIndex, newStudent);

                    System.out.println("Student modified successfully!");
                    runStudentController();
                }else{
                    System.out.println("Do you want modify again? <Y/N>");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer == true) {
                        modifyStudentDetail();
                        runStudentController();
                    } else {
                        MessageUI.displayExitMessage();
                        runStudentController();
                    }
                }
            } else {
                MessageUI.displayNotRelated();
                System.out.println("Do you want continue modify student detail? <Y|N>");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true) {
                    modifyStudentDetail();
                } else {
                    runStudentController();
                }
            }
        }else{
            MessageUI.displayEmpty();
        }
    }
    
    private void searchStudentRegisteredCourse(){
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.println("Please select a student that you want search for registed courses");
            int selectedIndex = sc.nextInt();
            sc.nextLine();

            //check the Index and display selected student detail
            if (selectedIndex <= studentList.getNumberOfEntries()) {
                if (studentList.getEntry(selectedIndex).getCourseList().getNumberOfEntries() >= 1) {
                    Student selectedStudent = studentList.getEntry(selectedIndex);
                    selectedStudent.toString();

                    ArrayList<Course> registeredCourse = selectedStudent.getCourseList();
                    courseUI.listHeader();
                    System.out.println(registeredCourse.toString());
                } else {
                    MessageUI.displayEmpty();
                }
            } else {
                MessageUI.displayNotRelated();
                System.out.println("Do you want continue search student for registered courses? <Y|N>");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true) {
                    searchStudentRegisteredCourse();
                } else {
                    runStudentController();
                }
            }
        } else {
            MessageUI.displayEmpty();
        }
    }
    
    private void addStudentToCourse() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("Please select a student for register course: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();

            //check the Index and display selected student detail
            if (selectedIndex >= 1 && selectedIndex <= studentList.getNumberOfEntries()) {
                Student studentDetail = studentList.getEntry(selectedIndex);

                System.out.println("Selected student detail: ");
                studentUI.header();
                System.out.println("   " + studentDetail);

                ArrayList<Course> registeredCourse = studentDetail.getCourseList();
                String faculty = studentDetail.getProgramme().getFaculty();
                LinkedList<Course> availableCourse = new LinkedList<>();
                LinkedList<Course> allCourse = CourseMenu.courseMap.getAllValue();
                System.out.println(allCourse.getNumberOfEntries());

                for (int i = 1; i <= allCourse.getNumberOfEntries(); i++) {
                    if (allCourse.getEntry(i).getFaculty().equals(faculty)) {
                        if (registeredCourse.getNumberOfEntries() >= 1) {
                            for (int j = 1; j <= registeredCourse.getNumberOfEntries(); j++) {
                                if (!allCourse.getEntry(i).equals(registeredCourse.getEntry(j))) {
                            availableCourse.add(allCourse.getEntry(i));
                                   
                                }
                            }
                        }
                    }
                }
                System.out.println("Available Course");
                System.out.println(availableCourse.getNumberOfEntries());
                System.out.println(availableCourse.toString());

                System.out.println("Please select a course that you want to register");
                int courseIndex = sc.nextInt();
                sc.nextLine();

                System.out.println(availableCourse.getEntry(courseIndex).toString());
                boolean ans = MessageUI.comfirmationMessage();

                if (ans = true) {
                    studentList.getEntry(selectedIndex).getCourseList().add(availableCourse.getEntry(courseIndex));
                    System.out.println("Student registered course successfully!");
                    runStudentController();
                } else {
                    System.out.println("Do you want register again? <Y/N>");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer = true) {
                        addStudentToCourse();
                    } else {
                        MessageUI.displayExitMessage();
                        runStudentController();
                    }
                }
            }
        } else {
            MessageUI.displayEmpty();
        }
    }


    private void removeFromCourse(){
        viewAllStudent();
        System.out.println("Please select a student for delete registered course: ");
        int selectedIndex = sc.nextInt();
        sc.nextLine();
        
        //check the Index and display selected student detail
        if(selectedIndex >=1 && selectedIndex <= studentList.getNumberOfEntries()){
            Student studentDetail = studentList.getEntry(selectedIndex);
            
            System.out.println("Selected student detail");
            System.out.println(studentDetail);
            if(!studentDetail.getCourseList().isEmpty()){
                System.out.println(studentDetail.getCourseList().toString());
                System.out.println("Please select a registered course that you want to delete.");
                int courseIndex = sc.nextInt();
                sc.nextLine();
                System.out.println(studentDetail.getCourseList().getEntry(courseIndex).toString());
                boolean ans = MessageUI.comfirmationMessage();
                
                if (ans = true){
                    studentList.getEntry(selectedIndex).getCourseList().remove(courseIndex);
                    System.out.println("Student deleted registered course successfully!");
                    runStudentController();
                }else{
                    System.out.println("Do you want delete again? <Y/N>");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer = true){
                        removeFromCourse();
                    }else{
                        MessageUI.displayExitMessage();
                        runStudentController();
                    }
                }
            }else{
                MessageUI.displayEmpty();
                System.out.println("Do you want select student again? <Y/N>");
                boolean answer = MessageUI.enterComfirm();
                if (answer = true){
                    removeFromCourse();
                }else{
                    MessageUI.displayExitMessage();
                    runStudentController();
                }
            }
        }else{
            MessageUI.displayInvalidChoiceMessage();
            System.out.println("Please try again...");
            removeFromCourse();
        }
    }
    

//    private void calculateFee(){
//        viewAllStudent();
//        System.out.println("Please select a student that you want calculate total course fee for registered courses: ");
//        int selectedIndex = sc.nextInt();
//        sc.nextLine();
//        
//        int totalCourseFee = 0;
//        //check the Index and display selected student detail
//        if(selectedIndex <= studentList.getNumberOfEntries()){
//            Student selectedStudent = studentList.getEntry(selectedIndex);
//            selectedStudent.toString();
//            
//            ArrayList<Course> registeredCourse = selectedStudent.getCourseList();
//            for (int i = 1; i <= registeredCourse.getNumberOfEntries();i++){
//                System.out.println(i + ". | " + registeredCourse.getEntry(i).getCourseCode() + " | " + registeredCourse.getEntry(i).getCourseName()+ 
//                                   " | " + registeredCourse.getEntry(i).getFaculty() + " | " + registeredCourse.getEntry(i).getCreditHours() +
//                                   " | " + registeredCourse.getEntry(i).getCourseStatus() + " | " + registeredCourse.getEntry(i).getCourseFee);
//                totalCourseFee += registeredCourse.getEntry(i).getCourseFee;
//            }
//            System.out.println("Total Course fee: " + totalCourseFee);
//            
//            System.out.println("\n\nDo you want search other student for calculate fee again? <Y/N>");
//                boolean answer = MessageUI.enterComfirm();
//                if (answer = true){
//                    calculateFee();
//                }else{
//                    MessageUI.displayExitMessage();
//                    runStudentController();
//                }
//
//        }
//    }
    
    private void filterStudent(){
        System.out.println("1. Filter Student Name");
        System.out.println("2. Filter Studnet age");
        System.out.println("3. Filter Student Programme");
        System.out.println("4. Filter Student Faculty");
        System.out.println("5. Filter Studnet Courses");
//        System.out.println("6.Filter Student Tutorial Group");
        System.out.println("Please select the number that you want to filter");
        int index = sc.nextInt();
        sc.nextLine();
        
        switch (index) {
            case 1:
                searchStudentName();
                MessageUI.pressEnter();
                break;
            case 2:
                searchStudentAge();
                MessageUI.pressEnter();
                break;
            case 3:
                searchProgramme();
                MessageUI.pressEnter();
                break;
            case 4:
                searchFaculty();
                MessageUI.pressEnter();
                break;
            case 5:
                searchCourse();
                MessageUI.pressEnter();
                break;        
        }
    }
    
    
    
    
    private void generateReport(){}
    
    
    
    private void searchStudentName(){
        ArrayList<Student> filteredStudent = new ArrayList<>();
        
        String partOfName = sc.nextLine();
        
        for (int i = 1; i <=studentList.getNumberOfEntries(); i++){
            if (partOfName.contains(studentList.getEntry(i).getStudentName())){
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if (filteredStudent.getNumberOfEntries() > 0){
            MessageUI.displayEmpty();
            System.out.println(filteredStudent.toString());
        }else{
            MessageUI.displayEmpty();
        }
    }
    
    
    private void searchStudentAge(){
        ArrayList<Student> filteredStudent = new ArrayList<>();
        
        int specifyAge = sc.nextInt();
        sc.nextLine();
        
        for (int i = 1; i <=studentList.getNumberOfEntries(); i++){
            if (specifyAge == (studentList.getEntry(i).getAge())){
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if(filteredStudent.getNumberOfEntries() > 0){
            System.out.println(filteredStudent.toString());
        }else{
            MessageUI.displayEmpty();
        }
    }
    
    private void searchProgramme(){
        ArrayList<Student> filteredStudent = new ArrayList<>();
        
        String studentProgramme = sc.nextLine();
        
        for (int i = 1; i <=studentList.getNumberOfEntries(); i++){
            if (studentProgramme.equals((studentList.getEntry(i).getProgramme().getProgramCode()))){
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if(filteredStudent.getNumberOfEntries() > 0){
            System.out.println(filteredStudent.toString());
        }else{
            MessageUI.displayEmpty();
        }
    }

    private void searchFaculty(){
        ArrayList<Student> filteredStudent = new ArrayList<>();
        
        String studentProgramme = sc.nextLine();
        
        for (int i = 1; i <=studentList.getNumberOfEntries(); i++){
            if (studentProgramme.equals((studentList.getEntry(i).getProgramme().getFaculty()))){
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if(filteredStudent.getNumberOfEntries() > 0){
            System.out.println(filteredStudent.toString());
        }else{
            MessageUI.displayEmpty();
        }
    }
        
    private void searchCourse(){
        ArrayList<Student> filteredStudent = new ArrayList<>();
        
        String studentCourse = sc.nextLine();
        
        for (int i = 1; i <=studentList.getNumberOfEntries(); i++){
            if (studentCourse.equals(courseList.getEntry(i).getCourseCode())){
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if(filteredStudent.getNumberOfEntries() > 0){
            System.out.println(filteredStudent.toString());
        }else{
            MessageUI.displayEmpty();
        }
    }
    
//    private void searchTutorialGroup(){
//        ArrayList<Student> filteredStudent = new ArrayList<>();
//        
//        String studentTutorialGroup = sc.nextLine();
//        
//        for (int i = 1; i <=studentList.getNumberOfEntries(); i++){
//            if (studentTutorialGroup.equals(tutorialGroupList.getEntry(i))){
//                filteredStudent.add(studentList.getEntry(i));
//            }
//        }
//        if(filteredStudent.getNumberOfEntries() > 0){
//            System.out.println(filteredStudent.toString());
//        }else{
//            MessageUI.displayEmpty();
//        }
//    }  
    
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

    public static LinkedList<Student> getStudentList() {
        return studentList;
    }

    public static void setStudentList(LinkedList<Student> studentList) {
        StudentController.studentList = studentList;
    }
    
    
    public static void main(String[] args) {
        new MainDao().generate();
        new StudentController().runStudentController();
    }
}
    

