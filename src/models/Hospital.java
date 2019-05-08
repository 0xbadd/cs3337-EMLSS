package models;

public class Hospital {
    private final Point location;
    private final String name;

    public Hospital(Point location, String name) {
        this.location = location;
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
