package de.cofinpro.tictactoe.view;

import de.cofinpro.tictactoe.controller.StatusModelListener;
import de.cofinpro.tictactoe.model.Status;
import de.cofinpro.tictactoe.model.StatusModel;

import javax.swing.*;

/**
 * UI class representing the Game Start/Reset button. Listens to StatusModel.
 */
public class StartResetButton extends JButton implements StatusModelListener {

    public StartResetButton() {
        super("Start");
        setName("ButtonStartReset");
    }

    @Override
    public void update(StatusModel statusModel) {
        setText(statusModel.getStatus() == Status.NOT_STARTED ? "Start" : "Reset");
    }
}
