package backend.simulation;

enum Severity {
    LIFE_THREATENING, NON_LIFE_THREATENING
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
