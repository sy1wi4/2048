package agh.cs.project2.gui;

import agh.cs.project2.game.Direction;
import agh.cs.project2.game.GameEngine;
import agh.cs.project2.game.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private final GameEngine gameEngine;
    private JLabel[] tiles;
    private final DataPanel dataPanel;

    public GamePanel(GameEngine gameEngine, DataPanel dataPanel) {
        this.gameEngine = gameEngine;
        this.dataPanel = dataPanel;
        setLayout(new GridLayout(4, 4));
        addKeyListener(this);
        setFocusable(true);
        initialPaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean f = true;   // if flag is false, then game is over
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> f = gameEngine.moveTiles(Direction.UP);
            case KeyEvent.VK_DOWN -> f = gameEngine.moveTiles(Direction.DOWN);
            case KeyEvent.VK_LEFT -> f = gameEngine.moveTiles(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> f = gameEngine.moveTiles(Direction.RIGHT);
        }

        dataPanel.updateScore();

        if (gameEngine.gameWon())  {
            JOptionPane.showMessageDialog(null, "Congratulations, you won!");
            System.exit(0);
        }

        if (!f) {
            JOptionPane.showMessageDialog(null, "Game over, try again!");
            System.exit(0);
        }

        repaintBoard();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void initialPaint() {
        this.tiles = new JLabel[16];
        JLabel tile;
        Tile[][] board = gameEngine.getBoard();

        for (int i = 0; i < 16; i++) {
            int col = i % 4;
            int row = (i - col) / 4;
            if (board[row][col].getValue() == 0) {
                tiles[i] = new JLabel("", JLabel.CENTER);
            }
            else {
                tiles[i] = new JLabel(String.valueOf(board[row][col].getValue()), JLabel.CENTER);
            }
            tiles[i].setBackground(board[row][col].getColor());
            tile = tiles[i];
            tile.setOpaque(true);
            tile.setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, new Color(0x998E86)));
            tile.setFont(new java.awt.Font("Dialog", Font.BOLD, 60));
            if (board[row][col].getValue() == 0) tile.setBackground(board[row][col].getColor());
            this.add(tile);
        }
    }

    private void repaintBoard() {
        JLabel tile;
        int value;
        Tile[][] board = gameEngine.getBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                value = board[i][j].getValue();
                tile = tiles[4 * i + j];
                if (value == 0)
                    tile.setText("");
                else {
                    tile.setFont(new java.awt.Font("Dialog", Font.BOLD, 52));
                    tile.setText(String.valueOf(value));
                }
                tile.setBackground(board[i][j].getColor());
            }
        }
    }
}
