package models;


public class Patient implements Comparable {
    private final String name;
    private final Point location;
    private final InjurySeverity injurySeverity;

    public Patient(String name, Point location, InjurySeverity injurySeverity) {
        this.name = name;
        this.location = location;
        this.injurySeverity = injurySeverity;
    }

    public String getName() {
        return name;
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
