package backend.algorithm.tests;

import backend.algorithm.SearchMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchMapTest {

    @Test
    void isValid() {
        int[][] actualGrid = {{0, 1}, {1, 0}};
        SearchMap searchMap = new SearchMap(actualGrid);

        boolean result = searchMap.isValid(0, 1);
        assertTrue(result);
    }

    @Test
    void isValidFalseCase() {
        int[][] actualGrid = {{0, 1}, {1, 0}};
        SearchMap searchMap = new SearchMap(actualGrid);

        boolean result = searchMap.isValid(0, 0);
        assertFalse(result);
    }

    @Test
    void getCell() {
        int[][] actualGrid = {{0, 1}, {1, 0}};
        SearchMap searchMap = new SearchMap(actualGrid);

        int expected = 1;
        int actual = searchMap.getCell(0, 1);
        assertEquals(expected, actual);
    }

    @Test
    void setCell() {
        int[][] actualGrid = {{0, 1}, {1, 0}};
        SearchMap searchMap = new SearchMap(actualGrid);

        searchMap.setCell(0, 0, 2);

        int expected = 2;
        int actual = searchMap.getCell(0, 0);
        assertEquals(expected, actual);
    }
}