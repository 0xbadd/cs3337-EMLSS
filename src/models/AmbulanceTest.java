package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmbulanceTest {

    @Test
    void driveTo() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 0), 0);
        ambulance.driveTo(new Point(1, 1));
        Point expected = new Point(1, 1);
        Point actual = ambulance.getLocation();
        assertEquals(expected, actual);
    }

    @Test
    void loadPatient() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 0), 0);
        int patientId = 3;
        ambulance.loadPatient(patientId);

        int expected = 3;
        int actual = ambulance.getLoadedPatientId();
        assertEquals(expected, actual);
    }

    @Test
    void unloadPatient() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 0), 0);
        int patientId = 3;
        ambulance.loadPatient(patientId);

        int expected = 3;
        int actual = ambulance.unloadPatient();
        assertTrue(expected == actual && !ambulance.hasPatient());
    }

    @Test
    void hasPatientTrueCase() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 0), 0);
        int patientId = 3;
        ambulance.loadPatient(patientId);

        assertTrue(ambulance.hasPatient());
    }

    @Test
    void hasPatientFalseCase() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 0), 0);

        assertFalse(ambulance.hasPatient());
    }

    @Test
    void getLoadedPatientId() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 0), 0);
        int patientId = 3;
        ambulance.loadPatient(patientId);

        int expected = 3;
        int actual = ambulance.getLoadedPatientId();
        assertEquals(expected, actual);
    }

    @Test
    void getLocation() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 1), 0);
        Point expected = new Point(0, 1);
        Point actual = ambulance.getLocation();
        assertEquals(expected, actual);
    }

    @Test
    void getFuel() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 1), 0);
        double expected = 100.0;
        double actual = ambulance.getFuel();
        assertEquals(expected, actual);
    }

    @Test
    void getHomeBase() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 1), 0);
        int expected = 0;
        int actual = ambulance.getHomeBase();
        assertEquals(expected, actual);
    }

    @Test
    void setFuel() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 1), 0);
        ambulance.setFuel(50.0);
        double expected = 50.0;
        double actual = ambulance.getFuel();
        assertEquals(expected, actual);
    }

    @Test
    void setHomeBase() {
        Ambulance ambulance = new Ambulance("ambulance", new Point(0, 1), 0);
        ambulance.setHomeBase(10);
        int expected = 10;
        int actual = ambulance.getHomeBase();
        assertEquals(expected, actual);
    }
}