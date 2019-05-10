package ambulanceAssignmentGenerator;

import models.Point;

import java.util.Stack;

public class Assignment {
    private final AssignmentType type;
    private final String ambulanceName;
    private final int ambulanceID;
    private final String destinationName;
    private final int destinationID;
    private final Stack<Point> path;

    public Assignment(AssignmentType type, String ambulanceName, int ambulanceID, String destinationName, int destinationID, Stack<Point> path) {
        this.type = type;
        this.ambulanceName = ambulanceName;
        this.ambulanceID = ambulanceID;
        this.destinationName = destinationName;
        this.destinationID = destinationID;
        this.path = path;
    }

    public Point getNextMovementPoint() {
        return path.pop();
    }

    public AssignmentType getType() {
        return type;
    }

    public String getAmbulanceName() {
        return ambulanceName;
    }

    public int getAmbulanceID() {
        return ambulanceID;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public int getDestinationID() {
        return destinationID;
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
        return (this.ambulanceID == other.ambulanceID) && (this.destinationID == other.destinationID) && (this.path.equals(other.path));
    }

    public String getLogString() {
        return String.format(
                "%-8s\t%-8s\t\t\t%s",
                type.getText(),
                ambulanceName,
                destinationName
        );
    }

    @Override
    public String toString() {
        return "Assignment {" +
                "Ambulance ID = " + ambulanceID +
                ", Destination ID = " + destinationID +
                ", Path = " + path +
                '}';
    }
}
