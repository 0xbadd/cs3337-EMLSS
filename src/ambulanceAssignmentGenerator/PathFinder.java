package ambulanceAssignmentGenerator;

import models.MapGrid;
import models.Point;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

class PathFinder {
    static class Vertex implements Comparable {
        final int priority;
        final Point node;

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

    // A* algorithm
    static Stack<Point> getPath(MapGrid grid, Point start, Point goal) {
        Stack<Point> path = new Stack<>();
        PriorityQueue<Vertex> frontier = new PriorityQueue<>();
        Map<Point, Point> cameFrom = new LinkedHashMap<>();
        Map<Point, Integer> costSoFar = new LinkedHashMap<>();

        frontier.add(new Vertex(start, 0));
        cameFrom.put(start, null);
        costSoFar.put(start, 0);

        while (!frontier.isEmpty()) {
            Point current = frontier.poll().node;

            if (current.equals(goal)) {
                while (!current.equals(start)) {
                    path.push(current);
                    current = cameFrom.get(current);
                }
                break;
            }

            for (Point next : grid.getNeighbors(current)) {
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

    private static int heuristic(Point a, Point b) {
        // Manhattan distance on a square grid
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
