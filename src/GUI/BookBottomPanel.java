package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Model.*;

public class BookBottomPanel extends JPanel{
    private JButton okBtn;
    private Book book;
    private ArrayList<Book> books = new ArrayList<>();

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
            if(bookName.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty()
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
        if(books.size() == 0) {
            String error = "There are no added books. Please add books to list!";
            JOptionPane.showMessageDialog(new JFrame(), error, "Error", 0);
        } else {
            for (int i = 0; i < books.size() - 1; i++) {
                System.out.println(book.toString());
            }
        }
    }
}

