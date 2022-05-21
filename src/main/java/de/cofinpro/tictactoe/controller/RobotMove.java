package de.cofinpro.tictactoe.controller;

import de.cofinpro.tictactoe.view.TicTacToe;

import javax.swing.*;

/**
 * SwingWorker thread that runs asynchronously from event dispatcher thread and performs a robot move after
 * 700 milli secs of waiting.
 */
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
