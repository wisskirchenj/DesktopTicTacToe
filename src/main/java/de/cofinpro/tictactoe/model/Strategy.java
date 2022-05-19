package de.cofinpro.tictactoe.model;

import java.util.Random;

public class Strategy {

    private static final Random RANDOM = new Random();

    private Strategy() {
        // no instances
    }

    public static int random(BoardModel boardModel) {
        int cell;
        do {
            cell = RANDOM.nextInt(3 * 3);
        } while (!boardModel.getCellText(3 - cell / 3 , cell % 3 + 1).equals(" "));
        return cell;
    }
}
