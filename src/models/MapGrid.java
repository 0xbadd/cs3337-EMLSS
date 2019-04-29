package models;

import java.util.*;

public class MapGrid {
    private int[][] grid;

    public static final int MAP_SIZE_X = 100;
    public static final int MAP_SIZE_Y = 100;

    public MapGrid() {
        grid = new int[MAP_SIZE_X][MAP_SIZE_Y];
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

    public void printGrid() {
        for (int[] ints : grid) {
            for (int column = 0; column < grid.length; column++) {
                System.out.print(ints[column] + ", ");
            }
            System.out.println();
        }
    }
}
