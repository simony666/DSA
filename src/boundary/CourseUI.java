package boundary;
//Eddie Chua

import java.util.Scanner;

public class CourseUI {

    public static void displayMenu() {
        System.out.println("\nCourse Management Subsystem");
        System.out.println("1. Add a new course");
        System.out.println("2. Remove a course");
        System.out.println("3. Search courses offered");
        System.out.println("4. Amend course details");
        System.out.println("5. List courses taken by different faculties");
        System.out.println("6. List all courses for a program");
        System.out.println("7. Add course to a program");
        System.out.println("8. Remove a course from a program");
        System.out.println("9. Generate summary reports");
        System.out.println("0. Exit");
    }

    public static String promptString(String name, int chars) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("\nEnter %s (max %d characters): ", name, chars);
        String prompt = scanner.nextLine().trim();
        while (prompt.length() > chars) {
            System.out.printf("Error: %s should be at most %d characters.", name, chars);
            System.out.printf("\nEnter %s again: ", name);
            prompt = scanner.nextLine().trim();
        }

        return prompt;
    }

    public static String promptSemester(String name) {
        Scanner scanner = new Scanner(System.in);
        String semester;

        while (true) {
            try {
                System.out.printf("\nEnter %s semester: ", name);
                semester = scanner.nextLine().trim();

                // Add any validation logic here if needed
                break; // Exit the loop if input is valid
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid semester.");
            }
        }

        return semester;
    }

    public static String promptCourseStatus(String courseStatus) {
        Scanner scanner = new Scanner(System.in);
        String CS;

        while (true) {
            System.out.print("\nEnter Course Status (repeat/resit/main/elective): ");
            CS = scanner.nextLine().trim().toLowerCase();

            if (CS.equals("repeat") || CS.equals("resit") || CS.equals("main") || CS.equals("elective")) {
                break;
            } else {
                System.out.println("Error: Course status should be repeat, resit, main, or elective.");
            }
        }

        return CS;
    }

    public static int promptCourseFee(String name) {
        Scanner scanner = new Scanner(System.in);

        int courseFee;
        while (true) {
            try {
                System.out.print("\nEnter course fee: ");
                courseFee = Integer.parseInt(scanner.nextLine().trim());
                if (courseFee >= 0) {
                    break;  // Exit loop if valid input
                } else {
                    System.out.println("Error: Course fee cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for the course fee.");
            }
        }

        return courseFee;
    }

    public static String listHeader() {
        return String.format("==================================================================================================================\n"
                + "| %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n"
                + "==================================================================================================================",
                "Code", "Course Name", "Faculty", "Semester", "Status", "Course Fee");
    }

    public static String listSeperator() {
        return String.format("==================================================================================================================");
    }

    public static void amendMenu() {
        System.out.println("\nSelect what to amend:");
        System.out.println("1. Course Code");
        System.out.println("2. Course Name");
        System.out.println("3. Faculty");
        System.out.println("4. Semester");
        System.out.println("5. Course Fee");
        System.out.println("0. Cancel Amendment");
        System.out.print("Enter your choice: ");
    }

    public static String programSummaryHeader() {
        return String.format("======================================================================================================\n"
                + "| %-12s | %-70s | %-10s |\n"
                + "======================================================================================================",
                "Program Code", "Program Name", "Faculty");
    }

}
