package ambulanceAssignmentGenerator;

import models.Point;

import java.util.Stack;

public class Assignment {
    private int ambulanceId;
    private int destinationId;
    private Stack<Point> path;

    public Assignment(int ambulanceId, int destinationId, Stack<Point> path) {
        this.ambulanceId = ambulanceId;
        this.destinationId = destinationId;
        this.path = path;
    }

    public Point getNextMovementPoint() {
        return path.pop();
    }

    public int getAmbulanceId() {
        return ambulanceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public Stack<Point> getPath() {
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || (this.getClass() != obj.getClass())) {
            return false;
        }

        Assignment other = (Assignment) obj;
        return (this.ambulanceId == other.ambulanceId) && (this.destinationId == other.destinationId) && (this.path.equals(other.path));
    }

    @Override
    public String toString() {
        return "Assignment {" +
                "Ambulance ID = " + ambulanceId +
                ", Destination ID = " + destinationId +
                ", Path = " + path +
                '}';
    }
}
