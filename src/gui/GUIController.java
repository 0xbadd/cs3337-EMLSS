package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import mainController.MainController;

public class GUIController {
    public FXCollections entries;
    private MainController mainController = new MainController();
    @FXML private TableView<AssignmentEntry> assignmentsTable;

    @FXML
    private void initialize() {
        mainController.startAcceptingEmergencyCalls();
    }

    private void addAssignment(String callID, String patientID, String assignedAmbulanceID, String status) {
        ObservableList<AssignmentEntry> data = assignmentsTable.getItems();
        data.add(new AssignmentEntry(callID, patientID, assignedAmbulanceID, status));
    }
}

