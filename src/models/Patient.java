package models;


public class Patient {
    private final Point location;
    private final int priority;
    private final InjurySeverity injurySeverity;

    public Patient(Point location, int priority, InjurySeverity injurySeverity) {
        this.location = location;
        this.priority = priority;
        this.injurySeverity = injurySeverity;
    }

    public Point getLocation() {
        return location;
    }

    public int getPriority() {
        return priority;
    }

    public InjurySeverity getInjurySeverity() {
        return injurySeverity;
    }
}
