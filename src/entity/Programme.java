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
public class Programme {
    private String progID;
    private String progName;
    
    private static ListInterface<Programme> programmeList = new ArrayList<>();

    public Programme(String progID, String progName) {
        this.progID = progID;
        this.progName = progName;
    }

    public String getProgID() {
        return progID;
    }

    public void setProgID(String progID) {
        this.progID = progID;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

    public void setProgrammeList(ListInterface<Programme> programmeList) {
        programmeList = programmeList;
    }
    

    @Override
    public String toString() {
        return "Programme{" + "progID=" + progID + ", progName=" + progName + ", coursesList=" + coursesList + ", TutorialGroupList=" + TutorialGroupList + '}';
    }

    
    
    
}
