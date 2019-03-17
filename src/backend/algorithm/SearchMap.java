package backend.algorithm;

public class SearchMap {
    private int[][] grid;

    public SearchMap(int[][] mapGrid) {
        this.grid = mapGrid;
    }

    public boolean isValid(int row, int column) {

        boolean result = false;

        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length)

            // check if cell is not blocked and not previously tried
            if (grid[row][column] == 1)
                result = true;

        return result;

    }

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    public int getNumRows() {return grid.length;}
    public int getNumCols() {return grid[0].length;}
}
