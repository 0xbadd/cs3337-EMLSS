package backend.algorithm;

import backend.simulation.Point;

import java.util.Stack;

public class Assignment {
    private int ambulanceId;
    private int patientId;
    private int hospitalId;
    private Stack<Point> path;

    public Assignment(int ambulanceId, int patientId, int hospitalId, Stack<Point> path) {
        this.ambulanceId = ambulanceId;
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.path = path;
    }

    public Point getNextMovementPoint() {
        return path.pop();
    }

    public int getAmbulanceId() {
        return ambulanceId;
    }

    public void setAmbulanceId(int ambulanceId) {
        this.ambulanceId = ambulanceId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Stack<Point> getPath() {
        return path;
    }
}
