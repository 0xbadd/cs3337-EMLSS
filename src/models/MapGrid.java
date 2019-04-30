package models;

import java.util.*;

public class MapGrid {
    private final int[][] grid;

    public static final int MAP_SIZE_X = 100;
    public static final int MAP_SIZE_Y = 100;

    public MapGrid() {
        grid = new int[MAP_SIZE_X][MAP_SIZE_Y];
    }

    public boolean isValidDrivingLocation(Point point) {
        return isInBounds(point) && isPassable(point);
    }

    private boolean isInBounds(Point point) {
        return point.getX() >= 0 && point.getX() < grid.length
                && point.getY() >= 0 && point.getY() < grid[0].length;
    }

    private boolean isPassable(Point point) {
        return grid[point.getX()][point.getY()] == 0;
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

}
