package de.cofinpro.tictactoe.view;

import javax.swing.*;
import java.awt.*;

/**
 * UI class representing the tic-tac-toe board, a JPanel with 9 buttons for the tic-tac-toe cells.
 */
public class Board extends JPanel {

    public Board() {
        super(new GridLayout(3, 3, -5, -5));
    }

    /**
     * clear all cells and disable buttons until game is started.
     */
    public void reset() {
        for (Component button: getComponents()) {
            ((JButton) button).setText(" ");
            button.setEnabled(false);
        }
    }

    /**
     * enable all buttons - called in starting of new game.
     */
    public void setEnabled() {
        for (Component button: getComponents()) {
            button.setEnabled(true);
        }
    }

    public JButton getButton(int number) {
        return (JButton) getComponent(number);
    }
}
