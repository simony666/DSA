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
    
    private String programmeCODE; //This Id is to compare Id with Programme's program id in (removeGroup)

    //store all student belong to this entity
    public LinkedList<Student> studentList = new LinkedList<>();
    
    
    
    //store all Course & Tutor belong to this entity 
    private static HashMap<Course, ArrayList<Tutor>> TutorialGrpTeachingList = new HashMap(); 
            
   
    
    public TutorialGroup(String tutorGroupID, String tutorGroupName,String programmeCODE) {
        this.tutorGroupID = tutorGroupID;
        this.tutorGroupName = tutorGroupName;
//        this.studentId = studentId;
        this.programmeCODE = programmeCODE;
//        this.studentList = new ArrayList<>();
    }
    
    public boolean addStudent(Student stu) {
        if(this.studentList.contains(stu)) {
            return true;
        }
        this.studentList.add(stu);
        
        return true;
    }
    
    public boolean removeStudent (Student stu) {
        if(!this.studentList.contains(stu)) {
            return true;
        }
        
        int position = this.studentList.indexOf(stu);
        this.studentList.remove(position);
        
        return true;
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

    public String getProgrammeCODE() {
        return programmeCODE;
    }

    public void setProgrammeCODE(String programmeCODE) {
        this.programmeCODE = programmeCODE;
    }

//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }

    public LinkedList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(LinkedList<Student> studentList) {
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
        return String.format("%-10s %-10s %-10s",tutorGroupID,tutorGroupName,programmeCODE);
    }

    
}

 
