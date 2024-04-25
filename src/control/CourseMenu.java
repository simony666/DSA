package control;

import boundary.CourseUI;
import dao.*;
import entity.*;
import adt.ListInterface;
import adt.ArrayList;
import adt.HashMap;
import adt.KeyValuePair;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseMenu {
//        public static HashMap<String, Course> courseMap = new HashMap<>();

    public static HashMap<String, Course> courseMap = new HashMap<>();
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
                    case 2:
                        removeCourse(scanner);
                        break;
                    case 3:
                        searchCourses(scanner);
                        break;
                    case 4:
                        amendCourseDetails(scanner);
                        break;
                    case 5:
                        listCoursesByFaculty();
                        break;
                    case 6:
                        listCoursesForProgram(programList, courseMap);
                        break;
                    case 7:
                        addCourseToProgram(scanner, programList);
                        break;
                    case 8:
                        removeCourseFromProgram(scanner, programList);
                        break;
                    case 9:
                        generateSummaryReport();
                        break;
                    case 0:
                        System.out.println("Returning to Main menu...\n");
                        ;
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

    //1.Add Course
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
        int courseFee = CourseUI.promptCourseFee("Course Fee");

        Course newCourse = new Course(courseCode, courseName, faculty, creditHours, courseStatus, courseFee);
        courseMap.put(courseCode, newCourse);

        System.out.println("Course added successfully.");
    }

    //2.Remove Course
    private static void removeCourse(Scanner scanner) {
        System.out.print("Enter course code to remove: ");
        String courseCode = scanner.nextLine();

        Course removedCourse = courseMap.remove(courseCode);
        if (removedCourse != null) {
            System.out.println("Course removed successfully.");

            ProgramMenu.removeCourseFromPrograms(courseCode);
        } else {
            System.out.println("Course not found.");
        }
    }

    //3.Search Course
    private static void searchCourses(Scanner scanner) {
        System.out.println("\nEnter course code to search: ");
        String courseCode = scanner.nextLine();

        Course foundCourse = courseMap.get(courseCode);

        System.err.println(CourseUI.listHeader());
        if (foundCourse != null) {
            System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                    foundCourse.getCourseCode(), foundCourse.getCourseName(),
                    foundCourse.getFaculty(), foundCourse.getCreditHours(), foundCourse.getCourseStatus(), foundCourse.getCourseFee());
            System.out.println(CourseUI.listSeperator());
        } else {
            System.out.println("Course not found.");
        }
    }

    //4.Amend Course Details
    private static void amendCourseDetails(Scanner scanner) {
        System.out.print("Enter course code to amend: ");
        String courseCode = scanner.nextLine();

        Course courseToAmend = courseMap.get(courseCode);
        if (courseToAmend != null) {

            System.out.println(CourseUI.listHeader());
            System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                    courseToAmend.getCourseCode(), courseToAmend.getCourseName(),
                    courseToAmend.getFaculty(), courseToAmend.getCreditHours(),courseToAmend.getCourseStatus(),
                    courseToAmend.getCourseFee());
            System.out.println(CourseUI.listSeperator());

            boolean exitAmendMenu = false;

            while (!exitAmendMenu) {
                CourseUI.amendMenu();

                try {
                    int amendChoice = Integer.parseInt(scanner.nextLine());

                    switch (amendChoice) {
                        case 1:
                            String newCourseCode = CourseUI.promptString("new Course Code", 10);
                            String oldCourseCode = courseToAmend.getCourseCode();
                            if (!newCourseCode.equals(oldCourseCode)) {
                                // Remove the old entry
                                courseMap.remove(oldCourseCode);
                                // Set the new course code
                                courseToAmend.setCourseCode(newCourseCode);
                                // Add a new entry with the new course code
                                courseMap.put(newCourseCode, courseToAmend);
                            }

                            System.out.println("Course Code amended successfully.");
                            break;

                        case 2:
                            String newCourseName = CourseUI.promptString("new Course Name", 40);
                            courseToAmend.setCourseName(newCourseName);
                            System.out.println("Course name amended successfully.");
                            break;

                        case 3:
                            String newFaculty = CourseUI.promptString("new Faculty", 10);
                            courseToAmend.setFaculty(newFaculty);
                            System.out.println("Course description amended successfully.");
                            break;

                        case 4:
                            int newCreditHours = CourseUI.promptCH("new Credit Hours");
                            courseToAmend.setCreditHours(newCreditHours);
                            System.out.println("Credit hours amended successfully.");
                            break;

                        case 5:
                            int newCourseFee = CourseUI.promptCourseFee("new Course Fee");
                            courseToAmend.setCourseFee(newCourseFee);
                            System.out.println("Course Fee amended successfully.");
                            break;

                        case 0:
                            System.out.println("Returning to Course Management Menu...");
                            exitAmendMenu = true;
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid integer.");
                    // Consume the invalid input to avoid an infinite loop
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    //5.List Courses By Faculty
    private static void listCoursesByFaculty() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter faculty name: ");
        String facultyToSearch = scanner.nextLine().trim();
        System.out.println("\nCourses for Faculty: " + facultyToSearch);
        System.out.println(CourseUI.listHeader());

        boolean found = false;
        for (KeyValuePair<String, Course> entry : courseMap.getAllEntries()) {
            Course course = entry.getValue();
            if (course != null && course.getFaculty().equalsIgnoreCase(facultyToSearch)) {
                found = true;
                System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                        course.getCourseCode(), course.getCourseName(),
                        course.getFaculty(), course.getCreditHours(), course.getCourseStatus(), course.getCourseFee());
            }
        }

        if (!found) {
            System.out.println("No courses found for the specified faculty.");
        }

        System.out.println(CourseUI.listSeperator());
    }

    //6.List Courses For Program
    private static void listCoursesForProgram(ListInterface<Programme> programList, HashMap<String, Course> courseMap) {
        Scanner scanner = new Scanner(System.in);

        // Get program code from the user
        System.out.print("Enter program code to list courses: ");
        String programCode = scanner.nextLine();

        // Find the program in the programList
        Programme programme = ProgramMenu.findProgramByCode(programList, programCode);

        if (programme != null) {
            // Get the linked courses from the program
            ArrayList<String> linkedCourses = programme.getLinkedCourses();

            if (!linkedCourses.isEmpty()) {
                System.out.println("\nCourses for Program " + programCode + ":");
                System.out.println(CourseUI.listHeader());

                // Display details of each linked course
                for (String courseCode : linkedCourses) {
                    Course course = courseMap.get(courseCode);
                    if (course != null) {
                        System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                                course.getCourseCode(), course.getCourseName(),
                                course.getFaculty(), course.getCreditHours(), course.getCourseStatus(), course.getCourseFee());
                    }
                }

                System.out.println(CourseUI.listSeperator());
            } else {
                System.out.println("No courses linked to the program.");
            }
        } else {
            System.out.println("Program not found.");
        }
    }

    //7.Add Course To Program
    private static void addCourseToProgram(Scanner scanner, ListInterface<Programme> programList) {
        String programCode = CourseUI.promptString("Program Code", 10);
        Programme programme = ProgramMenu.findProgramByCode(programList, programCode);

        if (programme != null) {
            String courseCode;
            do {
                courseCode = CourseUI.promptString("Course Code", 10);

                if (!courseMap.containsKey(courseCode)) {
                    System.out.println("Course code doesn't exit. Please enter an existing course code.");
                }
            } while (!courseMap.containsKey(courseCode));

            if (!programme.getLinkedCourses().contains(courseCode)) {
                programme.addLinkedCourse(courseCode);
                System.out.println("Course added to program successfully.");
            } else {
                System.out.println("Course is already associated with the program.");
            }
        } else {
            System.out.println("Program not found.");
        }

    }

    //8.Remove Course From Program
    private static void removeCourseFromProgram(Scanner scanner, ListInterface<Programme> programList) {
        String programCode = CourseUI.promptString("Program Code", 10);
        Programme programme = ProgramMenu.findProgramByCode(programList, programCode);

        if (programme != null) {
            String courseCode = CourseUI.promptString("Course Code", 10);

            if (programme.getLinkedCourses().contains(courseCode)) {
                programme.removeLinkedCourse(courseCode);
                System.out.println("Course removed from program successfully.");
            } else {
                System.out.println("Course is not associated with the program.");
            }
        } else {
            System.out.println("Program not found.");
        }
    }

    //9.Generate Summary Report
    private static void generateSummaryReport() {
        System.out.println("\nSummary Report:");
        System.out.println("=======================================");

        int totalCourses = courseMap.size();
        System.out.println("Total Number of Courses: " + totalCourses);

        int totalPrograms = programList.getNumberOfEntries();
        System.out.println("Total Number of Programs: " + totalPrograms);

        System.out.println("\nNumber of Courses Linked per Program:");
        for (Programme programme : programList) {
            int linkedCoursesCount = programme.getLinkedCourses().getNumberOfEntries();
            System.out.println(programme.getProgramCode() + ": " + linkedCoursesCount);
        }

        double averageLinkedCourses = calculateAverageLinkedCourses(programList);
        System.out.println("Average Linked Courses per Program: " + averageLinkedCourses);

        System.out.println("=======================================");
    }

    private static double calculateAverageLinkedCourses(ListInterface<Programme> programList) {

        int totalLinkedCourses = 0;
        for (Programme program : programList) {
            totalLinkedCourses += program.getLinkedCourses().getNumberOfEntries();
        }

        return (double) totalLinkedCourses / programList.getNumberOfEntries();
    }

    public static void main(String[] args) {

        CourseMenu CouserMenu = new CourseMenu();
        courseMap = CourseInitializer.initializeCourses();

        CourseMenu.courseMenu();

    }

}
