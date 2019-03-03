package backend.simulation;

import javafx.geometry.Point2D;

enum Severity {
    LIFE_THREATING, NON_LIFE_THREATING
}

public class Patient {
    private int id;
    private Point2D location;
    private Severity injurySeverity;

    public Patient(int id, Point2D location, Severity injurySeverity) {
        this.id = id;
        this.location = location;
        this.injurySeverity = injurySeverity;
    }

    public int getId() {
        return id;
    }

    public Point2D getLocation() {
        return location;
    }

    public Severity getInjurySeverity() {
        return injurySeverity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public void setInjurySeverity(Severity injurySeverity) {
        this.injurySeverity = injurySeverity;
    }
}
