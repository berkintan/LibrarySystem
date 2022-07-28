package GUI;

import Model.Book;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookBottomPanel extends JPanel {
    private Book book;
    private static ArrayList<Book> books;
    private JTable table;
    private DefaultTableModel tablemodel;

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
                    String pageNO = numberofPages.getText();
                    int page = Integer.parseInt(pageNO);
                    book = new Book(bookName.getText(), author.getText(), page, publisher.getText());
                    String message = "Book added successfully!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Done!", 1);
                    books.add(book);

                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter a number for the " +
                            "\"Number of pages\" section!!!!!", "Error!", 0);
                }
            }
        });
    }

    public void listBooks() {
        if (books.size() == 0) {
            String error = "There are no added books. Please add books to list!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new FlowLayout());
            String[] headers = {"Name", "Author", "Publisher", "Number of Pages"};
            Object[][] bookInfo = new Object[books.size()][4];
            for(int j = 0; j < books.size(); j++) {
                bookInfo[j][0] = books.get(j).getNameOftheBook();
                bookInfo[j][1] = books.get(j).getAuthor();
                bookInfo[j][2] = books.get(j).getPublisher();
                bookInfo[j][3] = String.valueOf(books.get(j).getNumberOfPages());
            }
            table = new JTable(bookInfo, headers);
            table.setEnabled(false); // Can not select items, for not-chane purposes...
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            this.add(panel);
        }
    }

    public void changeBookInfo() {
        if(books.size() == 0) {
            String error = "There are no added books. Please add a book to change book info!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new FlowLayout());
            String[] headers = {"Name", "Author", "Publisher", "Number of Pages"};
            Object[][] bookInfo = new Object[books.size()][4];
            for(int j = 0; j < books.size(); j++) {
                bookInfo[j][0] = books.get(j).getNameOftheBook();
                bookInfo[j][1] = books.get(j).getAuthor();
                bookInfo[j][2] = books.get(j).getPublisher();
                bookInfo[j][3] = String.valueOf(books.get(j).getNumberOfPages());
            }
            tablemodel = new DefaultTableModel(bookInfo,headers);
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

            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int i = table.getSelectedRow();
                    newbookname.setText((String) tablemodel.getValueAt(i,0));
                    newauthorname.setText((String) tablemodel.getValueAt(i,1));
                    newpublisher.setText((String) tablemodel.getValueAt(i,2));
                    newpageno.setText((String) tablemodel.getValueAt(i,3));
                }
            });

            change.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = table.getSelectedRow();
                    tablemodel.setValueAt(newbookname.getText(),i,0);
                    tablemodel.setValueAt(newauthorname.getText(),i,1);
                    tablemodel.setValueAt(newpublisher.getText(),i,2);
                    tablemodel.setValueAt(newpageno.getText(),i,3);
                    book.setNameOftheBook(newbookname.getText());
                    book.setAuthor(newauthorname.getText());
                    book.setPublisher(newpublisher.getText());
                    book.setNumberOfPages(Integer.parseInt(newpageno.getText()));
                }
            });
        }
    }

    public void deleteBook() {
        if(books.size() == 0) {
            String error = "There are no books added!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
        } else {
            this.removeAll();
            this.repaint();
            this.revalidate();
            JPanel panel = new JPanel(new BorderLayout());
            String[] headers = {"Name", "Author", "Publisher", "Number of Pages"};
            Object[][] bookInfo = new Object[books.size()][4];
            for(int j = 0; j < books.size(); j++) {
                bookInfo[j][0] = books.get(j).getNameOftheBook();
                bookInfo[j][1] = books.get(j).getAuthor();
                bookInfo[j][2] = books.get(j).getPublisher();
                bookInfo[j][3] = String.valueOf(books.get(j).getNumberOfPages());
            }
            tablemodel = new DefaultTableModel(bookInfo,headers);
            table = new JTable(tablemodel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.NORTH);

            JButton deletebutton = new JButton("Delete Selected Book");
            panel.add(deletebutton);
            this.add(panel, BorderLayout.CENTER);

            deletebutton.addActionListener(e -> {
                if (table.getSelectedRow() != -1) {
                    tablemodel.removeRow(table.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Book Deleted Successfully!");
                    books.remove(table.getSelectedRow() + 1);
                }

            });


        }
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
}