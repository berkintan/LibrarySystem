package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{

    public LoginPage() {
        super("Login Page");
        super.setSize(400,400);
        super.setLayout(new GridLayout(3,2));
        JPanel panel = new JPanel();
        JLabel name = new JLabel("User Name:");
        name.setBounds(50,50,100,50);
        JTextField nameField = new JTextField();
        nameField.setBounds(50,100,100, 50);
        JLabel password = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        panel.add(name);
        panel.add(nameField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(new JLabel());
        JButton loginButton = new JButton("Log in");
        panel.add(loginButton);
        super.add(panel, BorderLayout.NORTH);


        super.setVisible(true);
    }
    public boolean isPassCorrect(String password) {
        boolean buffer = false;


        return buffer;
    }
}
