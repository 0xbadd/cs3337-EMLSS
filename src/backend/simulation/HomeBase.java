package backend.simulation;

import javafx.geometry.Point2D;

public class HomeBase {
    private int id;
    private Point2D location;
    private int capacity;
    private int numAmbulances;

    HomeBase(int id, Point2D location, int capacity) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
    }

    public void houseAmbulance() {
        capacity--;
    }

    public int getId() {
        return id;
    }

    public Point2D getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }
}
