package backend.algorithm.tests;

import backend.algorithm.AssignmentGenerator;
import backend.algorithm.Assignment;
import backend.simulation.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentGeneratorTest {

    @Test
    void makePatientAssignment() {
        int[][] mapGrid = {
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 }
        };
        Map.Entry<Integer, Patient> patient = new AbstractMap.SimpleEntry<>(0, new Patient(new Point(3, 0), InjurySeverity.NON_LIFE_THREATENING));
        Map<Integer, Ambulance> ambulances = new LinkedHashMap<>();
        ambulances.put(1, new Ambulance(new Point(6, 6), 10));
        ambulances.put(2, new Ambulance(new Point(5, 0), 10));
        ambulances.put(3, new Ambulance(new Point(0, 6), 10));
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator(ambulances);

        Stack<Point> expectedPath = new Stack<>();
        expectedPath.push(new Point(3, 0));
        expectedPath.push(new Point(4, 0));
        expectedPath.push(new Point(5, 0));

        Assignment expected = new Assignment(2, 0, expectedPath);
        Assignment actual =  assignmentGenerator.makePatientAssignment(mapGrid, patient);
        assertEquals(expected, actual);
    }

    @Test
    void makeHospitalAssignment() {
        int[][] mapGrid = {
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 }
        };
        Map<Integer, Ambulance> availableAmbulances = new LinkedHashMap<>();
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator(availableAmbulances);

        Map<Integer, Hospital> hospitalDirectory = new LinkedHashMap<>();
        hospitalDirectory.put(0, new Hospital(new Point(0, 4)));
        hospitalDirectory.put(1, new Hospital(new Point(6, 6)));

        Map.Entry<Integer, Ambulance> ambulanceEntry = new AbstractMap.SimpleEntry<>(2, new Ambulance(new Point(0,0), 10));

        Stack<Point> expectedPath    = new Stack<>();
        expectedPath.push(new Point(0, 4));
        expectedPath.push(new Point(0, 3));
        expectedPath.push(new Point(0, 2));
        expectedPath.push(new Point(0, 1));
        expectedPath.push(new Point(0,0));

        Assignment expected = new Assignment(2,0, expectedPath);
        Assignment actual = assignmentGenerator.makeHospitalAssignment(mapGrid, ambulanceEntry, hospitalDirectory);
        assertEquals(expected, actual);
    }

    @Test
    void makeHomeBaseAssignment() {
        int[][] mapGrid = {
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1 }
        };
        Map.Entry<Integer, Ambulance> ambulanceEntry = new AbstractMap.SimpleEntry<>(0, new Ambulance(new Point(3, 0), 1));
        Map<Integer, HomeBase> homeBaseDirectory = new LinkedHashMap<>();
        homeBaseDirectory.put(1, new HomeBase(new Point(3, 3), 3));
        homeBaseDirectory.put(2, new HomeBase(new Point(1, 7), 3));
        Map<Integer, Ambulance> availableAmbulances = new LinkedHashMap<>();
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator(availableAmbulances);

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
    void getPath() {
        Map<Integer, Ambulance> availableAmbulances = new LinkedHashMap<>();
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator(availableAmbulances);
        int[][] mapGrid = {
                { 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 1, 1, 0, 1 },
                { 1, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 1, 1 },
                { 0, 0, 1, 1, 1, 1, 0 },
                { 0, 0, 1, 0, 0, 1, 0 },
                { 0, 0, 1, 1, 1, 1, 1 }
        };
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(mapGrid.length - 1, mapGrid[0].length - 1);

        Stack<Point> expected = new Stack<>();
        expected.push(new Point(6,6));
        expected.push(new Point(6,5));
        expected.push(new Point(5,5));
        expected.push(new Point(4,5));
        expected.push(new Point(3,5));
        expected.push(new Point(3,6));
        expected.push(new Point(2,6));
        expected.push(new Point(1,6));
        expected.push(new Point(0,6));
        expected.push(new Point(0,5));
        expected.push(new Point(0,4));
        expected.push(new Point(1,4));
        expected.push(new Point(1,3));
        expected.push(new Point(1,2));
        expected.push(new Point(0,2));
        expected.push(new Point(0,1));
        expected.push(new Point(0,0));

        Stack<Point> actual = assignmentGenerator.getPath(mapGrid, startPoint, endPoint);
        assertEquals(expected, actual);
    }

    @Test
    void getShortestDistance() {
        Map<Integer, Ambulance> availableAmbulances = new LinkedHashMap<>();
        AssignmentGenerator assignmentGenerator = new AssignmentGenerator(availableAmbulances);
        Point startPoint = new Point(5, 3);
        Point endPoint1 =  new Point(10, 10);
        Point endPoint2 = new Point( 9, 6);
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