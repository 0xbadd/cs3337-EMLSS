package models;


public class Patient implements Comparable {
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

    @Override public int compareTo(Object o) {
        Patient p = (Patient) o;
        return this.injurySeverity.getPriority() - p.injurySeverity.getPriority();
    }
}
