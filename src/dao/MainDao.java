/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import control.*;
import entity.*;

/**
 *
 * @author yongc
 */
public class MainDao {
    public void generate(){
        HashMap<String, Course> courseMap = CourseMenu.courseMap;
        ArrayList<Programme> programList = ProgramMenu.programList;
        LinkedList<Student> studentList = StudentController.getStudentList();
        ArrayList<Tutor> tutorList = TeachingAssignment.tutorList;
        ArrayList<TutorialGroup> tgList = TutorialGroupManagement.getTutorialGroupList();
        
        
        Course course1 = new Course("BACS1014", "PROBLEM SOLVING AND PROGRAMMING", "FOCS", "Y1S3", "Main", 777);
        Course course2 = new Course("BJEL1013", "ENGLISH FOR TERTIARY STUDIES", "FOCS", "Y1S2", "Elective", 777);
        Course course3 = new Course("MPU-3103", "PENGHAYATAN ETIKA DAN PERADABAN", "FOCS", "Y2S3", "Elective", 777);
        Course course4 = new Course("BACS1053", "DATABASE MANAGEMENT", "FOCS", "Y1S1", "Main", 777);
        Course course5 = new Course("BACS1113", "COMPUTER ORGANISATION AND ARCHITECTURE", "FOCS", "Y2S3", "Resit", 50);
        Course course6 = new Course("BAIT1023", "WEB DESIGN AND DEVELOPMENT", "FOCS", "Y2S2", "Main", 777);
        Course course7 = new Course("BAIT1043", "SYSTEMS ANALYSIS AND DESIGN", "FOCS", "Y1S3", "Main", 777);
        Course course8 = new Course("BAIT1173", "IT FUNDAMENTALS", "FOCS", "Y3S3", "Main", 777);
        Course course9 = new Course("BAMS1613", "PROBABILITY AND STATISTICS", "FOCS", "Y2S3", "Main", 777);
        Course course10 = new Course("MPU-3322", "CONTEMPORARY MALAYSIAN ISSUES", "FOCS", "Y2S1", "Elective", 777);
        Course course11 = new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "FAFB", "Y1S2", "Elective", 777);
        Course course12 = new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "FAFB", "Y2S1", "Elective", 777);
        Course course13 = new Course("BBFA1113", "PRINCIPLES OF ACCOUNTING", "FAFB", "Y1S2", "Repeat", 777);
        Course course14 = new Course("BBBE1033", "ECONOMICS", "FAFB", "Y2S3", "Repeat", 777);
        Course course15 = new Course("BAMS1733", "QUANTITATIVE STUDIES", "FAFB", "Y3S1", "Resit", 50);
        Course course16 = new Course("BBDM1043", "PRINCIPLES OF MANAGEMENT", "FAFB", "Y3S2", "Main", 777);
        Course course17 = new Course("BMIT1733", "IT FUNDAMENTALS AND APPLICATIONS", "FAFB", "Y3S3", "Main", 777);
        Course course18 = new Course("BBFA1123", "FINANCIAL ACCOUNTING", "FAFB", "Y4S1", "Resit", 777);
        Course course19 = new Course("BBMC1113", "MANAGEMENT ACCOUNTING", "FAFB", "Y1S3", "Main", 777);
        Course course20 = new Course("BBMF1813", "PRINCIPLES OF FINANCE", "FAFB", "Y1S2", "Repeat", 50);
        
        courseMap.put(course1.getCourseCode(), course1);
        courseMap.put(course2.getCourseCode(), course2);
        courseMap.put(course3.getCourseCode(), course3);
        courseMap.put(course4.getCourseCode(), course4);
        courseMap.put(course5.getCourseCode(), course5);
        courseMap.put(course6.getCourseCode(), course6);
        courseMap.put(course7.getCourseCode(), course7);
        courseMap.put(course8.getCourseCode(), course8);
        courseMap.put(course9.getCourseCode(), course9);
        courseMap.put(course10.getCourseCode(), course10);    
        courseMap.put(course11.getCourseCode(), course11);
        courseMap.put(course12.getCourseCode(), course12);
        courseMap.put(course13.getCourseCode(), course13);
        courseMap.put(course14.getCourseCode(), course14);
        courseMap.put(course15.getCourseCode(), course15);
        courseMap.put(course16.getCourseCode(), course16);
        courseMap.put(course17.getCourseCode(), course17);
        courseMap.put(course18.getCourseCode(), course18);
        courseMap.put(course19.getCourseCode(), course19);
        courseMap.put(course20.getCourseCode(), course20);
        
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
        
        Student s1 = new Student("Eric Chou" ,22,program1);
        Student s2 = new Student("Jay Chou",23, program1);
        Student s3 = new Student("Bruce Lee",19,program1);
        Student s4 = new Student("Jackey Chung",20,program2);
        Student s5 = new Student("Eddie Chua",20,program2);
        Student s6 = new Student("Chu Ren",18,program3);
        Student s7 = new Student("Zheng Hao",20,program3);
        Student s8 = new Student("Chuy Mon",22,program3);
        Student s9 = new Student("Zhi Yen",21,program4);
        Student s10 = new Student("Zhi Yee",22,program4);
        Student s11 = new Student("Adeline",40,program4);
        
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        studentList.add(s6);
        studentList.add(s7);
        studentList.add(s8);
        studentList.add(s9);
        studentList.add(s10);
        studentList.add(s11);
                
        //FOCS Student
        s1.getCourseList().add(course8);
        s2.getCourseList().add(course8);
        s3.getCourseList().add(course8);
        s4.getCourseList().add(course8);
        s5.getCourseList().add(course8);
        s6.getCourseList().add(course8);
        s7.getCourseList().add(course8);
        s8.getCourseList().add(course8);
        
        s1.getCourseList().add(course4);
        s2.getCourseList().add(course4);
        s3.getCourseList().add(course4);
        s4.getCourseList().add(course4);
        s5.getCourseList().add(course4);
        s6.getCourseList().add(course4);
        s7.getCourseList().add(course4);
        s8.getCourseList().add(course4);
        
        s1.getCourseList().add(course6);
        s2.getCourseList().add(course6);
        s3.getCourseList().add(course6);
        
        s9.getCourseList().add(course20);
        s10.getCourseList().add(course20);
        s11.getCourseList().add(course20);
        
        

        TutorialGroup tg1 = new TutorialGroup("G1","Group 1", program1.getProgramCode());
        TutorialGroup tg2 = new TutorialGroup("G2","Group 2", program1.getProgramCode());
        TutorialGroup tg3 = new TutorialGroup("G3","Group 3", program2.getProgramCode());
        TutorialGroup tg4 = new TutorialGroup("G4","Group 4", program2.getProgramCode());
        TutorialGroup tg5 = new TutorialGroup("G5","Group 5", program3.getProgramCode());

        tgList.add(tg1);
        tgList.add(tg2);
        tgList.add(tg3);
        tgList.add(tg4);
        tgList.add(tg5);
        
        program1.getTutorialGroupList().add(tg1);
        program1.getTutorialGroupList().add(tg2);
        program1.getTutorialGroupList().add(tg3);
        program1.getTutorialGroupList().add(tg4);
        program1.getTutorialGroupList().add(tg5);       
        
        program2.getTutorialGroupList().add(tg1);
        program2.getTutorialGroupList().add(tg2);
        program2.getTutorialGroupList().add(tg3);
        program2.getTutorialGroupList().add(tg4);
        program2.getTutorialGroupList().add(tg5);

        program3.getTutorialGroupList().add(tg1);
        program3.getTutorialGroupList().add(tg2);
        program3.getTutorialGroupList().add(tg3);
        program3.getTutorialGroupList().add(tg4);
        program3.getTutorialGroupList().add(tg5);

        program4.getTutorialGroupList().add(tg1);
        program4.getTutorialGroupList().add(tg2);
        program4.getTutorialGroupList().add(tg3);
        program4.getTutorialGroupList().add(tg4);
        program4.getTutorialGroupList().add(tg5);

        

        tg1.getStudentList().add(s1);
        s1.setTutorialGroup(tg1);
        tg1.getStudentList().add(s2);
        s2.setTutorialGroup(tg1);
        tg2.getStudentList().add(s3);
        s3.setTutorialGroup(tg2);
        tg2.getStudentList().add(s4);
        s4.setTutorialGroup(tg2);
        tg3.getStudentList().add(s5);
        s5.setTutorialGroup(tg3);
        tg2.getStudentList().add(s6);
        s6.setTutorialGroup(tg2);
        tg1.getStudentList().add(s7);
        s7.setTutorialGroup(tg1);
        tg2.getStudentList().add(s8);
        s8.setTutorialGroup(tg2);
        tg1.getStudentList().add(s9);
        s9.setTutorialGroup(tg1);
