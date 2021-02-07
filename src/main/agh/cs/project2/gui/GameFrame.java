package agh.cs.project2.gui;

import agh.cs.project2.game.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final GameEngine gameEngine = new GameEngine();

    public GameFrame() {
        super("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,800);
        setLocationRelativeTo(null);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        DataPanel dataPanel = new DataPanel(gameEngine);
        GamePanel gamePanel = new GamePanel(gameEngine, dataPanel);

        container.add(dataPanel);
        container.add(gamePanel);

        add(container);

        setVisible(true);
    }
}
