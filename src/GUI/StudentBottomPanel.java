package GUI;

import Controller.JDBC;
import Model.Borrow;
import Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class StudentBottomPanel extends JPanel {
    private Student student;
    private Borrow borrow;
    private ArrayList<Borrow> borrowedBooks = new ArrayList<>();
    private BookBottomPanel bookBottomPanel;
    private ArrayList<Student> students = new ArrayList<>();
    private JTable table;
    private JTable table1;
    private DefaultTableModel tablemodel;
    private JDBC connection = new JDBC();
    private ResultSet rs;
    private Statement statement;
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

        add.addActionListener(e -> {
            if(studentName .getText().isEmpty() || studentSurname.getText().isEmpty() || studentID.getText().isEmpty()) {
                String error = "Please fill all of the information for student.";
                JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
            } else {
                try {
                    Connection con = connection.connection();
                    String s = "INSERT INTO student(student_name,student_surname,student_number) VALUES (?,?,?)";
                    PreparedStatement pt = con.prepareStatement(s);
                    pt.setString(1,studentName.getText());
                    pt.setString(2, studentSurname.getText());
                    pt.setInt(3, Integer.parseInt(studentID.getText()));
                    pt.executeUpdate();
                    String message = "Student added successfully!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Success", 1);
                }catch (NumberFormatException error) {
                    String error1 = "Please type a number for the Student ID section.";
                    JOptionPane.showMessageDialog(new JFrame(), error1, "Error", 0);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void borrowBook() throws SQLException {
            JFrame frame = new JFrame("Borrow Book Section");
            frame.setSize(1000, 525);
            frame.setLayout(new FlowLayout());
            JPanel panel1 = new JPanel();
            bookBottomPanel = new BookBottomPanel(this);
            Connection con = connection.connection();
            statement = con.createStatement();
            String sql = "SELECT * FROM book";
            ResultSet rs = statement.executeQuery(sql);
            tablemodel = new DefaultTableModel(new String[]{"Book Name", "Author", "Publisher", "Number of Pages"}, 0);
            while (rs.next()) {
                String a = rs.getString("book_name");
                String b = rs.getString("book_author");
                String c = rs.getString("book_publisher");
                String d = rs.getString("book_numberofpages");
                tablemodel.addRow(new Object[]{a, b, c, d});
            }
            table = new JTable(tablemodel);
            JScrollPane scrollPane = new JScrollPane(table);
            panel1.add(scrollPane);
            frame.add(panel1);

            JPanel panel2 = new JPanel();
            Connection connection1 = connection.connection();
            statement = connection1.createStatement();
            String query = "SELECT * FROM student";
            rs = statement.executeQuery(query);
            tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID"}, 0);
            while(rs.next()) {
                String a = rs.getString("student_name");
                String b = rs.getString("student_surname");
                String c = rs.getString("student_number");
                tablemodel.addRow(new Object[]{a,b,c});
            }
            table1 = new JTable(tablemodel);
            JScrollPane scrollPane1 = new JScrollPane(table1);
            panel2.add(scrollPane1);
            frame.add(panel2);
            JButton borrowButton = new JButton("Borrow");
            frame.add(borrowButton);
            frame.setVisible(true);

            borrowButton.addActionListener (e -> {
                int i = table.getSelectedRow();
                int i1 = table1.getSelectedRow();
                Connection connection2 = connection.connection();
                String query1 = "INSERT INTO borrowedbook(student_name,student_surname,student_id,book_name,book_author,book_publisher,book_pageno) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pt = null;
                try {
                    pt = connection2.prepareStatement(query1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    pt.setString(1, (String) table1.getValueAt(i1,0));
                    pt.setString(2, (String) table1.getValueAt(i1,1));
                    pt.setString(3, (String) table1.getValueAt(i1,2));
                    pt.setString(4, (String) table.getValueAt(i,0));
                    pt.setString(5, (String) table.getValueAt(i,1));
                    pt.setString(6, (String) table.getValueAt(i,2));
                    pt.setInt(7, Integer.parseInt((String) table.getValueAt(i,3)));
                    pt.executeUpdate();
                    String message = "Done!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Successful!", 1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        }
    public void listBorrowedBooks() throws SQLException {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel();
            Connection con = connection.connection();
            statement = con.createStatement();
            String sql = "SELECT * FROM borrowedbook";
            rs = statement.executeQuery(sql);
            tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID", "Book Name", "Book Author", "Book Publisher", "Book Page Number"}, 0);
            while(rs.next()) {
                String a = rs.getString("student_name");
                String b = rs.getString("student_surname");
                String c = rs.getString("student_id");
                String d = rs.getString("book_name");
                String e = rs.getString("book_author");
                String f = rs.getString("book_publisher");
                String g = rs.getString("book_pageno");
                tablemodel.addRow(new Object[]{a,b,c,d,e,f,g});
            }
            table = new JTable(tablemodel);
            table.setEnabled(false);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(800,425));
            panel.add(scrollPane);
            this.add(panel);
        }
    public void listStudents() throws SQLException {
            createTable();
    }
    public void changeStudentInfo() throws SQLException {
        Connection connection1 = connection.connection();
        statement = connection1.createStatement();
        String query = "SELECT COUNT (*) FROM student";
        rs = statement.executeQuery(query);
        int size = 0;
        while (rs.next()){
            size = rs.getInt(1);
        }
        if(size == 0) {
            String error = "Please add students!";
            JOptionPane.showMessageDialog(new JFrame(),error,"Error!",0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new BorderLayout());
            Connection con = connection.connection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet rs = statement.executeQuery(sql);
            tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID"}, 0);
            while (rs.next()) {
                String a = rs.getString("student_name");
                String b = rs.getString("student_surname");
                String c = rs.getString("student_number");
                tablemodel.addRow(new Object[]{a, b, c});
            }
            table = new JTable(tablemodel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.NORTH);

            this.add(panel, BorderLayout.CENTER);

            JPanel newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(4,2));
            JTextField newstudentname = new JTextField();
            JTextField newstudentsurname = new JTextField();
            JTextField newstudentid = new JTextField();
            JButton change = new JButton("Change Student Info");
            newPanel.add(new JLabel("Name of the Student: "));
            newPanel.add(newstudentname);
            newPanel.add(new Label("Surname of the Student: "));
            newPanel.add(newstudentsurname);
            newPanel.add(new JLabel("Student ID: "));
            newPanel.add(newstudentid);
            newPanel.add(new JLabel(""));
            newPanel.add(change);
            this.add(newPanel, BorderLayout.NORTH);

            table.getSelectionModel().addListSelectionListener(e -> {
                int i = table.getSelectedRow();
                newstudentname.setText((String) tablemodel.getValueAt(i,0));
                newstudentsurname.setText((String) tablemodel.getValueAt(i,1));
                newstudentid.setText((String) tablemodel.getValueAt(i,2));
            });

            change.addActionListener(e -> {
                int i = table.getSelectedRow();
                String oldstudentname = (String) tablemodel.getValueAt(i,0);
                tablemodel.setValueAt(newstudentname.getText(),i,0);
                tablemodel.setValueAt(newstudentsurname.getText(),i,1);
                tablemodel.setValueAt(newstudentid.getText(),i,2);
                try {
                    Connection connection2 = connection.connection();
                    statement = connection2.createStatement();
                    PreparedStatement preparedStatement = connection2.prepareStatement("SELECT student_id FROM student WHERE student_name = ?");
                    preparedStatement.setString(1,oldstudentname);
                    ResultSet result = preparedStatement.executeQuery();
                    int value = 0;
                    while(result.next()) {
                        value = ((Number) result.getObject(1)).intValue();
                    }
                    Connection connection3 = connection.connection();
                    PreparedStatement pt = connection3.prepareStatement("UPDATE student SET student_name = ?, student_surname = ?, student_number = ? WHERE student_id = ?");
                    pt.setString(1, (String) table.getValueAt(i,0));
                    pt.setString(2, (String) table.getValueAt(i,1));
                    pt.setInt(3, Integer.parseInt((String) table.getValueAt(i,2)));
                    pt.setInt(4, value);
                    pt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
    public void deleteStudent() throws SQLException {
        Connection connection1 = connection.connection();
        statement = connection1.createStatement();
        String query = "SELECT COUNT (*) FROM student";
        rs = statement.executeQuery(query);
        int size = 0;
        while (rs.next()){
            size = rs.getInt(1);
        }
        if(size == 0) {
            String error = "Please add students!";
            JOptionPane.showMessageDialog(new JFrame(),error,"Error!",0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new BorderLayout());
            Connection con = connection.connection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet rs = st.executeQuery(sql);
            tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID"}, 0);
            while (rs.next()) {
                String a = rs.getString("student_name");
                String b = rs.getString("student_surname");
                String c = rs.getString("student_number");
                tablemodel.addRow(new Object[]{a, b, c});
            }
            table = new JTable(tablemodel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.NORTH);

            JButton deletebutton = new JButton("Delete Selected Student");
            panel.add(deletebutton);
            this.add(panel, BorderLayout.CENTER);

            deletebutton.addActionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    try {
                        String student_id = (String) tablemodel.getValueAt(table.getSelectedRow(), 2);
                        PreparedStatement pt = con.prepareStatement("DELETE FROM student WHERE student_number = ?");
                        pt.setInt(1, Integer.parseInt(student_id));
                        pt.executeUpdate();
                        pt.close();
                        JOptionPane.showMessageDialog(null, "Student Deleted Successfully!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            });
        }
        }
    public void createTable() throws SQLException {
        this.removeAll();
        this.repaint();
        this.revalidate();
        JPanel panel = new JPanel(new FlowLayout());
        Connection con = connection.connection();
        statement = con.createStatement();
        String sql = "SELECT * FROM student";
        rs = statement.executeQuery(sql);
        tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID"}, 0);
        while(rs.next()) {
            String a = rs.getString("student_name");
            String b = rs.getString("student_surname");
            String c = rs.getString("student_number");
            tablemodel.addRow(new Object[]{a,b,c});
        }
        table = new JTable(tablemodel);
        table.setEnabled(false); // Can not select items, for not-change purposes...
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        this.add(panel);
    }
}
