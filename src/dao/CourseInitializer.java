package dao;

import adt.*;
import entity.Course;

/**
 *
 * @author Eddie Chua
 */
public class CourseInitializer {
    public static HashMap<String, Course> initializeCourses() {
        HashMap<String, Course> courseMap = new HashMap<>();
        Course course1 = new Course("BACS1014", "PROBLEM SOLVING AND PROGRAMMING", "FOCS", 4, "Main");
        Course course2 = new Course("BJEL1013", "ENGLISH FOR TERTIARY STUDIES", "FOCS", 3, " Elective");
        Course course3 = new Course("MPU-3103", "PENGHAYATAN ETIKA DAN PERADABAN", "FOCS", 3, " Elective");
        Course course4 = new Course("BACS1053", "DATABASE MANAGEMENT", "FOCS", 3, "Main");
        Course course5 = new Course("BACS1113", "COMPUTER ORGANISATION AND ARCHITECTURE", "FOCS", 3, "Resit");
        Course course6 = new Course("BAIT1023", "WEB DESIGN AND DEVELOPMENT", "FOCS", 3, "Main");
        Course course7 = new Course("BAIT1043", "SYSTEMS ANALYSIS AND DESIGN", "FOCS", 3, "Main");
        Course course8 = new Course("BAIT1173", "IT FUNDAMENTALS", "FOCS", 3, "Main");
        Course course9 = new Course("BAMS1613", "PROBABILITY AND STATISTICS", "FOCS", 3, "Main");
        Course course10 = new Course("MPU-3322", "CONTEMPORARY MALAYSIAN ISSUES", "FOCS", 2, " Elective");
        Course course11 = new Course("MPU-3133", "FALSAFAH DAN ISU SEMASA", "FAFB", 3, " Elective");
        Course course12 = new Course("MPU-3302", "INTEGRITY AND ANTI-CORRUPTION", "FAFB", 2, " Elective");
        Course course13 = new Course("BBFA1113", "PRINCIPLES OF ACCOUNTING", "FAFB", 3, "Repeat");
        Course course14 = new Course("BBBE1033", "ECONOMICS", "FAFB", 3, "Repeat");
        Course course15 = new Course("BAMS1733", "QUANTITATIVE STUDIES", "FAFB", 3, "Resit");
        Course course16 = new Course("BBDM1043", "PRINCIPLES OF MANAGEMENT", "FAFB", 3, "Main");
        Course course17 = new Course("BMIT1733", "IT FUNDAMENTALS AND APPLICATIONS", "FAFB", 3, "Main");
        Course course18 = new Course("BBFA1123", "FINANCIAL ACCOUNTING", "FAFB", 3, "Resit");
        Course course19 = new Course("BBMC1113", "MANAGEMENT ACCOUNTING", "FAFB", 3, "Main");
        Course course20 = new Course("BBMF1813", "PRINCIPLES OF FINANCE", "FAFB", 3, "Repeat");


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
        
        return courseMap;
    }
    
}
