package backend;

import javafx.geometry.Point2D;

public class Ambulance {
    private int id;
    private Point2D location;
    private double fuel;
    private int homeBase;
    private int patientsLoaded;

    public Ambulance(int id, Point2D location, int homeBase) {
        this.id = id;
        this.location = location;
        this.fuel = 100.0;
        this.homeBase = homeBase;
        this.patientsLoaded = 0;
    }

    public void driveTo(Point2D location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Point2D getLocation() {
        return location;
    }

    public double getFuel() {
        return fuel;
    }

    public int getHomeBase() {
        return homeBase;
    }

    public int getPatientsLoaded() {
        return patientsLoaded;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setHomeBase(int homeBase) {
        this.homeBase = homeBase;
    }

    public void setPatientsLoaded(int patientsLoaded) {
        this.patientsLoaded = patientsLoaded;
    }
}
