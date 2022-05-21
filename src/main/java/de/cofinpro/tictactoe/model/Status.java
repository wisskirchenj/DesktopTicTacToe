package de.cofinpro.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum type for the game status, 5 status exist.
 */
@Getter
@AllArgsConstructor
public enum Status {

    NOT_STARTED("Game is not started"),
    PROGRESS("Game in progress"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    DRAW("Draw");

    private final String statusMessage;
}
