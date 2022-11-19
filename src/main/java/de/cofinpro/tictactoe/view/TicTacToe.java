package de.cofinpro.tictactoe.view;

import de.cofinpro.tictactoe.controller.*;
import de.cofinpro.tictactoe.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 * class representing the Tic-Tac-Toe JFrame. It initializes all UI-components, controls
 * the game by keeping the StatusModel and implements ActionListener for the cell buttons.
 */
public class TicTacToe extends JFrame implements GameControl, ActionListener {

    private Board board;
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
        setJMenuBar(createMenuBar());
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        menuGame.setMnemonic(KeyEvent.VK_G);
        menuGame.setName("MenuGame");
        menuGame.add(createMenuItem("Human vs Human", "MenuHumanHuman", 'H', GameMode.HUMAN_HUMAN));
        menuGame.add(createMenuItem("Human vs Robot", "MenuHumanRobot", 'R', GameMode.HUMAN_ROBOT));
        menuGame.add(createMenuItem("Robot vs Human", "MenuRobotHuman", 'u', GameMode.ROBOT_HUMAN));
        menuGame.add(createMenuItem("Robot vs Robot", "MenuRobotRobot", 'o', GameMode.ROBOT_ROBOT));
        menuGame.addSeparator();
        JMenuItem menuItemExit = new JMenuItem("Exit", 'x');
        menuItemExit.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        menuGame.add(menuItemExit);
        menubar.add(menuGame);
        return menubar;
    }

    private JMenuItem createMenuItem(String text, String name, char mnemonic, GameMode mode) {
        JMenuItem menuItem = new JMenuItem(text, mnemonic);
        menuItem.setName(name);
        menuItem.addActionListener(new StartMenuListener(this, mode, statusModel));
        return menuItem;
    }

    private Component createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, -10, -10));
        buttonPanel.add(createPlayerButton(1));
        StartResetButton startResetButton = new StartResetButton();
        startResetButton.addActionListener(new StartResetListener(this, statusModel));
        statusModel.registerListener(startResetButton);
        buttonPanel.add(startResetButton);
        buttonPanel.add(createPlayerButton(2));
        return buttonPanel;
    }

    private PlayerButton createPlayerButton(int playerNumber) {
        PlayerButton playerButton = new PlayerButton(playerNumber);
        playerButton.addActionListener(new PlayerButtonListener(playerNumber - 1, statusModel));
        statusModel.registerListener(playerButton);
        return playerButton;
    }

    private Component createStatusPanel() {
        JPanel statusPanel = new JPanel();
        StatusLabel statusLabel = new StatusLabel();
        statusModel.registerListener(statusLabel);
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
        board.reset();
        return board;
    }

    public void resetGame() {
        statusModel.reset();
        boardModel.reset();
        board.reset();
    }

    public void startGame() {
        board.setEnabled();
        statusModel.setStatus(Status.PROGRESS);
        if (statusModel.getPlayer(0) == Player.ROBOT) {
            robotPlayerMove();
        }
    }

    private void robotPlayerMove() {
        JButton button = board.getButton(Strategy.random(boardModel));
        new RobotMove(button, this).execute();
    }

    public void performMove(JButton button) {
        int move = statusModel.incrementAndGetMove();
        boardModel.setCellText(button, statusModel.getMove() % 2 == 1 ? 'X' : 'O');
        button.setText(boardModel.getCellText(button));
        statusModel.setStatus(boardModel.determineStatus(move));
        if (statusModel.getStatus() == Status.PROGRESS && statusModel.getPlayer(move % 2) == Player.ROBOT) {
            robotPlayerMove();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (statusModel.getPlayer(statusModel.getMove() % 2) != Player.ROBOT &&
                boardModel.getCellText(button).equals(" ") &&
                statusModel.getStatus() != Status.X_WINS &&
                statusModel.getStatus() != Status.O_WINS) {
            performMove(button);
        }
    }
}