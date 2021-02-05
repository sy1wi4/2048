package agh.cs.project2;


import static agh.cs.project2.Direction.*;

public class Engine {
    private Board board;
    private int score;
    public Engine(){
        this.board = new Board();
        this.score = 0;


    }

    public void run(){
        board.printBoard();

        for (int i = 0; i < 8; i++){
            if (i%2 == 0) {
//                System.out.println("up");
//                moveTiles(UP);
//                board.printBoard();

                System.out.println("right");
                moveTiles(RIGHT);
            }
            else {
//                System.out.println("down");
//                moveTiles(DOWN);
//                board.printBoard();

                System.out.println("left");
                moveTiles(LEFT);
            }
            board.printBoard();
        }
    }


    private void moveTiles(Direction direction){
        switch (direction){
            case UP -> moveVertical(UP);
            case DOWN -> moveVertical(DOWN);
            case LEFT -> moveHorizontal(LEFT);
            case RIGHT -> moveHorizontal(RIGHT);
        }

        // TODO: dodaj 2 lub inną wartość (niewiększą niż max)
        Tile freeTile = board.getRandomFreeTile();
        freeTile.updateValue(2);

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
//            boolean prevMerged = false;  // czy poprzednia była merge'owana   TO POTRZEBNE???

            for (int row : rows){
//                System.out.print(row);
//                System.out.print(col + " ");

                if (!board.isOccupied(row,col)){
//                    System.out.println("wolne");
                    if (toPlace == -1) toPlace = row;

                }
                else{

                    Tile tile = board.getTile(row, col);

                    // tylko przesuwamy (lub nie jak nie ma gdzie)
                    if (prev == null || (prev.getValue() != tile.getValue())){
                        if (toPlace == -1) {
//                            System.out.println("nie ruszaj");
                            prev = board.getTile(row,col);
                        }
                        else {
//                            System.out.println("przesun " + toPlace);
                            tile.setX(toPlace);
                            board.placeTile(tile);
                            board.removeTile(row, col);
                            toPlace = -1;
                        }
                        prev = tile;
                    }

                    // merge
                    else {
//                        System.out.println("merge " + prev.getX() + " " + prev.getY());
                        int val = tile.getValue();
                        board.placeTile(prev.mergeTiles(tile, prev.getX(),prev.getY()));
                        board.removeTile(row,col);
                        toPlace = -1;
//                        prevMerged = true;
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
//            boolean prevMerged = false;  // czy poprzednia była merge'owana   TO POTRZEBNE???

            for (int col : cols) {
//                System.out.print(row);
//                System.out.print(col + " ");

                if (!board.isOccupied(row, col)) {
//                    System.out.println("wolne");
                    if (toPlace == -1) toPlace = col;

                } else {

                    Tile tile = board.getTile(row, col);

                    // tylko przesuwamy (lub nie jak nie ma gdzie)
                    if (prev == null || (prev.getValue() != tile.getValue())) {
                        if (toPlace == -1) {
//                            System.out.println("nie ruszaj");
                            prev = board.getTile(row, col);
                        } else {
//                            System.out.println("przesun " + toPlace);
                            tile.setY(toPlace);
                            board.placeTile(tile);
                            board.removeTile(row, col);
                            toPlace = -1;
                        }
                        prev = tile;
                    }

                    // merge
                    else {
//                        System.out.println("merge " + prev.getX() + " " + prev.getY());
                        int val = tile.getValue();
                        board.placeTile(prev.mergeTiles(tile, prev.getX(), prev.getY()));
                        board.removeTile(row, col);
                        toPlace = -1;
//                        prevMerged = true;
                        score += 2 * val;
                    }

                }
            }
        }
    }

    public int getScore() {
        return score;
    }
}
