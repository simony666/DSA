package boundary;

//import entity.Tutor;
import entity.*;
import java.util.Scanner;

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
        return index;
    }
    
    public String getSearchInput() {
        
        String searchInput = scanner.nextLine();
        return searchInput;
    }
    
    public String getInput() {
        
        String Input = scanner.nextLine();
        return Input;
    }
    
    public Tutor inputTutorDetails() {
        String tutorID = inputTutorID();
        String tutorName = inputTutorName();
//        String course = inputTutorCourse();
        System.out.println();
        return new Tutor(tutorID, tutorName);
    }
    
    
//    public Courses inputAssignTutorGrpToTutor() {
//        String courseName = inputTutorCourse();
//        System.out.println();
//        return new Courses("",courseName);
//    } 
    
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
}
