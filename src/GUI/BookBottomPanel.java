package GUI;

import Controller.JDBC;
import Model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class BookBottomPanel extends JPanel {
    private Book book;
    private static ArrayList<Book> books;
    private JTable table;
    private DefaultTableModel tablemodel;
    private JDBC connection = new JDBC();
    private Statement statement;
    private ResultSet rs;

    public BookBottomPanel() {
        this.setLayout(new BorderLayout());
        books = new ArrayList<>();
    }
    public BookBottomPanel(StudentBottomPanel studentBottomPanel) {

    }

    public void addBook() {
        this.removeAll();
        this.repaint();
        this.revalidate();
        JPanel panel = new JPanel(new GridLayout(5,2));
        JTextField bookName = new JTextField();
        JTextField author = new JTextField();
        JTextField publisher = new JTextField();
        JTextField numberofPages = new JTextField();
        JButton addBtn = new JButton("Add");
        panel.add(new JLabel("Name of the book: "));
        panel.add(bookName);
        panel.add(new JLabel("Name of the author: "));
        panel.add(author);
        panel.add(new JLabel("Name of the publisher: "));
        panel.add(publisher);
        panel.add(new JLabel("Number of pages: "));
        panel.add(numberofPages);
        panel.add(new JLabel());
        panel.add(addBtn);
        this.add(panel);

        addBtn.addActionListener(e -> {
            if (bookName.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()
                    || numberofPages.getText().isEmpty()) {
                String error = "Please fill all of the information for the book!";
                JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
            } else {
                try {
                    Connection con = connection.connection();
                    String s = "INSERT INTO book(book_name,book_author,book_publisher,book_numberofpages) VALUES (?,?,?,?)";
                    PreparedStatement pt = con.prepareStatement(s);
                    book = new Book(bookName.getText(), author.getText(), numberofPages.getText(), publisher.getText());
                    pt.setString(1,book.getNameOftheBook());
                    pt.setString(2,book.getAuthor());
                    pt.setString(3,book.getPublisher());
                    pt.setString(4,book.getNumberOfPages());
                    pt.executeUpdate();
                    String message = "Book added successfully!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Done!", 1);
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter a number for the " +
                            "\"Number of pages\" section!!!!!", "Error!", 0);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void listBooks() throws SQLException {
        Connection connection1 = connection.connection();
        statement = connection1.createStatement();
        String query = "SELECT COUNT (*) FROM book";
        rs = statement.executeQuery(query);
        int size = 0;
        while (rs.next()) {
            size = rs.getInt(1);
        }
        if (size == 0) {
            String error = "Please add students!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error!", 0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new FlowLayout());
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
            table.setEnabled(false); // Can not select items, for not-chane purposes...
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            this.add(panel);
        }
    }


    public void changeBookInfo() throws SQLException {
        Connection connection1 = connection.connection();
        statement = connection1.createStatement();
        String query = "SELECT COUNT (*) FROM book";
        rs = statement.executeQuery(query);
        int size = 0;
        while (rs.next()){
            size = rs.getInt(1);
        }
        if(size == 0) {
            String error = "Please add books!";
            JOptionPane.showMessageDialog(new JFrame(),error,"Error!",0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new FlowLayout());
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
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.NORTH);

            this.add(panel, BorderLayout.CENTER);

            JPanel newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(5,2));
            JTextField newbookname = new JTextField();
            JTextField newauthorname = new JTextField();
            JTextField newpageno = new JTextField();
            JTextField newpublisher = new JTextField();
            JButton change = new JButton("Change Book Info");
            newPanel.add(new JLabel("Name of the book"));
            newPanel.add(newbookname);
            newPanel.add(new JLabel("Name of the author: "));
            newPanel.add(newauthorname);
            newPanel.add(new JLabel("Name of the publisher: "));
            newPanel.add(newpublisher);
            newPanel.add(new JLabel("Number of pages: "));
            newPanel.add(newpageno);
            newPanel.add(new JLabel());
            newPanel.add(change);
            this.add(newPanel, BorderLayout.NORTH);

            table.getSelectionModel().addListSelectionListener(e -> {
                int i = table.getSelectedRow();
                newbookname.setText((String) tablemodel.getValueAt(i,0));
                newauthorname.setText((String) tablemodel.getValueAt(i,1));
                newpublisher.setText((String) tablemodel.getValueAt(i,2));
                newpageno.setText((String) tablemodel.getValueAt(i,3));
            });

            change.addActionListener(e -> {
                int i = table.getSelectedRow();
                String oldbookname = (String) tablemodel.getValueAt(i,0); // new change
                tablemodel.setValueAt(newbookname.getText(),i,0);
                tablemodel.setValueAt(newauthorname.getText(),i,1);
                tablemodel.setValueAt(newpublisher.getText(),i,2);
                tablemodel.setValueAt(newpageno.getText(),i,3);
                try {
                    String bookname = (String) table.getValueAt(i, 0);
                    Connection connection3 = connection.connection();
                    statement = connection3.createStatement();
                    PreparedStatement preparedStatement = connection3.prepareStatement("SELECT book_id FROM book where book_name = ?");
                    preparedStatement.setString(1, oldbookname); // changed the parameter to oldbookname
                    ResultSet result = preparedStatement.executeQuery();
                    int value = 0;
                    while (result.next()) {
                        value = ((Number) result.getObject(1)).intValue();
                    }
                    Connection connection2 = connection.connection();
                    PreparedStatement pt = connection2.prepareStatement("UPDATE book SET book_name = ?, book_author = ?, book_publisher = ?, book_numberofpages = ? WHERE book_id = ?");
                    pt.setString(1, (String) table.getValueAt(i,0));
                    pt.setString(2, (String) table.getValueAt(i,1));
                    pt.setString(3, (String) table.getValueAt(i,2));
                    pt.setString(4, (String) table.getValueAt(i,3));
                    pt.setInt(5, value);
                    pt.executeUpdate();
                    pt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public void deleteBook() throws SQLException {
        Connection connection1 = connection.connection();
        statement = connection1.createStatement();
        String query = "SELECT COUNT (*) FROM book";
        rs = statement.executeQuery(query);
        int size = 0;
        while (rs.next()){
            size = rs.getInt(1);
        }
        if(size == 0) {
            String error = "Please add books!";
            JOptionPane.showMessageDialog(new JFrame(),error,"Error!",0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new FlowLayout());
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
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.NORTH);

            JButton deletebutton = new JButton("Delete Selected Book");
            panel.add(deletebutton);
            this.add(panel, BorderLayout.CENTER);

            deletebutton.addActionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    String book_name = (String) tablemodel.getValueAt(table.getSelectedRow(), 0);
                    try {
                    PreparedStatement pt = con.prepareStatement("DELETE FROM book WHERE book_name = ?");
                    pt.setString(1, book_name);
                    pt.executeUpdate();
                    pt.close();
                    JOptionPane.showMessageDialog(null, "Book Deleted Successfully!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            });


        }
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
}