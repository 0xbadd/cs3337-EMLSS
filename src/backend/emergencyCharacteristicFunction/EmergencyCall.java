package backend.emergencyCharacteristicFunction;

import backend.mainController.InjurySeverity;
import backend.mainController.MainController;
import backend.mainController.Patient;
import backend.mainController.Point;

import java.util.Map;

public class EmergencyCall {
    private int time;
    private int numPatients;
    private Point location;
    private Map<Integer, Patient> patients;

    public EmergencyCall(int time, int numPatients, Point location) {
        this.time = time;
        this.numPatients = numPatients;
        this.location = location;

        for (int i = 0; i < numPatients; i++) {
            int injurySeverityRoll = (int) (Math.random() * 101);
            InjurySeverity injurySeverity;
            if (injurySeverityRoll <= 90) {
                injurySeverity = InjurySeverity.NON_LIFE_THREATENING;
            } else {
                injurySeverity = InjurySeverity.LIFE_THREATENING;
            }

            patients.put(MainController.createId(), new Patient(location, injurySeverity));
        }
    }

    public int getTime() {
        return time;
    }

    public int getNumPatients() {
        return numPatients;
    }

    public Point getLocation() {
        return location;
    }

    public Map<Integer, Patient> getPatients() {
        return patients;
    }
}
