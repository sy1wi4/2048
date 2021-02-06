package agh.cs.project2.gui;

import agh.cs.project2.game.Direction;
import agh.cs.project2.game.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener{
    private final GameEngine gameEngine = new GameEngine();


    public GamePanel() {
        setPreferredSize(new Dimension(400, 400));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(createLine(Box.createVerticalStrut(25)));
        JLabel jLabel = new JLabel("2048");
        jLabel.setForeground(new java.awt.Color(0xAF672B));
        jLabel.setFont(new java.awt.Font("MyFont", 1, 80));
        this.add(createLine(jLabel));
        this.add(createLine(Box.createVerticalStrut(50)));

        addKeyListener(this);
        setFocusable(true);

        // TODO: przycisk startu, score

        playGame();

//        addStartButton();
//        addEndButton();
    }

    private void playGame(){
        gameEngine.run();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(new Color(151, 219, 19));
//        g.fillRect(0,0,this.getWidth(), this.getHeight());
    }

    private JPanel createLine(Component c){
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                System.out.println("up");
                gameEngine.moveTiles(Direction.UP);
            }
            case KeyEvent.VK_DOWN -> {
                System.out.println("down");
                gameEngine.moveTiles(Direction.DOWN);
            }
            case KeyEvent.VK_LEFT -> {
                System.out.println("left");
                gameEngine.moveTiles(Direction.LEFT);
            }
            case KeyEvent.VK_RIGHT -> {
                System.out.println("right");
                gameEngine.moveTiles(Direction.RIGHT);
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
