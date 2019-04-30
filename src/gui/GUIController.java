package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import mainController.MainController;

public class GUIController {
    private MainController mainController = new MainController();
    @FXML
    private TableView<AssignmentEntry> assignmentsTable;

    @FXML
    private void initialize() {
        mainController.startAcceptingEmergencyCalls();
    }

    private void addAssignment(String callID, String patientID, String assignedAmbulanceID, String status) {
        assignmentsTable.getItems().add(new AssignmentEntry(callID, patientID, assignedAmbulanceID, status));
    }
}

