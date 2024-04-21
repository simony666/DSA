package entity;

import adt.ArrayList;
import adt.ListInterface;
import dao.ProgramInitializer;

public class Programme {

    private String programCode;
    private String programName;
    private String faculty;
    public static ListInterface<Programme> programList = new ArrayList<>();
    private ArrayList<String> linkedCourses;
    
    //store all current entity
    private static ArrayList<Programme> programmeList = new ArrayList<>();
    
    public Programme(String progID, String progName) {
        this.progID = progID;
        this.progName = progName;
    }
    
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

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public static ListInterface<Programme> getProgramList() {
        return programList;
    }

    public static void setProgramList(ListInterface<Programme> programList) {
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

    @Override
    public String toString() {
        return "Programme{" + "programCode=" + programCode + ", programName=" + programName + ", faculty=" + faculty + '}';
    }

}
