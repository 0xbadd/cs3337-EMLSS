package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void getLocation() {
        Patient patient = new Patient("bob", new Point(0, 1), InjurySeverity.NON_LIFE_THREATENING);
        Point expected = new Point(0, 1);
        Point actual = patient.getLocation();
        assertEquals(expected, actual);
    }

    @Test
    void getInjurySeverity() {
        Patient patient = new Patient("bob", new Point(0, 1), InjurySeverity.NON_LIFE_THREATENING);
        InjurySeverity expected = InjurySeverity.NON_LIFE_THREATENING;
        InjurySeverity actual = patient.getInjurySeverity();
        assertEquals(expected, actual);
    }
}