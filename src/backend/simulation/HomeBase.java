package backend.simulation;

import javafx.geometry.Point2D;

public class HomeBase {
    private final Point2D location;
    private int currentCapacity;

    HomeBase(Point2D location, int initialCapacity) {
        this.location = location;
        this.currentCapacity = initialCapacity;
    }

    public void houseAmbulance() {
        currentCapacity--;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
