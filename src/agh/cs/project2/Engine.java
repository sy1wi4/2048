package agh.cs.project2;


public class Engine {
    private Board board;
    private int score;
    public Engine(){
        this.board = new Board();
        this.score = 0;


    }

    public void run(){
        board.printBoard();

        for (int i = 0; i < 5; i++){
            moveTiles(Direction.UP);
            board.printBoard();
        }
    }


    private void moveTiles(Direction direction){
        switch (direction){
            case UP -> moveUp();
        }

        // TODO: dodaj 2 lub inną wartość (niewiększą niż max)
        Tile freeTile = board.getRandomFreeTile();
        freeTile.updateValue(2);

    }

    private void moveUp(){

        for (int col = 0; col < board.getSize(); col++){

            int toPlace = -1;  // najdalszy możliwy wiersz, w którym możemy umieścić płytkę
            Tile prev = null;  //poprzednia płytka w danej kolumnie
            boolean prevMerged = false;  // czy poprzednia była merge'owana

            for (int row = 0; row < board.getSize() ; row++){
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
                        prevMerged = true;
                        score += 2 * val;
                    }

                }
            }
        }

    }

}
