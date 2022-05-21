package de.cofinpro.tictactoe.view;

import de.cofinpro.tictactoe.controller.StatusModelListener;
import de.cofinpro.tictactoe.model.Player;
import de.cofinpro.tictactoe.model.Status;
import de.cofinpro.tictactoe.model.StatusModel;

import javax.swing.*;

/**
 * UI class representing the Player buttons, used to switch between Robot and Human. Listens to StatusModel.
 */
public class PlayerButton extends JButton implements StatusModelListener {

    private final int playerIndex;

    public PlayerButton(int playerNumber) {
        super(Player.HUMAN.getText());
        this.playerIndex = playerNumber - 1;
        setName("ButtonPlayer" + playerNumber);
    }

    @Override
    public void update(StatusModel statusModel) {
        setEnabled(statusModel.getStatus() == Status.NOT_STARTED);
        setText(statusModel.getPlayer(playerIndex).getText());
    }
}
