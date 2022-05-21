package de.cofinpro.tictactoe.model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * the board model class behind the tic-tac-toe board, that stores the state of all game cells (as char).
 * besides various getters and setters, it offers a method to determine the game's status (WIN, DRAW, PROGRESS).
 */
public class BoardModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251811L;

    private final char[][] cells = new char[3][3];

    public BoardModel() {
        reset();
    }

    public void reset() {
        Arrays.stream(cells).forEach(charArr -> Arrays.fill(charArr, ' '));
    }

    public String getCellText(JButton button) {
        return getCellText(button.getName().charAt(button.getName().length() - 1) - '1' + 1,
                button.getName().charAt(button.getName().length() - 2) - 'A' + 1);
    }

    public void setCellText(JButton button, char value) {
        int rowIndex = button.getName().charAt(button.getName().length() - 1) - '1';
        int columnIndex = button.getName().charAt(button.getName().length() - 2) - 'A';
        cells[columnIndex][rowIndex] = value;
    }

    public String getCellText(int row, int column) {
        return String.valueOf(cells[column - 1][row - 1]);
    }

    public String getCellName(int row, int column) {
        return "Button" + (char) ('A' + column - 1) + row;
    }

    /**
     * checks the cell contents to check, if a win took place or a draw or nothing yet.
     * @param move the number of the move to decide on draw or game in progress
     * @return the Status object of the game
     */
    public Status determineStatus(int move) {
        for (int i = 0; i < 3; i++) {
            if (cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i]
                    && cells[0][i] != ' ') {
                return cells[0][i] == 'X' ? Status.X_WINS : Status.O_WINS;
            }
            if (cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]
                    && cells[i][0] != ' ') {
                return cells[i][0] == 'X' ? Status.X_WINS : Status.O_WINS;
            }
        }
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]
                && cells[0][0] != ' ') {
            return cells[0][0] == 'X' ? Status.X_WINS : Status.O_WINS;
        }
        if (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]
                && cells[0][2] != ' ') {
            return cells[0][2] == 'X' ? Status.X_WINS : Status.O_WINS;
        }
        return move == 9 ? Status.DRAW : Status.PROGRESS;
    }
}