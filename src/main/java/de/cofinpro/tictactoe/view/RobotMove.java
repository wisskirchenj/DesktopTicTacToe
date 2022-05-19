package de.cofinpro.tictactoe.view;

import javax.swing.*;

public class RobotMove extends SwingWorker<Object, Void> {

    JButton button;
    TicTacToe ticTacToe;

    public RobotMove(JButton button, TicTacToe ticTacToe) {
        this.button = button;
        this.ticTacToe = ticTacToe;
    }

    @Override
    protected Object doInBackground() throws Exception {
        Thread.sleep(700);
        ticTacToe.performMove(button);
        return null;
    }
}
