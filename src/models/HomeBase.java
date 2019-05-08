package models;

public class HomeBase {
    private final String name;
    private final Point location;
    private int currentCapacity;

    public HomeBase(String name, Point location, int initialCapacity) {
        this.name = name;
        this.location = location;
        this.currentCapacity = initialCapacity;
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
}
