package backend.mainController;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MapGrid {
    class Cell {
        int row;
        int col;
        private Cell parent;

        Cell(int row, int col, Cell p) {
            this.row = row;
            this.col = col;
            this.parent = p;
        }

        Cell getParent() { return parent; }
    }

    private int[][] mapGrid;

    public MapGrid(int mapSizeX, int mapSizeY) {
        mapGrid = new int[mapSizeX][mapSizeY];

        // carve out streets
        for (int row = 0; row < mapGrid.length; row += 3) {
            for (int column = 0; column <= mapGrid.length; column += 3) {
                mapGrid[row][column] = 1;
            }

        }
    }

    public Stack<Point> getPath(Point startPoint, Point endPoint) {
        Stack<Point> path = new Stack<>();
        Queue<Cell> frontier = new LinkedList<>();
        List<Cell> visited = new LinkedList<>();

        boolean done = false;
        Cell start = new Cell(startPoint.getX(), startPoint.getY(), null);
        frontier.add(start);
        visited.add(start);

        // breadth first search
        while (!frontier.isEmpty()) {
            Cell current = frontier.poll();

            if (current.row == endPoint.getX() && current.col == endPoint.getY()) {
                done = true;
            }

            if (done) {
                path.push(new Point(current.row, current.col));
                boolean isNotStartingPoint = current.row != startPoint.getX() && current.col != startPoint.getY();
                while (current.getParent() != null && isNotStartingPoint) {
                    current = current.getParent();
                    path.push(new Point(current.row, current.col));
                }
                break;
            }

            for (Cell next : getNeighbors(current.row, current.col, current)) {
                if (isValidDrivingLocation(next.row, next.col) && !visited.contains(next)) {
                    frontier.add(next);
                    visited.add(next);
                }
            }
        }

        return path;
    }

    public boolean isValidDrivingLocation(int row, int column) {
        boolean result = false;
        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < mapGrid.length && column >= 0 && column < mapGrid[0].length) {
            // check if cell is not blocked and not previously tried
            if (mapGrid[row][column] == 1) {
                result = true;
            }
        }

        return result;
    }

    public List<Cell> getNeighbors(int row, int column, Cell parent) {
        List<Cell> neighbors = new LinkedList<>();

        neighbors.add(new Cell(row, ++column, parent)); // up
        neighbors.add(new Cell(row, --column, parent)); // down
        neighbors.add(new Cell(--row, column, parent)); // left
        neighbors.add(new Cell(++row, column, parent)); // right

        return neighbors;
    }

    public int[][] getMapGrid() {
        return mapGrid;
    }

    public void setMapGridCell(int row, int col, int value) {
        mapGrid[row][col] = value;
    }
}
