package agh.cs.project2.gui;

import agh.cs.project2.game.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {


    public GameFrame() {
        super("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
        setLocationRelativeTo(null);  // after pack()
        setLayout(new GridLayout(4,4));
        getContentPane().setBackground(new java.awt.Color(0xCDC1B4));
        GamePanel gamePanel = new GamePanel();

        add(gamePanel);
        setVisible(true);

//        pack();


    }
}
