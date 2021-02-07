package agh.cs.project2.game;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Integer.max;

public class Board {
    private int size;
    private final Tile[][] board;
    Random rand = new Random();
    private int maxTileValue;

    // default board 4x4
    public Board(){
        this.board = new Tile[4][4];
        this.size = 4;
        this.maxTileValue = 0;
        initializeBoard();
        placeInitialTiles();
    }

    public Board(int size){
        this.size = size;
        this.board = new Tile[size][size];
    }

    private void initializeBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = new Tile(i,j);
            }
        }
    }

    public Tile getTile(int x, int y){
        return board[x][y];
    }

    private void placeInitialTiles(){
        Tile freeTile1 = getRandomFreeTile();
        freeTile1.updateValue(2);

        Tile freeTile2 = getRandomFreeTile();
        freeTile2.updateValue(2);

        updateMaxTileValue(2);
    }

    private LinkedList<Tile> getFreeTiles(){
        LinkedList<Tile> freeTiles = new LinkedList<>();
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (board[i][j].getValue() == 0){
                    freeTiles.add(getTile(i,j));
                }
            }
        }
        return freeTiles;
    }

    public Tile getRandomFreeTile(){
        LinkedList<Tile> tiles = getFreeTiles();
        if (tiles.size() == 0) return null;

        int idx = rand.nextInt(tiles.size());
        return tiles.get(idx);
    }

    public void placeTile(Tile tile){
        board[tile.getX()][tile.getY()] = tile;
    }

    public void removeTile(int x, int y){
        board[x][y] = new Tile(x,y,0);
    }

    public int getSize() {
        return size;
    }


    public boolean isOccupied(int x, int y){
        return getTile(x,y).getValue() != 0;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void updateMaxTileValue(int value) {
        this.maxTileValue = max(this.maxTileValue,value);
    }

    public int getMaxTileValue() {
        return maxTileValue;
    }
}
