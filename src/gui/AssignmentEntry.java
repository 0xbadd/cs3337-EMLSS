package gui;

import javafx.beans.property.SimpleStringProperty;

class AssignmentEntry {
    private final SimpleStringProperty callID = new SimpleStringProperty("");
    private final SimpleStringProperty patientID = new SimpleStringProperty("");
    private final SimpleStringProperty assignedAmbulanceID = new SimpleStringProperty("");
    private final SimpleStringProperty status = new SimpleStringProperty("");

    public AssignmentEntry() {
        this("", "", "", "");
    }

    public AssignmentEntry(String callID, String patientID, String assignedAmbulanceID, String status) {
        setCallID(callID);
        setPatientID(patientID);
        setAssignedAmbulanceID(assignedAmbulanceID);
        setStatus(status);
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

    public String getPatientID() {
        return patientID.get();
    }

    public SimpleStringProperty patientIDProperty() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID.set(patientID);
    }

    public String getAssignedAmbulanceID() {
        return assignedAmbulanceID.get();
    }

    public SimpleStringProperty assignedAmbulanceIDProperty() {
        return assignedAmbulanceID;
    }

    public void setAssignedAmbulanceID(String assignedAmbulanceID) {
        this.assignedAmbulanceID.set(assignedAmbulanceID);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
