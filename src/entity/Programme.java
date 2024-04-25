package entity;

import adt.ArrayList;
import adt.ListInterface;

public class Programme {

    private String programCode;
    private String programName;
    private String faculty;
    private ArrayList<String> linkedCourses; 
    public ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();

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
    }
    
    public ArrayList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(ArrayList<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }
    
    @Override
    public String toString() {
        return String.format("%-5s %-20s %5s", programCode, programName, faculty);
        }     
    
}


