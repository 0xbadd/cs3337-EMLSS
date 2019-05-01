package gui;

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
    private void initialize() {
        Runnable emls = mc::startAcceptingEmergencyCalls;
        executor.submit(emls);

        Timeline updater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            for (Map.Entry<Integer, EmergencyCall> callEntry : mc.getEmergencyCallDirectory().entrySet()) {
                for (int patientID : callEntry.getValue().getPatientIDList()) {
                    if (!getPatientIDs().contains(patientID)) {
                        addAssignment(callEntry.getKey().toString(), Integer.toString(patientID), "test", "test");
                    }
                }
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    public void shutdown() {
        executor.shutdownNow();
    }

    private void addAssignment(String callID, String patientID, String assignedAmbulanceID, String status) {
        assignmentsTable.getItems().add(new AssignmentEntry(callID, patientID, assignedAmbulanceID, status));
    }

    private List<Integer> getPatientIDs() {
        ObservableList<AssignmentEntry> entries = assignmentsTable.getItems();
        List<Integer> IDs = new ArrayList<>();
        for (AssignmentEntry entry : entries) {
            IDs.add(Integer.parseInt(entry.getPatientID()));
        }
        return IDs;
    }
}

