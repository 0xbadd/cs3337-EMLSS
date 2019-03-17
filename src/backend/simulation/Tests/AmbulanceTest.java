package backend.simulation.Tests;

import backend.simulation.Ambulance;
import backend.simulation.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AmbulanceTest {

    @org.junit.jupiter.api.Test
    void driveTo() {
        Ambulance ambulance = new Ambulance(new Point(0, 0), 0);
        ambulance.driveTo(new Point(1, 1));
        Point expected = new Point(1, 1);
        Point actual = ambulance.getLocation();
        assertTrue(expected.equals(actual));
    }

    @org.junit.jupiter.api.Test
    void getLocation() {
        Ambulance ambulance = new Ambulance(new Point(0, 0), 0);
        Point expected = new Point(0, 0);
        Point actual = ambulance.getLocation();
        assertTrue(expected.equals(actual));
    }

    @org.junit.jupiter.api.Test
    void getFuel() {
        Ambulance ambulance = new Ambulance(new Point(0, 0), 0);
        double expected = 100.0;
        double actual = ambulance.getFuel();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getHomeBase() {
        Ambulance ambulance = new Ambulance(new Point(0, 0), 0);
        int expected = 0;
        int actual = ambulance.getHomeBase();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void setFuel() {
        Ambulance ambulance = new Ambulance(new Point(0, 0), 0);
        ambulance.setFuel(50.0);
        double expected = 50.0;
        double actual = ambulance.getFuel();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void setHomeBase() {
        Ambulance ambulance = new Ambulance(new Point(0, 0), 0);
        ambulance.setHomeBase(10);
        int expected = 10;
        int actual = ambulance.getHomeBase();
        assertEquals(expected, actual);
    }
}