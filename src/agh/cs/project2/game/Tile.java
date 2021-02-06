package agh.cs.project2.game;

import java.awt.*;

public class Tile {
    private int value;
    private int x;
    private int y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.value = 0;
    }

    public Tile (int x, int y, int value){
        this(x,y);
        this.updateValue(value);

    }

    public void updateValue(int newValue){
        this.value = newValue;
    }

    public int getValue() {
        return value;
    }

    public Tile mergeTiles(Tile this, Tile other, int x, int y){
        return new Tile(x,y,2 * this.value);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor(){
        Color color;
        switch (this.value){
            case 0 -> {
                color = new Color(180, 174, 167);
            }
            case 2 -> {
                color = new Color(234, 233, 222);
            }
            case 4 -> {
                color = new Color(212, 212, 192);
            }
            case 8 -> {
                color = new Color(217, 160, 112);
            }
            case 16 -> {
                color = new Color(206, 142, 83, 252);
            }
            case 32 -> {
                color = new Color(212, 99, 75);
            }
            case 64 -> {
                color = new Color(203, 78, 54);
            }
            case 128 -> {
                color = new Color(229, 228, 109);
            }
            case 256 -> {
                color = new Color(187, 186, 62);
            }
            case 512 -> {
                color = new Color(172, 170, 30);
            }
            case 1024 -> {
                color = new Color(141, 138, 25);
            }
            case 2048 -> {
                color = new Color(89, 180, 79);
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.value);
        }
        return color;
    }
}
