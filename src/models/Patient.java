package models;


public class Patient {
    private final Point location;
    private final InjurySeverity injurySeverity;

    public Patient(Point location, InjurySeverity injurySeverity) {
        this.location = location;
        this.injurySeverity = injurySeverity;
    }

    public Point getLocation() {
        return location;
    }

    public InjurySeverity getInjurySeverity() {
        return injurySeverity;
    }
}
