package models;

public class Hospital {
    private Point location;

    public Hospital(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
