
package control;

import dao.*;
import adt.ListInterface;
import adt.HashMap;
import adt.ArrayList;
import adt.LinkedList;
import entity.Course;
import entity.Tutor;
import boundary.TeachingAssignmentUI;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.util.Iterator;
import utility.MessageUI;

public class TeachingAssignment {
//    ArrayList<Course> courseList = Course.getCourseList();
    ArrayList<Course> courseList = new ArrayList<>();

    ArrayList<Tutor> tutorList = Tutor.getTutorList();

    ArrayList<TutorialGroup> tutorialGroupList = TutorialGroupManagement.getTutorialGroupList();
    
    HashMap<String, Course> courseMap = CourseInitializer.initializeCourses();
    

    private final TeachingAssignmentUI teachingAssignmentUI = new TeachingAssignmentUI();
    
       //----------------------------Testing--------------------------------------
    
    public void TestingValue() {
        
 
       tutorList.add(new Tutor("1", "Tutor1"));
       tutorList.add(new Tutor("2", "Tutor2"));
       tutorList.add(new Tutor("3", "Tutor3"));
        

//        courseList.add(new Course("C1", "DSA", "SUPER", 10, "Yee"));
//        courseList.add(new Course("C2", "RearchM", "MyGuru", 15, "WOW"));
//        courseList.add(new Course("C3", "IS", "BAIDU" , 20, "Shit"));

        tutorialGroupList.add(new TutorialGroup("TG1","tutorialGroup1", "S1"));
        tutorialGroupList.add(new TutorialGroup("TG2","tutorialGroup2", "S2"));
        tutorialGroupList.add(new TutorialGroup("TG3","tutorialGroup3", "S3"));
    }
    
    public void displayTesting() {
        System.out.println(tutorList.toString());
        System.out.println(courseList.toString());
        System.out.println(tutorialGroupList.toString());
    }
    
       //----------------------------Testing--------------------------------------

    public void entry() {
        boolean transferCourseData = false;
        getHashMapCourseList(transferCourseData);
        int choice = 0;
        do {
            
            choice = teachingAssignmentUI.getTAMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1: // Assign tutor to course 
                        //Assign it for make sure tutor to validate courseType p, t, l and available
                    
                    assignTutorToCourse();
                    break;
                case 2: // Assign tutorial groups to a tutor 
                        //To know Tutor handle which tutorial group

                    assignTutorGrpToTutor();
                    break;
                case 3: // Add tutors to a tutorial group for a course
                        // Which tutorial group is under course validate by case 1 assigned, 
                        //the tutorial group assigned the course and add tutor into it with validate courseType
                        addTutorToTutorialGrpForACourse();
                    break;
                case 4: // Search course under a tutor
                        searchCourseUnderTutor();
                    break;
                case 5: // Search tutors for a course (T, P, L)
                        searchTutorsForCourseType();
                    break;
                case 6: // List tutors and tutorial groups for a course
                        listTutorsAndTutorialGroupsForCourse();
                    break;
                case 7: // List course for each tutor
                      listCourseForEachTutor();
                    break;
                case 8: // Filter tutors based on criterion
                        filterTutorsBasedOnCriterion();
                    break;
                    
                case 9: //Generate Report
                        generateReports();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }
    
    public void getHashMapCourseList(boolean transferCourseData) {
            
            if (transferCourseData == true){
                return;
            }
            
            LinkedList<Course> allCourse = courseMap.getAllValue();
            for (int i = 1; i <= allCourse.getNumberOfEntries(); i++){
                    courseList.add(allCourse.getEntry(i));

            }
            transferCourseData = true;
    }
    
    
    public String getAllCourse() {
        
        String outputCourseList = "";
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
          outputCourseList += i + "." + courseList.getEntry(i) + "\n";
        }
        return outputCourseList;
            
    }
    
