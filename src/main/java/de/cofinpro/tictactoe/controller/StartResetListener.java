package de.cofinpro.tictactoe.controller;

import de.cofinpro.tictactoe.model.Status;
import de.cofinpro.tictactoe.model.StatusModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for the StartResetButton, that can start and reset the game by the GameControl interface to
 * the TicTacToe class.
 */
public class StartResetListener implements ActionListener {

    private final GameControl gameControl;
    private final StatusModel statusModel;

    public StartResetListener(GameControl ticTacToe, StatusModel statusModel) {
        this.gameControl = ticTacToe;
        this.statusModel = statusModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (statusModel.getStatus() == Status.NOT_STARTED) {
            gameControl.startGame();
        } else {
            gameControl.resetGame();
        }
    }
}
