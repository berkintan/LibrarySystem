package Model;

import java.util.ArrayList;

public class Base {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void addStudent(Student student) {
        students.add(student);
    }
}
