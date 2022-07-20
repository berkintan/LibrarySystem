package GUI;

import javax.swing.*;
import java.awt.*;

public class BookPanel extends JPanel {
    private BookTopPanel bookTopPanel;
    private BookBottomPanel bookBottomPanel;

    public BookPanel() {
        bookTopPanel = new BookTopPanel();
        this.add(bookTopPanel);
    }
}
