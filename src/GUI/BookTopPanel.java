package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;

public class BookTopPanel extends JPanel {
    private JButton changeBtn;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton listBtn;
    private BookBottomPanel bookBottomPanel;


    public BookTopPanel() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Control Section..."));
        JPanel buttons = new JPanel();
        addBtn = new JButton("Add Book");
        changeBtn = new JButton("Change Book Info");
        deleteBtn = new JButton("Delete Book");
        listBtn = new JButton("List Books");
        buttons.add(addBtn);
        buttons.add(changeBtn);
        buttons.add(deleteBtn);
        buttons.add(listBtn);
        this.add(buttons);

        this.bookBottomPanel = new BookBottomPanel();
        this.add(bookBottomPanel);

        addBtn.addActionListener(e -> {
            this.bookBottomPanel.addBook();
        });

        listBtn.addActionListener(e -> {
            this.bookBottomPanel.listBooks();
        });
    }
}
