package backend.ambulanceAssignmentGenerator;

import backend.mainController.*;

import java.util.*;

public class AssignmentGenerator {
    public Assignment makePatientAssignment(MapGrid mapGrid, Map.Entry<Integer, Patient> patient, Map<Integer, Ambulance> availableAmbulanceDirectory) {
        Map<Integer, Point> availableAmbulanceLocations = new LinkedHashMap<>();
        Point patientLocation = patient.getValue().getLocation();
        for (Map.Entry<Integer, Ambulance> pair : availableAmbulanceDirectory.entrySet()) {
            int id = pair.getKey();
            Point ambulanceLocation = pair.getValue().getLocation();
            availableAmbulanceLocations.put(id, ambulanceLocation);
        }
        int ambulanceId = getShortestDistance(patientLocation, availableAmbulanceLocations);
        Point ambulanceLocation = availableAmbulanceDirectory.get(ambulanceId).getLocation();
        Stack<Point> path = mapGrid.getPath(ambulanceLocation, patientLocation);

       return new Assignment(ambulanceId, patient.getKey(), path);
    }

    public Assignment makeHospitalAssignment(MapGrid mapGrid, Map.Entry<Integer, Ambulance> ambulance, Map<Integer, Hospital> hospitalDirectory) {
        Map<Integer, Point> hospitalLocations = new LinkedHashMap<>();
        for (Map.Entry<Integer, Hospital> pair : hospitalDirectory.entrySet()) {
            int id = pair.getKey();
            Point hospitalLocation = pair.getValue().getLocation();
            hospitalLocations.put(id, hospitalLocation);
        }
        Point ambulanceLocation = ambulance.getValue().getLocation();
        int hospitalId = getShortestDistance(ambulanceLocation, hospitalLocations);
        Point hospitalLocation = hospitalDirectory.get(hospitalId).getLocation();
        Stack<Point> path = mapGrid.getPath(ambulanceLocation, hospitalLocation);

        return new Assignment(ambulance.getKey(), hospitalId, path);
    }

    public Assignment makeHomeBaseAssignment(MapGrid mapGrid, Map.Entry<Integer, Ambulance> ambulance, Map<Integer, HomeBase> homeBaseDirectory) {
        int homeBaseId =ambulance.getValue().getHomeBase();
        Point homeBaseLocation = homeBaseDirectory.get(homeBaseId).getLocation();
        Point ambulanceLocation = ambulance.getValue().getLocation();
        Stack<Point> path = mapGrid.getPath(ambulanceLocation, homeBaseLocation);

        return new Assignment(ambulance.getKey(), homeBaseId, path);
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
