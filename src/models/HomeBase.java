package models;

public class HomeBase {
    private final String name;
    private final Point location;
    private int currentCapacity;
    private int radius;

    public HomeBase(String name, Point location, int initialCapacity, int radius) {
        this.name = name;
        this.location = location;
        this.currentCapacity = initialCapacity;
        this.radius = radius;
    }

    public void houseAmbulance() {
        currentCapacity--;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public int getRadius() {
        return radius;
    }
}
