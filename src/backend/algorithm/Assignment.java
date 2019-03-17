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

    public int getPatientId() {
        return patientId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public Stack<Point> getPath() {
        return path;
    }
}
