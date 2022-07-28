package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentTopPanel extends JPanel {
    private JButton addStudent;
    private JButton changeStudentInfo;
    private JButton deleteStudent;
    private JButton borrowBook;
    private JButton listStudent;
    private StudentBottomPanel studentBottomPanel;
    public StudentTopPanel() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Control Section..."));
        JPanel buttons = new JPanel();
        addStudent = new JButton("Add Student");
        changeStudentInfo = new JButton("Change Student Info");
        deleteStudent = new JButton("Delete Student");
        listStudent = new JButton("List Students");
        borrowBook = new JButton("Borrow Book");
        buttons.add(addStudent);
        buttons.add(changeStudentInfo);
        buttons.add(deleteStudent);
        buttons.add(listStudent);
        buttons.add(borrowBook);
        this.add(buttons);

        this.studentBottomPanel = new StudentBottomPanel();
        this.add(studentBottomPanel);

        addStudent.addActionListener(e -> {
            studentBottomPanel.addStudent();
        });

        borrowBook.addActionListener(e -> {
            studentBottomPanel.borrowBook();
        });

        listStudent.addActionListener(e -> {
            studentBottomPanel.listStudents();
        });
    }
}
