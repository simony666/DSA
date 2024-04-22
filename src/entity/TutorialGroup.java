/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import adt.*;
/**
 *
 * @author User
 */
public class TutorialGroup {
    private String tutorGroupID;
    private String tutorGroupName;
    
    private Tutor tutor;
    
    private String programmeId; //This Id is to compare Id with Programme's program id in (removeGroup)
    private String studentId; //This Id is to compare Id with Student's studentID in (removeStudnet)

    //store all student belong to this entity
    private ArrayList<Student> studentList = new ArrayList<>();
    
    //store all current entity
    private static ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();
    
    //store all Course & Tutor belong to this entity 
    private static HashMap<Course, ArrayList<Tutor>> TutorialGrpTeachingList= new HashMap();

    public TutorialGroup() {
    }
   
    
    public TutorialGroup(String tutorGroupID, String tutorGroupName, String studentId) {
        this.tutorGroupID = tutorGroupID;
        this.tutorGroupName = tutorGroupName;
        this.studentId = studentId;
    }

    public String getTutorGroupID() {
        return tutorGroupID;
    }

    public void setTutorGroupID(String tutorGroupID) {
        this.tutorGroupID = tutorGroupID;
    }

    public String getTutorGroupName() {
        return tutorGroupName;
    }

    public void setTutorGroupName(String tutorGroupName) {
        this.tutorGroupName = tutorGroupName;
    }
    
    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public static ArrayList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public static void setTutorialGroupList(ArrayList<TutorialGroup> tutorialGroupList) {
        TutorialGroup.tutorialGroupList = tutorialGroupList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public static HashMap<Course, ArrayList<Tutor>> getTutorialGrpTeachingList() {
        return TutorialGrpTeachingList;
    }

    public static void setTutorialGrpTeachingList(HashMap<Course, ArrayList<Tutor>> TutorialGrpTeachingList) {
        TutorialGroup.TutorialGrpTeachingList = TutorialGrpTeachingList;
    }

    @Override
    public String toString() {
        return "TutorialGroup{" + "tutorGroupID=" + tutorGroupID + ", tutorGroupName=" + tutorGroupName + ", programmeId=" + programmeId + '}';
    }

    
}

 
