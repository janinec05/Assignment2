import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testEquality() {
        Coordinate c1 = new Coordinate(2, 3);
        Coordinate c2 = new Coordinate(2, 3);

        assertEquals(c1, c2, "Coordinates with the same x and y should be equal.");
    }

    @Test
    void testInequality() {
        Coordinate c1 = new Coordinate(2, 3);
        Coordinate c2 = new Coordinate(3, 2);

        assertNotEquals(c1, c2, "Coordinates with different x or y should not be equal.");
    }

    @Test
    void testEdgeCases() {
        Coordinate edgeCase1 = new Coordinate(0, 0);
        Coordinate edgeCase2 = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);

        assertEquals(0, edgeCase1.getX(), "Edge case with x=0 should match.");
        assertEquals(0, edgeCase1.getY(), "Edge case with y=0 should match.");
        assertEquals(Integer.MAX_VALUE, edgeCase2.getX(), "Edge case for maximum x value should match.");
        assertEquals(Integer.MAX_VALUE, edgeCase2.getY(), "Edge case for maximum y value should match.");
    }

    @Test
    void testMatchingCoordinates() {
        Coordinate c1 = new Coordinate(5, 5);
        Coordinate c2 = new Coordinate(5, 5);

        assertTrue(c1.equals(c2), "Coordinates with the same x and y should match.");
    }
}
