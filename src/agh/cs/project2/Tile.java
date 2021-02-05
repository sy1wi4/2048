package agh.cs.project2;

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
}
