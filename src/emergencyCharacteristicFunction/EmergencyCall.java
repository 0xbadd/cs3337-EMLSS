package emergencyCharacteristicFunction;

import models.Point;

import java.util.List;

public class EmergencyCall {
    private final int time;
    private final int numPatients;
    private final Point location;
    private final List<Integer> patientIdList;

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

    public List<Integer> getPatientIDList() {
        return patientIdList;
    }

    public String getLogString() {
        return time + "\t" + numPatients + "\t\t\t\t\t" + location.toString();
    }

    public String toString() {
        return "EmergencyCall {" +
                "Time Of Call = " + time +
                ", Number Of Patients = " + numPatients +
                ", Location = " + location +
                ", Patient ID List = " + patientIdList +
                '}';
    }
}
