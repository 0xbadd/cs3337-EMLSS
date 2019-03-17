package backend.simulation;

public class Ambulance {
    private Point location;
    private double fuel;
    private int homeBase;

    public Ambulance(Point location, int homeBase) {
        this.location = location;
        this.fuel = 100.0;
        this.homeBase = homeBase;
    }

    public void driveTo(Point location) {
        this.location = location;
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

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setHomeBase(int homeBase) {
        this.homeBase = homeBase;
    }
}
