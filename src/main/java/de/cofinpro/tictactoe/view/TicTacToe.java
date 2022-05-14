package de.cofinpro.tictactoe.view;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    public TicTacToe() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setSize(450, 450);
        setLocationRelativeTo(null);
        decorateFrame();
        setVisible(true);
    }

    private void decorateFrame() {
        setLayout(new GridLayout(3,3));
    }
}