//        tg2.getStudentList().add(s10);
//        s10.setTutorialGroup(tg2);
//        tg3.getStudentList().add(s11);
//        s11.setTutorialGroup(tg3);


        Tutor t1 = new Tutor("T001","Poh Chu Ren");
        Tutor t2 = new Tutor("T002","Poh Ren Ren");
        Tutor t3 = new Tutor("T003","Poh Chu Chu");
        Tutor t4 = new Tutor("T004","Goh Qin Long");
        Tutor t5 = new Tutor("T005","Goh Qin Qin");
        Tutor t6 = new Tutor("T006","Goh Long Long");

        tutorList.add(t1);
        tutorList.add(t2);
        tutorList.add(t3);
        tutorList.add(t4);
        tutorList.add(t5);
        tutorList.add(t6);

        AssignmentTeam a = new AssignmentTeam("Assignment Team 1",tg1,course1);
        AssignmentTeam b = new AssignmentTeam("Assignment Team 2",tg1,course2);
        AssignmentTeam c = new AssignmentTeam("Assignment Team 3",tg2,course1);
        AssignmentTeam d = new AssignmentTeam("Assignment Team 4",tg2,course2);
        AssignmentTeam e = new AssignmentTeam("Assignment Team 5",tg3,course1);
        AssignmentTeam f = new AssignmentTeam("Assignment Team 6",tg3,course2);
        AssignmentTeam g = new AssignmentTeam("Assignment Team 7",tg3,course3);
        AssignmentTeam h = new AssignmentTeam("Assignment Team 8",tg3,course4);
        AssignmentTeam i = new AssignmentTeam("Assignment Team 9",tg3,course8);

    
        a.addStudent(s1);
        a.addStudent(s2);
        a.addStudent(s3);
        b.addStudent(s4);
        b.addStudent(s5);
        c.addStudent(s6);
        d.addStudent(s7);
        e.addStudent(s8);
        f.addStudent(s9);
        g.addStudent(s10);
        g.addStudent(s11);
        i.addStudent(s1);
        i.addStudent(s2);
                
        
        AssignmentTeamCrtl crtl = new AssignmentTeamCrtl();
        
        crtl.addAT(a);
        crtl.addAT(b);
        crtl.addAT(c);
        crtl.addAT(d);
        crtl.addAT(e);
        crtl.addAT(f);
        crtl.addAT(g);
        crtl.addAT(h);
        crtl.addAT(i);



    }

    

    
            /*

        
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
             */
}
