package GUI;

import Controller.JDBC;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class StudentBottomPanel extends JPanel {
    private BookBottomPanel bookBottomPanel;
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
        AtomicReference<ResultSet> rs = new AtomicReference<>(statement.executeQuery(sql));
        tablemodel = new DefaultTableModel(new String[]{"Book Name", "Author", "Publisher", "Number of Pages", "Availability"}, 0);
        while (rs.get().next()) {
            String a = rs.get().getString("book_name");
            String b = rs.get().getString("book_author");
            String c = rs.get().getString("book_publisher");
            String d = rs.get().getString("book_numberofpages");
            String e = rs.get().getString("book_available");
            if(Objects.equals(e, "t")) {
                e = "Yes";
            } else {
                e = "No";
            }
            tablemodel.addRow(new Object[]{a, b, c, d, e});
        }
        table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane);
        frame.add(panel1);

        JPanel panel2 = new JPanel();
        Connection connection1 = connection.connection();
        statement = connection1.createStatement();
        String query = "SELECT * FROM student";
        rs.set(statement.executeQuery(query));
        tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID"}, 0);
        while(rs.get().next()) {
            String a = rs.get().getString("student_name");
            String b = rs.get().getString("student_surname");
            String c = rs.get().getString("student_number");
            tablemodel.addRow(new Object[]{a,b,c});
        }
        table1 = new JTable(tablemodel);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        panel2.add(scrollPane1);
        frame.add(panel2);

        // Start date...

        JLabel startdate = new JLabel("Start date: ");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
        model.setSelected(true); // Default today's date...
        frame.add(startdate);
        frame.add(datePicker);

        // Finish date...
        JLabel finishdate = new JLabel("Finish date: ");
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        frame.add(finishdate);
        frame.add(datePicker1);

        JButton borrowButton = new JButton("Borrow");
        JButton listOrderButton = new JButton("List Existing Orders");
        frame.add(borrowButton);
        frame.add(listOrderButton);
        frame.setVisible(true);

        listOrderButton.addActionListener(ex -> {
            JFrame frame1 = new JFrame("Existing Orders");
            frame1.setSize(850,475);
            JPanel panel = new JPanel();
            try {
                Connection connection2 = connection.connection();
                statement = connection2.createStatement();
                String sqlquery = "SELECT * FROM orderedbook";
                ResultSet rss = statement.executeQuery(sqlquery);
                tablemodel = new DefaultTableModel(new String[]{"Student Name", "Student Surname", "Student ID", "Book Name", "Book Author", "Book Publisher", "Book Page Number"},0);
                while(rss.next()) {
                    String a = rss.getString("student_name");
                    String b = rss.getString("student_surname");
                    String c = rss.getString("student_id");
                    String d = rss.getString("book_name");
                    String e = rss.getString("book_author");
                    String f = rss.getString("book_publisher");
                    String g = rss.getString("book_pageno");
                    tablemodel.addRow(new Object[]{a,b,c,d,e,f,g});
                }
                table = new JTable(tablemodel);
                table.setEnabled(false);
                JScrollPane scrollPane2 = new JScrollPane(table);
                scrollPane2.setPreferredSize(new Dimension(800,400));
                panel.add(scrollPane2);
                frame1.add(panel);

            } catch (SQLException exx) {
                throw new RuntimeException(exx);
            }
            frame1.setVisible(true);
        });

        borrowButton.addActionListener (e -> {
            if(datePicker1.getModel().getValue() == null || datePicker.getModel().getValue() == null) {
                String err = "Please select a time slot!";
                JOptionPane.showMessageDialog(new Frame(),err,"Error",0);
            } else {
                int i = table.getSelectedRow();
                int i1 = table1.getSelectedRow();
                String statusString = (String) table.getValueAt(i, 4);
                boolean status = true;
                if (Objects.equals(statusString, "No")) {
                    status = false;
                }
                Connection connection2 = connection.connection();
                String query1 = "INSERT INTO borrowedbook(student_name,student_surname,student_id,book_name,book_author,book_publisher,book_pageno) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pt;
                try {
                    pt = connection2.prepareStatement(query1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (!status) {
                        String error = "Selected book has been already borrowed!, Would you like to create an order?";
                        int selectionKey = JOptionPane.showConfirmDialog(new JPanel(), error, "Error!", JOptionPane.YES_NO_OPTION);
                        if (selectionKey == 0) {
                            JFrame createOrder = new JFrame("Create Order For Book");
                            createOrder.setLayout(new FlowLayout());
                            JLabel label = new JLabel("Book will be available at: ");
                            createOrder.add(label);
                            Connection connection3 = connection.connection();
                            statement = connection3.createStatement();
                            String qurry = "SELECT book_availabledate FROM book WHERE book_name = ?";
                            PreparedStatement ppt = connection3.prepareStatement(qurry);
                            ppt.setString(1, (String) table.getValueAt(table.getSelectedRow(), 0));
                            ResultSet resultSet = ppt.executeQuery();
                            String info = "";
                            while (resultSet.next()) {
                                info = resultSet.getString(1);
                            }
                            JLabel label1 = new JLabel(info);
                            createOrder.add(label1);
                            JLabel label2 = new JLabel("Would you like to order the book? Please select a time slot.");
                            createOrder.add(label2);
                            String finalInfo = info;

                            //Start date
                            JLabel startdate2 = new JLabel("Start date:");
                            UtilDateModel model2 = new UtilDateModel();
                            JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
                            JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
                            //model2.setSelected(true); // Default today's date...
                            createOrder.add(startdate2);
                            createOrder.add(datePicker2);

                            //Finish date
                            JLabel finishdate3 = new JLabel("Finish date: ");
                            UtilDateModel model3 = new UtilDateModel();
                            JDatePanelImpl datePanel3 = new JDatePanelImpl(model3);
                            JDatePickerImpl datePicker3 = new JDatePickerImpl(datePanel3);
                            //model.setSelected(true); // Default today's date...
                            createOrder.add(finishdate3);
                            createOrder.add(datePicker3);

                            JButton orderButton = new JButton("Order The Book");
                            createOrder.add(orderButton);

                            String finalInfo1 = info;
                            orderButton.addActionListener(ex -> {
                                if(datePicker2.getModel().getValue() == null || datePicker3.getModel().getValue() == null) {
                                    String err1 = "Please select a time slot!";
                                    JOptionPane.showMessageDialog(new JFrame(), err1, "Error", 1);
                                } else {
                                    int selectedStartDate1 = datePicker1.getModel().getDay();
                                    int step = datePicker1.getModel().getMonth();
                                    String selectedStartDate2 = getMonth(step);
                                    int selectedStartDate3 = datePicker1.getModel().getYear();
                                    String start = selectedStartDate1 + " " + selectedStartDate2 + " " + selectedStartDate3;

                                    int selectedFinishDate1 = datePicker2.getModel().getDay();
                                    int step1 = datePicker2.getModel().getMonth();
                                    String selectedFinishDate2 = getMonth(step1);
                                    int selectedFinishDate3 = datePicker2.getModel().getYear();
                                    String finish = selectedFinishDate1 + " " + selectedFinishDate2 + " " + selectedFinishDate3;

                                    Locale.setDefault(Locale.UK);
                                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                                    LocalDate from = LocalDate.parse(start, dateFormatter);
                                    LocalDate to = LocalDate.parse(finish, dateFormatter);
                                    long difference = ChronoUnit.DAYS.between(from, to);
                                     if(difference < 0) {
                                         String message = "You can not take the book before the available date! Please select another date after " + finalInfo1;
                                         JOptionPane.showMessageDialog(new JPanel(),message,"Error",0);
                                     } else {
                                         // CONT...... //////////////

                                     }
                                }
                            });
                            createOrder.setSize(600, 150);
                            createOrder.setVisible(true);
                        }
                    } else {
                        pt.setString(1, (String) table1.getValueAt(i1, 0));
                        pt.setString(2, (String) table1.getValueAt(i1, 1));
                        pt.setString(3, (String) table1.getValueAt(i1, 2));
                        pt.setString(4, (String) table.getValueAt(i, 0));
                        pt.setString(5, (String) table.getValueAt(i, 1));
                        pt.setString(6, (String) table.getValueAt(i, 2));
                        pt.setInt(7, Integer.parseInt((String) table.getValueAt(i, 3)));
                        pt.executeUpdate();

                        int selectedStartDate1 = datePicker.getModel().getDay();
                        int step = datePicker.getModel().getMonth();
                        step++;
                        String selectedStartDate2 = getMonth(step);
                        int selectedStartDate3 = datePicker.getModel().getYear();
                        String start = selectedStartDate1 + " " + selectedStartDate2 + " " + selectedStartDate3;

                        int selectedFinishDate1 = datePicker1.getModel().getDay();
                        int step1 = datePicker1.getModel().getMonth();
                        step1++;
                        System.out.println(step1);
                        String selectedFinishDate2 = getMonth(step1);
                        int selectedFinishDate3 = datePicker1.getModel().getYear();
                        String finish = selectedFinishDate1 + " " + selectedFinishDate2 + " " + selectedFinishDate3;

                        Connection connection3 = connection.connection();
                        String qq = "UPDATE book SET book_available = ?, book_availabledate = ? WHERE book_name = ?";
                        PreparedStatement preparedStatement = connection3.prepareStatement(qq);
                        preparedStatement.setBoolean(1, false);
                        preparedStatement.setString(2, finish);
                        preparedStatement.setString(3, (String) table.getValueAt(i, 0));
                        preparedStatement.executeUpdate();

                        String message = "Done!";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Successful!", 1);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
        scrollPane.setPreferredSize(new Dimension(1000,425));
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
    public void releaseBook() throws SQLException {
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
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(800,425));
        JButton releaseButton = new JButton("Release Book");
        panel.add(scrollPane);
        panel.add(releaseButton);
        this.add(panel);

        releaseButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1) {
                try {
                    SimpleDateFormat dtf = new SimpleDateFormat("dd MM yyyy");
                    Calendar cal = Calendar.getInstance();
                    String date = dtf.format(cal.getTime());
                    String substring = date.substring(3,5);
                    String month = getMonth(Integer.parseInt(substring));
                    String substring1 = date.substring(0,2);
                    String substring2 = date.substring(6,10);
                    String dateafter = substring1 + " " + month + " " + substring2;
                    String book_name = (String) tablemodel.getValueAt(table.getSelectedRow(), 3);
                    PreparedStatement pt = con.prepareStatement("DELETE FROM borrowedbook WHERE book_name = ?");
                    pt.setString(1, book_name);
                    pt.executeUpdate();
                    pt = con.prepareStatement("UPDATE book SET book_available = ?, book_availabledate = ? WHERE book_name = ?");
                    pt.setBoolean(1, true);
                    pt.setString(2,dateafter);
                    pt.setString(3, book_name);
                    pt.executeUpdate();
                    String message = "Book has been succesfully released!";
                    JOptionPane.showMessageDialog(new JPanel(), message, "Success!", 1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public void createTable() throws SQLException {
        this.removeAll();
        this.repaint();
        this.revalidate();
        JPanel panel = new JPanel(new BorderLayout());
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
        this.add(panel, BorderLayout.SOUTH);
    }

    public String getMonth(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "lol";
        };
    }
}
