package ambulanceAssignmentGenerator;

public enum AssignmentType {
    PICKUP("PICKUP"), DROPOFF("DROPOFF"), RETURN("RETURN");

    private String text;

    AssignmentType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
