package backend.simulation;

import javafx.geometry.Point2D;

public class Hospital {
    private int id;
    private Point2D location;

    Hospital(int id, Point2D location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
