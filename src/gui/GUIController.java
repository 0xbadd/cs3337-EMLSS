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

public class GUIController {
    private final MainController mc = new MainController();
    @FXML
    private TableView<AssignmentEntry> assignmentsTable;

    @FXML
    private void initialize() {
        mc.startAcceptingEmergencyCalls();

        Timeline updater = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            for (Map.Entry<Integer, EmergencyCall> callEntry : mc.getEmergencyCallDirectory().entrySet()) {
                for (int patientID : callEntry.getValue().getPatients()) {
                    if (!getPatientIDs().contains(patientID)) {
                        addAssignment(callEntry.getKey().toString(), Integer.toString(patientID), "test", "test");
                    }
                }
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
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

