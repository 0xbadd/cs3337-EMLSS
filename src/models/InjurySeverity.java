package models;

public enum InjurySeverity {
    LIFE_THREATENING(3), NON_LIFE_THREATENING(1);

    private int priority;

    InjurySeverity(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
