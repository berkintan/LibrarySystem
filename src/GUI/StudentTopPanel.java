package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StudentTopPanel extends JPanel {
    private JButton addStudent;
    private JButton borrowedBooklist;
    private JButton changeStudentInfo;
    private JButton deleteStudent;
    private JButton borrowBook;
    private JButton listStudent;
    private JButton releaseBook;
    private StudentBottomPanel studentBottomPanel;
    public StudentTopPanel() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Control Section..."));
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(1000,35));
        addStudent = new JButton("Add Student");
        changeStudentInfo = new JButton("Change Student Info");
        deleteStudent = new JButton("Delete Student");
        listStudent = new JButton("List Students");
        borrowBook = new JButton("Borrow Book");
        borrowedBooklist = new JButton("Borrowed Book List");
        releaseBook = new JButton("Release Borrowed Book");

        buttons.add(addStudent);
        buttons.add(changeStudentInfo);
        buttons.add(deleteStudent);
        buttons.add(listStudent);
        buttons.add(borrowBook);
        buttons.add(borrowedBooklist);
        buttons.add(releaseBook);
        this.add(buttons);

        this.studentBottomPanel = new StudentBottomPanel();
        this.add(studentBottomPanel);

        addStudent.addActionListener(e -> {
            studentBottomPanel.addStudent();
        });

        borrowBook.addActionListener(e -> {
            try {
                studentBottomPanel.borrowBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        listStudent.addActionListener(e -> {
            try {
                studentBottomPanel.listStudents();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        borrowedBooklist.addActionListener(e -> {
            try {
                studentBottomPanel.listBorrowedBooks();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        changeStudentInfo.addActionListener(e -> {
            try {
                studentBottomPanel.changeStudentInfo();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        deleteStudent.addActionListener(e -> {
            try {
                studentBottomPanel.deleteStudent();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        releaseBook.addActionListener(e -> {
            try {
                studentBottomPanel.releaseBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
