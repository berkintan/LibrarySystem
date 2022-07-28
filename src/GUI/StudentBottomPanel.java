package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Book;
import Model.Student;
import GUI.BookBottomPanel;

public class StudentBottomPanel extends JPanel {
    private Student student;
    private BookTopPanel bookTopPanel;
    private BookBottomPanel bookBottomPanel;
    private ArrayList<Student> students = new ArrayList<>();
    private JTable table;
    private DefaultTableModel tablemodel;
    public StudentBottomPanel() {
        this.setLayout(new BorderLayout());
    }

    public void addStudent() {
        this.removeAll();
        this.repaint();
        this.revalidate();
        JPanel panel = new JPanel(new GridLayout(5,2));
        JTextField studentName = new JTextField();
        JTextField studentSurname = new JTextField();
        JTextField studentID = new JTextField();
        JButton add = new JButton("Add Student");
        panel.add(new JLabel("Name: "));
        panel.add(studentName);
        panel.add(new JLabel("Surname: "));
        panel.add(studentSurname);
        panel.add(new JLabel("Student ID: "));
        panel.add(studentID);
        panel.add(new JLabel(""));
        panel.add(add);
        this.add(panel);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentName .getText().isEmpty() || studentSurname.getText().isEmpty() || studentID.getText().isEmpty()) {
                    String error = "Please fill all of the information for student.";
                    JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
                } else {
                    try {
                        String studentid = studentID.getText();
                        int id = Integer.parseInt(studentid);
                        student = new Student(studentName.getText(), studentSurname.getText(), studentID.getText());
                        String message = "Student added successfully!";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Success", 1);
                        students.add(student);
                    }catch (NumberFormatException error) {
                        String error1 = "Please type a number for the Student ID section.";
                        JOptionPane.showMessageDialog(new JFrame(), error1, "Error", 0);
                    }
                    }
            }
        });
    }
    public void borrowBook() {
        JFrame frame = new JFrame("Borrow Book Section");
        frame.setSize(700,700);
        frame.setLayout(new GridLayout(1,2));
        JPanel panel1 = new JPanel();
        bookBottomPanel = new BookBottomPanel(this);
        String[] headers = {"Name", "Author", "Publisher", "Number of Pages"};
        Object[][] bookInfo = new Object[bookBottomPanel.getBooks().size()][4];
        for(int j = 0; j < bookBottomPanel.getBooks().size(); j++) {
            bookInfo[j][0] = bookBottomPanel.getBooks().get(j).getNameOftheBook();
            bookInfo[j][1] = bookBottomPanel.getBooks().get(j).getAuthor();
            bookInfo[j][2] = bookBottomPanel.getBooks().get(j).getPublisher();
            bookInfo[j][3] = String.valueOf(bookBottomPanel.getBooks().get(j).getNumberOfPages());
        }
        table = new JTable(bookInfo, headers);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane);
        frame.add(panel1);

        JPanel panel2 = new JPanel();


        frame.setVisible(true);
    }
    public void listStudents() {
        if(students.size() == 0) {
            String error = "Please add students to list them!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new FlowLayout());
            String[] headers = {"Name", "Surname", "Student ID"};
            Object[][] studentInfo = new Object[students.size()][3];
            for(int j = 0; j < students.size(); j++) {
                studentInfo[j][0] = students.get(j).getName();
                studentInfo[j][1] = students.get(j).getSurname();
                studentInfo[j][2] = String.valueOf(students.get(j).getStudentID());
            }
            table = new JTable(studentInfo, headers);
            table.setEnabled(false); // Can not select items, for not-chane purposes...
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            this.add(panel);
        }
    }
}
