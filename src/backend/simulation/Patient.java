package backend.simulation;

enum Severity {
    LIFE_THREATING, NON_LIFE_THREATING
}

public class Patient {
    private Point location;
    private final Severity injurySeverity;

    public Patient(Point location, Severity injurySeverity) {
        this.location = location;
        this.injurySeverity = injurySeverity;
    }

    public Point getLocation() {
        return location;
    }

    public Severity getInjurySeverity() {
        return injurySeverity;
    }
}
