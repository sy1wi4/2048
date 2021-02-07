package agh.cs.project2.gui;

import agh.cs.project2.game.GameEngine;
import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private final GameEngine gameEngine;
    private final JLabel score;

    public DataPanel(GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.score = new JLabel("   Score: " + String.valueOf(gameEngine.getScore()));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(createLine(Box.createVerticalStrut(20)));
        JLabel jLabel = new JLabel(" 2048");
        jLabel.setForeground(new java.awt.Color(0xAF672B));
        jLabel.setFont(new java.awt.Font("MyFont", 1, 60));

        this.add(createLine(jLabel));
        this.add(createLine(Box.createVerticalStrut(30)));
        placeScore();
    }

    private JPanel createLine(Component c) {
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        jp.add(Box.createHorizontalBox());
        jp.add(c);
        jp.add(Box.createHorizontalGlue());
        jp.setOpaque(false);
        return jp;
    }

    private void placeScore(){
        score.setForeground(new java.awt.Color(0x4E3520));
        score.setFont(new java.awt.Font("MyFont", 1, 24));
        this.add(createLine(score));
    }

    public void updateScore(){
        score.setText(String.valueOf("   Score: " + gameEngine.getScore()));
    }

}
