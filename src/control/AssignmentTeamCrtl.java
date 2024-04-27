package control;

import adt.ArrayList;
import adt.LinkedList;
import boundary.AssignmentTeamUI;
import dao.MainDao;
import entity.AssignmentTeam;
import entity.Student;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author yongc
 */
public class AssignmentTeamCrtl {

    private Scanner scanner = new Scanner(System.in);
    private final AssignmentTeamUI ui = new AssignmentTeamUI();

    private static LinkedList<AssignmentTeam> assignmentList = new LinkedList<>();//store all current entity

    public void entry() {
        int choice = ui.getMenuChoice();
        switch (choice) {
            case 1:
                createTeamCrtl();
                break;
            case 2:
                RemoveTeamCrtl();
                break;
            case 3:
                ModifyTeamCrtl();
                break;
            case 4:
                AddStudentCrtl();
                break;
            case 5:
                RemoveStudentCrtl();
                break;
            case 6:
                MergeTeamCrtl();
                break;
            case 7:
                ListTeamCrtl();
                break;
            case 8:
                ListTeamMemberCrtl();
                break;
            case 9:
                ReportCrtl();
                break;
            case 10:
                MessageUI.displayExitMessage();
                break;
            default:
                System.out.println("Something went wrong!");
                break;
        }
        //1. Create Assignment Team"
        //2. Remove Assignment Team");
        //3. Modify Assignment Team");
        //4. Add Student");
        //5. Remove Student");
        //6. Merge Assignment Team");
        //7. Display Assignment Team");
        //8. Display Assignment Team Member");
        //9. Report");\
        //10. Exit");
    }

    //case 1
    public void createTeamCrtl() {
        //create New Entity, add into list
        AssignmentTeam newTeam = ui.getNewTeam();
        if (newTeam != null) {
            addAT(newTeam);
            MessageUI.clearScreen();
            System.out.println("Assignment team with student limit to " + String.valueOf(newTeam.getLimit()) + " student created successfully.");
        }else{
            MessageUI.clearScreen();
        }

        //alway redirect back to main menu
        entry();
    }

    //case 2
    public void RemoveTeamCrtl() {
        //create New Entity, add into list
        AssignmentTeam oldTeam = ui.getRemoveTeam();
        if (oldTeam != null) {
            removeAT(oldTeam);
            MessageUI.clearScreen();
            System.out.println("Assignment team " + oldTeam.getAssignName() + " remove successfully.");
        }else{
            MessageUI.clearScreen();
        }

        //alway redirect back to main menu
        entry();
    }

    //case 3
    public void ModifyTeamCrtl() {
        ui.modifyTeam();

        //alway redirect back to main menu
        entry();
    }

    //case 4
    public void AddStudentCrtl() {
        System.out.println("Please Select A Team To Add Student");
        AssignmentTeam at1 = ui.getTeam();

        Student stu = ui.getStudent();
        if (stu != null) {
            //check student course == at course
            if (stu.getCourseList().contains(at1.getCourse())) {
                at1.addStudent(stu);
                System.out.println("Success Add Student!");
            } else {
                //else student.course not same, cancel!
                System.out.println("Student is not under this course! Cancelled!");
            }

        }//else student == null, user cancel, return to main menu      

        entry();
    }

    //case 5
    public void RemoveStudentCrtl() {
        System.out.println("Please Select A Team To Add Student");
        AssignmentTeam at1 = ui.getTeam();
        if (at1 != null) {
            ArrayList<Student> StuList = at1.getStudentList();
            Student stu = null;
            int choice = 0;
            while (choice == 0) {
                System.out.println("Student List");
                int result = MessageUI.displayList(StuList);
                System.out.println("Please Select A Student To Remove Or \"-1\" to remove");
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == -1) {
                    break;
                } else if (choice < 0 || choice > result) {
                    MessageUI.clearScreen();
                    MessageUI.displayInvalidChoiceMessage();
                    choice = 0;
                } else {
                    stu = StuList.getEntry(choice);
                    StuList.remove(StuList.indexOf(stu));
                    System.out.println("Student Successfully Removed!");
                }

            }
        }

        entry();
    }

