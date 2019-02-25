package backend;

import javafx.geometry.Point2D;

public class HomeBase {
    private int id;
    private Point2D location;
    private int capacity;
    private int numAmbulances;

    public HomeBase(int id, Point2D location, int capacity, int numAmbulances) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.numAmbulances = numAmbulances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getNumAmbulances() {
        return numAmbulances;
    }

    public void setNumAmbulances(int numAmbulances) {
        this.numAmbulances = numAmbulances;
    }
}
