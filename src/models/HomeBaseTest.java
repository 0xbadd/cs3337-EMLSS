package models;

import models.HomeBase;
import models.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeBaseTest {

    @Test
    void houseAmbulance() {
        HomeBase homeBase = new HomeBase(new Point(0, 1), 3);
        homeBase.houseAmbulance();
        int expected = 2;
        int actual = homeBase.getCurrentCapacity();
        assertEquals(expected, actual);
    }

    @Test
    void getLocation() {
        HomeBase homeBase = new HomeBase(new Point(0, 1), 3);
        Point expected = new Point(0, 1);
        Point actual = homeBase.getLocation();
        assertEquals(expected, actual);
    }

    @Test
    void getCurrentCapacity() {
        HomeBase homeBase = new HomeBase(new Point(0, 1), 3);
        int expected = 3;
        int actual = homeBase.getCurrentCapacity();
        assertEquals(expected, actual);
    }
}