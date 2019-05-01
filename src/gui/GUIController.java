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
    private TableView<CallEntry> callTable;

    @FXML
    private void initialize() {
        Runnable emls = mc::startAcceptingEmergencyCalls;
        executor.submit(emls);

        Timeline updater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            for (Map.Entry<Integer, EmergencyCall> callEntry : mc.getEmergencyCallDirectory().entrySet()) {
                boolean hasCall = getCallIDs().contains(callEntry.getKey());
                if (!hasCall) {
                    String callID = callEntry.getKey().toString();
                    String time = Integer.toString(callEntry.getValue().getTime());
                    String numPatients = Integer.toString(callEntry.getValue().getNumPatients());
                    String location = callEntry.getValue().getLocation().asString();
                    addCall(callID, time, numPatients, location);
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

    private List<Integer> getCallIDs() {
        ObservableList<CallEntry> entries = callTable.getItems();
        List<Integer> IDs = new ArrayList<>();
        for (CallEntry entry : entries) {
            IDs.add(Integer.parseInt(entry.getCallID()));
        }
        return IDs;
    }
}

