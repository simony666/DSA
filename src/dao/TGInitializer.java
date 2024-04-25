package dao;

import control.TutorialGroupManagement;
import adt.*;
import entity.Programme;
import entity.TutorialGroup;
import entity.Student;
import dao.ProgramInitializer;

public class TGInitializer {
    
    
    public static ArrayList<TutorialGroup> initializeGroup() {
        
        ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();
              
       TutorialGroup group1 = new TutorialGroup("G1", "Group 1", "RIT");
       TutorialGroup group2 = new TutorialGroup("G2", "Group 2", "RSD");
       TutorialGroup group3 = new TutorialGroup("G3", "Group 3", "RSF");
       TutorialGroup group4 = new TutorialGroup("G4", "Group 4", "RAC");


        tutorialGroupList.add(group1);
        tutorialGroupList.add(group2);
        tutorialGroupList.add(group3);
        tutorialGroupList.add(group4);

        return tutorialGroupList;
    }
    
    
    public static ListInterface<Student> initializeStudent() {
        
        ListInterface<Student> TeststudentList = new LinkedList<>();
        
        Programme program1 = Programme.getProgramList().getEntry(1);
        Programme program2 = Programme.getProgramList().getEntry(2);
        Programme program3 = Programme.getProgramList().getEntry(3);
        Programme program4 = Programme.getProgramList().getEntry(4);
        
               
        Student student1 = new Student("Eric Chou" ,22,program1);
        Student student2 = new Student("Jay Chou",23, program2);
        Student student3 = new Student("Bruce Lee",19,program3);
        Student student4 = new Student("Jackey Chung",20,program4);
        
        TutorialGroup tg1  = TutorialGroupManagement.getTutorialGroupList().getEntry(1);
        TutorialGroup tg2  = TutorialGroupManagement.getTutorialGroupList().getEntry(2);
        TutorialGroup tg3  = TutorialGroupManagement.getTutorialGroupList().getEntry(3);
        TutorialGroup tg4  = TutorialGroupManagement.getTutorialGroupList().getEntry(4);

        tg1.addStudent(student1);
        tg2.addStudent(student2);
        tg3.addStudent(student3);
        tg4.addStudent(student4);
        
        TeststudentList.add(student1);
        TeststudentList.add(student2);
        TeststudentList.add(student3);
        TeststudentList.add(student4);

        return TeststudentList;
    }
    

}
