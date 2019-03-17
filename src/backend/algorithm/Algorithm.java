package backend.algorithm;

import backend.simulation.Point;

import java.util.*;

public class Algorithm {
    public int getShortestDistance(Point startPoint, Map<Integer, Point> endPoints) {
        Map<Double, Integer> distances = new LinkedHashMap<>();
        List<Double> distancesList = new LinkedList<>();
        for (Map.Entry<Integer, Point> pair : endPoints.entrySet()) {
            int id = pair.getKey();
            Point endPoint = pair.getValue();
            double distance = startPoint.distanceTo(endPoint);
            distances.put(distance, id);
            distancesList.add(distance);
        }
        Comparator<Double> comp = (Double a, Double b) -> {
          if (a < b) {
              return 1;
          } else if (a > b) {
              return 2;
          } else {
              return 0;
          }
        };
        distancesList.sort(comp);

        double shortestDistance = ((LinkedList<Double>) distancesList).getFirst();

        return distances.get(shortestDistance);
    }
}
