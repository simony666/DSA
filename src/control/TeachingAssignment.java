package control;

import adt.ListInterface;
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
    ArrayList<Course> courseList = new ArrayList<>();
    ArrayList<Tutor> tutorList = new ArrayList<>();
    ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();
    

    private final TeachingAssignmentUI teachingAssignmentUI = new TeachingAssignmentUI();
    
       //----------------------------Testing--------------------------------------
    public void TestingValue() {
;
        tutorList.add(new Tutor("T1", "Tutor1"));
        tutorList.add(new Tutor("T2", "Tutor2"));
        tutorList.add(new Tutor("T3", "Tutor3"));
        

        courseList.add(new Course("C1", "BAC2022", "IOT", "PehLeader", 100));
        courseList.add(new Course("C2", "BAC2023", "DSA", "PohLeader", 200));
        courseList.add(new Course("C3", "BAC2024", "Network" ,"YeeLeader", 300));
        
//        studentList = new ArrayList<>();
//        studentList.add(new Student("S1", "student1"));
//        studentList.add(new Student("S2", "student2"));
//        studentList.add(new Student("S3", "student3"));
        

        tutorialGroupList.add(new TutorialGroup("TG1","tutorialGroup1"));
        tutorialGroupList.add(new TutorialGroup("TG2","tutorialGroup2"));
        tutorialGroupList.add(new TutorialGroup("TG3","tutorialGroup3"));
    }
    
    public void displayTesting() {
        System.out.println(tutorList.toString());
        System.out.println(courseList.toString());
//        System.out.println(studentList.toString());
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
                    assignTutorToCourse();
                    break;
                case 2: // Assign tutorial groups to a tutor
                    assignTutorGrpToTutor();
                    break;
                case 3: // Add tutors to a tutorial group for a course
//                    addTutorToTutorialGroupForACourse();
                    break;
                case 4: // Search course under a tutor
                    searchCourseUnderTutor();
                    break;
                case 5: // Search tutors for a course (T, P, L)
//                    searchTutorsForCourseType();
                    break;
                case 6: // List tutors and tutorial groups for a course
//                    listTutorsAndTutorialGroupsForCourse();
                    break;
                case 7: // List course for each tutor
//                    listCoursesForEachTutor();
                    break;
                case 8: // Filter tutors based on criterion
                    filterTutors();
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

    teachingAssignmentUI.listAllTutors(getAllTutors());
    int selectedTutorIndex = teachingAssignmentUI.getIndex(); // Subtract 1 to adjust for 0-based index

    if (selectedTutorIndex > 0 && selectedTutorIndex <= tutorList.getNumberOfEntries()) {
        Tutor selectedTutor = tutorList.getEntry(selectedTutorIndex);

        if (courseList.isEmpty()) {
            teachingAssignmentUI.CourseIsEmpty();
            return;
        }

        teachingAssignmentUI.listAllCourse(getAllCourse());
        int selectedCourseIndex = teachingAssignmentUI.getIndex(); // Subtract 1 to adjust for 0-based index
        
        if (selectedCourseIndex > 0 && selectedCourseIndex <= courseList.getNumberOfEntries()) {
            Course selectedCourse = courseList.getEntry(selectedCourseIndex);
            int choice = 0;
            
            
                do {
                
                    choice = teachingAssignmentUI.getCourseTypeChoice();
                
                
                    switch (choice) {
                    case 0:
                        MessageUI.displayExitMessage();
                    break;
                    case 1: //  Assign Tutor to Tutorial
                        selectedCourse.getTutorialList().add(selectedTutor);
                        
                    break;
                    case 2: // Assign Tutor to Practical
                        selectedCourse.getPracticalList().add(selectedTutor);
                        
                    break;
                    case 3: // Assign Tutor to Lecture
                        selectedCourse.getLectureList().add(selectedTutor);
                        
                    break;
                
                    default:
                        MessageUI.displayInvalidChoiceMessage();
                    break;
                }
                
                
            
            } while (choice != 0 && (choice < 1 || choice > 3));

            System.out.println("Successfully assigned tutor " + selectedTutor.getTutorName() + " to course " + selectedCourse.getCourseName());
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
    
//    public void addTutorToTutorialGroupForACourse() {
//    if (tutorialGroupList.isEmpty()) {
//        teachingAssignmentUI.TutorGrpIsEmpty();
//        return;
//    }
//
//    teachingAssignmentUI.listAllTutorialGroup(getAllTutorialGroups());
//    int selectedTutorialGroupIndex = teachingAssignmentUI.getIndex(); // Subtract 1 to adjust for 0-based index
//
//    if (selectedTutorialGroupIndex > 0 && selectedTutorialGroupIndex <= tutorialGroupList.getNumberOfEntries()) {
//        TutorialGroup selectedTutorialGroup = tutorialGroupList.getEntry(selectedTutorialGroupIndex);
//
//        if (courseList.isEmpty()) {
//            teachingAssignmentUI.CourseIsEmpty();
//            return;
//        }
//
//        teachingAssignmentUI.listAllCourse(getAllCourse());
//        int selectedCourseIndex = teachingAssignmentUI.getIndex(); // Subtract 1 to adjust for 0-based index
//
//        if (selectedCourseIndex > 0 && selectedCourseIndex <= courseList.getNumberOfEntries()) {
//            Course selectedCourse = courseList.getEntry(selectedCourseIndex);
//
//            if (courseList.isEmpty()) {
//                teachingAssignmentUI.CourseIsEmpty();
//                
//                return;
//            }
//
//            teachingAssignmentUI.listAllTutors(getAllTutors());
//            int selectedTutorIndex = teachingAssignmentUI.getIndex(); // Subtract 1 to adjust for 0-based index
//
//            if (selectedTutorIndex > 0 && selectedTutorIndex <= tutorList.getNumberOfEntries()) {
//                Tutor selectedTutor = selectedCourse.getTutorialList().getEntry(selectedTutorIndex);
//
//                
//                selectedTutorialGroup.getTutorList().add(selectedTutor);
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
    
    String Searching = teachingAssignmentUI.getInput();
    boolean TutorExist = false;
    
    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
        Course courseGetEntry = courseList.getEntry(i);
        
        if (courseGetEntry.getCourseID().equals(Searching) || courseGetEntry.getCourseName().equals(Searching)) {
            System.out.println("Course ID: " + courseGetEntry.getCourseID() + "\nCourse Name: " + courseGetEntry.getCourseName());
            
            if (courseGetEntry.getTutorialList().isEmpty() 
                    && courseGetEntry.getPracticalList().isEmpty() 
                    && courseGetEntry.getLectureList().isEmpty()) {
    
                        System.out.println("The Course is not assigned any Tutor yet");
                    
                    }
            
            
            
            
//            for (int j = 1; j <= tutorList.getNumberOfEntries(); j++) {
//                 
//                
//                Tutor tutorGetEntry = tutorList.getEntry(j);
//                
//                if (courseGetEntry.getTutorialList() != null && courseGetEntry.getTutorialList().equals(tutorGetEntry)) {
//                    TutorExist = true;
//                                  
//                    System.out.println( j  + ". " + tutorGetEntry);
//                }
//            }
            
            else {
                    TutorExist = true;
                    
                    for (int j = 1; j <= courseGetEntry.getTutorialList().getNumberOfEntries(); j++){
                
                        if (!courseGetEntry.getTutorialList().isEmpty()) {
                                
                                                   
                                System.out.println( j  + ". " + courseGetEntry.getTutorialList());
                            }
                
                        else if (!courseGetEntry.getTutorialList().isEmpty()) {
                                
                                                   
                                System.out.println( j  + ". " + courseGetEntry.getPracticalList());
                            }
                
                        else if (!courseGetEntry.getTutorialList().isEmpty()) {
                                
                                                   
                                System.out.println( j  + ". " + courseGetEntry.getLectureList());
                            }
                    }
                
            }
            
            if (!TutorExist) {
                System.out.println("No courses found for the given input.");
             }
            
            return;
        }
        else {
            teachingAssignmentUI.CourseNotFound();
            return;
        }
    }
    return;
}

//public void searchTutorsForCourseType() {
//    if (courseList.isEmpty()) {
//        teachingAssignmentUI.TutorIsEmpty();
//        return;
//    }
//
//    teachingAssignmentUI.listAllCourse(getAllCourse());
//    
//    String Searching = teachingAssignmentUI.getInput();
//    
//    boolean TutorExist = false;
//    
//    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
//        Course courseGetEntry = courseList.getEntry(i);
//        int index = 0;
//        
//        if (courseGetEntry.getPracticalList().contains(Searching)
//                || courseGetEntry.getLectureList().contains(Searching)
//                || courseGetEntry.getTutorialList().contains(Searching)) {
//            System.out.println("Course Type: " + Searching);
//            
//            
//            for (int j = 1; j <= tutorList.getNumberOfEntries(); j++) {
//                 
//                
//                
////                String tutorString = String.format(tutorGetEntry);
//                  
//                Course tutorGetEntry = courseList.getEntry(j);
//                
//                if (courseGetEntry.getTutorialList() != null && courseGetEntry.getTutorialList().equals(tutorGetEntry)) {
//                    TutorExist = true;
//                                                   
//                    System.out.println( ++index  + ". " + tutorGetEntry);
//                }
//                
//                else if (courseGetEntry.getTutorialList() != null && courseGetEntry.getPracticalList().equals(tutorGetEntry)) {
//                    TutorExist = true;
//                                                   
//                    System.out.println( ++index  + ". " + tutorGetEntry);
//                }
//                
//                else if (courseGetEntry.getTutorialList() != null && courseGetEntry.getLectureList().equals(tutorGetEntry)) {
//                    TutorExist = true;
//                                                   
//                    System.out.println( ++index  + ". " + tutorGetEntry);
//                }
//                
//            }
//            
//            if (!TutorExist) {
//                System.out.println("The Course Type is not assigned any Tutor yet");
//                
//            }
//            
//            return;
//        }
//        
//        
//    }
//    
//    if (!TutorExist){
//            teachingAssignmentUI.CourseNotFound();
//            return;
//        }
//    
//    return;
//}

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

//public void listCoursesForEachTutor() {
//        if (tutorList.isEmpty()) {
//            System.out.println("No tutors available.");
//            return;
//        }
//
//        Iterator<Tutor> tutorIterator = tutorList.getIterator();
//
//        int tutorCount = 0;
//        System.out.println("Courses for each tutor:");
//
//        while (tutorIterator.hasNext()) {
//            Tutor tutor = tutorIterator.next();
//            Iterator<Course> courseIterator = tutor.getCourses();
//            int courseCount = 0;
//
//            System.out.println((++tutorCount) + ". Tutor " + tutor.getTutorID());
//
//            while (courseIterator.hasNext()) {
//                Course course = courseIterator.next();
//                System.out.println((++courseCount) + ". " + course);
//            }
//
//            if (courseCount == 0) {
//                System.out.println("No courses found for this tutor.");
//            }
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
            
            if (courseGetEntry.getTutorialList() != null && courseGetEntry.getTutorialList().equals(tutorGetEntry)) {
            courseExist = true;
                      
            System.out.println( ++index  + ". " + courseGetEntry.getCourseID() + "," + courseGetEntry.getCourseName());
                }
            
        }
        
        if (!courseExist){
            System.out.println("The Tutor is not assigned any course yet");
        }
        
         
        

        System.out.println();
    }
}

public void filterTutors() {
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

    

    public static void main(String[] args) {        
        
        TeachingAssignment taUI = new TeachingAssignment();
        
        taUI.TestingValue();
        taUI.displayTesting();
        taUI.entry();      
         
         
    }
    
}