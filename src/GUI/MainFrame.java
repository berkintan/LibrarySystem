package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Menu menu;

    public MainFrame() {
        super("Library Menagement System");
        super.setLayout(new BorderLayout());

        String message = "Welcome to the Library System. Please select the action you would like to perform from the Library menu.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Info", 1);

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
