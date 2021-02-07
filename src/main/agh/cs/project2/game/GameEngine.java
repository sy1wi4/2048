package agh.cs.project2.game;

import static agh.cs.project2.game.Direction.*;

public class GameEngine {
    private final Board board;
    private int score;
    private int toPlace;            // farthest row, where tile can be placed in given column
    private Tile prev;              // previously considered tile in given column
    private boolean prevMerged;     // whether prev tile is merged tile - then next tile cannot be merged with it, just moved

    public GameEngine(){
        this.board = new Board();
        this.score = 0;
    }

    public boolean moveTiles(Direction direction){
        switch (direction){
            case UP -> moveVertical(UP);
            case DOWN -> moveVertical(DOWN);
            case LEFT -> moveHorizontal(LEFT);
            case RIGHT -> moveHorizontal(RIGHT);
        }

        return addRandomTileOnBoard();
    }

    public boolean addRandomTileOnBoard(){
        Tile freeTile = board.getRandomFreeTile();

        if (freeTile == null) {
//            System.out.println("Game over");
            return false;
        }

        int value;
        if (board.getMaxTileValue() > 2)
            value = Math.random() > 0.2 ? 2 : 4;
        else value = 2;
        freeTile.updateValue(value);
        return true;
    }


    private void moveVertical(Direction direction){
        int[] rows = getLineOrder(direction);

        for (int col = 0; col < board.getSize(); col++){
            nextLine();
            for (int row : rows){
                if (!board.isOccupied(row,col)){
                    if (toPlace == -1) toPlace = row;
                }
                else{
                    Tile tile = board.getTile(row, col);

                    // move tile to first possible place (or not if there is no place)
                    if (prevMerged || prev == null || (prev.getValue() != tile.getValue())){
                        if (toPlace == -1) {
                        }
                        else {
                            tile.setX(toPlace);
                            board.placeTile(tile);
                            board.removeTile(row, col);

                            if (direction == UP) toPlace += 1;
                            else toPlace -= 1;
                        }
                        prev = tile;
                        prevMerged = false;
                    }

                    // merge current tile with previous
                    else {
                        mergeAdjacentTiles(prev, tile, row, col);
                        if (direction == UP) toPlace = prev.getX() + 1;
                        else toPlace = prev.getX() - 1;
                        prevMerged = true;
                        updateScoreAfterMerge(2 * tile.getValue());
                    }
                }
            }
        }
    }


    private void moveHorizontal(Direction direction) {
        int[] cols = getLineOrder(direction);

        for (int row = 0; row < board.getSize(); row++) {
            nextLine();

            for (int col : cols) {
                if (!board.isOccupied(row, col)) {
                    if (toPlace == -1) toPlace = col;
                } else {
                    Tile tile = board.getTile(row, col);

                    if (prevMerged || prev == null || (prev.getValue() != tile.getValue())) {
                        if (toPlace == -1) {
                        } else {
                            tile.setY(toPlace);
                            board.placeTile(tile);
                            board.removeTile(row, col);
                            if (direction == LEFT) toPlace += 1;
                            else toPlace -= 1;
                        }
                        prev = tile;
                        prevMerged = false;
                    }

                    else {
                        mergeAdjacentTiles(prev, tile, row, col);
                        if (direction == LEFT) toPlace = prev.getY() + 1;
                        else toPlace = prev.getY() - 1;
                        prevMerged = true;
                        updateScoreAfterMerge(2 * tile.getValue());
                    }

                }
            }
        }
    }


    // Direction: UP, RIGHT, LEFT, DOWN
    // UP or LEFT - go from 0 row/col
    // DOWN or RIGHT - go from last row/col

    private int[] getLineOrder(Direction direction){
        if (direction == UP || direction == LEFT){
            return new int[]{0, 1, 2, 3};
        }
        else {
            return new int[]{3,2,1,0};
        }
    }

    private void updateScoreAfterMerge(int newScore){
        this.score += newScore;
    }

    // reset
    private void nextLine(){
        this.toPlace = -1;
        this.prev = null;
        this.prevMerged = false;
    }

    private void mergeAdjacentTiles(Tile prev, Tile current, int row,int col){
        Tile mergedTile = prev.mergeTiles(current, prev.getX(),prev.getY());
        board.updateMaxTileValue(mergedTile.getValue());
        board.placeTile(mergedTile);
        board.removeTile(row, col);
    }

    public int getScore() {
        return score;
    }

    public Tile[][] getBoard() {
        return board.getBoard();
    }

    public boolean gameWon(){
        return (board.getMaxTileValue() == 2048);
    }

    public void placeTile(Tile tile){
        board.placeTile(tile);
    }
}
