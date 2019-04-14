package backend.emergencyCharacteristicFunction;

import backend.mainController.InjurySeverity;
import backend.mainController.MainController;
import backend.mainController.Patient;
import backend.mainController.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmergencyCall {
    private int time;
    private int numPatients;
    private Point location;
    private List<Integer> patientIdList;

    public EmergencyCall(int time, int numPatients, Point location, List<Integer> patientIdList) {
        this.time = time;
        this.numPatients = numPatients;
        this.location = location;
        this.patientIdList = patientIdList;
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

    public List<Integer> getPatients() {
        return patientIdList;
    }

}
