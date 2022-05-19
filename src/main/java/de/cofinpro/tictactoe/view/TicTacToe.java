package de.cofinpro.tictactoe.view;

import de.cofinpro.tictactoe.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    private JLabel statusLabel;
    private Board board;
    private JButton startResetButton;
    private final BoardModel boardModel = new BoardModel();
    private final StatusModel statusModel = new StatusModel();

    public TicTacToe() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(createButtonPanel(), BorderLayout.NORTH);
        add(createDecoratedBoard(), BorderLayout.CENTER);
        add(createStatusPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private Component createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, -10, -10));
        buttonPanel.add(createButton("Human", "ButtonPlayer1", new PlayerButtonListener(0)));
        startResetButton = createButton("Start", "ButtonStartReset", new StartResetListener());
        buttonPanel.add(startResetButton);
        buttonPanel.add(createButton("Human", "ButtonPlayer2", new PlayerButtonListener(1)));
        return buttonPanel;
    }

    private JButton createButton(String text, String name, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setName(name);
        button.addActionListener(actionListener);
        return button;
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    private Component createStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel(statusModel.getStatus().getStatusMessage());
        statusLabel.setName("LabelStatus");
        statusPanel.add(statusLabel);
        return statusPanel;
    }

    private Component createDecoratedBoard() {
        board = new Board();
        for (int row = 3; row > 0; row--) {
            for (int column = 1; column <= 3; column++) {
                JButton button = new JButton(" ");
                button.setName(boardModel.getCellName(row, column));
                button.setFont(new Font("Arial", Font.BOLD, 40));
                button.setFocusPainted(false);
                button.addActionListener(this);
                board.add(button);
            }
        }
        return board;
    }

    private void robotPlayerMove() {
        JButton button = board.getButton(Strategy.random(boardModel));
        new RobotMove(button, this).execute();
    }

    void performMove(JButton button) {
        int move = statusModel.incrementAndGetMove();
        startResetButton.setText("Reset");
        boardModel.setCellText(button, move % 2 == 1 ? 'X' : 'O');
        button.setText(boardModel.getCellText(button));
        statusModel.setStatus(boardModel.determineStatus(move));
        setStatus(statusModel.getStatus().getStatusMessage());
        if (statusModel.getStatus() == Status.PROGRESS && statusModel.getPlayer(move % 2) == Player.Robot) {
            robotPlayerMove();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (statusModel.getPlayer(statusModel.getMove() % 2) != Player.Robot &&
                boardModel.getCellText(button).equals(" ") &&
                statusModel.getStatus() != Status.X_WINS &&
                statusModel.getStatus() != Status.O_WINS) {
            performMove(button);
        }
    }

    class StartResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (statusModel.getStatus() != Status.NOT_STARTED) {
                startResetButton.setText("Start");
                statusModel.reset();
                boardModel.reset();
                board.reset();
                setStatus(statusModel.getStatus().getStatusMessage());
            } else if (statusModel.getPlayer(0) == Player.Robot) {
                startResetButton.setText("Reset");
                robotPlayerMove();
            }
        }
    }

    class PlayerButtonListener implements ActionListener {

        private final int player;

        public PlayerButtonListener(int player) {
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            statusModel.togglePlayer(player);
            ((JButton) e.getSource()).setText(statusModel.getPlayer(player).name());
        }
    }
}
