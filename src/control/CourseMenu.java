package control;

import boundary.CourseUI;
import entity.*;
import adt.ListInterface;
import adt.ArrayList;
import adt.HashMap;
import adt.KeyValuePair;
import dao.MainDao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseMenu {
//        public static HashMap<String, Course> courseMap = new HashMap<>();

    public static HashMap<String, Course> courseMap = new HashMap<>();
    public static ListInterface<Programme> programList = ProgramMenu.programList;
    
    MainCrtl  mainController = new MainCrtl();

    public void updateCourseMap(HashMap<String, Course> newCourseMap) {
        this.courseMap = newCourseMap;
    }

    public void courseMenu() {

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            CourseUI.displayMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

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
                        generateSummaryReport(scanner);
                        break;
                    case 0:
                        System.out.println("Returning to Main menu...\n");
                        mainController.entry();
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
        // Display all courses
        System.out.println("\nAvailable Courses:");
        for (Course course : courseMap.getAllValue()) {
            System.out.println(course.getCourseCode() + " - " + course.getCourseName());
        }

        System.out.print("\nEnter course code to remove: ");
        String courseCode = scanner.nextLine();

        Course removedCourse = courseMap.remove(courseCode);
        if (removedCourse != null) {
            System.out.println("Course removed successfully.");
            ProgramMenu.removeCourseFromPrograms(courseCode);
        } else {
            System.out.println("Course not found. Please enter a valid course code or press 0 to exit.");

            String input;
            do {
                System.out.print("Enter course code or press 0 to exit: ");
                input = scanner.nextLine();

                if (input.equals("0")) {
                    return; // Exit the method if the user enters 0
                }

                removedCourse = courseMap.remove(input);
                if (removedCourse != null) {
                    System.out.println("Course removed successfully.");
                    ProgramMenu.removeCourseFromPrograms(input);
                    return; // Exit the method after successfully removing the course
                } else {
                    System.out.println("Course not found. Please try again.");
                }
            } while (true); // Repeat until a valid course code is entered or the user chooses to exit
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
        System.out.println("\nAvailable Courses:");
        for (Course course : courseMap.getAllValue()) {
            System.out.println(course.getCourseCode() + " - " + course.getCourseName());
        }
        System.out.print("Enter course code to amend: ");
        String courseCode = scanner.nextLine();

        Course courseToAmend = courseMap.get(courseCode);
        if (courseToAmend != null) {

            System.out.println(CourseUI.listHeader());
            System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                    courseToAmend.getCourseCode(), courseToAmend.getCourseName(),
                    courseToAmend.getFaculty(), courseToAmend.getCreditHours(), courseToAmend.getCourseStatus(),
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
    private static void generateSummaryReport(Scanner scanner) {
        int reportChoice;
        do {
            System.out.println("\n======================================");
            System.out.println("\tSelect a Summary Report");
            System.out.println("======================================");
            System.out.println("1. Course Summary Report");
            System.out.println("2. Programme Summary Report");
            System.out.println("======================================");
            System.out.print("Enter (1-2): ");
            reportChoice = scanner.nextInt();
            scanner.nextLine();

            switch (reportChoice) {
                case 1:
                    generateCourseReport();
                    break;
                case 2:
                    generateProgrammeReport();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } while (reportChoice != 1 && reportChoice != 2);
    }

    private static void generateCourseReport() {
        System.out.println("\n                                             Course Summary Report:");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mma")));

        System.err.println(CourseUI.listHeader());
        for (KeyValuePair<String, Course> entry : courseMap.getAllEntries()) {
            Course course = entry.getValue();
            System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                    course.getCourseCode(), course.getCourseName(),
                    course.getFaculty(), course.getCreditHours(), course.getCourseStatus(), course.getCourseFee());
        }
        System.err.println(CourseUI.listSeperator());

        int totalCourses = courseMap.size();
        System.out.println("Total Number of Courses: " + totalCourses);

        int totalPrograms = programList.getNumberOfEntries();
        System.out.println("Total Number of Programs: " + totalPrograms);

        System.out.println("\nNumber of Courses Linked per Program:");
        for (Programme programme : programList) {
            int linkedCoursesCount = programme.getLinkedCourses().getNumberOfEntries();
            System.out.println(programme.getProgramCode() + ": " + linkedCoursesCount);
        }

        System.out.println("\n\n                                       End Of Course Summary Report");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    private static void generateProgrammeReport() {
        System.out.println("\n                                             Programme Summary Report:");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mma")));

        System.err.println(CourseUI.programSummaryHeader());
        for (Programme programme : programList) {
            System.out.printf("| %-12s | %-70s | %-10s |\n", programme.getProgramCode(), programme.getProgramName(), programme.getFaculty());
        }
        System.out.println("======================================================================================================\n");

        System.out.println("Programmes and their Linked Courses:");
        for (Programme programme : programList) {
            System.out.println("\nProgram Code: " + programme.getProgramCode() + " - Program Name: " + programme.getProgramName());
            System.out.println(CourseUI.listHeader());

            int totalCreditHours = 0;
            int totalProgramFee = 0;

            for (String courseCode : programme.getLinkedCourses()) {
                Course course = courseMap.get(courseCode);
                if (course != null) {
                    System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                            course.getCourseCode(), course.getCourseName(),
                            course.getFaculty(), course.getCreditHours(), course.getCourseStatus(), course.getCourseFee());
                    totalCreditHours += course.getCreditHours();
                    totalProgramFee += course.getCourseFee();

                }
            }
            System.out.println(CourseUI.listSeperator());

            System.out.println("Total Credit Hours for Program " + programme.getProgramCode() + ": " + totalCreditHours + " Hours");
            System.out.println("Total Fee for Program " + programme.getProgramCode() + ": RM " + totalProgramFee);

        }

        System.out.println("\n\n                                       End Of Programme Summary Report");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        new MainDao().generate();
        new CourseMenu().courseMenu();

    }

}
