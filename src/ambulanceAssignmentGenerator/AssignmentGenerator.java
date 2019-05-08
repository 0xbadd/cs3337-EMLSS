package ambulanceAssignmentGenerator;

import mainController.Logger;
import mainController.PatientEntry;
import models.*;

import java.util.*;

public class AssignmentGenerator {
    public Assignment makePatientAssignment(
            MapGrid mapGrid, PatientEntry patientEntry, Map<Integer, Ambulance> availableAmbulanceDirectory
    ) {
        Map<Integer, Point> availableAmbulanceLocations = new LinkedHashMap<>();
        Point patientLocation = patientEntry.getValue().getLocation();
        for (Map.Entry<Integer, Ambulance> pair : availableAmbulanceDirectory.entrySet()) {
            int id = pair.getKey();
            Point ambulanceLocation = pair.getValue().getLocation();
            availableAmbulanceLocations.put(id, ambulanceLocation);
        }

        int ambulanceID = getShortestDistance(patientLocation, availableAmbulanceLocations);
        String ambulanceName = availableAmbulanceDirectory.get(ambulanceID).getName();
        Point ambulanceLocation = availableAmbulanceDirectory.get(ambulanceID).getLocation();
        Stack<Point> path = PathFinder.getPath(mapGrid, ambulanceLocation, patientLocation);
        String patientName = patientEntry.getValue().getName();

        Logger.log("PICKUP\t" + ambulanceName + "\t" + patientEntry.getKey());

        return new Assignment(ambulanceName, ambulanceID, patientName, patientEntry.getKey(), path);
    }

    public Assignment makeHospitalAssignment(
            MapGrid mapGrid, Map.Entry<Integer, Ambulance> ambulanceEntry, Map<Integer, Hospital> hospitalDirectory
    ) {
        Map<Integer, Point> hospitalLocations = new LinkedHashMap<>();
        for (Map.Entry<Integer, Hospital> pair : hospitalDirectory.entrySet()) {
            int id = pair.getKey();
            Point hospitalLocation = pair.getValue().getLocation();
            hospitalLocations.put(id, hospitalLocation);
        }

        String ambulanceName = ambulanceEntry.getValue().getName();
        Point ambulanceLocation = ambulanceEntry.getValue().getLocation();
        int hospitalID = getShortestDistance(ambulanceLocation, hospitalLocations);
        String hospitalName = hospitalDirectory.get(hospitalID).getName();
        Point hospitalLocation = hospitalDirectory.get(hospitalID).getLocation();
        Stack<Point> path = PathFinder.getPath(mapGrid, ambulanceLocation, hospitalLocation);

        return new Assignment(ambulanceName, ambulanceEntry.getKey(), hospitalName, hospitalID, path);
    }

    public Assignment makeHomeBaseAssignment(
            MapGrid mapGrid, Map.Entry<Integer, Ambulance> ambulanceEntry, Map<Integer, HomeBase> homeBaseDirectory
    ) {
        String ambulanceName = ambulanceEntry.getValue().getName();
        int homeBaseID = ambulanceEntry.getValue().getHomeBase();
        String homebaseName = homeBaseDirectory.get(homeBaseID).getName();
        Point homeBaseLocation = homeBaseDirectory.get(homeBaseID).getLocation();
        Point ambulanceLocation = ambulanceEntry.getValue().getLocation();
        Stack<Point> path = PathFinder.getPath(mapGrid, ambulanceLocation, homeBaseLocation);

        return new Assignment(ambulanceName, ambulanceEntry.getKey(), homebaseName, homeBaseID, path);
    }

    int getShortestDistance(Point startPoint, Map<Integer, Point> endPoints) {
        Map<Double, Integer> distances = new LinkedHashMap<>();
        LinkedList<Double> distancesList = new LinkedList<>();

        for (Map.Entry<Integer, Point> pair : endPoints.entrySet()) {
            int id = pair.getKey();
            Point endPoint = pair.getValue();
            double distance = startPoint.distanceTo(endPoint);
            distances.put(distance, id);
            distancesList.add(distance);
        }

        distancesList.sort(Double::compareTo);

        double shortestDistance = distancesList.getFirst();

        return distances.get(shortestDistance);
    }
}
