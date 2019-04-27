package ambulanceAssignmentGenerator;

import models.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentGeneratorTest {

    @Test
    void makePatientAssignment() {
        MapGrid mapGrid = new MapGrid();
        Map.Entry<Integer, Patient> patient = new AbstractMap.SimpleEntry<>(0, new Patient(new Point(3, 0), InjurySeverity.NON_LIFE_THREATENING));
        Map<Integer, Ambulance> ambulances = new LinkedHashMap<>();
        ambulances.put(1, new Ambulance(new Point(6, 6), 10));
        ambulances.put(2, new Ambulance(new Point(5, 0), 10));
        ambulances.put(3, new Ambulance(new Point(0, 6), 10));
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator();

        Stack<Point> expectedPath = new Stack<>();
        expectedPath.push(new Point(3, 0));
        expectedPath.push(new Point(4, 0));
        expectedPath.push(new Point(5, 0));

        Assignment expected = new Assignment(2, 0, expectedPath);
        Assignment actual = assignmentGenerator.makePatientAssignment(mapGrid, patient, ambulances);
        assertEquals(expected, actual);
    }

    @Test
    void makeHospitalAssignment() {
        MapGrid mapGrid = new MapGrid();
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator();

        Map<Integer, Hospital> hospitalDirectory = new LinkedHashMap<>();
        hospitalDirectory.put(0, new Hospital(new Point(0, 4)));
        hospitalDirectory.put(1, new Hospital(new Point(6, 6)));

        Map.Entry<Integer, Ambulance> ambulanceEntry = new AbstractMap.SimpleEntry<>(2, new Ambulance(new Point(0, 0), 10));

        Stack<Point> expectedPath = new Stack<>();
        expectedPath.push(new Point(0, 4));
        expectedPath.push(new Point(0, 3));
        expectedPath.push(new Point(0, 2));
        expectedPath.push(new Point(0, 1));
        expectedPath.push(new Point(0, 0));

        Assignment expected = new Assignment(2, 0, expectedPath);
        Assignment actual = assignmentGenerator.makeHospitalAssignment(mapGrid, ambulanceEntry, hospitalDirectory);
        assertEquals(expected, actual);
    }

    @Test
    void makeHomeBaseAssignment() {
        MapGrid mapGrid = new MapGrid();
        Map.Entry<Integer, Ambulance> ambulanceEntry = new AbstractMap.SimpleEntry<>(0, new Ambulance(new Point(3, 0), 1));
        Map<Integer, HomeBase> homeBaseDirectory = new LinkedHashMap<>();
        homeBaseDirectory.put(1, new HomeBase(new Point(3, 3), 3));
        homeBaseDirectory.put(2, new HomeBase(new Point(1, 7), 3));
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator();

        Stack<Point> expectedPath = new Stack<>();
        expectedPath.push(new Point(3, 3));
        expectedPath.push(new Point(3, 2));
        expectedPath.push(new Point(3, 1));
        expectedPath.push(new Point(3, 0));

        Assignment expected = new Assignment(0, 1, expectedPath);
        Assignment actual = assignmentGenerator.makeHomeBaseAssignment(mapGrid, ambulanceEntry, homeBaseDirectory);
        assertEquals(expected, actual);
    }

    @Test
    void getShortestDistance() {
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator();
        Point startPoint = new Point(5, 3);
        Point endPoint1 = new Point(10, 10);
        Point endPoint2 = new Point(9, 6);
        Point endPoint3 = new Point(11, 20);
        Map<Integer, Point> endPoints = new LinkedHashMap<>();
        endPoints.put(1, endPoint1);
        endPoints.put(2, endPoint2);
        endPoints.put(3, endPoint3);

        int expected = 2;
        int actual = assignmentGenerator.getShortestDistance(startPoint, endPoints);
        assertEquals(expected, actual);
    }
}