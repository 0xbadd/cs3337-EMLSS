package models;

public class Ambulance {
    private Point location;
    private double fuel;
    private int homeBase;
    private int loadedPatientId;

    public Ambulance(Point location, int homeBase) {
        this.location = location;
        this.fuel = 100.0;
        this.homeBase = homeBase;
        this.loadedPatientId = 0;
    }

    public void driveTo(Point location) {
        this.location = location;
    }

    public void loadPatient(int patientId) {
        loadedPatientId = patientId;
    }

    public int unloadPatient() {
        int patientId = loadedPatientId;
        loadedPatientId = 0;
        return patientId;
    }

    public boolean hasPatient() {
        return loadedPatientId != 0;
    }

    public Point getLocation() {
        return location;
    }

    public double getFuel() {
        return fuel;
    }

    public int getHomeBase() {
        return homeBase;
    }

    public int getLoadedPatientId() {
        return loadedPatientId;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setHomeBase(int homeBase) {
        this.homeBase = homeBase;
    }
}
