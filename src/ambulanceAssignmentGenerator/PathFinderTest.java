package ambulanceAssignmentGenerator;

import models.MapGrid;
import models.Point;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    @Test
    void getPath() {
        MapGrid mapGrid = new MapGrid();
        Point start = new Point(0,0);
        Point end = new Point(0, 3);

        Stack<Point> expected = new Stack<>();
        expected.push(new Point(0, 3));
        expected.push(new Point(0, 2));
        expected.push(new Point(0, 1));

        Stack<Point> actual = PathFinder.getPath(mapGrid, start, end);

        assertEquals(expected, actual);
    }
}