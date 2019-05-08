package gui;

import javafx.beans.property.SimpleStringProperty;

public class AssignmentEntry {
    private final SimpleStringProperty type = new SimpleStringProperty("");
    private final SimpleStringProperty ambulanceName = new SimpleStringProperty("");
    private final SimpleStringProperty destinationName = new SimpleStringProperty("");

    public AssignmentEntry() {
        this("", "", "");
    }

    public AssignmentEntry(String type, String ambulanceName, String destinationName) {
        setType(type);
        setAmbulanceName(ambulanceName);
        setDestinationName(destinationName);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAmbulanceName() {
        return ambulanceName.get();
    }

    public SimpleStringProperty ambulanceNameProperty() {
        return ambulanceName;
    }

    public void setAmbulanceName(String ambulanceName) {
        this.ambulanceName.set(ambulanceName);
    }

    public String getDestinationName() {
        return destinationName.get();
    }

    public SimpleStringProperty destinationNameProperty() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName.set(destinationName);
    }
}
