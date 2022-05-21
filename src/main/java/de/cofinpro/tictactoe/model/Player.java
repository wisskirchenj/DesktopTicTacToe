package de.cofinpro.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum for the two player types with their button texts.
 */
@Getter
@AllArgsConstructor
public enum Player {
    HUMAN("Human"),
    ROBOT("Robot");

    private final String text;
}
