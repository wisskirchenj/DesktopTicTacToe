package de.cofinpro.tictactoe.model;

public enum Status {

    NOT_STARTED(" Game is not started"),
    PROGRESS(" Game in progress"),
    X_WINS(" X wins"),
    O_WINS(" O wins"),
    DRAW(" Draw");
    private final String statusMessage;

    Status(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
