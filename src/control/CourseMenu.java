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

    MainCrtl mainController = new MainCrtl();

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
                        addProgrammeToCourse(scanner);
                        break;
                    case 2:
                        removeProgrammeFromCourse(scanner);
                        break;
                    case 3:
                        addCourseToProgram(scanner, programList);
                        break;
                    case 4:
                        removeCourseFromProgram(scanner, programList);
                        break;
                    case 5:
                        searchSemesterForCourse(scanner);
                        break;
                    case 6:
                        amendCourseDetails(scanner);
                        break;
                    case 7:
                        listCoursesByFaculty();
                        break;
                    case 8:
                        listCoursesForProgram(programList, courseMap);
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

    //1.Add Course To Programme
    private void addProgrammeToCourse(Scanner scanner) {
        // Display all available programs
        System.out.println("Available Programs:");
        for (Programme program : programList) {
            System.out.println(program.getProgramCode() + " - " + program.getProgramName());
        }

        // Get program code from the user
        String programCode = CourseUI.promptString("Program Code", 10);
        Programme programme = ProgramMenu.findProgramByCode(programList, programCode);

        if (programme != null) {
            // Display courses not linked to the selected program
            System.out.println("\nCourses Available to Add:");

            for (KeyValuePair<String, Course> entry : courseMap.getAllEntries()) {
                Course course = entry.getValue();
                if (!programme.getLinkedCourses().contains(course.getCourseCode())) {
                    System.out.println(course.getCourseCode() + " - " + course.getCourseName());
                }
            }

            String courseCode;
            do {
                courseCode = CourseUI.promptString("Course Code", 10);

                if (!courseMap.containsKey(courseCode)) {
                    System.out.println("Course code doesn't exist. Please enter an existing course code.");
                } else if (programme.getLinkedCourses().contains(courseCode)) {
                    System.out.println("Course is already associated with the program.");
                }
            } while (!courseMap.containsKey(courseCode) || programme.getLinkedCourses().contains(courseCode));

            programme.addLinkedCourse(courseCode);
            System.out.println("Course added to program successfully.");
        } else {
            System.out.println("Program not found.");
        }
    }

    //2.Remove Programme From Course
    private static void removeProgrammeFromCourse(Scanner scanner) {
        // Display all programs and their linked courses
        System.out.println("\nPrograms and their Linked Courses:");
        for (Programme programme : programList) {
            System.out.println("\nProgram Code: " + programme.getProgramCode() + " - Program Name: " + programme.getProgramName());
            ArrayList<String> linkedCourses = programme.getLinkedCourses();
            if (!linkedCourses.isEmpty()) {
                System.out.println("Linked Courses:");
                for (String courseCode : linkedCourses) {
                    Course course = courseMap.get(courseCode);
                    if (course != null) {
                        System.out.println(course.getCourseCode() + " - " + course.getCourseName());
                    }
                }
            } else {
                System.out.println("No linked courses.");
            }
        }

        // Get program code from the user
        String programCode = CourseUI.promptString("Program Code", 10);
        Programme programme = ProgramMenu.findProgramByCode(programList, programCode);

        // Check if the program is found and proceed with removing the course
        if (programme != null) {
            String courseCode;
            do {
                courseCode = CourseUI.promptString("Course Code", 10);

                if (!courseMap.containsKey(courseCode)) {
                    System.out.println("Course code doesn't exist. Please enter an existing course code.");
                }
            } while (!courseMap.containsKey(courseCode));

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

    //3.Add Course To Program
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

    //4.Remove Course From Program
    private static void removeCourseFromProgram(Scanner scanner, ListInterface<Programme> programList) {
        // Display all programs and their linked courses
        System.out.println("\nPrograms and their Linked Courses:");
        for (Programme programme : programList) {
            System.out.println("\nProgram Code: " + programme.getProgramCode() + " - Program Name: " + programme.getProgramName());
            ArrayList<String> linkedCourses = programme.getLinkedCourses();
            if (!linkedCourses.isEmpty()) {
                System.out.println("Linked Courses:");
                for (String courseCode : linkedCourses) {
                    Course course = courseMap.get(courseCode);
                    if (course != null) {
                        System.out.println(course.getCourseCode() + " - " + course.getCourseName());
                    }
                }
            } else {
                System.out.println("No linked courses.");
            }
        }

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

    //5.Search Course
    private static void searchSemesterForCourse(Scanner scanner) {
        System.out.println("\nEnter course code to search semester: ");
        String courseCode = scanner.nextLine();

        Course foundCourse = courseMap.get(courseCode);

        System.err.println(CourseUI.listHeader());
        if (foundCourse != null) {
            System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                    foundCourse.getCourseCode(), foundCourse.getCourseName(),
                    foundCourse.getFaculty(), foundCourse.getSemester(), foundCourse.getCourseStatus(), foundCourse.getCourseFee());
            System.out.println(CourseUI.listSeperator());
        } else {
            System.out.println("Course not found.");
        }
    }

    //6.Amend Course Details
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
                    courseToAmend.getFaculty(), courseToAmend.getSemester(), courseToAmend.getCourseStatus(),
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
                            String newSemester = CourseUI.promptSemester("new semester");
                            courseToAmend.setSemester(newSemester);
                            System.out.println("Semester amended successfully.");
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

    //7.List Courses By Faculty
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
                        course.getFaculty(), course.getSemester(), course.getCourseStatus(), course.getCourseFee());
            }
        }

        if (!found) {
            System.out.println("No courses found for the specified faculty.");
        }

        System.out.println(CourseUI.listSeperator());
    }

    //8.List All Courses For Program
    private static void listCoursesForProgram(ListInterface<Programme> programList, HashMap<String, Course> courseMap) {
        System.err.println(CourseUI.programSummaryHeader());
        for (Programme programme : programList) {
            System.out.printf("| %-12s | %-70s | %-10s |\n", programme.getProgramCode(), programme.getProgramName(), programme.getFaculty());
        }
        System.out.println("======================================================================================================\n");

        System.out.println("Programmes and their Linked Courses:");
        for (Programme programme : programList) {
            System.out.println("\nProgram Code: " + programme.getProgramCode() + " - Program Name: " + programme.getProgramName());
            System.out.println(CourseUI.listHeader());

            for (String courseCode : programme.getLinkedCourses()) {
                Course course = courseMap.get(courseCode);
                if (course != null) {
                    System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                            course.getCourseCode(), course.getCourseName(),
                            course.getFaculty(), course.getSemester(), course.getCourseStatus(), course.getCourseFee());

                }
            }
            System.out.println(CourseUI.listSeperator());
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
                    course.getFaculty(), course.getSemester(), course.getCourseStatus(), course.getCourseFee());
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

            int totalProgramFee = 0;

            for (String courseCode : programme.getLinkedCourses()) {
                Course course = courseMap.get(courseCode);
                if (course != null) {
                    System.out.printf("| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n",
                            course.getCourseCode(), course.getCourseName(),
                            course.getFaculty(), course.getSemester(), course.getCourseStatus(), course.getCourseFee());
                    totalProgramFee += course.getCourseFee();

                }
            }
            System.out.println(CourseUI.listSeperator());

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
