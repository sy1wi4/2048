package agh.cs.project2;


import agh.cs.project2.game.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    private final Point p1 = new Point(1,2);
    private final Point p2 = new Point(12,3);
    private final Point p3 = new Point(1,2);

    @Test
    public void equalsTest(){
        assertEquals(p1, p3);
        assertNotEquals(p1, p2);
    }

    @Test
    public void hashCodeTest(){
        assertEquals(p1.hashCode(), p3.hashCode());
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }

}
