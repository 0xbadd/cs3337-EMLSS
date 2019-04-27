package mainController;

import ambulanceAssignmentGenerator.Assignment;
import ambulanceAssignmentGenerator.AssignmentGenerator;
import models.Ambulance;
import models.MapGrid;
import models.Patient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class patientPickupAssignmentManager implements Runnable {
    private Map<Integer, Ambulance> ambulanceDirectory;
    private Queue<Map.Entry<Integer, Patient>> patientQueue;
    private MapGrid mapGrid;
    private AssignmentGenerator assignmentGenerator;
    private List<Assignment> assignments;

    public patientPickupAssignmentManager(List<Assignment> assignments, Map<Integer, Ambulance> ambulanceDirectory, Queue<Map.Entry<Integer, Patient>> patientQueue, MapGrid mapGrid, AssignmentGenerator assignmentGenerator) {
        this.ambulanceDirectory = ambulanceDirectory;
        this.patientQueue = patientQueue;
        this.mapGrid = mapGrid;
        this.assignmentGenerator = assignmentGenerator;
        this.assignments = assignments;
    }

    @Override
    public void run() {
        while (true) {
            while (!patientQueue.isEmpty()) {
                Map<Integer, Ambulance> availableAmbulanceDirectory = getAvaialableAmbulances();
                Map.Entry<Integer, Patient> patientEntry = patientQueue.poll();
                assignmentGenerator.makePatientAssignment(mapGrid, patientEntry, availableAmbulanceDirectory);
            }
        }
    }

    private Map<Integer, Ambulance> getAvaialableAmbulances() {
        Map<Integer, Ambulance> availableAmbulanceDirectory = new LinkedHashMap<>();
        for (Map.Entry<Integer, Ambulance> ambulanceEntry : this.ambulanceDirectory.entrySet()) {
            Integer ambulanceId = ambulanceEntry.getKey();
            Ambulance ambulance = ambulanceEntry.getValue();
            if (isAmbulanceAvailable(ambulanceId)) {
                availableAmbulanceDirectory.put(ambulanceId, ambulance);
            }
        }
        return availableAmbulanceDirectory;
    }

    private boolean isAmbulanceAvailable(int ambulanceId) {
        for (Assignment assignment : this.assignments) {
            if (assignment.getAmbulanceId() ==  ambulanceId) {
                return false;
            }
        }
        return true;
    }
}
