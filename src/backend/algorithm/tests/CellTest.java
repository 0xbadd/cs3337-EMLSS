package backend.algorithm.tests;

import backend.algorithm.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void getParent() {
        Cell cell = new Cell(0, 1, new Cell(2, 3, null));

        Cell expected = new Cell(2, 3,null);
        Cell actual = cell.getParent();
        assertEquals(expected, actual);
    }
}