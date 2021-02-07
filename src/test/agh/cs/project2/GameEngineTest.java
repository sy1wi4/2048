package agh.cs.project2;

import agh.cs.project2.game.Direction;
import agh.cs.project2.game.GameEngine;
import agh.cs.project2.game.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {
    private final GameEngine gameEngine = new GameEngine();

    @Test
    public void moveTileTest(){
        Tile[][] b = gameEngine.getBoard();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                b[i][j].updateValue(0);
            }
        }
        gameEngine.placeTile(new Tile(1,1,2));
        gameEngine.placeTile(new Tile(1,2,2));
        gameEngine.placeTile(new Tile(1,3,2));

        gameEngine.moveTiles(Direction.LEFT);
        assertEquals(new Tile(1,0,4), b[1][0]);
        assertEquals(new Tile(1,1,2), b[1][1]);
        assertEquals(new Tile(1,2,0), b[1][2]);

        Tile tile1 = new Tile(0,3,512);
        Tile tile2 = new Tile(1,3,16);
        Tile tile3 = new Tile(2,3,1024);

        gameEngine.placeTile(tile1);
        gameEngine.placeTile(tile2);
        gameEngine.placeTile(tile3);
        gameEngine.moveTiles(Direction.UP);

        assertEquals(tile1, b[0][3]);
        assertEquals(tile2, b[1][3]);
        assertEquals(tile3, b[2][3]);
    }

    @Test
    public void addRandomTileOnBoardTest(){
        gameEngine.addRandomTileOnBoard();
        Tile[][] b = gameEngine.getBoard();
        int nonzeroTile = 0;

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (b[i][j].getValue() == 2 || b[i][j].getValue() == 4)
                    nonzeroTile++;
            }
        }

        assertEquals(3, nonzeroTile);
    }

    @Test
    public void gameWonTest(){
        assertFalse(gameEngine.gameWon());
        Tile tile = new Tile(1,2,1024);
        gameEngine.placeTile(tile);
        assertFalse(gameEngine.gameWon());

        Tile tile2 = new Tile(3,2,2048);
        gameEngine.placeTile(tile2);
        assertTrue(gameEngine.gameWon());
    }
}
