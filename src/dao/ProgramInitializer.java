package dao;

import entity.Programme;
import adt.ListInterface;


public class ProgramInitializer {

    public static void initializePrograms(ListInterface<Programme> programList) {
        Programme program1 = new Programme("RSD", "Bachelor of Information Technology in Software Systems Development", "FOCS");
        program1.addLinkedCourse("BACS1014");
        program1.addLinkedCourse("BJEL1013");
       
        programList.add(program1);  
        Programme program2 = new Programme("RSF", "Bachelor of Computer Science in Software Engineering", "FOCS");
        program2.addLinkedCourse("BAIT1173");
        programList.add(program2);
        
        Programme program3 = new Programme("RAC", "Bachelor of Accounting", "FAFB");
        program3.addLinkedCourse("MPU-3133");
        program3.addLinkedCourse("MPU-3302");
        program3.addLinkedCourse("BBFA1113");
        programList.add(program3);
        
    }
}