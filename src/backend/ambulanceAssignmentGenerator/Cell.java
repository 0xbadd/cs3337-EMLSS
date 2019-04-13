package backend.ambulanceAssignmentGenerator;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || (this.getClass() != obj.getClass())) {
            return false;
        }

        Cell other = (Cell) obj;
        return (this.row == other.row) && (this.col == other.col) && (this.parent == other.parent);
    }
}
