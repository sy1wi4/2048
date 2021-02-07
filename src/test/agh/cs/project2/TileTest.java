package agh.cs.project2;

import agh.cs.project2.game.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    private final Tile tile1 = new Tile(1, 1);
    private final Tile tile2 = new Tile(1, 3);
    private final Tile tile3 = new Tile(0, 1);
    private final Tile tile4 = new Tile(1, 1);

    private final Color color2048 = new Color(89, 180, 79);
    private final Color color256 = new Color(187, 186, 62);
    private final Color color2 = new Color(234, 233, 222);

    @Test
    public void mergeTest() {
        tile1.updateValue(4);
        tile2.updateValue(4);
        Tile merged = tile1.mergeTiles(tile2, tile1.getX(), tile1.getY());
        Tile expected = new Tile(1, 1);
        expected.updateValue(8);
        assertEquals(expected, merged);

        tile3.updateValue(16);
        assertThrows(IllegalArgumentException.class,
                () -> tile1.mergeTiles(tile3, tile1.getX(), tile1.getY()));
    }

    @Test
    public void updateValueTest() {
        tile1.updateValue(1024);
        assertEquals(1024, tile1.getValue());
    }

    @Test
    public void getColorTest(){
        tile1.updateValue(2);
        assertEquals(color2, tile1.getColor());

        tile1.updateValue(256);
        assertEquals(color256, tile1.getColor());

        tile1.updateValue(2048);
        assertEquals(color2048, tile1.getColor());
    }


    @Test
    public void equalsTest() {
        tile1.updateValue(8);
        tile3.updateValue(16);
        tile4.updateValue(8);

        assertEquals(tile1, tile4);
        assertNotEquals(tile1, tile3);
    }

    @Test
    public void hashCodeTest() {
        tile1.updateValue(8);
        tile3.updateValue(16);
        tile4.updateValue(8);

        assertEquals(tile1.hashCode(), tile4.hashCode());
        assertNotEquals(tile1.hashCode(), tile3.hashCode());
    }
}
