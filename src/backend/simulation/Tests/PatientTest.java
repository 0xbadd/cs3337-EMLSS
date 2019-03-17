package backend.simulation.Tests;

import backend.simulation.InjurySeverity;
import backend.simulation.Patient;
import backend.simulation.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void getLocation() {
        Patient patient = new Patient(new Point(0, 0), InjurySeverity.NON_LIFE_THREATENING);
        Point expected = new Point(0, 0);
        Point actual = patient.getLocation();
        assertEquals(expected, actual);
    }

    @Test
    void getInjurySeverity() {
        Patient patient = new Patient(new Point(0, 0), InjurySeverity.NON_LIFE_THREATENING);
        InjurySeverity expected = InjurySeverity.NON_LIFE_THREATENING;
        InjurySeverity actual = patient.getInjurySeverity();
        assertEquals(expected, actual);
    }
}