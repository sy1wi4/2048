package agh.cs.project2.game;



import static agh.cs.project2.game.Direction.*;

public class GameEngine {
    private Board board;
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

        // TODO: dodaj 2 lub inną wartość (niewiększą niż max)
        Tile freeTile = board.getRandomFreeTile();
        if (freeTile == null) {
            System.out.println("Game over");
//            System.exit(0);
            return false;
        }
        freeTile.updateValue(2);
        return true;
    }

    // up - od 0 wiersza
    // down - od ostatniego wiersza
    private void moveVertical(Direction direction){
        int[] rows;
        if (direction == UP) rows = new int[]{0, 1, 2, 3};
        else rows = new int[]{3, 2, 1, 0};

        for (int col = 0; col < board.getSize(); col++){

            int toPlace = -1;  // najdalszy możliwy wiersz, w którym możemy umieścić płytkę
            Tile prev = null;  //poprzednia płytka w danej kolumnie
            boolean prevMerged = false;  // czy poprzednia była merge'owana - w tym wypadku kolejną tylko przesuwamy

            for (int row : rows){

                if (!board.isOccupied(row,col)){
                    if (toPlace == -1) toPlace = row;
                }
                else{
                    Tile tile = board.getTile(row, col);

                    // tylko przesuwamy (lub nie, jeśli nie ma gdzie)
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

                    // merge
                    else {
                        int val = tile.getValue();
                        board.placeTile(prev.mergeTiles(tile, prev.getX(),prev.getY()));
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

    // left - od 0 kolumny
    // right - od ostatniej kolumny
    private void moveHorizontal(Direction direction) {
        int[] cols;
        if (direction == LEFT) cols = new int[]{0, 1, 2, 3};
        else cols = new int[]{3, 2, 1, 0};

        for (int row = 0; row < board.getSize(); row++) {

            int toPlace = -1;  // najdalsza możliwa kolumna, w której możemy umieścić płytkę
            Tile prev = null;  //poprzednia płytka w danym wierszu
            boolean prevMerged = false;  // czy poprzednia była merge'owana   TO POTRZEBNE???  -- chyba tak

            for (int col : cols) {
//                System.out.print(row);
//                System.out.print(col + " ");

                if (!board.isOccupied(row, col)) {
//                    System.out.println("wolne");
                    if (toPlace == -1) toPlace = col;

                } else {

                    Tile tile = board.getTile(row, col);

                    // tylko przesuwamy (lub nie jak nie ma gdzie)
                    if (prevMerged || (prev == null || (prev.getValue() != tile.getValue()))) {
                        if (toPlace == -1) {
//                            System.out.println("nie ruszaj");
                            prev = board.getTile(row, col);
                        } else {
//                            System.out.println("przesun " + toPlace);
                            tile.setY(toPlace);
                            board.placeTile(tile);
                            board.removeTile(row, col);
                            if (direction == LEFT) toPlace += 1;
                            else toPlace -= 1;
                        }
                        prev = tile;
                        prevMerged = false;
                    }

                    // merge
                    else {
//                        System.out.println("merge " + prev.getX() + " " + prev.getY());
                        int val = tile.getValue();
                        board.placeTile(prev.mergeTiles(tile, prev.getX(), prev.getY()));
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
}
