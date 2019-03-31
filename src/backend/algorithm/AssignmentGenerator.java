package backend.algorithm;

import backend.simulation.*;

import java.util.*;

public class AssignmentGenerator {
    private Map<Integer, Ambulance> availableAmbulances;

    public AssignmentGenerator(Map<Integer, Ambulance> ambulances) {
        this.availableAmbulances = ambulances;
    }

    public Assignment makePatientAssignment(int[][] mapGrid, Map.Entry<Integer, Patient> patient) {
        Map<Integer, Point> availableAmbulanceLocations = new LinkedHashMap<>();
        Point patientLocation = patient.getValue().getLocation();
        for (Map.Entry<Integer, Ambulance> pair : availableAmbulances.entrySet()) {
            int id = pair.getKey();
            Point ambulanceLocation = pair.getValue().getLocation();
            availableAmbulanceLocations.put(id, ambulanceLocation);
        }
        int ambulanceId = getShortestDistance(patientLocation, availableAmbulanceLocations);
        Point ambulanceLocation = availableAmbulances.get(ambulanceId).getLocation();
        Stack<Point> path = getPath(mapGrid, ambulanceLocation, patientLocation);

        //availableAmbulances.remove(ambulanceId);

       return new Assignment(ambulanceId, patient.getKey(), path);
    }

    public Assignment makeHospitalAssignment(int[][] mapGrid, Map.Entry<Integer, Ambulance> ambulance, Map<Integer, Hospital> hospitalDirectory) {
        Map<Integer, Point> hospitalLocations = new LinkedHashMap<>();
        for (Map.Entry<Integer, Hospital> pair : hospitalDirectory.entrySet()) {
            int id = pair.getKey();
            Point hospitalLocation = pair.getValue().getLocation();
            hospitalLocations.put(id, hospitalLocation);
        }
        Point ambulanceLocation = ambulance.getValue().getLocation();
        int hospitalId = getShortestDistance(ambulanceLocation, hospitalLocations);
        Point hospitalLocation = hospitalDirectory.get(hospitalId).getLocation();
        Stack<Point> path = getPath(mapGrid, ambulanceLocation, hospitalLocation);

        return new Assignment(ambulance.getKey(), hospitalId, path);
    }

    public Assignment makeHomeBaseAssignment(int[][] mapGrid, Map.Entry<Integer, Ambulance> ambulance, Map<Integer, HomeBase> homeBaseDirectory) {
        int homeBaseId =ambulance.getValue().getHomeBase();
        Point homeBaseLocation = homeBaseDirectory.get(homeBaseId).getLocation();
        Point ambulanceLocation = ambulance.getValue().getLocation();
        Stack<Point> path = getPath(mapGrid, ambulanceLocation, homeBaseLocation);

        return new Assignment(ambulance.getKey(), homeBaseId, path);
    }

    public Stack<Point> getPath(int[][] mapGrid, Point startPoint, Point endPoint) {
        Stack<Point> path = new Stack<>();
        SearchMap map = new SearchMap(mapGrid);
        Queue<Cell> queue = new LinkedList<>();

        boolean done = false;
        queue.add(new Cell(startPoint.getX(), startPoint.getY(), null));
        // breadth first search
        while (!queue.isEmpty()) {
            Cell c = queue.poll();

            if (c.row == endPoint.getX() && c.col == endPoint.getY()) {
                done = true;
            }

            // Right
            if (map.isValid(c.row + 1, c.col)) {
                map.setCell(c.row, c.col, 3);
                Cell nextCell = new Cell(c.row + 1, c.col, c);
                queue.add(nextCell);
            }

            // Left
            if (map.isValid(c.row - 1, c.col)) {
                map.setCell(c.row, c.col, 3);
                Cell nextCell = new Cell(c.row - 1, c.col, c);
                queue.add(nextCell);
            }

            // Up
            if (map.isValid(c.row, c.col + 1)) {
                map.setCell(c.row, c.col, 3);
                Cell nextCell = new Cell(c.row, c.col + 1, c);
                queue.add(nextCell);
            }

            // Down
            if (map.isValid(c.row, c.col - 1)) {
                map.setCell(c.row, c.col, 3);
                Cell nextCell = new Cell(c.row, c.col - 1, c);
                queue.add(nextCell);
            }

            if (done) {
                path.push(new Point(c.row, c.col));
                while (c.getParent() != null) {
                    c = c.getParent();
                    path.push(new Point(c.row, c.col));
                }
                break;
            }
        }

        return path;
    }

    public int getShortestDistance(Point startPoint, Map<Integer, Point> endPoints) {
        Map<Double, Integer> distances = new LinkedHashMap<>();
        LinkedList<Double> distancesList = new LinkedList<>();
        for (Map.Entry<Integer, Point> pair : endPoints.entrySet()) {
            int id = pair.getKey();
            Point endPoint = pair.getValue();
            double distance = startPoint.distanceTo(endPoint);
            distances.put(distance, id);
            distancesList.add(distance);
        }
        Comparator<Double> comp = (Double a, Double b) -> {
          if (a < b) {
              return -1;
          } else if (a > b) {
              return 1;
          } else {
              return 0;
          }
        };
        distancesList.sort(comp);

        double shortestDistance = distancesList.getFirst();

        return distances.get(shortestDistance);
    }
}
