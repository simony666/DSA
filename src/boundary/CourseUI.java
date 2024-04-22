package boundary;
//Eddie Chua

import java.util.Scanner;

public class CourseUI {

    public static void displayMenu() {
        System.out.println("\nCourse Management Subsystem");
        System.out.println("1. Add a new course");
        System.out.println("2. Remove a course");
        System.out.println("3. Modify student detail");
        System.out.println("4. Search student for registered course");
        System.out.println("5. Add student to course");
        System.out.println("6. List all courses for a program");
        System.out.println("7. Add course to a program");
        System.out.println("8. Remove a course from a program");
        System.out.println("9. Generate summary reports");
        System.out.println("0. View all student");
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

    public static int promptCH(String name) {
        Scanner scanner = new Scanner(System.in);

        int creditHours;

        while (true) {
            try {
                System.out.printf("\nEnter %s (1-5): ", name);
                creditHours = Integer.parseInt(scanner.nextLine().trim());

                if (creditHours >= 1 && creditHours <= 5) {
                    break;
                } else {
                    System.out.println("Error: Credit hours should be a single digit (1-5).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }

        return creditHours;
    }

    public static String promptCourseStatus(String courseStatus) {
        Scanner scanner = new Scanner(System.in);
        String prompt;

        while (true) {
            System.out.print("\nEnter Course Status (repeat/resit/main/elective): ");
            prompt = scanner.nextLine().trim().toLowerCase();

            if (prompt.equals("repeat") || prompt.equals("resit") || prompt.equals("main") || prompt.equals("elective")) {
                break;
            } else {
                System.out.println("Error: Course status should be repeat, resit, main, or elective.");
            }
        }

        return prompt;
    }

    public static String listHeader() {
        return String.format("===================================================================================\n" + "| %-10s | %-40s | %-10s | %-10s | %-5s |\n" + "===================================================================================", "Code", "Course Name", "Faculty", "Credit Hours", "Status");
    }

    public static String listSeperator() {
        return String.format("========================================================================================");
    }

    public static void amendMenu() {
        System.out.println("\nSelect what to amend:");
        System.out.println("1. Course Code");
        System.out.println("2. Course Name");
        System.out.println("3. Faculty");
        System.out.println("4. Credit Hours");
        System.out.println("0. Cancel Amendment");
        System.out.print("Enter your choice: ");
    }

}
