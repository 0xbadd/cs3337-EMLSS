package backend.simulation;

public class Hospital {
    private Point location;

    Hospital(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
