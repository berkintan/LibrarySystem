package GUI;

import Model.Borrow;
import Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentBottomPanel extends JPanel {
    private Student student;
    private Borrow borrow;
    private ArrayList<Borrow> borrowedBooks = new ArrayList<>();
    private BookTopPanel bookTopPanel;
    private BookBottomPanel bookBottomPanel;
    private ArrayList<Student> students = new ArrayList<>();
    private JTable table;
    private JTable table1;
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
        frame.setSize(1000,525);
        frame.setLayout(new FlowLayout());
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
        String[] headers1 = {"Name", "Surname", "Student ID"};
        Object[][] studentInfo1 = new Object[students.size()][3];
        for(int j = 0; j < students.size(); j++) {
            studentInfo1[j][0] = students.get(j).getName();
            studentInfo1[j][1] = students.get(j).getSurname();
            studentInfo1[j][2] = String.valueOf(students.get(j).getStudentID());
        }
        table1 = new JTable(studentInfo1, headers1);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        panel2.add(scrollPane1);
        frame.add(panel2);
        JButton borrowButton = new JButton("Borrow");
        frame.add(borrowButton);
        frame.setVisible(true);

        borrowButton.addActionListener(e -> {
            int i = table.getSelectedRow();
            String name = (String) table.getValueAt(i,0);
            String author = (String) table.getValueAt(i, 1);
            String publisher = (String) table.getValueAt(i,2);
            int numberofpages = Integer.parseInt(String.valueOf(table.getValueAt(i,3)));
            i = table1.getSelectedRow();
            String studentname = (String) table1.getValueAt(i,0);
            String studentsurname = (String) table1.getValueAt(i,1);
            int studentID = Integer.parseInt(String.valueOf(table1.getValueAt(i,2)));
            borrow = new Borrow(name,author,publisher,numberofpages,studentname,studentsurname,studentID);
            borrowedBooks.add(borrow);
            String message = "Done!";
            JOptionPane.showMessageDialog(new JFrame(), message, "Successful!", 1);
        });

    }
    public void listBorrowedBooks() {
        if(borrowedBooks.size() == 0) {
            String error = "There are no borrowed books!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel();
            String[] headers = {"Book Name", "Author", "Publisher", "Number of Pages", "Student Name", "Student Surname", "Student ID" };
            Object[][] borrowedInfo = new Object[borrowedBooks.size()][7];
            for(int i = 0; i < borrowedBooks.size(); i++) {
                borrowedInfo[i][0] = borrowedBooks.get(i).getName();
                borrowedInfo[i][1] = borrowedBooks.get(i).getAuthor();
                borrowedInfo[i][2] = borrowedBooks.get(i).getPublisher();
                borrowedInfo[i][3] = borrowedBooks.get(i).getNumberofpages();
                borrowedInfo[i][4] = borrowedBooks.get(i).getStudentName();
                borrowedInfo[i][5] = borrowedBooks.get(i).getStudentSurname();
                borrowedInfo[i][6] = borrowedBooks.get(i).getStudentID();
            }
            table = new JTable(borrowedInfo, headers);
            table.setEnabled(false);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(800,425));
            panel.add(scrollPane);
            this.add(panel);
        }
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
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            table.setFillsViewportHeight(true);
            table.setEnabled(false); // Can not select items, for not-change purposes...
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            this.add(panel);
        }
    }
}
