package de.cofinpro.tictactoe.controller;

import de.cofinpro.tictactoe.model.Player;
import de.cofinpro.tictactoe.model.StatusModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener implementation for all the menu items of the Game-menu to reset and start with different game modes.
 */
public class StartMenuListener implements ActionListener {

    private final GameControl gameControl;
    private final StatusModel statusModel;
    private final GameMode gameMode;

    public StartMenuListener(GameControl ticTacToe, GameMode gameMode, StatusModel statusModel) {
        this.gameControl = ticTacToe;
        this.gameMode = gameMode;
        this.statusModel = statusModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameControl.resetGame();
        statusModel.setPlayer(0, switch (gameMode) {
            case HUMAN_HUMAN, HUMAN_ROBOT -> Player.HUMAN;
            case ROBOT_HUMAN, ROBOT_ROBOT -> Player.ROBOT;
        });
        statusModel.setPlayer(1, switch (gameMode) {
            case HUMAN_HUMAN, ROBOT_HUMAN -> Player.HUMAN;
            case HUMAN_ROBOT, ROBOT_ROBOT -> Player.ROBOT;
        });
        gameControl.startGame();
    }
}
