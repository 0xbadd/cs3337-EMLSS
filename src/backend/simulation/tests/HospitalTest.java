package backend.simulation.tests;

import backend.simulation.Hospital;
import backend.simulation.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {

    @Test
    void getLocation() {
        Hospital hospital = new Hospital(new Point(0, 1));
        Point expected = new Point(0, 1);
        Point actual = hospital.getLocation();
        assertEquals(expected, actual);
    }
}