package gui;

import ambulanceAssignmentGenerator.Assignment;
import emergencyCharacteristicFunction.EmergencyCall;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import mainController.MainController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GUIController {
    private final MainController mc = new MainController();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    @FXML
    private TableView<AssignmentEntry> assignmentsTable;
    @FXML
    private TableView<CallEntry> callTable;

    @FXML
    private void initialize() {
        Runnable emls = mc::startAcceptingEmergencyCalls;
        executor.submit(emls);

        Timeline updater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            for (Map.Entry<Integer, EmergencyCall> callDirectoryEntry : mc.getEmergencyCallDirectory().entrySet()) {
                boolean tableHasCall = getCallTableIDs().contains(callDirectoryEntry.getKey());
                if (!tableHasCall) {
                    String callID = callDirectoryEntry.getKey().toString();
                    String time = Integer.toString(callDirectoryEntry.getValue().getTime());
                    String numPatients = Integer.toString(callDirectoryEntry.getValue().getNumPatients());
                    String location = callDirectoryEntry.getValue().getLocation().toString();
                    addCall(callID, time, numPatients, location);
                }
            }
            for (Map.Entry<Integer, Assignment> assignmentEntry : mc.getAssignmentDirectory().entrySet()) {
                boolean tableHasAssignment = getAssignmentTableIDs().contains(assignmentEntry.getKey());
                if (!tableHasAssignment) {
                    Assignment assignment = assignmentEntry.getValue();
                    String type = assignment.getType().getText();
                    String ambulanceName = assignment.getAmbulanceName();
                    String destinationName = assignment.getDestinationName();
                    addAssignment(assignmentEntry.getKey(), type, ambulanceName, destinationName);
                }
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    public void shutdown() {
        executor.shutdownNow();
    }

    private void addCall(String callID, String time, String numPatients, String location) {
        callTable.getItems().add(new CallEntry(callID, time, numPatients, location));
    }

    private void addAssignment(int ID, String type, String ambulanceName, String destinationName) {
        assignmentsTable.getItems().add(new AssignmentEntry(ID, type, ambulanceName, destinationName));
    }

    private List<Integer> getAssignmentTableIDs() {
        ObservableList<AssignmentEntry> entries = assignmentsTable.getItems();
        List<Integer> IDs = new ArrayList<>();
        for (AssignmentEntry entry : entries) {
            IDs.add(entry.getID());
        }
        return IDs;
    }

    private List<Integer> getCallTableIDs() {
        ObservableList<CallEntry> entries = callTable.getItems();
        List<Integer> IDs = new ArrayList<>();
        for (CallEntry entry : entries) {
            IDs.add(Integer.parseInt(entry.getCallID()));
        }
        return IDs;
    }
}