    public String getAllTutorialGroups(){
         String outputTutorialGroups = "";
         for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
          outputTutorialGroups += i + "." + tutorialGroupList.getEntry(i) + "\n";
        }
        return outputTutorialGroups;
    
    }
    
    
    public void assignTutorToCourse() {
    if (tutorList.isEmpty()) {
        teachingAssignmentUI.TutorIsEmpty();
        return;
    }


    teachingAssignmentUI.printAllList(tutorList);
    
    int selectedTutorIndex = teachingAssignmentUI.getIndex();

    if (selectedTutorIndex > 0 && selectedTutorIndex <= tutorList.getNumberOfEntries()) {
        Tutor selectedTutor = tutorList.getEntry(selectedTutorIndex);

        if (courseList.isEmpty()) {
            teachingAssignmentUI.CourseIsEmpty();
            return;
        }

        teachingAssignmentUI.listAllCourse(getAllCourse());
        int selectedCourseIndex = teachingAssignmentUI.getIndex();
        
        if (selectedCourseIndex > 0 && selectedCourseIndex <= courseList.getNumberOfEntries()) {
            Course selectedCourse = courseList.getEntry(selectedCourseIndex);
            int choice = 0;
            
            
                do {
                
                    choice = teachingAssignmentUI.getCourseTypeChoice();
                    String confirmChoice = "NULL";
                
                    switch (choice) {
                    case 0:
                        MessageUI.displayExitMessage();
                    break;
                    case 1: //  Assign Tutor to Tutorial
                            
                            
                            do{
                                if (!selectedCourse.getTutorialList().isEmpty()) {
                                
                                    System.out.println("It is assigned by tutor, are you sure want to replace it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        selectedCourse.getTutorialList().replace(selectedTutorIndex, selectedTutor);
                                        selectedTutor.getTutorialList().replace(selectedCourseIndex, selectedCourse);
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getTutorialList().add(selectedTutor);
                                        selectedTutor.getTutorialList().add(selectedCourse);
                                       
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                    
                                }
                                
                               if (!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n"))){
                                   MessageUI.displayInvalidChoiceMessage();
                               }        
                                
                        }while(!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n")));
                        
                        
                        
                    break;
                    case 2: // Assign Tutor to Practical
                        do{
                                if (!selectedCourse.getPracticalList().isEmpty()) {
                                
                                    System.out.println("It is assigned by tutor, are you sure want to replace it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getPracticalList().replace(selectedTutorIndex, selectedTutor);
                                        selectedTutor.getPracticalList().replace(selectedCourseIndex, selectedCourse);
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getPracticalList().add(selectedTutor);
                                        selectedTutor.getPracticalList().add(selectedCourse);
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                    
                                }
                                
                               if (!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n"))){
                                   MessageUI.displayInvalidChoiceMessage();
                               }        
                                
                        }while(!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n")));
                        
                    break;
                    case 3: // Assign Tutor to Lecture
                        do{
                                if (!selectedCourse.getLectureList().isEmpty()) {
                                
                                    System.out.println("It is assigned by tutor, are you sure want to replace it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getLectureList().replace(selectedTutorIndex, selectedTutor);
                                        selectedTutor.getLectureList().replace(selectedCourseIndex, selectedCourse);
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getLectureList().add(selectedTutor);
                                        selectedTutor.getLectureList().add(selectedCourse);
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                    
                                }
                                
                               if (!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n"))){
                                   MessageUI.displayInvalidChoiceMessage();
                               }        
                                
                        }while(!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n")));
                        
                    break;
                
                    default:
                        MessageUI.displayInvalidChoiceMessage();
                    break;
                }
                
                
            
            } while (choice != 0 && (choice < 1 || choice > 3));

            choice = 0;
        } else {
            teachingAssignmentUI.CourseNotFound();
            return;
        }
    } else {
        teachingAssignmentUI.TutorNotFound();
        return;
    }
    return;
}
    
    
    public void assignTutorGrpToTutor() {
    if (tutorialGroupList.isEmpty()) {
        teachingAssignmentUI.TutorGrpIsEmpty();
        return;
    }

    teachingAssignmentUI.listAllTutors(getAllTutors());
    int selectedTutorIndex = teachingAssignmentUI.getIndex();
    
    

    if (selectedTutorIndex > 0 && selectedTutorIndex <= tutorialGroupList.getNumberOfEntries()) {
        Tutor selectedTutor = tutorList.getEntry(selectedTutorIndex);

        if (tutorList.isEmpty()) {
            teachingAssignmentUI.TutorIsEmpty();
            return;
        }
        
        teachingAssignmentUI.listAllTutorialGroup(getAllTutorialGroups());
        
        int selectedTutorialGroupIndex = teachingAssignmentUI.getIndex();

        if ( selectedTutorialGroupIndex > 0 && selectedTutorialGroupIndex <= tutorList.getNumberOfEntries()) {
            
            TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(selectedTutorialGroupIndex);

            selectedTutor.getTutorialGroupList().add(selectedTutorialGroup);
            selectedTutorialGroup.setTutor(selectedTutor);
            

            System.out.println("Successfully assigned tutorial group " + selectedTutorialGroup.getTutorGroupName() + " to tutor " + selectedTutor.getTutorName());
            //Testing Output
            
            System.out.println(selectedTutorialGroup.getTutor().getTutorName());
            
            
        } else {
            teachingAssignmentUI.TutorNotFound();
            return;
        }
    } else {
        teachingAssignmentUI.TutorGrpNotFound();
        return;
    }
    return;
}
    
    public void addTutorToTutorialGrpForACourse() {
    if (tutorialGroupList.isEmpty()) {
        teachingAssignmentUI.TutorGrpIsEmpty();
            return;
    }
    
    teachingAssignmentUI.listAllTutorialGroup(getAllTutorialGroups());
    int selectedTutorialGroupIndex = teachingAssignmentUI.getIndex();

    if (selectedTutorialGroupIndex > 0 && selectedTutorialGroupIndex <= tutorialGroupList.getNumberOfEntries()) {
        TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(selectedTutorialGroupIndex);

           if (courseList.isEmpty()) {
            teachingAssignmentUI.CourseIsEmpty();
            return;
        }
           
        teachingAssignmentUI.listAllCourse(getAllCourse());
        int selectedCourseIndex = teachingAssignmentUI.getIndex();
        
        if (selectedCourseIndex > 0 && selectedCourseIndex <= courseList.getNumberOfEntries()) {
            Course selectedCourse = courseList.getEntry(selectedCourseIndex);

           if (courseList.isEmpty()) {
                teachingAssignmentUI.CourseIsEmpty();
                
                return;
            }
                int choice = 0;
           do {
                    
                    choice = teachingAssignmentUI.getCourseTypeChoice();
                    String confirmChoice = "NULL";
                
                    switch (choice) {
                    case 0:
                        MessageUI.displayExitMessage();
                    break;
                    case 1: //  Select Tutorial of tutor to add to Tutorial Group
                        
                            if (selectedCourse.getTutorialList().isEmpty()){
                                System.out.println("No Tutor available to assign this Tutorial");
                                break;
                            }
                                
                            int selectTutorialOfTutorIndex;
                            
                                do {
                                    for (int i = 1; i <= selectedCourse.getTutorialList().getNumberOfEntries(); i++) {
                                            Tutor getTutor = selectedCourse.getTutorialList().getEntry(i);
                                            System.out.println((i) + ". " + getTutor);
                                        }
                                
                                selectTutorialOfTutorIndex = teachingAssignmentUI.getIndex();
                                
                                if (selectTutorialOfTutorIndex <= 0 || selectTutorialOfTutorIndex > tutorList.getNumberOfEntries()){
                                    MessageUI.displayInvalidChoiceMessage();
                                }
                                
                                } while (selectTutorialOfTutorIndex <= 0 || selectTutorialOfTutorIndex > tutorList.getNumberOfEntries());
                                
                                
                                
                            
                                
                                
                                
                                do{
                                if (!selectedTutorialGroup.getTutorialGrpTutorialList().isEmpty()) {
                                
                                    System.out.println("It is assigned by tutor, are you sure want to replace it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        selectedTutorialGroup.getTutorialGrpTutorialList().put(selectedCourse, selectedCourse.getTutorialList());
                                        System.out.println( "Testing Tutorial " + selectedTutorialGroup.getTutorialGrpTutorialList().get(selectedCourse));
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedTutorialGroup.getTutorialGrpTutorialList().put(selectedCourse, selectedCourse.getTutorialList());
                                        System.out.println( "Testing Tutorial " + selectedTutorialGroup.getTutorialGrpTutorialList().get(selectedCourse));

                                        
                                    }
                                    
                                }
                                
                               if (!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n"))){
                                   MessageUI.displayInvalidChoiceMessage();
                               }        
                                
                        }while(!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n")));
                                

                    break;
                    case 2: // Assign Tutor to Practical
                        
                        if (selectedCourse.getTutorialList().isEmpty()){
                                System.out.println("No Tutor available to assign this Practical");
                                break;
                            }
                                
                            int selectPracticalOfTutorIndex;
                            
                                do {

                                  for (int i = 1; i <= selectedCourse.getPracticalList().getNumberOfEntries(); i++) {
                                            Tutor getTutor = selectedCourse.getPracticalList().getEntry(i);
                                            System.out.println((i) + ". " + getTutor);
                                        }
                                
                                selectPracticalOfTutorIndex = teachingAssignmentUI.getIndex();
                                
                                if (selectPracticalOfTutorIndex <= 0 || selectPracticalOfTutorIndex > tutorList.getNumberOfEntries()){
                                    MessageUI.displayInvalidChoiceMessage();
                                }
                                
                                } while (selectPracticalOfTutorIndex <= 0 || selectPracticalOfTutorIndex > tutorList.getNumberOfEntries());
                                
                                
                                
                            
                                
                                
                                
                                do{
                                if (!selectedTutorialGroup.getTutorialGrpPracticalList().isEmpty()) {
                                
                                    System.out.println("It is assigned by tutor, are you sure want to replace it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        selectedTutorialGroup.getTutorialGrpPracticalList().put(selectedCourse, selectedCourse.getPracticalList());
                                        System.out.println( "Testing Practical " + selectedTutorialGroup.getTutorialGrpPracticalList().get(selectedCourse));
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedTutorialGroup.getTutorialGrpPracticalList().put(selectedCourse, selectedCourse.getPracticalList());
                                        System.out.println( "Testing Practical " + selectedTutorialGroup.getTutorialGrpPracticalList().get(selectedCourse));

                                        
                                    }
                                    
                                }
                                
                               if (!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n"))){
                                   MessageUI.displayInvalidChoiceMessage();
                               }        
                                
                        }while(!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n")));

                    break;
                    case 3: // Assign Tutor to Lecture
                        
                        if (selectedCourse.getTutorialList().isEmpty()){
                                System.out.println("No Tutor available to assign this Lecture");
                                break;
                            }
                                
                            int selectLectureOfTutorIndex;
                            
                                do {
                                    for (int i = 1; i <= selectedCourse.getLectureList().getNumberOfEntries(); i++) {
                                            Tutor getTutor = selectedCourse.getLectureList().getEntry(i);
                                            System.out.println((i) + ". " + getTutor);
                                        }
                                
                                selectLectureOfTutorIndex = teachingAssignmentUI.getIndex();
                                
                                if (selectLectureOfTutorIndex <= 0 || selectLectureOfTutorIndex > tutorList.getNumberOfEntries()){
                                    MessageUI.displayInvalidChoiceMessage();
                                }
                                
                                } while (selectLectureOfTutorIndex <= 0 || selectLectureOfTutorIndex > tutorList.getNumberOfEntries());
                                
                                
                                
                            
                                
                                
                                
                                do{
                                if (!selectedTutorialGroup.getTutorialGrpLectureList().isEmpty()) {
                                
                                    System.out.println("It is assigned by tutor, are you sure want to replace it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        selectedTutorialGroup.getTutorialGrpLectureList().put(selectedCourse, selectedCourse.getLectureList());
                                        System.out.println( "Lecture " + selectedTutorialGroup.getTutorialGrpLectureList().get(selectedCourse));
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedTutorialGroup.getTutorialGrpLectureList().put(selectedCourse, selectedCourse.getLectureList());
                                        System.out.println( "Lecture " + selectedTutorialGroup.getTutorialGrpLectureList().get(selectedCourse));

                                        
                                    }
                                    
                                }
                                
                               if (!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n"))){
                                   MessageUI.displayInvalidChoiceMessage();
                               }        
                                
                        }while(!(confirmChoice.equalsIgnoreCase("y") || confirmChoice.equalsIgnoreCase("n")));
  
                    break;
                
                    default:
                        MessageUI.displayInvalidChoiceMessage();
                    break;
                }
                
                
            
            } while (choice != 0 && (choice < 1 || choice > 3));
           
           
           
           
        } else {
            teachingAssignmentUI.CourseNotFound();
            return;
        }
    } else {
        teachingAssignmentUI.TutorGrpNotFound();
        return;
    }
    return;
}
    
public void searchCourseUnderTutor() {
    if (courseList.isEmpty()) {
        teachingAssignmentUI.TutorIsEmpty();
        return;
    }

    teachingAssignmentUI.listAllCourse(getAllCourse());
    
    String input = teachingAssignmentUI.getInput();

    boolean courseFound = false;
    
    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
        Course courseGetEntry = courseList.getEntry(i);
        if (courseGetEntry.getCourseCode().contains(input) || courseGetEntry.getCourseName().contains(input)) {
            System.out.println("Course ID: " + courseGetEntry.getCourseCode() + "\nCourse Name: " + courseGetEntry.getCourseName());
            courseFound = true;
            
            if (courseGetEntry.getTutorialList().isEmpty() 
                    && courseGetEntry.getPracticalList().isEmpty() 
                    && courseGetEntry.getLectureList().isEmpty()) {
                
                System.out.println("The Course is not assigned any Tutor yet");
            } else {
                int num = 0;
                 for (int j = 1 ; j <= courseGetEntry.getTutorialList().getNumberOfEntries() ; j++){
                                    if (!courseGetEntry.getTutorialList().isEmpty()) {
                                    ++num;
                                                   
                                    System.out.println( num  + ". " + courseGetEntry.getTutorialList());
                                    System.out.println( num  + ". Tutorial Tutor: " + courseGetEntry.getTutorialList());
                                    }
                                }
                                
                                for (int k = 1 ; k <= courseGetEntry.getTutorialList().getNumberOfEntries() ; k++){
                                    if (!courseGetEntry.getPracticalList().isEmpty()) {
                                    ++num;
                                                   
                                    System.out.println( num  + ". " + courseGetEntry.getPracticalList());
                                    System.out.println( num  + ". Practical Tutor: " + courseGetEntry.getPracticalList());
                                    }
                                }
                                
                                for (int l = 1 ; l <= courseGetEntry.getTutorialList().getNumberOfEntries() ; l++){
                                    if (!courseGetEntry.getLectureList().isEmpty()) {
                                    ++num;
                                                   
                                    System.out.println( num  + ". " + courseGetEntry.getLectureList());
                                    System.out.println( num  + ". Lecture Tutor: " + courseGetEntry.getLectureList());
                                }
            }
            break; // Stop searching after finding the course
        }
    }
    
    }
    if (!courseFound) {
            System.out.println("Course not found.");
        }
}


public void searchTutorsForCourseType() {
    if (courseList.isEmpty()) {
        teachingAssignmentUI.TutorIsEmpty();
        return;
    }

    teachingAssignmentUI.listAllCourse(getAllCourse());
    
    String Input = teachingAssignmentUI.getInput();
    
    boolean TutorExist = false;
    
    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
        Course courseGetEntry = courseList.getEntry(i);
        int index = 0;
        
        if (courseGetEntry.getPracticalList().equals(Input)
                || courseGetEntry.getLectureList().equals(Input)
                || courseGetEntry.getTutorialList().equals(Input)) {
            System.out.println("Course Type: " + Input);
            
            
            for (int j = 1; j <= tutorList.getNumberOfEntries(); j++) {
                 
                
                
//                String tutorString = String.format(tutorGetEntry);
                  
                Course tutorGetEntry = courseList.getEntry(j);
                
                if (courseGetEntry.getTutorialList() != null && courseGetEntry.getTutorialList().equals(tutorGetEntry)) {
                    TutorExist = true;
                                                   
                    System.out.println( ++index  + ". " + tutorGetEntry);
                }
                
                else if (courseGetEntry.getPracticalList() != null && courseGetEntry.getPracticalList().equals(tutorGetEntry)) {
                    TutorExist = true;
                                                   
                    System.out.println( ++index  + ". " + tutorGetEntry);
                }
                
                else if (courseGetEntry.getTutorialList() != null && courseGetEntry.getLectureList().equals(tutorGetEntry)) {
                    TutorExist = true;
                                                   
                    System.out.println( ++index  + ". " + tutorGetEntry);
                }
                
            }
            
            if (!TutorExist) {
                System.out.println("The Course Type is not assigned any Tutor yet");
                
            }
            
            return;
        }
        
        
    }
    
    if (!TutorExist){
            teachingAssignmentUI.CourseNotFound();
            return;
        }
    
    return;
}

public void listTutorsAndTutorialGroupsForCourse() {
    if (courseList.isEmpty()) {
        teachingAssignmentUI.TutorIsEmpty();
        return;
    }

    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
        Course getCourse = courseList.getEntry(i);
        System.out.println( "\n" + i + "Course :" + getCourse.getCourseName() + ":");

        if (getCourse.getTutorialList().isEmpty()
    && getCourse.getPracticalList().isEmpty()
    && getCourse.getLectureList().isEmpty()
    && (TutorialGroup.getTutorialGrpTutorialList().isEmpty() 
        || !(TutorialGroup.getTutorialGrpTutorialList().containsKey(getCourse)))
    && (TutorialGroup.getTutorialGrpPracticalList().isEmpty() 
        || !(TutorialGroup.getTutorialGrpPracticalList().containsKey(getCourse)))
    && (TutorialGroup.getTutorialGrpLectureList().isEmpty() 
        || !(TutorialGroup.getTutorialGrpLectureList().containsKey(getCourse)))) {
            System.out.println("No tutors assigned to this course.");
            
        } else {
            
    
            
                
            for (int j = 1; j <= getCourse.getTutorialList().getNumberOfEntries(); j++) {
                int index = 0;
                
                TutorialGroup getTutorialGroup = tutorialGroupList.getEntry(j);  
                
                if (getTutorialGroup.getTutorialGrpTutorialList().containsKey(getCourse)
                    || getTutorialGroup.getTutorialGrpPracticalList().containsKey(getCourse)
                    || getTutorialGroup.getTutorialGrpLectureList().containsKey(getCourse)){
                    
                    System.out.println( ++index + ". \n" + getTutorialGroup);
                    
                    if (getCourse.getTutorialList().equals(getTutorialGroup.getTutorialGrpTutorialList().get(getCourse))){
                    System.out.println( " Tutorial Of Tutor: " 
                                        + getTutorialGroup.getTutorialGrpTutorialList().get(getCourse));
                    }

                    if (getCourse.getPracticalList().equals(getTutorialGroup.getTutorialGrpPracticalList().get(getCourse))){
                    System.out.println(" Practical Of Tutor: " 
                                        + getTutorialGroup.getTutorialGrpPracticalList().get(getCourse));
                    }

                    if (getCourse.getLectureList().equals(getTutorialGroup.getTutorialGrpLectureList().get(getCourse))){
                    System.out.println(" Lectture Of Lecture: " 
                                        + getTutorialGroup.getTutorialGrpLectureList().get(getCourse));
                    }

                    

                }
                
            }
                
            }
        }

}

//public void listTutorsAndTutorialGroupsForCourse() {
//    if (courseList.isEmpty()) {
//        teachingAssignmentUI.TutorIsEmpty();
//        return;
//    }
//
//    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
//        Course getCourse = courseList.getEntry(i);
//        System.out.println( "\n" + i + "Course :" + getCourse.getCourseName() + ":");
//
//        if (getCourse.getTutorialList().isEmpty()
//            && getCourse.getPracticalList().isEmpty()
//            && getCourse.getLectureList().isEmpty()
//            && TutorialGroup.getTutorialGrpTutorialList().isEmpty() 
//            || !(TutorialGroup.getTutorialGrpTutorialList().containsKey(getCourse))
//            && TutorialGroup.getTutorialGrpPracticalList().isEmpty() 
//            || !(TutorialGroup.getTutorialGrpPracticalList().containsKey(getCourse))
//            && TutorialGroup.getTutorialGrpLectureList().isEmpty() 
//            || !(TutorialGroup.getTutorialGrpLectureList().containsKey(getCourse))) {
//            System.out.println("No tutors assigned to this course.");
//            
//        } else {
//            
//    
//            
//                
//            for (int j = 1; j <= getCourse.getTutorialList().getNumberOfEntries(); j++) {
//                int index = 0;
//                
//                TutorialGroup getTutorialGroup = tutorialGroupList.getEntry(j);  
//                
//                if (getTutorialGroup.getTutorialGrpTeachingList().containsKey(getCourse)){
//                    
//                    System.out.println( ++index + ". \n" + getTutorialGroup);
//                    
//                    if (getCourse.getTutorialList().equals(getTutorialGroup.getTutorialGrpTeachingList().get(getCourse))){
//                    System.out.println( " Tutorial Of Tutor: " 
//                                        + getTutorialGroup.getTutorialGrpTeachingList().get(getCourse));
//                    }
//
//                    if (getCourse.getPracticalList().equals(getTutorialGroup.getTutorialGrpTeachingList().get(getCourse))){
//                    System.out.println(" Practical Of Tutor: " 
//                                        + getTutorialGroup.getTutorialGrpTeachingList().get(getCourse));
//                    }
//
//                    if (getCourse.getLectureList().equals(getTutorialGroup.getTutorialGrpTeachingList().get(getCourse))){
//                    System.out.println(" Lectture Of Lecture: " 
//                                        + getTutorialGroup.getTutorialGrpTeachingList().get(getCourse));
//                    }
//
//                    
//
//                }
//                
//            }
//                
//            }
//        }
//
//}



public void listCourseForEachTutor() {
    
    if (tutorList.isEmpty()) {
        teachingAssignmentUI.TutorIsEmpty();
        return;
    }

    for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
        boolean courseExist = false;
        int index = 0;
        
        Tutor tutorGetEntry = tutorList.getEntry(i);
        
        System.out.println( i + ".Tutor ID: " + tutorGetEntry.getTutorID() + ", Tutor Name: " + tutorGetEntry.getTutorName());
        System.out.println( "___________________________________________________");
        

        for (int j = 1; j <= courseList.getNumberOfEntries(); j++) {
            
            Course courseGetEntry = courseList.getEntry(j);
            
            if (courseGetEntry.getTutorialList() != null && courseGetEntry.getTutorialList().contains(tutorGetEntry)) {
            courseExist = true;
                      
            System.out.println( ++index  + ". " + courseGetEntry.getCourseCode() + "," + courseGetEntry.getCourseName());
                }
            
            if (courseGetEntry.getPracticalList() != null && courseGetEntry.getPracticalList().contains(tutorGetEntry)) {
            courseExist = true;
                      
            System.out.println( ++index  + ". " + courseGetEntry.getCourseCode() + "," + courseGetEntry.getCourseName());
                }
            
            if (courseGetEntry.getLectureList() != null && courseGetEntry.getLectureList().contains(tutorGetEntry)) {
            courseExist = true;
                      
            System.out.println( ++index  + ". " + courseGetEntry.getCourseCode() + "," + courseGetEntry.getCourseName());
                }
            
        }
        
        if (!courseExist){
            System.out.println("The Tutor is not assigned any course yet");
        }
        
         
        

        System.out.println();
    }
}

public void filterTutorsBasedOnCriterion() { // need more improvement
    System.out.println("Enter Criterion: ");
    String Searching = teachingAssignmentUI.getInput();
    int index = 0;
    
    if (tutorList.isEmpty()) {
        System.out.println("No tutors found with the given criteria.");
        return;
    }
    
    for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
        Tutor tutorGetEntry = tutorList.getEntry(i);
            if (tutorGetEntry.getTutorID().contains(Searching) 
                || tutorGetEntry.getTutorName().contains(Searching)){
//            tutorList.contains(tutorGetEntry)
                System.out.println(tutorGetEntry);
                
            
        }
    }
    

}
    
    public String getAllTutors() {
        
        String outputTutorList = "";
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
          outputTutorList += i + "." + tutorList.getEntry(i) + "\n";
        }
        return outputTutorList;
        
    }
    
    
    public void displayTutorList() {
        teachingAssignmentUI.listAllTutors(getAllTutors()); 
      }
        
    
    public void printTutorialList(Course selectedCourse){// I need delete
        
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course getCourse = courseList.getEntry(i);
            
            for (int j = 1; j <= tutorList.getNumberOfEntries(); j++){
                
                Tutor getTutor = tutorList.getEntry(j);
//                System.out.println( j + "TutorList" + tutorList.getEntry(j));
                
                if (getTutor.getTutorialList().equals(selectedCourse)){

                    System.out.println((j) + ". " + getTutor.getTutorialList());
                }
            }
                
        }

    }
    
    public void generateReports(){
        // report generate menu choice
        int choice;
        do{
            choice = teachingAssignmentUI.reportMenu();
            switch(choice){
                case 1:
                    TutorialGrolupReport();
                    break;
                case 2:
                    tgPReport();
                    break;
                default:
                    MessageUI.displayExitMessage();
                    break;
            }
        }while(choice > 0 && choice < 3);
    }
    
    public void TutorialGrolupReport(){
        String content = "";
        // get report ui
        content += teachingAssignmentUI.generateReport1(content);
        // get report content
        content += String.format("\n%-4s %-8s\n",
                "", "TutorID ", "Name");

        for (int i = 1; i <= tutorList.getNumberOfEntries() ; i++){
            Tutor getTutorEntry = tutorList.getEntry(i); 
            String getTutor = "";
            
            
            content += String.format("%-4s %-10s\n", 
                    Integer.toString(i) + ".", getTutorEntry);
        }
        content += teachingAssignmentUI.SeparateLine()
                + getHighestTutorAssignedCourse()
                + getHighestTutorAssignedTutorial()
//                + getLowestTutorAssignedTutorial()
                + getHighestTutorAssignedPractical()
//                + getLowestTutorAssignedPractical()
                + getHighestTutorAssignedLecture()
//                + getLowestTutorAssignedLecture()
//                + getAmountOfTutorNoAssigned()
                + teachingAssignmentUI.ReportFooter();
        // generate it
        generateTypeChoice(content);
    }
    
    public String getHighestTutorAssignedCourse(){
        String BuildString = "\n\nHighest Of Tutor Assigned Course: ";
        int HighestNum = 0;
        int num = 0;
        int temporalNumber;
        
        Tutor highestTutor = null;
        
        HashMap<Tutor, Integer> countAssignedCourse = new HashMap<>();
        
        
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++){
            Tutor getTutor = tutorList.getEntry(i);
            
             
            for (int j = 1; j <= getTutor.getTutorialList().getNumberOfEntries(); j++){
                Course getTutorialUnderCourse = getTutor.getTutorialList().getEntry(j);
                
                 if (getTutor.getTutorialList().getEntry(j) == null) {
                    continue; // Skip to the next iteration
                }

                if (getTutorialUnderCourse == null) {
                    continue; // Skip to the next iteration
                }
                for (int k = 1; k <= courseList.getNumberOfEntries(); k++){
                    Course getCourse = courseList.getEntry(k);
                    if (getTutorialUnderCourse.equals(getCourse)){
                        
                        if ( countAssignedCourse.containsKey(getTutor)){
                            
                            temporalNumber = countAssignedCourse.get(getTutor);
                            temporalNumber += 1;
                            countAssignedCourse.put(getTutor, temporalNumber);
                        }
                        
                        else {countAssignedCourse.put(getTutor, ++num);}
                    }
                }
            }
            
            for (int l = 1; l <= getTutor.getPracticalList().getNumberOfEntries(); l++){
                Course getTutorialUnderCourse = getTutor.getPracticalList().getEntry(l);
                
                if (getTutor.getPracticalList().getEntry(l) == null) {
                    continue; // Skip to the next iteration
                }

                if (getTutorialUnderCourse == null) {
                    continue; // Skip to the next iteration
                }
                for (int m = 1; m <= courseList.getNumberOfEntries(); m++){
                    
                    Course getCourse = courseList.getEntry(m);
                    if (getTutorialUnderCourse.equals(getCourse)){
                        if ( countAssignedCourse.containsKey(getTutor)){
                            
                            temporalNumber = countAssignedCourse.get(getTutor);
                            temporalNumber += 1;
                            countAssignedCourse.put(getTutor, temporalNumber);
                        }
                        
                        else {countAssignedCourse.put(getTutor, ++num);}
                        
                        
                    }
                    
                }
            }
            
            for (int n = 1; n <= getTutor.getLectureList().getNumberOfEntries(); n++){
                Course getTutorialUnderCourse = getTutor.getLectureList().getEntry(n);
                
                if (getTutor.getLectureList().getEntry(n) == null) {
                    continue; // Skip to the next iteration
                }

                if (getTutorialUnderCourse == null) {
                    continue; // Skip to the next iteration
                }
                for (int p = 1; p <= courseList.getNumberOfEntries(); p++){
                    Course getCourse = courseList.getEntry(p);
                    if (getTutorialUnderCourse.equals(getCourse)){

                        if ( countAssignedCourse.containsKey(getTutor)){
                            
                            temporalNumber = countAssignedCourse.get(getTutor);
                            temporalNumber += 1;
                            countAssignedCourse.put(getTutor, temporalNumber);
                        }
                        
                        else {countAssignedCourse.put(getTutor, ++num);}
                    }
                }
            }
        
        }
        
           for (int i = 1; i <= countAssignedCourse.size(); i++) {
               Tutor getTutor = tutorList.getEntry(i);
               Tutor tutor = null;
               
               if (countAssignedCourse.get(getTutor) != null){
                   tutor = getTutor;
               }
               
               Integer countInteger = countAssignedCourse.get(getTutor);
                if (countInteger != null && tutor != null) {
                int count = countInteger.intValue();
                if (count > HighestNum) {
                HighestNum = count;
                highestTutor = getTutor;
                }
        }

    }

    if (highestTutor != null) {
        BuildString += highestTutor + 
                       "\nTotal Courses Assigned: " + HighestNum;
    } else {
        BuildString += "\nNo Tutors Found.";
    }

    return BuildString;
}
    
    public String getHighestTutorAssignedTutorial(){
        String BuildString = "\n\nHighest Of Tutor Assigned Tutorial Course: ";
        int HighestNum = 0;
        int num = 0;
        int temporalNumber;
        
        Tutor highestTutor = null;
        
        HashMap<Tutor, Integer> countAssignedTutorial = new HashMap<>();
        
        
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++){
            Tutor getTutor = tutorList.getEntry(i);
            
             
            for (int j = 1; j <= getTutor.getTutorialList().getNumberOfEntries(); j++){
                Course getTutorialUnderCourse = getTutor.getTutorialList().getEntry(j);
                
                 if (getTutor.getTutorialList().getEntry(j) == null) {
                    continue; // Skip to the next iteration
                }

                if (getTutorialUnderCourse == null) {
                    continue; // Skip to the next iteration
                }
                for (int k = 1; k <= courseList.getNumberOfEntries(); k++){
                    Course getCourse = courseList.getEntry(k);
                    if (getTutorialUnderCourse.equals(getCourse)){
                        
                        if ( countAssignedTutorial.containsKey(getTutor)){
                            
                            temporalNumber = countAssignedTutorial.get(getTutor);
                            temporalNumber += 1;
                            countAssignedTutorial.put(getTutor, temporalNumber);
                        }
                        
                        else {countAssignedTutorial.put(getTutor, ++num);}
                    }
                }
            }            
        
        }
        
           for (int i = 1; i <= countAssignedTutorial.size(); i++) {
               Tutor getTutor = tutorList.getEntry(i);
               Tutor tutor = null;
               
               if (countAssignedTutorial.get(getTutor) != null){
                   tutor = getTutor;
               }
               
               Integer countInteger = countAssignedTutorial.get(getTutor);
                if (countInteger != null && tutor != null) {
                int count = countInteger.intValue();
                if (count > HighestNum) {
                HighestNum = count;
                highestTutor = getTutor;
                }
        }

    }

    if (highestTutor != null) {
        BuildString += highestTutor + 
                       "\nThe Most Higher Number of Tutor Assigned Course: " + HighestNum;
    } else {
        BuildString += "\nNo Tutorial Of Tutors found.";
    }

    return BuildString;
}
    
    public String getHighestTutorAssignedPractical(){
        String BuildString = "\n\nHighest Of Tutor Assigned Practical Course: ";
        int HighestNum = 0;
        int num = 0;
        int temporalNumber;
        
        Tutor highestTutor = null;
        
        HashMap<Tutor, Integer> countAssignedPractical = new HashMap<>();
        
        
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++){
            Tutor getTutor = tutorList.getEntry(i);
            
            
            for (int l = 1; l <= getTutor.getPracticalList().getNumberOfEntries(); l++){
                Course getTutorialUnderCourse = getTutor.getPracticalList().getEntry(l);
                
                if (getTutor.getPracticalList().getEntry(l) == null) {
                    continue; // Skip to the next iteration
                }

                if (getTutorialUnderCourse == null) {
                    continue; // Skip to the next iteration
                }
                for (int m = 1; m <= courseList.getNumberOfEntries(); m++){
                    
                    Course getCourse = courseList.getEntry(m);
                    if (getTutorialUnderCourse.equals(getCourse)){
                        if ( countAssignedPractical.containsKey(getTutor)){
                            
                            temporalNumber = countAssignedPractical.get(getTutor);
                            temporalNumber += 1;
                            countAssignedPractical.put(getTutor, temporalNumber);
                        }
                        
                        else {countAssignedPractical.put(getTutor, ++num);}
                        
                        
                    }
                    
                }
            }
        
        }
        
           for (int i = 1; i <= countAssignedPractical.size(); i++) {
               Tutor getTutor = tutorList.getEntry(i);
               Tutor tutor = null;
               
               if (countAssignedPractical.get(getTutor) != null){
                   tutor = getTutor;
               }
               
               Integer countInteger = countAssignedPractical.get(getTutor);
                if (countInteger != null && tutor != null) {
                int count = countInteger.intValue();
                if (count > HighestNum) {
                HighestNum = count;
                highestTutor = getTutor;
                }
        }

    }

    if (highestTutor != null) {
        BuildString += highestTutor + 
                       "\nTotal Courses Assigned: " + HighestNum;
    } else {
        BuildString += "\nNo Tutors Found.";
    }

    return BuildString;
}
    
    
    public String getHighestTutorAssignedLecture(){
        String BuildString = "\n\nHighest Of Tutor Assigned Lecture Course: ";
        int HighestNum = 0;
        int num = 0;
        int temporalNumber;
        
        Tutor highestTutor = null;
        
        HashMap<Tutor, Integer> countLectureCourse = new HashMap<>();
        
        
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++){
            Tutor getTutor = tutorList.getEntry(i);
            
            for (int n = 1; n <= getTutor.getLectureList().getNumberOfEntries(); n++){
                Course getTutorialUnderCourse = getTutor.getLectureList().getEntry(n);
                
                if (getTutor.getLectureList().getEntry(n) == null) {
                    continue; // Skip to the next iteration
                }

                if (getTutorialUnderCourse == null) {
                    continue; // Skip to the next iteration
                }
                for (int p = 1; p <= courseList.getNumberOfEntries(); p++){
                    Course getCourse = courseList.getEntry(p);
                    if (getTutorialUnderCourse.equals(getCourse)){

                        if ( countLectureCourse.containsKey(getTutor)){
                            
                            temporalNumber = countLectureCourse.get(getTutor);
                            temporalNumber += 1;
                            countLectureCourse.put(getTutor, temporalNumber);
                        }
                        
                        else {countLectureCourse.put(getTutor, ++num);}
                    }
                }
            }
        
        }
        
           for (int i = 1; i <= countLectureCourse.size(); i++) {
               Tutor getTutor = tutorList.getEntry(i);
               Tutor tutor = null;
               
               if (countLectureCourse.get(getTutor) != null){
                   tutor = getTutor;
               }
               
               Integer countInteger = countLectureCourse.get(getTutor);
                if (countInteger != null && tutor != null) {
                int count = countInteger.intValue();
                if (count > HighestNum) {
                HighestNum = count;
                highestTutor = getTutor;
                }
        }

    }

    if (highestTutor != null) {
        BuildString += highestTutor + 
                       "\nTotal Courses Assigned: " + HighestNum;
    } else {
        BuildString += "\nNo Tutors Found.";
    }

    return BuildString;
}
    

    
    // report 2
    public void tgPReport(){
//        String content = "";
//        // get report ui
//        content += ui.report2(content);
//        // get report content
//        content += String.format("\n%-4s %-15s %-30s\n",
//                "", "Programme ID", "Number Of Tutorial Groups");
//        int i = 0;
//        Iterator<Programme> pIt = programmeList.getIterator();
//        while(pIt.hasNext()){
//            i++;
//            int amount = 0;
//            Programme p = pIt.next();
//            Iterator<TutorialGroup> tgIt = tgList.getIterator();
//            while(tgIt.hasNext()){
//                TutorialGroup tg = tgIt.next();
//                if(p.getTgList().contains(tg)){
//                    amount++;
//                }
//            }
//            content += String.format("%-4s %-15s %-8d\n", 
//                    Integer.toString(i) + ".", p.getProgrammeID(), amount);
//        }
//        content += ui.longBrakeLine()
//                + getLargestTGinP()
//                + getSmallestTGinP()
//                + checkAmountOfProgrammeNoHasTG()
//                + ui.longBrakeLine();
//        // generate it
//        generateTypeChoice(content);
    }
    
    public void generateTypeChoice(String content){
        int typeChoice = teachingAssignmentUI.reoportOutputTypeMenu();
        switch (typeChoice){
            case 1:
                teachingAssignmentUI.generateConsoleReport(content);
                break;
            case 2:
                teachingAssignmentUI.generateWindowReport(content);
            default:
                MessageUI.displayInvalidChoiceMessage();
        }
    }
    
    private static <T> boolean areArrayListsEqual(ArrayList<T> list1, ArrayList<T> list2) {
    if (list1.getNumberOfEntries() != list2.getNumberOfEntries()) {
        return false;
    }

    for (int i = 1; i <= list1.getNumberOfEntries(); i++) {
        if (!list1.getEntry(i).equals(list2.getEntry(i))) {
            return false;
        }
    }
    
    return true;
    }   

    public static void main(String[] args) {        
        
        TeachingAssignment taUI = new TeachingAssignment();
        
        taUI.TestingValue();
        taUI.displayTesting();
        taUI.entry();      
         
         
    }
    
}
