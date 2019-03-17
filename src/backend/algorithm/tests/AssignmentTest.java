package backend.algorithm.tests;

import backend.algorithm.Assignment;
import backend.simulation.Point;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    void getNextMovementPoint() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, 2, actualPath);

        Point expected = new Point(2,2);
        Point actual = assignment.getNextMovementPoint();
        assertEquals(expected, actual);
    }

    @Test
    void getAmbulanceId() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, 2, actualPath);

        int expected = 0;
        int actual = assignment.getAmbulanceId();
        assertEquals(expected, actual);
    }

    @Test
    void getPatientId() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, 2, actualPath);

        int expected = 1;
        int actual = assignment.getPatientId();
        assertEquals(expected, actual);
    }

    @Test
    void getHospitalId() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, 2, actualPath);

        int expected = 2;
        int actual = assignment.getHospitalId();
        assertEquals(expected, actual);
    }

    @Test
    void getPath() {
        Stack<Point> actualPath = new Stack<>();
        actualPath.push(new Point(1,1));
        actualPath.push(new Point(2, 2));
        Assignment assignment = new Assignment(0, 1, 2, actualPath);

        Stack<Point> expected = new Stack<>();
        expected.push(new Point(1,1));
        expected.push(new Point(2, 2));
        Stack<Point> actual = assignment.getPath();
        assertEquals(expected, actual);
    }
}