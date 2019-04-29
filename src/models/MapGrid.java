package models;

import java.util.*;

public class MapGrid {
    private int[][] grid;

    public static final int MAP_SIZE_X = 100;
    public static final int MAP_SIZE_Y = 100;

    class Vertex implements Comparable {
        int priority;
        Point node;

        Vertex(Point node, int priority) {
            this.priority = priority;
            this.node = node;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || (this.getClass() != obj.getClass())) {
                return false;
            }

            Vertex other = (Vertex) obj;
            return (this.priority == other.priority) && (this.node == other.node);
        }

        @Override
        public int compareTo(Object o) {
            Vertex v = (Vertex) o;
            return this.priority - v.priority;
        }
    }

    public MapGrid() {
        grid = new int[MAP_SIZE_X][MAP_SIZE_Y];
    }

    public Stack<Point> getPath(Point start, Point goal) {
        Stack<Point> path = new Stack<>();
        PriorityQueue<Vertex> frontier = new PriorityQueue<>();
        Map<Point, Point> cameFrom = new LinkedHashMap<>();
        Map<Point, Integer> costSoFar = new LinkedHashMap<>();

        frontier.add(new Vertex(start, 0));
        cameFrom.put(start, null);
        costSoFar.put(start, 0);

        // A* algorithm
        while (!frontier.isEmpty()) {
            Point current = frontier.poll().node;

            if (current.equals(goal)) {
                while (!current.equals(start)) {
                    path.push(current);
                    current = cameFrom.get(current);
                }
                break;
            }

            for (Point next : getNeighbors(current)) {
                int newCost = costSoFar.get(current) + 1;
                if (!cameFrom.containsKey(next) || newCost < costSoFar.get(next)) {
                    costSoFar.put(next, newCost);
                    int priority = newCost + heuristic(goal, next);
                    frontier.add(new Vertex(next, priority));
                    cameFrom.put(next, current);
                }
            }
        }

        return path;
    }

    public boolean isValidDrivingLocation(Point point) {
        int row = point.getX();
        int column = point.getY();
        boolean result = false;
        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length) {
            // check if cell is not blocked
            if (grid[row][column] == 1) {
                result = true;
            }
        }

        return result;
    }

    public List<Point> getNeighbors(Point point) {
        int row = point.getX();
        int column = point.getY();
        List<Point> neighbors = new LinkedList<>();

        neighbors.add(new Point(row, column + 1)); // up
        neighbors.add(new Point(row, column - 1)); // down
        neighbors.add(new Point(row - 1, column)); // left
        neighbors.add(new Point(row + 1, column)); // right

        return neighbors;
    }

    private int heuristic(Point a, Point b) {
        // Manhattan distance on a square grid
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public void printGrid() {
        for (int[] ints : grid) {
            for (int column = 0; column < grid.length; column++) {
                System.out.print(ints[column] + ", ");
            }
            System.out.println();
        }
    }
}
