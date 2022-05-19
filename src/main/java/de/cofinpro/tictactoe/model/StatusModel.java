package de.cofinpro.tictactoe.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class StatusModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;
    private Status status = Status.NOT_STARTED;
    private int move = 0;
    private Player[] player = new Player[] {Player.Human, Player.Human};

    public void reset() {
        move = 0;
        status = Status.NOT_STARTED;
    }

    public int incrementAndGetMove() {
        return ++move;
    }

    public Player getPlayer(int i) {
        return player[i];
    }

    public void togglePlayer(int i) {
        player[i] = player[i] == Player.Human ? Player.Robot : Player.Human;
    }
}
