package backend.ambulanceAssignmentGenerator.tests;

import backend.ambulanceAssignmentGenerator.Assignment;
import backend.mainController.Point;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    void getNextMovementPoint() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, actualPath);

        Point expected = new Point(2,2);
        Point actual = assignment.getNextMovementPoint();
        assertEquals(expected, actual);
    }

    @Test
    void getAmbulanceId() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, actualPath);

        int expected = 0;
        int actual = assignment.getAmbulanceId();
        assertEquals(expected, actual);
    }

    @Test
    void getDestinationId() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, actualPath);

        int expected = 1;
        int actual = assignment.getDestinationId();
        assertEquals(expected, actual);
    }

    @Test
    void getPath() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, actualPath);

        Stack<Point> expected = new Stack<>();
        expected.push(new Point(1,1));
        expected.push(new Point(2, 2));
        Stack<Point> actual = assignment.getPath();
        assertEquals(expected, actual);
    }

    @Test
    void equals() {
        Stack<Point> path1 = new Stack<>();
        path1.push(new Point(1,1));
        Stack<Point> path2 = new Stack<>();
        path2.push(new Point(1,1));
        Assignment assignment1 = new Assignment(0,1, path1);
        Assignment assignment2 = new Assignment(0, 1, path2);
        assertEquals(assignment1, assignment2);
    }
}