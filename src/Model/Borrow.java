package Model;

public class Borrow {
    private String name;
    private String author;
    private String publisher;
    private int numberofpages;
    private String studentName;
    private String studentSurname;
    private int studentID;

    public Borrow(String name, String author, String publisher, int numberofpages, String studentName, String studentSurname, int studentID) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.numberofpages = numberofpages;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberofpages() {
        return numberofpages;
    }

    public void setNumberofpages(int numberofpages) {
        this.numberofpages = numberofpages;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
