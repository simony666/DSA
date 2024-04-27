package control;

import dao.MainDao;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author Yong Choy Mun
 */
public class MainCrtl {

    Scanner scanner = new Scanner(System.in);

    public void entry() {
        int choice = getMenuChoice();
        switch (choice) {
            case 1:
                new StudentController().runStudentController();
                break;
            case 2:
                new CourseMenu().courseMenu();
                break;
            case 3:
                new TeachingAssignment().entry();
                break;
            case 4:
                new AssignmentTeamCrtl().entry();
                break;
            case 5:
                new TutorialGroupManagement().entry();
                break;
            case 6:
                MessageUI.displayExitMessage();
                break;
            default:
                System.out.println("Something went wrong!");
                break;
        }
    }

    private int getMenuChoice() {
        int choice = 0;
        while (choice == 0) {
            System.out.println("===================================");
            System.out.println("=     TARUM Management System     =");
            System.out.println("===================================");
            System.out.println("1. Student");
            System.out.println("2. Course");
            System.out.println("3. Teaching Assignment");
            System.out.println("4. Assignment Team");
            System.out.println("5. Tutorial Group Management");
            System.out.println("6. Exit");
            System.out.println("\nPlease Enter Menu Index(1-6):");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < 1 || choice > 6) {
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        new MainDao().generate();
        new MainCrtl().entry();
    }

}
