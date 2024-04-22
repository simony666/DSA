package control;

import entity.Programme;

import adt.ArrayList;
import adt.ListInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Scanner;
import dao.ProgramInitializer;
import adt.DynamicLinkedList;
import adt.DynamicLinkedListInterface;
import entity.Course;

public class ProgramMenu {

    public static ListInterface<Programme> programList = new ArrayList<>();

    static {
        initializePrograms(programList);
    }

    private static void initializePrograms(ListInterface<Programme> programList) {
        ProgramInitializer.initializePrograms(programList);
    }

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
