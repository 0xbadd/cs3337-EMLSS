package backend.simulation;

public class HomeBase {
    private final Point location;
    private int currentCapacity;

    public HomeBase(Point location, int initialCapacity) {
        this.location = location;
        this.currentCapacity = initialCapacity;
    }

    public void houseAmbulance() {
        currentCapacity--;
    }

    public Point getLocation() {
        return location;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
