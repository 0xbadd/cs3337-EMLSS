package backend.simulation;

import javafx.geometry.Point2D;

public class Hospital {
    private Point2D location;

    Hospital(Point2D location) {
        this.location = location;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
