package control;
        
import boundary.CourseUI;
import dao.*;
import entity.*;
import adt.ListInterface;
import adt.ArrayList;
import adt.HashMap;
import adt.KeyValuePair;
import adt.DynamicLinkedList;
import java.util.InputMismatchException;
import java.util.Scanner;

    public class CourseMenu { 
//        public static HashMap<String, Course> courseMap = new HashMap<>();
        public static HashMap<String, Course> courseMap = CourseInitializer.initializeCourses();
        public static ListInterface<Programme> programList = Programme.programList;
        
        
        
        public void updateCourseMap(HashMap<String, Course> newCourseMap) {
        this.courseMap = newCourseMap;
}


        public static void courseMenu() {
            
            Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                CourseUI.displayMenu();
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (choice) {
                        case 1:
                            addCourse();
                            break;
                        case 0:
                            System.out.println("Returning to Main menu...\n");
                            //Main Menu;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                    choice = -1; // Assign an invalid value to continue the loop
                }
            } while (choice != 0);

            scanner.close();
        }

        
        private static void addCourse() {
            String courseCode;
            do {
                courseCode = CourseUI.promptString("Course Code", 10);

                if (courseMap.containsKey(courseCode)) {
                    System.out.println("Course code already exists. Please enter a unique course code.");
                }
            } while (courseMap.containsKey(courseCode));
            String courseName = CourseUI.promptString("Course Name", 40);
            String faculty = CourseUI.promptString("Faculty", 10);
            int creditHours = CourseUI.promptCH("Credit Hours");
            String courseStatus = CourseUI.promptCourseStatus("Course Status");

            Course newCourse = new Course(courseCode, courseName, faculty, creditHours, courseStatus);
            courseMap.put(courseCode, newCourse);

            System.out.println("Course added successfully.");
        }
    

              
        public static void main(String[] args){
        
            CourseMenu CouserMenu = new CourseMenu();
            
            CourseMenu.courseMenu();
            
        
        }
    }
