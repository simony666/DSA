package control;

import entity.Programme;

import adt.ArrayList;
import adt.ListInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Scanner;
import adt.DynamicLinkedList;
import adt.DynamicLinkedListInterface;
import entity.Course;
import entity.TutorialGroup;

public class ProgramMenu {

    public static ArrayList<Programme> programList = new ArrayList<>();

    static void removeCourseFromPrograms(String course) {
        // Iterate through all programs and remove the course from linkedCourses
        for (Programme programme : programList) {
            programme.removeLinkedCourse(course);
        }
    }

    public static Programme findProgramByCode(ListInterface<Programme> programList, String programCode) {
        for (Programme programme : programList) {
            if (programme.getProgramCode().equalsIgnoreCase(programCode)) {
                return programme;
            }
        }
        return null; // Program not found
    }

}
