package ambulanceAssignmentGenerator;

import models.Point;

import java.util.Stack;

public class Assignment {
    private final int ambulanceId;
    private final int destinationId;
    private final Stack<Point> path;

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

    public String getPrintString() {
        return "Assignment {" +
                "Ambulance ID = " + ambulanceId +
                ", Destination ID = " + destinationId +
                "}";
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
