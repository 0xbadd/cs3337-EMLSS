package backend.algorithm;

public class Cell {
    int row;
    int col;
    private Cell parent;

    public Cell(int row, int col, Cell p) {
        this.row = row;
        this.col = col;
        this.parent = p;
    }

    public Cell getParent() { return parent; }
}
