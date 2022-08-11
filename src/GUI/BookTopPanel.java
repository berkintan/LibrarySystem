package GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class BookTopPanel extends JPanel {
    private JButton changeBtn;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton listBtn;
    protected static BookBottomPanel bookBottomPanel;


    public BookTopPanel() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Control Section..."));
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(1000,35));
        addBtn = new JButton("Add Book");
        changeBtn = new JButton("Change Book Info");
        deleteBtn = new JButton("Delete Book");
        listBtn = new JButton("List Books");
        buttons.add(addBtn);
        buttons.add(changeBtn);
        buttons.add(deleteBtn);
        buttons.add(listBtn);
        this.add(buttons);

        bookBottomPanel = new BookBottomPanel();
        this.add(bookBottomPanel);

        addBtn.addActionListener(e -> {
            bookBottomPanel.addBook();
        });

        listBtn.addActionListener(e -> {
            try {
                bookBottomPanel.listBooks();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        changeBtn.addActionListener(e -> {
            try {
                bookBottomPanel.changeBookInfo();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                bookBottomPanel.deleteBook();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
