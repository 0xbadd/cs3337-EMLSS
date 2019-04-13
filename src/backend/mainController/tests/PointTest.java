package backend.mainController.tests;

import backend.mainController.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void getX() {
        Point point = new Point(0, 1);
        int expected = 0;
        int actual = point.getX();
        assertEquals(expected, actual);
    }

    @Test
    void getY() {
        Point point = new Point(0, 1);
        int expected = 1;
        int actual = point.getY();
        assertEquals(expected, actual);
    }

    @Test
    void distanceTo() {
        Point point = new Point(5, 3);
        double expected = 5;
        double actual = point.distanceTo(new Point(9, 6));
        assertEquals(expected, actual);
    }

    @Test
    void equals() {
        Point point1 = new Point(0, 1);
        Point point2 = new Point(0, 1);
        assertEquals(point1, point2);
    }

    @Test
    void equalsFalseCase() {
        Point point1 = new Point(0, 1);
        Point point2 = new Point(3, 3);
        assertNotEquals(point1, point2);
    }
}