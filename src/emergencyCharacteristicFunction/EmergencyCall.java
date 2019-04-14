package emergencyCharacteristicFunction;

import models.Point;

import java.util.List;

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
