package Model;

import java.util.ArrayList;

public class Student {
    private String name;
    private String surname;
    private String studentID;

    public Student(String name, String surname, String studentID) {
        this.name = name;
        this.surname = surname;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
