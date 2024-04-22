
package control;

import adt.ListInterface;
import adt.HashMap;
import adt.ArrayList;
import entity.Course;
import entity.Tutor;
import boundary.TeachingAssignmentUI;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.util.Iterator;
import utility.MessageUI;

public class TeachingAssignment {
    ArrayList<Course> courseList = Course.getCourseList();

    ArrayList<Tutor> tutorList = Tutor.getTutorList();

    ArrayList<TutorialGroup> tutorialGroupList = TutorialGroup.getTutorialGroupList();
    
//    HashMap<Course, ArrayList<Tutor>> TutorialGrpTeachingList = new HashMap();
    

    private final TeachingAssignmentUI teachingAssignmentUI = new TeachingAssignmentUI();
    
       //----------------------------Testing--------------------------------------
    
    public void TestingValue() {
        
 
       tutorList.add(new Tutor("1", "Tutor1"));
       tutorList.add(new Tutor("2", "Tutor2"));
       tutorList.add(new Tutor("3", "Tutor3"));
        

        courseList.add(new Course("C1", "DSA", "SUPER", 10, "Yee"));
        courseList.add(new Course("C2", "RearchM", "MyGuru", 15, "WOW"));
        courseList.add(new Course("C3", "IS", "BAIDU" , 20, "Shit"));

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
//                    listTutorsAndTutorialGroupsForCourse();
                    break;
                case 7: // List course for each tutor
                      listCourseForEachTutor();
                    break;
                case 8: // Filter tutors based on criterion
                    filterTutorsBasedOnCriterion();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
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
//                                        selectedTutor.getTutorialList().replace(selectedCourseIndex, selectedCourse);
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getTutorialList().add(selectedTutor);
                                        selectedTutor.getTutorialList().add(selectedCourse);
                                        System.out.println("Testing 1" + selectedTutor.getTutorialList());
                                        System.out.println("Testing 2" + selectedCourse.getTutorialList());
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
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getPracticalList().add(selectedTutor);
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
                                        TeachingAssignmentUI.getAssignedTutorToCourse(selectedTutor, selectedCourse);
                                    }
                                
                                }
                                
                                else {
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                        
                                        selectedCourse.getLectureList().add(selectedTutor);
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
                                
//                                printTutorialList(selectedCourse);
                                
//                                for (int j = 1; j <= tutorList.getNumberOfEntries(); j++){
//                
//                                    Tutor getTutor = tutorList.getEntry(j);
//                                    
//                                    if (getTutor.getTutorialList().equals(selectedCourse.getTutorialList())){
//
//                                        System.out.println((j) + ". " + getTutor.getTutorialList());
//                                    }
//                                }
                        
//                                for (int j = 1; j <= tutorList.getNumberOfEntries(); j++) {
//                                        Tutor getTutor = tutorList.getEntry(j);
//    
//                                    // Check if the tutorial lists are equal
//                                    if (areArrayListsEqual(getTutor.getTutorialList(), selectedCourse.getTutorialList())) {
//                                        System.out.println((j) + ". " + getTutor.getTutorialList());
//                                    }
//                                }
                                
                                
                                int selectTutorIndex = teachingAssignmentUI.getIndex();
                                
                                if (selectTutorIndex > 0 && selectTutorIndex <= tutorList.getNumberOfEntries()) {
                                    
                                
                                
                                
                                    
                                    System.out.println("Are you sure you want to add it? (Y/N)");
                                    confirmChoice = teachingAssignmentUI.getInput();
                                    
                                    if (confirmChoice.equalsIgnoreCase("y")){
                                         
                                        
                                        selectedTutorialGroup.getTutorialGrpTeachingList().put(selectedCourse, selectedCourse.getTutorialList());
                                        
                         
                                        
                                    }
                                    
                                    System.out.println(selectedTutorialGroup.getTutorialGrpTeachingList().get(selectedCourse));
                                    
                                }
                                    
                                
        
                                
                        
                        
                        
                        
                    break;
                    case 2: // Assign Tutor to Practical

                    break;
                    case 3: // Assign Tutor to Lecture
  
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
    
//    public void addTutorToTutorialGrpForACourse() {
//    if (tutorialGroupList.isEmpty()) {
//        teachingAssignmentUI.TutorGrpIsEmpty();
//            return;
//    }
//    
//    teachingAssignmentUI.listAllTutorialGroup(getAllTutorialGroups());
//    int selectedTutorialGroupIndex = teachingAssignmentUI.getIndex();
//
//    if (selectedTutorialGroupIndex > 0 && selectedTutorialGroupIndex <= tutorialGroupList.getNumberOfEntries()) {
//        TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(selectedTutorialGroupIndex);
//
//           if (courseList.isEmpty()) {
//            teachingAssignmentUI.CourseIsEmpty();
//            return;
//        }
//
//        teachingAssignmentUI.listAllCourse(getAllCourse());
//        int selectedCourseIndex = teachingAssignmentUI.getIndex();
//        
//        if (selectedCourseIndex > 0 && selectedCourseIndex <= courseList.getNumberOfEntries()) {
//            Course selectedCourse = courseList.getEntry(selectedCourseIndex);
//
//           if (courseList.isEmpty()) {
//                teachingAssignmentUI.CourseIsEmpty();
//                
//                return;
//            }
//           TutorialGrpTeachingList.put(selectedCourse, null);
//           
//           
//            
//            int selectedTutorIndex = teachingAssignmentUI.getIndex(); // Subtract 1 to adjust for 0-based index
//
//            if (selectedTutorIndex > 0 && selectedTutorIndex <= tutorList.getNumberOfEntries()) {
//                Tutor selectedTutor = selectedCourse.getTutorialList().getEntry(selectedTutorIndex);
//                ArrayList<Tutor> tutorsForCourse = TutorialGrpTeachingList.get(selectedCourse);
////                tutorsForCourse.add(selectedTutor);
//                
//                TutorialGrpTeachingList.(selectedCourse,  tutorsForCourse);
//                
//                System.out.println (TutorialGrpTeachingList.get(selectedCourse));
//                
//                System.out.println("Successfully added tutor " + selectedTutor.getTutorName() + " to tutorial group " 
//                        + selectedTutorialGroup.getTutorGroupName() 
//                        + " for course " + selectedCourse.getCourseName());
//            } else {
//                teachingAssignmentUI.TutorNotFound();
//                return;
//                
//            }
//        } else {
//            teachingAssignmentUI.CourseNotFound();
//            return;
//        }
//    } else {
//        teachingAssignmentUI.TutorGrpNotFound();
//        return;
//    }
//    return;
//}
    
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

//private void listTutorsAndTutorialGroupsForCourse() {
//        teachingAssignmentUI.listAllCourse(getAllCourse());
//        int courseIndex = teachingAssignmentUI.getIndex() - 1;
//
//        if (courseIndex >= 0 && courseIndex < courseList.getNumberOfEntries()) {
//            Course selectedCourse = courseList.getEntry(courseIndex + 1);
//            teachingAssignmentUI.displayTutors(selectedCourse.getTutors());
//            teachingAssignmentUI.displayTutorialGroups(selectedCourse.getTutorialGroups());
//        } else {
//            MessageUI.displayInvalidIndexMessage(); //Course
//        }
//    }

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
    String Searching = teachingAssignmentUI.getInput();
    int index = 0;
    
    if (tutorList.isEmpty()) {
        System.out.println("No tutors found with the given criteria.");
        return;
    }
    
    for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
        Tutor tutorGetEntry = tutorList.getEntry(i);
        if (tutorGetEntry.getTutorID().contains(Searching) 
                || tutorGetEntry.getTutorName().contains(Searching) 
                ){
//            tutorList.contains(tutorGetEntry)
                System.out.println(tutorGetEntry);
            
        }
    }

}

//public void listCourseForEachTutor() {
//    
//    if (tutorList.isEmpty()) {//        TteachingAssignmentUI.TutorIsEmpty();
//        return;
//    }
//
//    for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
//        Boolean courseExist = false;
//        Tutor tutor = tutorList.getEntry(i);
//         Course courseGetEntry = courseList.getEntry(i);
//
//        System.out.println("\nTutor ID: " + tutor.getTutorID() + ", Tutor Name: " + tutor.getTutorName());
//        System.out.println("Course:");
//
//        for (int j = 1; j <= tutorList.getNumberOfEntries(); j++) {
//                int index = 0; 
//                
//                Tutor tutorGetEntry = tutorList.getEntry(j);
//                
//                if (courseGetEntry.getTutor() != null && courseGetEntry.getTutor().equals(tutorGetEntry)) {
//                    courseExist = true;
//                    ++index;              
//                    System.out.println(index  + ". " + courseGetEntry);
//                    System.out.println();
//                }
//                
//                
//            }
//        if (courseExist == false) {
//            System.out.print("No Assigged yet");
//            System.out.println();
//        }
//    }
//}
    
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
