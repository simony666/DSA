 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author yongc
 */
public class AssignmentTeamUI {
    //create assignment teams for a tutor group
    //remove assignment team
    //amend assignment team details
    //add student to assignment teams
    //remove student from assignment teams
    //merge assignment team based on criteria
    //list assignment teams for a tutorial group
    //list students under and assignment team
    //2 summary report
    
    //tutorial group has many assignment teams
    //a team has many students, Limit the size
    
    private Scanner scanner = new Scanner(System.in);
    
    public int getMenuChoice(){
        int choice = 0;
        while(choice == 0){
            System.out.println("===================================");
            System.out.println("=         Assignment Team         =");
            System.out.println("===================================");
            System.out.println("1. Create Assignment Team");
            System.out.println("2. Remove Assignment Team");
            System.out.println("3. Modify Assignment Team");
            System.out.println("4. Add Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Merge Assignment Team");
            System.out.println("7. Display Assignment Team");
            System.out.println("8. Display Assignment Team Member");
            System.out.println("9. Report");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice<=0 || choice >=9){
                //set choice to invalid to display again
                MessageUI.clearScreen();
                MessageUI.displayInvalidChoiceMessage();
                choice = 0;
            }
        }
        return choice;
    }
    
    public static void displayCreateTeam(){
        System.out.println("===================================");
        System.out.println("=     Create Assignment Team      =");
        System.out.println("===================================");
        
    }
}
