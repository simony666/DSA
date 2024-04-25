package entity;

import adt.ArrayList;
import adt.ListInterface;
import dao.ProgramInitializer;
import dao.TGInitializer;

public class Programme {

    private String programCode;
    private String programName;
    private String faculty;
    public static ArrayList<Programme> programList = new ArrayList<>();
    private ArrayList<String> linkedCourses; 
    public static ArrayList<TutorialGroup> tutorialGroupList = TGInitializer.initializeGroup();
    
    static {
        initializePrograms(programList);
    }

    private static void initializePrograms(ListInterface<Programme> programList) {
        ProgramInitializer.initializePrograms(programList);
    }

    public Programme(String programCode, String programName, String faculty) {
        this.programCode = programCode;
        this.programName = programName;
        this.faculty = faculty;
        this.linkedCourses = new ArrayList<>();

    }

    private String progID;
    private String progName;

    //store all current entity
    public static ArrayList<Programme> programmeList = new ArrayList<>();
    
    //private static ListInterface<Programme> programmeList = new ArrayList<>();
    

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getFaculty() {
        return faculty;
    }
    public void setProgName(String progName) {
        this.progName = progName;
    }    

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public static ListInterface<Programme> getProgramList() {
        return programList;
    }

    public static void setProgramList(ArrayList<Programme> programList) {
        Programme.programList = programList;
    }

    public ArrayList<String> getLinkedCourses() {
        return linkedCourses;
    }

    public void setLinkedCourses(ArrayList<String> linkedCourses) {
        this.linkedCourses = linkedCourses;
    }

    public void addLinkedCourse(String courseCode) {
        linkedCourses.add(courseCode);
    }

    public void removeLinkedCourse(String courseCode) {
        if (courseCode != null && !courseCode.isEmpty()) {
            for (int i = 1; i <= linkedCourses.getNumberOfEntries(); i++) {
                if (linkedCourses.getEntry(i).equals(courseCode)) {
                    linkedCourses.remove(i);
                }
            }
        } else {
            System.out.println("Course code cannot be null or empty.");
        }
    public static ArrayList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public static void setTutorialGroupList(ArrayList<TutorialGroup> tutorialGroupList) {
        Programme.tutorialGroupList = tutorialGroupList;
    }
    
    @Override
    public String toString() {
        return String.format("%-5s %-20s %5s", programCode, programName, faculty);
        }     
    
}


