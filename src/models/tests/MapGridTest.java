package models.tests;

import models.MapGrid;
import models.Point;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MapGridTest {

    @Test
    void getPath() {
        MapGrid mapGrid = new MapGrid();
        Point startPoint = new Point(0,0);
        Point endPoint = new Point(0, 3);

        Stack<Point> expected = new Stack<>();
        expected.push(new Point(0, 3));
        expected.push(new Point(0, 2));
        expected.push(new Point(0, 1));

        Stack<Point> actual = mapGrid.getPath(startPoint, endPoint);

        assertEquals(expected, actual);
    }

    @Test
    void isValidDrivingLocation() {
        MapGrid mapGrid = new MapGrid();

        assertTrue(mapGrid.isValidDrivingLocation(0, 0));
    }

    @Test
    void isValidDrivingLocationFalseCase() {
        MapGrid mapGrid = new MapGrid();

        assertFalse(mapGrid.isValidDrivingLocation(2, 2));
    }
}