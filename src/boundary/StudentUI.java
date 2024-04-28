package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author User
 */
public class StudentUI {
        Scanner sc = new Scanner(System.in);
        
    public void header(){
        System.out.println("================================================================================");
        System.out.println("                                    Student List                                ");
        System.out.println("================================================================================");
        studentHeader();
    }
    
    public void studentHeader(){
        System.out.printf("   | %-10s | %-20s | %-10s | %-10s | %-10s |\n", 
                "Student ID", "Student Name", "Student Age", "Programme", "Faculty");
    }
    
    public void CourseHeader() {
        System.out.println("==================================================================================================================");
        System.out.printf("   | %-10s | %-40s | %-10s | %-15s | %-10s | %-10s |\n", 
                "Code", "Course Name", "Faculty", "Semester", "Status", "Course Fee");
        System.out.println("==================================================================================================================");
                
    }

    public int getMenuChoice() {
        System.out.println("                        Student Registration Management");
        System.out.println("====================================================================================");
        System.out.println();
        System.out.println("*-----------------------------------------------------------------------------------*");
        System.out.println("|                                    Student                                        |");
        System.out.println("|-----------------------------------------------------------------------------------|");
        System.out.println("| 1. Add new students                                                               |");
        System.out.println("| 2. Remove a student                                                               |");
        System.out.println("| 3. Ammend student details                                                         |");
        System.out.println("| 4. Search students for registered courses                                         |");
        System.out.println("| 5. Add Student to a few course                                                    |");
        System.out.println("| 6. Remove student from a courses                                                  |");
        System.out.println("| 7. Fee paid for registered courses                                                |");
        System.out.println("| 8. Filters student                                                                |");
        System.out.println("| 9. Generate summary report                                                        |");
        System.out.println("| 10. View all Student                                                              |");
        System.out.println("| 0. Return to main page                                                            |");
        System.out.println("*-----------------------------------------------------------------------------------*");
        System.out.print("Select number: ");

        int index = sc.nextInt();
        sc.nextLine();
        return index;
    }
    
    public int getreportChoie(){
        System.out.println("=================================");
        System.out.println("Select a Summary Report");
        System.out.println("=================================");
        System.out.println("1. Course Summary Report");
        System.out.println("2. Student Summary Report");
        System.out.println("================================================");
        System.out.print("Enter (1-2): ");
        
        int index = sc.nextInt();
        sc.nextLine();
        return index;
    }

    public void displayFilterMenu() {
        System.out.println("Filter by:");
        System.out.println("1. Student Name");
        System.out.println("2. Student Age");
        System.out.println("3. Programme");
        System.out.println("4. Faculty");
        System.out.println("5. Course ID");

        System.out.print("Enter your choices : ");
    }

    
    public int verifyAge() {
        int age = 0;
        try {
            System.out.print("Please enter student age: ");
            age = sc.nextInt();
            sc.nextLine();
            if (age < 18 ) {
                
                throw new IllegalArgumentException("Invalid age. Please try again(Age must greater than or equal to 18)");
            }
            
        } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again(Age must greater than or equal to 18)");
                return verifyAge();
                
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a age.");
            sc.nextLine();
            return verifyAge();
        }
        return age;
    }
   
    public void generateReportOne() {
        ReportTopOne();
        reportHeaderOne();
    }
    
    public void generateReportTwo(){
        reportHeaderTwo();
    }
    
    public void ReportTopOne() {
        seperateOne();
        System.out.println("                          TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY                              ");
        System.out.println("                                         STUDNET MANAGEMENT SUBSYSTEM");
    }
    
    public void ReportTopTwo() {
        seperateTwo();
        System.out.println("          TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY                 ");
        System.out.println("                       STUDNET MANAGEMENT SUBSYSTEM");
    }
    
    
    public void reportHeaderOne(){
        System.out.println("\n            STUDENT SUMMARY REPORT 1 =>   TOTAL COURSE REGISTERED BY STUDENT AND TOTAL COURSE FEE                 ");
        seperateOne();
        getCurrentDateTime();
        System.out.printf("   | %-10s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s |\n", "STUDENT ID", "STUDENT NAME", "STUDENT AGE", "PROGRAMME", "FACULTY", "COUNT COURSE", "TOTAL COURSE FEE");
        seperateOne();
    }
    
        public void reportHeaderTwo(){
        System.out.println("\n            STUDENT SUMMARY REPORT 2 =>   STUDENT DEMOGRAPHICS");
        seperateTwo();
        getCurrentDateTime();
        header();
        seperateTwo();
    }
    
    
    
    public void seperateOne(){
        System.out.println("==================================================================================================================");
    }
    
    public void seperateTwo(){
        System.out.println("================================================================================");
    }
    
    public void getCurrentDateTime() {

        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, h:mma");

        // Format the current date and time using the formatter
        String formattedDateTime = currentDateTime.format(formatter);

        // Print the formatted date
        System.out.println("\nGenerated Report at: " + formattedDateTime + "\n\n");

    }
    
    public void reportFototerOne(){
        System.out.println("\n                                      END OF THE STUDENT SUMMARY REPORT");
        seperateOne();
    }
    
    public void reportFototerTwo(){
        System.out.println("\n                       END OF THE STUDENT SUMMARY REPORT");
        seperateTwo();
    }
}