//case 6
    public void MergeTeamCrtl() {
        //choose 2 team
        //can merge if: 2 team is same course & tutorial group
        //sum of student <= limit
        //if over limit = cancel!
        System.out.println("Please Select A Team To Merge");
        AssignmentTeam at1 = ui.getTeam();
        AssignmentTeam at2 = null;

        boolean go = false;

        if (at1 == null) {
            //exit
            go = true;
        }
        while (!go) {
            System.out.println("Please Select A Team To Merge");
            at2 = ui.getTeam();
            if (at2 == null) {
                //exit
                go = true;
            } else if (at1.equals(at2)) {
                MessageUI.clearScreen();
                System.out.println("Please Select A Diffrent Assignment Team");
            } else {
                go = true;
            }
        }

        //at1 and at2 is AT object need to merge
        //at1 or at2 is null == direct exit
        if (at1 != null && at2 != null) {
            //need to merge, check the requirements
            //what is the requirement???
            boolean canMerge = (at1.getTutorialGroup() == at2.getTutorialGroup() && at1.getCourse() == at2.getCourse());
            int index = (at1.getLimit() >= at2.getLimit() ? 1 : 2);
            AssignmentTeam mergeTeam = (at1.getLimit() >= at2.getLimit() ? at1 : at2);
            int sCount1 = at1.getStudentCount();
            int sCount2 = at2.getStudentCount();
            int max = mergeTeam.getLimit();
            if (!canMerge) {
                //unable to merge due to Programme or Course Not Same
                System.out.println("Unable To Merge! Course and Tutorial Group are diffrence");
            } else if (sCount1 + sCount2 <= max) {
                //able to merge
                ArrayList<Student> sList = at1.getStudentList();
                ArrayList<Student> s2List = at2.getStudentList();
                for (int i = 0; i < s2List.getNumberOfEntries(); i++) {
                    if (!sList.contains(s2List.getEntry(i))) {
                        sList.add(s2List.getEntry(i));
                    }
                }
                System.out.println("Success Merge Team!");
                if (index == 1) {
                    //merge to at1
                    at1.setStudentList(sList);
                    removeAT(at2);
                    System.out.println(at1.toString());
                } else {
                    //merge to at2
                    at2.setStudentList(sList);
                    removeAT(at1);
                    System.out.println(at2.toString());
                }
            } else {
                //unable to merge due to exceed limit
                System.out.println("Unable To Merge! student limit exceed!");
            }

        }

        //alway redirect back to main menu
        entry();
    }

    //case 7
    public void ListTeamCrtl() {
        ui.displayATList();

        System.out.println("Press Enter to continue");
        scanner.nextLine();
        MessageUI.clearScreen();

        //alway redirect back to main menu
        entry();
    }

    //case 8
    public void ListTeamMemberCrtl() {
        AssignmentTeam at = ui.getTeam();
        ui.displayATMemberList(at);

        System.out.println("Press Enter to continue");
        scanner.nextLine();
        MessageUI.clearScreen();

        //alway redirect back to main menu
        entry();
    }

    //case 9
    public void ReportCrtl() {
        int choice = 0;
        while (choice == 0){
            System.out.println("Report Menu");
            System.out.println("1. Team Count By Student Count");
            System.out.println("2. Team Count By Course");
            System.out.println("3. Exit...");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice <=0 || choice >3){
                MessageUI.displayInvalidChoiceMessage();
                MessageUI.clearScreen();
                choice = 0;
            }
        }
        
        if (choice == 1){
            ui.Report1();
        }else if (choice == 2){
            ui.Report2();
        }
        //else = exit
        
        
        //alway redirect back to main menu
        entry();
    }
    
    

    //own function
    public boolean addAT(AssignmentTeam at) {
        return AssignmentTeamCrtl.assignmentList.add(at);
    }

    public boolean removeAT(AssignmentTeam at) {
        var position = AssignmentTeamCrtl.assignmentList.indexOf(at);
        if (position >= 0) {
            return AssignmentTeamCrtl.assignmentList.remove(position) != null;
        }
        return false;
    }

    public LinkedList<AssignmentTeam> getAssignmentList() {
        return AssignmentTeamCrtl.assignmentList;
    }

    public void setAssignmentList(LinkedList<AssignmentTeam> assignmentList) {
        AssignmentTeamCrtl.assignmentList = assignmentList;
    }

    public static void main(String[] args) {
        new MainDao().generate();
        //CourseInitializer.initializeCourses();
        AssignmentTeamCrtl crtl = new AssignmentTeamCrtl();
        //AssignmentTeamUI ui = new AssignmentTeamUI();
        crtl.entry();
        //ui.displayATList();
    }
}
