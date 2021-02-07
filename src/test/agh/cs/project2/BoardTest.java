package agh.cs.project2;

import agh.cs.project2.game.Board;
import agh.cs.project2.game.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board = new Board();

    @Test
    public void initialBoardTest(){
        Tile[][] b = board.getBoard();
        int twoTiles = 0;
        int zeroTiles = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (b[i][j].getValue() == 0) zeroTiles++;
                else if (b[i][j].getValue() == 2) twoTiles++;
            }
        }
        assertEquals(14, zeroTiles);
        assertEquals(2, twoTiles);
    }

    @Test
    public void getFreeTileTest(){
        Tile[][] b = board.getBoard();
        assertNotNull(board.getRandomFreeTile());

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                b[i][j].updateValue(4);
            }
        }

        assertNull(board.getRandomFreeTile());
    }

    @Test
    public void placeTileTest(){
        Tile toPlace = new Tile(1,1,2048);
        board.placeTile(toPlace);

        Tile[][] b = board.getBoard();
        assertEquals(b[1][1], toPlace);
    }

    @Test
    public void removeTileTest(){
        Tile toPlace = new Tile(1,1,2048);
        board.placeTile(toPlace);

        board.removeTile(1,1);
        Tile[][] b = board.getBoard();

        assertEquals(0, b[1][1].getValue());
    }

    @Test
    public void isOccupiedTest(){
        board.removeTile(1,1);
        assertFalse(board.isOccupied(1,1));

        board.placeTile(new Tile(3,2,8));
        assertTrue(board.isOccupied(3,2));
    }

    @Test
    public void maxTileValueTest(){
        Board board2 = new Board();
        board2.updateMaxTileValue(128);
        assertEquals(128, board2.getMaxTileValue());

        board2.updateMaxTileValue(16);
        assertEquals(128, board2.getMaxTileValue());
    }
}
