package agh.cs.project2.game;



import static agh.cs.project2.game.Direction.*;

public class GameEngine {
    private final Board board;
    private int score;

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

        System.out.println(score);
        return addRandomTileOnBoard();
    }

    private boolean addRandomTileOnBoard(){
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
        int[] rows;
        if (direction == UP) rows = new int[]{0, 1, 2, 3};
        else rows = new int[]{3, 2, 1, 0};

        for (int col = 0; col < board.getSize(); col++){

            int toPlace = -1;  // farthest row, where tile can be placed in given column
            Tile prev = null;  // previously considered tile in given column
            boolean prevMerged = false;  // whether prev tile is merged tile - then next tile cannot be merged with it, just moved

            for (int row : rows){
                if (!board.isOccupied(row,col)){
                    if (toPlace == -1) toPlace = row;
                }
                else{
                    Tile tile = board.getTile(row, col);

                    // move tile to first possible place (or not if there is no place)
                    if (prevMerged || prev == null || (prev.getValue() != tile.getValue())){
                        if (toPlace == -1) {
                            prev = board.getTile(row,col);
                        }
                        else {
                            tile.setX(toPlace);
                            board.placeTile(tile);
                            board.removeTile(row, col);

                            if (direction == UP) toPlace += 1;
                            else toPlace -= 1;
                        }
                        prev = tile;
                    }

                    // merge current tile with previous
                    else {
                        int val = tile.getValue();
                        Tile mergedTile = prev.mergeTiles(tile, prev.getX(),prev.getY());
                        board.updateMaxTileValue(mergedTile.getValue());
                        board.placeTile(mergedTile);
                        board.removeTile(row,col);

                        if (direction == UP) toPlace = prev.getX() + 1;
                        else toPlace = prev.getX() - 1;

                        prevMerged = true;
                        score += 2 * val;
                    }
                }
            }
        }
    }


    private void moveHorizontal(Direction direction) {
        int[] cols;
        if (direction == LEFT) cols = new int[]{0, 1, 2, 3};
        else cols = new int[]{3, 2, 1, 0};

        for (int row = 0; row < board.getSize(); row++) {

            int toPlace = -1;
            Tile prev = null;
            boolean prevMerged = false;

            for (int col : cols) {
                if (!board.isOccupied(row, col)) {
                    if (toPlace == -1) toPlace = col;

                } else {

                    Tile tile = board.getTile(row, col);

                    if (prevMerged || (prev == null || (prev.getValue() != tile.getValue()))) {
                        if (toPlace == -1) {
                            prev = board.getTile(row, col);
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
                        int val = tile.getValue();
                        Tile mergedTile = prev.mergeTiles(tile, prev.getX(),prev.getY());
                        board.updateMaxTileValue(mergedTile.getValue());

                        board.placeTile(mergedTile);
                        board.removeTile(row, col);
                        if (direction == LEFT) toPlace = prev.getY() + 1;
                        else toPlace = prev.getY() - 1;
                        prevMerged = true;
                        score += 2 * val;
                    }

                }
            }
        }
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
}
