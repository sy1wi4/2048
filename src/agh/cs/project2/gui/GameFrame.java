package agh.cs.project2.gui;

import javax.swing.*;

public class GameFrame extends JFrame {


    public GameFrame() {
        super("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
        setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();

        add(gamePanel);
        setVisible(true);

    }
}
