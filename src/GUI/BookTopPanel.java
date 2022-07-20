package GUI;

import javax.swing.*;

public class BookTopPanel extends JPanel {
    private JButton changeBtn;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton listBtn;

    public BookTopPanel() {
        this.setBorder(BorderFactory.createTitledBorder("Controllers"));
        addBtn = new JButton("Add Book");
        changeBtn = new JButton("Change Book Info");
        deleteBtn = new JButton("Delete Book");
        listBtn = new JButton("List Books");
        this.add(addBtn);
        this.add(changeBtn);
        this.add(deleteBtn);
        this.add(listBtn);


    }
}
