/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import control.AssignmentTeamCrtl;
import entity.*;

/**
 *
 * @author yongc
 */
public class AssignmentTeamDao {
    public void generate(){
        Student s1 = new Student("SN001");
        Student s2 = new Student("SN002");
        Student s3 = new Student("SN003");
        Student s4 = new Student("SN004");
        Student s5 = new Student("SN005");
        Student s6 = new Student("SN006");
        Student s7 = new Student("SN007");
        Student s8 = new Student("SN008");
        Student s9 = new Student("SN009");
        Student s10 = new Student("SN010");
        AssignmentTeam a = new AssignmentTeam("Assignment Team 1");
        AssignmentTeam b = new AssignmentTeam("Assignment Team 2");
        AssignmentTeam c = new AssignmentTeam("Assignment Team 3");
        AssignmentTeam d = new AssignmentTeam("Assignment Team 4");
        AssignmentTeam e = new AssignmentTeam("Assignment Team 5");
        AssignmentTeam f = new AssignmentTeam("Assignment Team 6");
        AssignmentTeam g = new AssignmentTeam("Assignment Team 7");
        AssignmentTeam h = new AssignmentTeam("Assignment Team 8");
        AssignmentTeam i = new AssignmentTeam("Assignment Team 9");
        AssignmentTeam j = new AssignmentTeam("Assignment Team 10");
        
        a.addStudent(s1);
        b.addStudent(s2);
        c.addStudent(s3);
        d.addStudent(s4);
        e.addStudent(s5);
        f.addStudent(s6);
        g.addStudent(s7);
        h.addStudent(s8);
        i.addStudent(s9);
        j.addStudent(s9);
        j.addStudent(s10);
        
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
        crtl.addAT(j);
        
        
        
        
        
    }
}
