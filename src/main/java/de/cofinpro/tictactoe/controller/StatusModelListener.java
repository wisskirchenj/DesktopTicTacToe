package de.cofinpro.tictactoe.controller;

import de.cofinpro.tictactoe.model.StatusModel;

/**
 * interface to implement for any class, that listens to changes of the game's StatusModel.
 */
public interface StatusModelListener {

    /**
     * method hook called in case of notification by the status model
     * @param model the changed status model
     */
    void update(StatusModel model);
}
