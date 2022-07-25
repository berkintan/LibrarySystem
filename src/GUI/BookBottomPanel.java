package GUI;

import javax.swing.*;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Model.*;

public class BookBottomPanel extends JPanel {
    private JButton okBtn;
    private Book book;
    private ArrayList<Book> books = new ArrayList<>();
    private JTable table;
    private DefaultTableModel tablemodel;

    public BookBottomPanel() {
        this.setLayout(new BorderLayout());
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
//            this.removeAll();
//            this.repaint();
//            this.revalidate();
//            JPanel panel = new JPanel(new GridLayout(books.size() + 1, 4));
//            JLabel name = new JLabel("Name");
//            JLabel author = new JLabel("Author");
//            JLabel publisher = new JLabel("Publisher");
//            JLabel numberofpages = new JLabel("Number Of Pages");
//            name.setForeground(Color.RED);
//            author.setForeground(Color.RED);
//            numberofpages.setForeground(Color.RED);
//            publisher.setForeground(Color.RED);
//            panel.add(name);
//            panel.add(author);
//            panel.add(publisher);
//            panel.add(numberofpages);
//            for (int i = 0; i < books.size(); i++) {
//                panel.add(new JLabel(String.valueOf(books.get(i).getNameOftheBook())));
//                panel.add(new JLabel(String.valueOf(books.get(i).getAuthor())));
//                panel.add(new JLabel(String.valueOf(books.get(i).getPublisher())));
//                panel.add(new JLabel(String.valueOf(books.get(i).getNumberOfPages())));
//            }
//            this.add(panel);
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
            table = new JTable(bookInfo, headers);
            table.setEnabled(false); // Can not select items, for not-chane purposes...
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane);
            this.add(panel);
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
                if(table.getSelectedRow() != -1) {
                    tablemodel.removeRow(table.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Deleted successfully");
                    books.remove(table.getSelectedRow() + 1);
                }
            });


        }
    }
}


