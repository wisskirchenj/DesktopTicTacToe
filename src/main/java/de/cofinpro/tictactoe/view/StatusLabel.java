package de.cofinpro.tictactoe.view;

import de.cofinpro.tictactoe.controller.StatusModelListener;
import de.cofinpro.tictactoe.model.Status;
import de.cofinpro.tictactoe.model.StatusModel;

import javax.swing.*;

/**
 * UI class Jlabel representing the status label. Listens to StatusModel.
 */
public class StatusLabel extends JLabel implements StatusModelListener {

    public StatusLabel() {
        super(Status.NOT_STARTED.getStatusMessage());
        setName("LabelStatus");
    }

    @Override
    public void update(StatusModel statusModel) {
       switch (statusModel.getStatus()) {
           case PROGRESS -> {
               int move = statusModel.getMove();
               boolean evenMove = move % 2 == 0;
               setText("The turn of %s Player (%c)".formatted(
                       statusModel.getPlayer(move % 2).getText(), evenMove ? 'X' : 'O'));
           }
           case X_WINS -> setText("The %s Player (X) wins".formatted(statusModel.getPlayer(0).getText()));
           case O_WINS -> setText("The %s Player (O) wins".formatted(statusModel.getPlayer(1).getText()));
           default -> setText(statusModel.getStatus().getStatusMessage());
       }
    }
}
