package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("Library Menagement System");
        super.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel bookPanel = new BookPanel();
        tabbedPane.add("Book",bookPanel);
        JPanel publisherPanel = new PublisherPanel();
        tabbedPane.add("Publisher", publisherPanel);
        JPanel studentPanel = new StudentPanel();
        tabbedPane.add("Student",studentPanel);
        super.add(tabbedPane);

        setJMenuBar(createMenuBar());
        super.setSize(650,500);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Library");
        JMenuItem item1 = new JMenuItem("Exit");

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(item1);
        menuBar.add(menu);

        return menuBar;
    }

}
