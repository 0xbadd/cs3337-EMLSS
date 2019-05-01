package gui;

import javafx.beans.property.SimpleStringProperty;

public class CallEntry {
    private final SimpleStringProperty callID = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty numPatients = new SimpleStringProperty("");
    private final SimpleStringProperty location = new SimpleStringProperty("");

    public CallEntry() {
        this("", "", "", "");
    }

    public CallEntry(String callID, String time, String numPatients, String location) {
        setCallID(callID);
        setTime(time);
        setNumPatients(numPatients);
        setLocation(location);
    }

    public String getCallID() {
        return callID.get();
    }

    public SimpleStringProperty callIDProperty() {
        return callID;
    }

    public void setCallID(String callID) {
        this.callID.set(callID);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getNumPatients() {
        return numPatients.get();
    }

    public SimpleStringProperty numPatientsProperty() {
        return numPatients;
    }

    public void setNumPatients(String numPatients) {
        this.numPatients.set(numPatients);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
}
