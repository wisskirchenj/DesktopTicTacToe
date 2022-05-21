package de.cofinpro.tictactoe.model;

import de.cofinpro.tictactoe.controller.StatusModelListener;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * central model class, that saves all game information on players, move and status - except the cell contents.
 * It maintains a list of registered StatusModelListener implementations, that are notified on model changes.
 */
@Getter
public class StatusModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;
    private Status status = Status.NOT_STARTED;
    private int move = 0;
    private final List<StatusModelListener> listeners = new ArrayList<>();
    private final Player[] player = new Player[] {Player.HUMAN, Player.HUMAN};

    public void registerListener(StatusModelListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        listeners.forEach(listener -> listener.update(this));
    }

    public void reset() {
        move = 0;
        status = Status.NOT_STARTED;
        notifyListeners();
    }

    public int incrementAndGetMove() {
        move++;
        notifyListeners();
        return move;
    }

    public void setStatus(Status status) {
        this.status = status;
        notifyListeners();
    }

    public Player getPlayer(int i) {
        return player[i];
    }

    public void setPlayer(int i, Player type) {
        player[i] = type;
        notifyListeners();
    }

    public void togglePlayer(int i) {
        player[i] = player[i] == Player.HUMAN ? Player.ROBOT : Player.HUMAN;
        notifyListeners();
    }
}
