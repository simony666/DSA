package dao;

import entity.Programme;
import adt.ListInterface;

public class ProgramInitializer {

    public static void initializePrograms(ListInterface<Programme> programList) {
        Programme program1 = new Programme("RIT", "Bachelor of Information Technology in Internet Technology", "FOCS");
        program1.addLinkedCourse("BACS1053");
        program1.addLinkedCourse("BAIT1173");
        programList.add(program1);

        Programme program2 = new Programme("RSD", "Bachelor of Information Technology in Software Systems Development", "FOCS");
        program2.addLinkedCourse("BACS1014");
        program2.addLinkedCourse("BJEL1013"); 
        programList.add(program2);
        
        Programme program3 = new Programme("RSF", "Bachelor of Computer Science in Software Engineering", "FOCS");
        program3.addLinkedCourse("BAIT1173");
        programList.add(program3);

        Programme program4 = new Programme("RAC", "Bachelor of Accounting", "FAFB");
        program4.addLinkedCourse("MPU-3133");
        program4.addLinkedCourse("MPU-3302");
        program4.addLinkedCourse("BBFA1113");
        programList.add(program4);

    }
}
