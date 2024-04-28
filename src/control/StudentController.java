package control;

/**
 *
 * @author Qin Long
 */
import adt.*;
import entity.*;
import boundary.*;
import dao.MainDao;
import java.util.Scanner;
import utility.MessageUI;

public class StudentController {

    Scanner sc = new Scanner(System.in);

    StudentUI studentUI = new StudentUI();

    MainCrtl mainController = new MainCrtl();

    public static LinkedList<Student> studentList = new LinkedList<>();
    private ArrayList<Course> courseList = new ArrayList<>();

    public void runStudentController() {
        int choice;
        do {
            MessageUI.clearScreen();
            choice = studentUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    MessageUI.clearScreen();
                    mainController.entry();
                    break;
                case 1:
                    MessageUI.clearScreen();
                    addStudent();
                    MessageUI.pressEnter();
                    break;
                case 2:
                    MessageUI.clearScreen();
                    removeStudent();
                    MessageUI.pressEnter();
                    break;
                case 3:
                    MessageUI.clearScreen();
                    modifyStudentDetail();
                    MessageUI.pressEnter();
                    break;
                case 4:
                    MessageUI.clearScreen();
                    searchStudentRegisteredCourse();
                    MessageUI.pressEnter();
                    break;
                case 5:
                    MessageUI.clearScreen();
                    addStudentToCourse();
                    MessageUI.pressEnter();
                    break;
                case 6:
                    MessageUI.clearScreen();
                    removeFromCourse();
                    MessageUI.pressEnter();
                case 7:
                    MessageUI.clearScreen();
                    calculateFee();
                    MessageUI.pressEnter();
                    break;
                case 8:
                    MessageUI.clearScreen();
                    filterStudent();
                    MessageUI.pressEnter();
                    break;
                case 9:
                    MessageUI.clearScreen();
                    generateReport();
                    MessageUI.pressEnter();
                    break;
                case 10:
                    MessageUI.clearScreen();
                    viewAllStudent();
                    MessageUI.pressEnter();
                    runStudentController();
            }
        } while (choice != 0);
    }

    public void generateReport() {
        int choice;
        choice = studentUI.getreportChoie();

        switch (choice) {
            case 1:
                displayReportOne();
                break;
            case 2:
                displayReportTwo();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
                break;
        }
    }

    private void viewAllStudent() {
        studentUI.header();
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + studentList.getEntry(i).toString());
        }
    }

    private void addStudent() {
        System.out.print("Please enter student Name: ");
        String studentName = sc.nextLine().trim();

        int studentAge = studentUI.verifyAge();

        //Display available programmes
        System.out.println("All programme: ");
        ListInterface<Programme> programList = CourseMenu.programList;
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Programme program = programList.getEntry(i);
            System.out.println(i + ". " + program.getProgramCode());
        }

        System.out.print("Select a programme: ");
        int selectedProgramIndex = sc.nextInt();
        sc.nextLine();
        String selectedProgramme = programList.getEntry(selectedProgramIndex).getProgramCode();
        String selectedProgrammeName = programList.getEntry(selectedProgramIndex).getProgramName();
        String selectedFaculty = programList.getEntry(selectedProgramIndex).getFaculty();

        System.out.printf("| Name: %-10s | Age: %-20s | Programme: %-10s | Faculty: %-10s |",
                studentName, studentAge, selectedProgramme, selectedFaculty);
        boolean ans = MessageUI.comfirmationMessage();

        if (ans == true) {
            Programme programme = new Programme(selectedProgramme, selectedProgrammeName, selectedFaculty);
            Student newStudent = new Student(studentName, studentAge, programme);
            studentList.add(newStudent);
            System.out.println("Student added successfully!");
            runStudentController();
        } else {
            System.out.print("Do you want add again? <Y/N> : ");
            boolean answer = MessageUI.enterComfirm();
            if (answer == true) {
                addStudent();
            } else {
                MessageUI.displayExitMessage();
                runStudentController();
            }
        }
    }

    private void removeStudent() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("\nPlease select a student that you want to delete: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();

            if (selectedIndex >= 0 && selectedIndex <= studentList.getNumberOfEntries()) {
                Student newStudent = studentList.getEntry(selectedIndex);
                System.out.println("\nStudent ID: " + newStudent.getStudentID() + "\nName: " + newStudent.getStudentName()
                        + "\nAge: " + newStudent.getAge() + "\nProgramme: " + newStudent.getProgramme().getProgramCode()
                        + "\nFaculty: " + newStudent.getProgramme().getFaculty());
                System.out.print("Course:");
                for (int i = 1; i <= newStudent.getCourseList().getNumberOfEntries(); i++) {
                    System.out.print(" <" + newStudent.getCourseList().getEntry(i).getCourseCode() + ">");
                }
                System.out.println("\nTutorial Group: " + newStudent.getTutorialGroup());
                boolean ans = MessageUI.comfirmationMessage();
                if (ans == true) {
                    studentList.remove(selectedIndex);
                    System.out.println("Student deleted successfully!");
                    runStudentController();
                } else {
                    System.out.print("Do you want delete again? <Y/N> : ");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer == true) {
                        removeStudent();
                    } else {
                        runStudentController();
                    }
                }
            } else {
                MessageUI.displayInvalidIndexMessage();
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void modifyStudentDetail() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("Please select a student that you want to modify: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();

            //check the Index and display selected student detail
            if (selectedIndex <= studentList.getNumberOfEntries()) {
                Student oldStudent = studentList.getEntry(selectedIndex);
                oldStudent.toString();

                //modify name
                System.out.print("Please enter student Name (press Enter to keep current): ");
                String studentName = sc.nextLine().trim();

                String newStudentName = null;
                if (studentName.isEmpty()) {
                    newStudentName = oldStudent.getStudentName();
                } else {
                    newStudentName = studentName;
                }

                //modify age
                System.out.print("Please enter student age (press Enter to keep current): ");
                String ageInput = sc.nextLine().trim();
                int newStudentAge = 0;
                if (!ageInput.isEmpty()) {
                    try {
                        int intAge = Integer.parseInt(ageInput);
                        if (intAge >= 18) {
                            newStudentAge = intAge;
                        } else {
                            System.out.println("Invalid input. Please try again(Age must greater than or equal to 18)");
                            newStudentAge = oldStudent.getAge();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric value for age.");
                        newStudentAge = oldStudent.getAge();
                    }
                } else {
                    newStudentAge = oldStudent.getAge();
                }

                //modify programme
                System.out.println("\nAll programme");
                ListInterface<Programme> programList = CourseMenu.programList;
                for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
                    Programme program = programList.getEntry(i);
                    System.out.println(i + ". " + program.getProgramCode());
                }

                System.out.print("Select a programme (press Enter to keep current): ");
                String programmeInput = sc.nextLine().trim();
                Programme newProgramme = new Programme(null, null, null);
                if (!programmeInput.isEmpty()) {
                    int indexProgramme = Integer.parseInt(programmeInput);
                    if (indexProgramme <= programList.getNumberOfEntries()) {
                        String newProgrammeCode = programList.getEntry(indexProgramme).getProgramCode();
                        String newProgrammeName = programList.getEntry(indexProgramme).getProgramName();
                        String newFaculty = programList.getEntry(indexProgramme).getFaculty();

                        newProgramme.setProgramCode(newProgrammeCode);
                        newProgramme.setProgramName(newProgrammeName);
                        newProgramme.setFaculty(newFaculty);

                    }
                } else {
                    newProgramme.setProgramCode(oldStudent.getProgramme().getProgramCode());
                    newProgramme.setProgramName(oldStudent.getProgramme().getProgramName());
                    newProgramme.setFaculty(oldStudent.getProgramme().getFaculty());
                }

                System.out.println("\nStudent ID: " + oldStudent.getStudentID()
                        + "\nName: " + newStudentName + "\nAge: " + newStudentAge
                        + "\nProgramme: " + newProgramme.getProgramCode() + "\nFaculty: " + newProgramme.getFaculty());
                boolean ans = MessageUI.comfirmationMessage();

                if (ans == true) {
                    Student newStudent = new Student(newStudentName, newStudentAge, newProgramme);
                    studentList.replace(selectedIndex, newStudent);

                    System.out.println("Student modified successfully!");
                    runStudentController();
                } else {
                    System.out.print("Do you want modify again? <Y/N> : ");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer == true) {
                        modifyStudentDetail();
                        runStudentController();
                    } else {
                        MessageUI.displayExitMessage();
                        runStudentController();
                    }
                }
            } else {
                MessageUI.displayNotRelated();
                System.out.print("Do you want continue modify student detail? <Y|N> : ");
                boolean answer = MessageUI.enterComfirm();
                if (answer == true) {
                    modifyStudentDetail();
                } else {
                    runStudentController();
                }
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void searchStudentRegisteredCourse() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("Please select a student that you want search for registed courses: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            //check the Index and display selected student detail
            if (selectedIndex <= studentList.getNumberOfEntries()) {
                if (studentList.getEntry(selectedIndex).getCourseList().getNumberOfEntries() >= 1) {
                    Student selectedStudent = studentList.getEntry(selectedIndex);
                    selectedStudent.toString();

                    ArrayList<Course> registeredCourse = selectedStudent.getCourseList();
                    studentUI.CourseHeader();
                    for (int i = 1; i <= registeredCourse.getNumberOfEntries(); i++) {
                        System.out.println(i + ". " + registeredCourse.getEntry(i).toString());
                    }
                } else {
                    MessageUI.displayEmpty();
                }
            } else {
                MessageUI.displayNotRelated();
                System.out.print("Do you want continue search student for registered courses? <Y|N> : ");
                boolean answer = MessageUI.enterComfirm();
                if (answer == true) {
                    searchStudentRegisteredCourse();
                } else {
                    runStudentController();
                }
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void addStudentToCourse() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("Please select a student for register course: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();

            //check the Index and display selected student detail
            if (selectedIndex >= 1 && selectedIndex <= studentList.getNumberOfEntries()) {
                Student studentDetail = studentList.getEntry(selectedIndex);

                System.out.println("\n\nSelected student detail: ");
                studentUI.studentHeader();
                System.out.println("   " + studentDetail);

                ArrayList<Course> registeredCourse = studentDetail.getCourseList();
                String faculty = studentDetail.getProgramme().getFaculty();
                LinkedList<Course> availableCourse = new LinkedList<>();
                LinkedList<Course> allCourse = CourseMenu.courseMap.getAllValue();

                for (Course course : allCourse) {
                    if (course.getFaculty().contains(faculty)) {
                        boolean alreadyRegistered = false;
                        for (Course registered : registeredCourse) {
                            if (course.equals(registered)) {
                                alreadyRegistered = true;
                                break;
                            }
                        }
                        if (!alreadyRegistered) {
                            availableCourse.add(course);
                        }
                    }
                }

                if (availableCourse.getNumberOfEntries() >= 1) {
                    System.out.println("\nAvailable Course");
                    studentUI.CourseHeader();
                    for (int i = 1; i <= availableCourse.getNumberOfEntries(); i++) {
                        System.out.println(i + ". " + availableCourse.getEntry(i).toString());
                    }
                    System.out.print("\nPlease select a course that you want to register: ");
                    int courseIndex = sc.nextInt();
                    sc.nextLine();

                    studentUI.CourseHeader();
                    System.out.println("   " + availableCourse.getEntry(courseIndex).toString());
                    boolean ans = MessageUI.comfirmationMessage();

                    if (ans == true) {
                        studentList.getEntry(selectedIndex).getCourseList().add(availableCourse.getEntry(courseIndex));
                        System.out.println("Student registered course successfully!");
                        runStudentController();
                    } else {
                        System.out.print("Do you want register again? <Y/N> : ");
                        boolean answer = MessageUI.enterComfirm();
                        if (answer == true) {
                            addStudentToCourse();
                        } else {
                            MessageUI.displayExitMessage();
                            runStudentController();
                        }
                    }
                } else {
                    MessageUI.displayEmpty();
                }
            } else {
                MessageUI.displayEmpty();
            }
        }
    }

    private void removeFromCourse() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("Please select a student for delete registered course: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();

            //check the Index and display selected student detail
            if (selectedIndex >= 1 && selectedIndex <= studentList.getNumberOfEntries()) {
                Student studentDetail = studentList.getEntry(selectedIndex);

                System.out.println("\nSelected student detail");
                studentUI.studentHeader();
                System.out.println("   " + studentDetail + "\n");
                if (!studentDetail.getCourseList().isEmpty()) {
                    studentUI.CourseHeader();
                    for (int i = 1; i <= studentDetail.getCourseList().getNumberOfEntries(); i++) {
                        System.out.println(i + ". " + studentDetail.getCourseList().getEntry(i).toString());
                    }
                    System.out.print("\nPlease select a registered course that you want to delete: ");
                    int courseIndex = sc.nextInt();
                    sc.nextLine();
                    studentUI.CourseHeader();
                    System.out.println("   " + studentDetail.getCourseList().getEntry(courseIndex).toString());
                    boolean ans = MessageUI.comfirmationMessage();

                    if (ans == true) {
                        studentList.getEntry(selectedIndex).getCourseList().remove(courseIndex);
                        System.out.println("Student deleted registered course successfully!");
                        runStudentController();
                    } else {
                        System.out.print("Do you want delete again? <Y/N> : ");
                        boolean answer = MessageUI.enterComfirm();
                        if (answer == true) {
                            removeFromCourse();
                        } else {
                            MessageUI.displayExitMessage();
                            runStudentController();
                        }
                    }
                } else {
                    MessageUI.displayEmpty();
                    System.out.print("Do you want select student again? <Y/N> : ");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer == true) {
                        removeFromCourse();
                    } else {
                        MessageUI.displayExitMessage();
                        runStudentController();
                    }
                }
            } else {
                MessageUI.displayInvalidChoiceMessage();
                System.out.println("Please try again...");
                removeFromCourse();
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void calculateFee() {
        if (studentList.getNumberOfEntries() >= 1) {
            viewAllStudent();
            System.out.print("\nPlease select a student that you want calculate total course fee for registered courses: ");
            int selectedIndex = sc.nextInt();
            sc.nextLine();
            System.out.println("\n\n");

            //check the Index and display selected student detail
            if (selectedIndex <= studentList.getNumberOfEntries()) {
                Student selectedStudent = studentList.getEntry(selectedIndex);
                selectedStudent.toString();
                int totalCourseFee = totalCourseFee(selectedIndex);

                if (selectedStudent.getCourseList().getNumberOfEntries() >= 1) {
                    ArrayList<Course> registeredCourse = selectedStudent.getCourseList();
                    studentUI.CourseHeader();
                    for (int i = 1; i <= registeredCourse.getNumberOfEntries(); i++) {
                        System.out.println(i + ". " + registeredCourse.getEntry(i).toString());
                    }
                    System.out.println("\nTotal Course fee: " + totalCourseFee);

                    System.out.print("\n\nDo you want search other student for calculate fee again? <Y/N> : ");
                    boolean answer = MessageUI.enterComfirm();
                    if (answer == true) {
                        calculateFee();
                    } else {
                        MessageUI.displayExitMessage();
                        runStudentController();
                    }
                } else {
                    MessageUI.displayEmpty();
                }

            } else {
                MessageUI.displayEmpty();
            }
        }
    }

    private void filterStudent() {
        studentUI.displayFilterMenu();
        int index = sc.nextInt();
        sc.nextLine();

        switch (index) {
            case 1:
                searchStudentName();
                MessageUI.pressEnter();
                break;
            case 2:
                searchStudentAge();
                MessageUI.pressEnter();
                break;
            case 3:
                searchProgramme();
                MessageUI.pressEnter();
                break;
            case 4:
                searchFaculty();
                MessageUI.pressEnter();
                break;
            case 5:
                searchCourse();
                MessageUI.pressEnter();
                break;
        }
    }

    private void displayReportOne() {
        studentUI.generateReportOne();

        int maxCourseCount = 0;
        Student studentCourse = null;

        int maxCourseFee = 0;
        Student studentCourseFee = null;

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {

            int countCourse = countCourse(i);
            if (countCourse > maxCourseCount) {
                studentCourse = studentList.getEntry(i);
                maxCourseCount = countCourse;
            }

            int courseFee = totalCourseFee(i);
            if (courseFee > maxCourseFee) {
                studentCourseFee = studentList.getEntry(i);
                maxCourseFee = courseFee;
            }

            System.out.print(i + ". " + studentList.getEntry(i).toString());
            System.out.printf(" %-11s | %-17s |\n", countCourse, courseFee);
        }
        studentUI.seperateOne();
        System.out.println("\nHIGHTEST COURSE COUNT: " + maxCourseCount + "  << " + studentCourseFee.toString() + " >>");
        System.out.println("\nHIGHTEST COURSE FEE: " + maxCourseFee + "  << " + studentCourseFee.toString() + " >>" + "\n");
        studentUI.reportFototerOne();

    }

    private void displayReportTwo() {
        studentUI.ReportTopTwo();
        studentUI.reportHeaderTwo();

        int ageLower = 0;
        LinkedList<Student> ageLowerStudent = new LinkedList<>();

        int ageMiddle = 0;
        LinkedList<Student> ageMiddleStudent = new LinkedList<>();

        int ageHigher = 0;
        LinkedList<Student> ageHigherStudent = new LinkedList<>();

        int totalAge = 0;
        int totalStudent = studentList.getNumberOfEntries();

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            int age = studentList.getEntry(i).getAge();

            if (age >= 18 && age <= 20) {
                ageLower += 1;
                ageLowerStudent.add(student);
            } else if (age >= 21 && age <= 25) {
                ageMiddle += 1;
                ageMiddleStudent.add(student);
            } else {
                ageHigher += 1;
                ageHigherStudent.add(student);
            }

            totalAge += age;
        }

        System.out.println("\nNUMBER OF STUDENTS IN AGE GROUP 18-20: " + ageLower + "\n");
        for (int i = 1; i <= ageLowerStudent.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + ageLowerStudent.getEntry(i).toString());
        }
        studentUI.seperateTwo();

        System.out.println("\nNUMBER OF STUDENTS IN AGE GROUP 21-25: " + ageMiddle + "\n");
        for (int i = 1; i <= ageMiddleStudent.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + ageMiddleStudent.getEntry(i).toString());
        }
        studentUI.seperateTwo();

        System.out.println("\nNUMBER OF STUDENTS IN AGE GROUP 26 AND ABOVE: " + ageHigher + "\n");
        for (int i = 1; i <= ageHigherStudent.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + ageHigherStudent.getEntry(i).toString());
        }
        studentUI.seperateTwo();

        System.out.println("\nTOTAL STUDENT: " + totalStudent);
        System.out.println("\nAVERAGE AGE OF STUDENTS: " + totalAge / totalStudent + "\n");
        studentUI.reportFototerTwo();
    }

    private void searchStudentName() {
        ArrayList<Student> filteredStudent = new ArrayList<>();

        System.out.print("\nEnter the partial of student name: ");
        String partOfName = sc.nextLine();

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            String studentName = studentList.getEntry(i).getStudentName().toUpperCase();
            if (studentName.contains(partOfName.toUpperCase())) {
                filteredStudent.add(studentList.getEntry(i));
            }

        }
        if (!filteredStudent.isEmpty()) {
            System.out.println("");
            studentUI.studentHeader();
            for (int i = 1; i <= filteredStudent.getNumberOfEntries(); i++) {
                System.out.println(i + ". " + filteredStudent.getEntry(i).toString());
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void searchStudentAge() {
        ArrayList<Student> filteredStudent = new ArrayList<>();

        System.out.print("Enter the student age: ");
        int specifyAge = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            if (specifyAge == (studentList.getEntry(i).getAge())) {
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if (!filteredStudent.isEmpty()) {
            System.out.println("");
            studentUI.studentHeader();
            for (int i = 1; i <= filteredStudent.getNumberOfEntries(); i++) {
                System.out.println(i + ". " + filteredStudent.getEntry(i).toString());
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void searchProgramme() {
        ArrayList<Student> filteredStudent = new ArrayList<>();

        System.out.print("Enter the student programme code: ");
        String studentProgramme = sc.nextLine();

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            if (studentProgramme.toUpperCase().contains((studentList.getEntry(i).getProgramme().getProgramCode()))) {
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if (!filteredStudent.isEmpty()) {
            System.out.println("");
            studentUI.studentHeader();
            for (int i = 1; i <= filteredStudent.getNumberOfEntries(); i++) {
                System.out.println(i + ". " + filteredStudent.getEntry(i).toString());
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void searchFaculty() {
        ArrayList<Student> filteredStudent = new ArrayList<>();

        System.out.print("Enter the student faculty: ");
        String studentProgramme = sc.nextLine();

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            if (studentProgramme.toUpperCase().contains((studentList.getEntry(i).getProgramme().getFaculty()))) {
                filteredStudent.add(studentList.getEntry(i));
            }
        }
        if (!filteredStudent.isEmpty()) {
            System.out.println("");
            studentUI.studentHeader();
            for (int i = 1; i <= filteredStudent.getNumberOfEntries(); i++) {
                System.out.println(i + ". " + filteredStudent.getEntry(i).toString());
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private void searchCourse() {
        ArrayList<Student> filteredStudent = new ArrayList<>();

        System.out.print("Enter the student course code: ");
        String studentCourse = sc.nextLine().toUpperCase(); // Convert to uppercase for case-insensitive comparison

        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            ListInterface<Course> courseList = student.getCourseList();
            for (int j = 1; j <= courseList.getNumberOfEntries(); j++) {
                Course course = courseList.getEntry(j);
                if (studentCourse.contains(course.getCourseCode().toUpperCase())) {
                    filteredStudent.add(student);
                    break; // No need to continue checking the other courses for this student
                }
            }
        }

        if (!filteredStudent.isEmpty()) {
            System.out.println("");
            studentUI.studentHeader();
            for (int i = 1; i <= filteredStudent.getNumberOfEntries(); i++) {
                System.out.println(i + ". " + filteredStudent.getEntry(i).toString());
            }
        } else {
            MessageUI.displayEmpty();
        }
    }

    private int countCourse(int i) {
        int countCourse = studentList.getEntry(i).getCourseList().getNumberOfEntries();
        return countCourse;
    }

    private int totalCourseFee(int i) {
        int totalCourseFee = 0;
        if (!studentList.getEntry(i).getCourseList().isEmpty()) {
            for (int j = 1; j <= studentList.getEntry(i).getCourseList().getNumberOfEntries(); j++) {
                int courseFee = studentList.getEntry(i).getCourseList().getEntry(j).getCourseFee();
                totalCourseFee += courseFee;
            }
        }
        return totalCourseFee;
    }

    public Student getStudent(String id) {
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            if (studentList.getEntry(i).getStudentID().equals(id)) {
                return studentList.getEntry(i);
            }
        }
        //using student id to find the student and return the student all detail(student object)
        //if no found return null
        return null;
    }

    public static LinkedList<Student> getStudentList() {
        return studentList;
    }

    public static void setStudentList(LinkedList<Student> studentList) {
        StudentController.studentList = studentList;
    }

    public static void main(String[] args) {
        new MainDao().generate();
        new StudentController().runStudentController();
    }
}
