package models.tests;

import models.MapGrid;
import models.Point;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MapGridTest {

    @Test
    void isValidDrivingLocation() {
        MapGrid mapGrid = new MapGrid();

        assertTrue(mapGrid.isValidDrivingLocation(new Point(0, 3)));
    }

    @Test
    void isValidDrivingLocationFalseCase() {
        MapGrid mapGrid = new MapGrid();

        assertFalse(mapGrid.isValidDrivingLocation(new Point(2, 2)));
    }

    @Test
    void getNeighbors() {
        MapGrid mapGrid = new MapGrid();
        Point test = new Point( 3,3);

        List<Point> expected = new LinkedList<>();
        expected.add(new Point(3, 4));
        expected.add(new Point(3, 2));
        expected.add(new Point(2, 3));
        expected.add(new Point(4, 3));

        List<Point> actual = mapGrid.getNeighbors(test);

        assertEquals(expected, actual);
    }
}