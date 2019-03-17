package backend.algorithm;

import backend.simulation.Point;

import java.util.*;

public class Algorithm {
    public Stack<Point> getPath(int[][] mapGrid, Point startPoint, Point endPoint) {
        Stack<Point> path = new Stack<>();
        SearchMap map = new SearchMap(mapGrid);
        Queue<Cell> queue = new LinkedList<>();

        boolean done = false;
        queue.add(new Cell(startPoint.getX(), startPoint.getY(), null));
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
