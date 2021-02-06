package agh.cs.project2.gui;

import agh.cs.project2.game.Direction;
import agh.cs.project2.game.GameEngine;
import agh.cs.project2.game.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private final GameEngine gameEngine = new GameEngine();
    private JLabel[] tiles;

    public GamePanel() {
//        setPreferredSize(new Dimension(400, 400));
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(createLine(Box.createVerticalStrut(25)));
//        JLabel jLabel = new JLabel("2048");
//        jLabel.setForeground(new java.awt.Color(0xAF672B));
//        jLabel.setFont(new java.awt.Font("MyFont", 1, 80));
//        this.add(createLine(jLabel));
//        this.add(createLine(Box.createVerticalStrut(50)));

        setLayout(new GridLayout(4, 4));
//        getContentPane().setBackground(new java.awt.Color(0xCDC1B4));


        addKeyListener(this);
        setFocusable(true);

        // TODO: przycisk startu, score

        initialPaint();



//        addStartButton();
//        addEndButton();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(new Color(151, 219, 19));
//        g.fillRect(0,0,this.getWidth(), this.getHeight());
    }

    private JPanel createLine(Component c) {
        JPanel jp = new JPanel();

        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        jp.add(Box.createHorizontalGlue());
        jp.add(c);
        jp.add(Box.createHorizontalGlue());
        jp.setOpaque(false);

        return jp;
    }

//    private void addStartButton(){
//        JButton startButton = new JButton("Start game");
//        startButton.addActionListener(e -> {
//            GameEngine engine = new GameEngine();
//            engine.run();
//        });
//        this.add(createLine(startButton));
//    }
//
//    private void addEndButton(){
//        JButton endButton = new JButton("End game");
//        endButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//        this.add(createLine(endButton));
//
//    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean f = true;   // if flag is false, then game is over
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                f = gameEngine.moveTiles(Direction.UP);
            }
            case KeyEvent.VK_DOWN -> {
                f = gameEngine.moveTiles(Direction.DOWN);
            }
            case KeyEvent.VK_LEFT -> {
                f = gameEngine.moveTiles(Direction.LEFT);
            }
            case KeyEvent.VK_RIGHT -> {
                f = gameEngine.moveTiles(Direction.RIGHT);
            }

        }
        if (!f) {
            JOptionPane.showMessageDialog(null, "Game over, try again!");
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
                tiles[i].setBackground(board[row][col].getColor());
            }
            else {
                tiles[i] = new JLabel(String.valueOf(board[row][col].getValue()), JLabel.CENTER);
                tiles[i].setBackground(board[row][col].getColor());
            }
            tile = tiles[i];
            tile.setOpaque(true);
            tile.setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, new Color(0x998E86)));
            tile.setFont(new java.awt.Font("Dialog", 1, 60));
            if (board[row][col].getValue() == 0) tile.setBackground(board[row][col].getColor());
            this.add(tile);
        }
//        this.setVisible(true);
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
                    tile.setFont(new java.awt.Font("Dialog", 1, 52));
                    tile.setText(String.valueOf(value));
                }
                tile.setBackground(board[i][j].getColor());
            }
        }
    }
}
