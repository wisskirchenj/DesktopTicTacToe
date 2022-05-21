package de.cofinpro.tictactoe.model;

import java.util.Random;

/**
 * Class offering static method(s) for game strategies of the Robots - currently only random..
 */
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
