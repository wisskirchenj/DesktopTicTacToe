package de.cofinpro.tictactoe.view;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public Board() {
        super(new GridLayout(3, 3, -5, -5));
    }

    public void reset() {
        for (Component button: getComponents()) {
            ((JButton) button).setText(" ");
        }
    }

    public JButton getButton(int number) {
        return (JButton) getComponent(number);
    }
}
