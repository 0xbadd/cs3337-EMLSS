package backend.simulation;

import javafx.geometry.Point2D;

public class HomeBase {
    private final int id;
    private final Point2D location;
    private int currentCapacity;

    HomeBase(int id, Point2D location, int initialCapacity) {
        this.id = id;
        this.location = location;
        this.currentCapacity = initialCapacity;
    }

    public void houseAmbulance() {
        currentCapacity--;
    }

    public int getId() {
        return id;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
