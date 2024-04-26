package boundary;

//import entity.Tutor;
import adt.ArrayList;
import entity.*;
import java.awt.Font;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TeachingAssignmentUI {
    
    Scanner scanner = new Scanner(System.in);
    
    public int getTAMenuChoice() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Assign tutor to courses");
        System.out.println("2. Assign tutorial groups to a tutor");
        System.out.println("3. Add tutors to a tutorial group for a course");
        System.out.println("4. Search courses under a tutor");
        System.out.println("5. Search tutors for a course (T, P, L)");
        System.out.println("6. List tutors and tutorial groups for a course");
        System.out.println("7. List courses for each tutor");
        System.out.println("8. Filter tutors based on critetia"); // (not sure, maybe is search tutors)
        System.out.println("9. Generate Reports");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public int getCourseTypeChoice() {
        System.out.println("\nAssign Tutor to Course Menu");
        System.out.println("1. Assign to Tutorial");
        System.out.println("2. Assign to Practical");
        System.out.println("3. Assign to Lecture");
        System.out.println("0. Cancel");
        System.out.println("\n");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }
    
    public int reportMenu(){
        System.out.println();
        System.out.println("Report Generate Menu\n");
        System.out.println("1. Generate Tutors report");
        System.out.println("2. Generate Tutorial groups report");
        System.out.println();
        System.out.print("Enter number : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    
    public int reoportOutputTypeMenu(){
        System.out.println();
        System.out.println("Report Outpot Type Menu\n");
        System.out.println("1. Console Output Format");
        System.out.println("2. Window Output Format");
        System.out.println();
        System.out.print("Enter number : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    
    public String generateReport1(String content) {
        String BuildString ="";
        BuildString += ReportTop() + ReportTitle() + getCurrentDateTime();
        
        return BuildString;
    }
    
    public String generateReport2(String content) {
        String BuildString ="";
        BuildString += ReportTop() + ReportTitle() + getCurrentDateTime();
        
        return BuildString;
    }
    
    public String ReportTop() {
        String BuildString = "";
        BuildString += "=====================================================================================";
        BuildString += "\n          TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY";
        BuildString += "\n";

        return BuildString;
    }
    
    public String ReportTitle() {
        String BuildString = "";
        BuildString += "                        Teaching Assignment SUMMARY REPORT";
        BuildString += "\n                      ----------------------------------";
        return BuildString;
    }
    
    public String ReportFooter(){
        String BuildString = "";
        BuildString += "\n";
        BuildString += "\n            END OF THE Teaching Assignment SUMMARY REPORT";
        BuildString += "\n====================================================================================";
        return BuildString;
    }
    
    public String SeparateLine(){
        String BuildString = "";
        
        BuildString += "\n------------------------------------------------------------------------------------\n";
        
        return BuildString;
    }
    
    public String getCurrentDateTime() {
        String BuildString  = "";

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, h:mma");

        // Format the current date and time using the formatter
        String formattedDateTime = currentDateTime.format(formatter);

        // Print the formatted date
        
        BuildString += "\nGenerated Report at: " + formattedDateTime + "\n\n";
        
        return BuildString;
    }
    
    public void generateConsoleReport(String report){
        System.out.println(report);
    }
    
    // report output type 2
    public void generateWindowReport(String report){
        generateReportWindowOutoput(report);
    }
    
    public void generateReportWindowOutoput(String reportContent){
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

        // Example: Set text in the text area
        String outputText = reportContent;
        textArea.setText(outputText);
    }
    
    public static void listAllTutors(String outputStr) {
      System.out.println("\nList of Tutors:\n" + outputStr);
    }
    
    public void TutorNotFound(){
        System.out.println("\nThe Tutor Not Found, It is not related Tutor exist.");
    }
    
    public void TutorIsEmpty(){
        System.out.println( "\nThe Tutor Not Found, It is Empty.");
    }
    
    public void CourseNotFound(){
        System.out.println("\nThe Course Not Found, It is not related Course exist.");
    }
    
    public void CourseIsEmpty(){
        System.out.println( "\nThe Course Not Found, It is Empty.");
    }
    
    public void TutorGrpNotFound(){
        System.out.println("\nThe Tutorial Group Not Found, It is not related Tutorial Group exist.");
    }
    
    public void TutorGrpIsEmpty(){
        System.out.println( "\nThe Tutorial Group Not Found, It is Empty.");
    }

    public void printTutorDetails(Tutor tutor, Course course) {
      System.out.println("Tutor Details");
      System.out.println("Tutor ID:" + tutor.getTutorID());
      System.out.println("Tutor Name: " + tutor.getTutorName());
      System.out.println("Course: " +course.getCourseName());
    }
    
    public String inputTutorID(){
        System.out.print("Enter Tutor ID: ");
        String tutorID = scanner.nextLine();
        return tutorID;
    }
    
    public String inputTutorName(){
        System.out.print("Enter Tutor name: ");
        String tutorName = scanner.nextLine();
        return tutorName;
    }
    
    public String inputTutorCourse(){
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        return course;
    }
    
    public String inputTutor(){
        System.out.print("Enter Tutor: ");
        String Tutor = scanner.nextLine();
        return Tutor;
    }
    
    public int getIndex(){
        System.out.print("Enter Desire Number: ");
//        int index = Integer.parseInt(scanner.nextLine()) - 1;
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
    
    public String getInput() {
        
        String Input = scanner.nextLine().trim();
        
        return Input;
        
    }
    
    public Tutor inputTutorDetails() {
        String tutorID = inputTutorID();
        String tutorName = inputTutorName();
//        String course = inputTutorCourse();
        System.out.println();
        return new Tutor(tutorID, tutorName);
    }
    
    public static String getAssignedTutorToCourse(Tutor selectedTutor, Course selectedCourse) {
    
    return "Successfully assigned tutor " + selectedTutor.getTutorName() 
            + " to course " + selectedCourse.getCourseName();
}
    
    //Course & Tutorial Groups UITesting
    public void listAllCourse(String outputCourseList) {
      System.out.println("\nList of Courses:\n" + outputCourseList);
    }
    
    public void listAllTutorialGroup(String outputTutorialGroupList){
        System.out.println("\nList of Tutorial Groups:\n" + outputTutorialGroupList);
    }
    
    public void displayAllTutorList(String outputTutorList){
        System.out.println("\nList of Tutor:\n" + outputTutorList);
    }
    
    public <T> String getAllList(ArrayList<T> list) { //delete
        StringBuilder outputList = new StringBuilder();
        for (int i = 0; i <= list.getNumberOfEntries(); ++i) {
            outputList.append(i + 1).append(".").append(list.getEntry(i)).append("\n");
        }
        return outputList.toString();
    }
    
    public <T> void printAllList(ArrayList<T> list) {
        for (int i = 1; i <= list.getNumberOfEntries(); ++i) {
            System.out.println((i) + "." + list.getEntry(i));
        }
    }
    
    
    
    public <T> void printSpecificList(T t) {
        System.out.println(t);
    }
    
}
