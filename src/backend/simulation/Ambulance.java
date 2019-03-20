package backend.simulation;

import javafx.geometry.Point2D;

public class Ambulance {
    private final int id;
    private Point2D location;
    private double fuel;
    private int homeBase;

    Ambulance(int id, Point2D location, int homeBase) {
        this.id = id;
        this.location = location;
        this.fuel = 100.0;
        this.homeBase = homeBase;
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

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setHomeBase(int homeBase) {
        this.homeBase = homeBase;
    }
}
