package GUI;

import javax.swing.*;
import java.awt.*;
import Model.*;

public class BookBottomPanel extends JPanel{
    private JButton okBtn;

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
    }
}
