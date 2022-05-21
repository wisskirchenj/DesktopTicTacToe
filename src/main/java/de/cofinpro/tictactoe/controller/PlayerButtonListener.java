package de.cofinpro.tictactoe.controller;

import de.cofinpro.tictactoe.model.StatusModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * action listener to toggle the player button - just sets the StatusModel property for the attached player.
 */
public class PlayerButtonListener implements ActionListener {

    private final int playerIndex;
    private final StatusModel statusModel;

    public PlayerButtonListener(int playerIndex, StatusModel statusModel) {
        this.playerIndex = playerIndex;
        this.statusModel = statusModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        statusModel.togglePlayer(playerIndex);
    }
}
