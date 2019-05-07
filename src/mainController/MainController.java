package mainController;

import ambulanceAssignmentGenerator.AssignmentGenerator;
import ambulanceAssignmentGenerator.Assignment;
import emergencyCharacteristicFunction.EmergencyCall;
import emergencyCharacteristicFunction.EmergencyCallGenerator;
import models.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainController {
    private static AtomicInteger idGen;
    private final Map<Integer, EmergencyCall> emergencyCallDirectory;
    private final Map<Integer, Ambulance> ambulanceDirectory;
    private final Map<Integer, Patient> patientDirectory;
    private final Map<Integer, HomeBase> homeBaseDirectory;
    private final Map<Integer, Hospital> hospitalDirectory;
    private final List<Assignment> assignments;
    private final Queue<Map.Entry<Integer, Patient>> patientQueue;
    private final Queue<Map.Entry<Integer, EmergencyCall>> emergencyCallQueue;
    private final AssignmentGenerator assignmentGenerator;
    private final MapGrid mapGrid;

    public MainController() {
        idGen = new AtomicInteger();
        emergencyCallDirectory = new LinkedHashMap<>();
        emergencyCallQueue = new LinkedList<>();
        homeBaseDirectory = generateHomeBases();
        ambulanceDirectory = generateAmbulances(homeBaseDirectory);
        patientDirectory = new LinkedHashMap<>();
        hospitalDirectory = generateHospitals();
        assignments = new LinkedList<>();
        patientQueue = new PriorityQueue<>();
        assignmentGenerator = new AssignmentGenerator();
        mapGrid = new MapGrid();
    }

    public void startAcceptingEmergencyCalls() {
        Logger.startNew();
        EmergencyCallGenerator.getCalls(emergencyCallQueue, patientDirectory);
        Logger.assignmentsHeader();
        while (!emergencyCallQueue.isEmpty() || !assignments.isEmpty()) {
            receiveCall();
            managePatientPickup();
            advanceAssignments();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void receiveCall() {
        Map.Entry<Integer, EmergencyCall> emergencyCallEntry = emergencyCallQueue.poll();
        assert emergencyCallEntry != null;
        emergencyCallDirectory.put(emergencyCallEntry.getKey(), emergencyCallEntry.getValue());

        List<Integer> patientIDs = emergencyCallEntry.getValue().getPatientIDList();
        for (int id : patientIDs) {
            Patient patient = patientDirectory.get(id);
            patientQueue.add(new AbstractMap.SimpleEntry<>(id, patient));
        }
    }

    private void managePatientPickup() {
        if (!patientQueue.isEmpty()) {
            Map<Integer, Ambulance> availableAmbulanceDirectory = getAvailableAmbulances();
            Map.Entry<Integer, Patient> patientEntry = patientQueue.poll();
            assert patientEntry != null;
            Assignment pickupAssignment = assignmentGenerator.makePatientAssignment(mapGrid, patientEntry, availableAmbulanceDirectory);
            assignments.add(pickupAssignment);
        }
    }

    private void advanceAssignments() {
        if (!assignments.isEmpty()) {
            for (Assignment assignment : assignments) {
                int ambulanceId = assignment.getAmbulanceId();
                Ambulance ambulance = ambulanceDirectory.get(ambulanceId);
                Point nextDestination = assignment.getNextMovementPoint();

                ambulance.driveTo(nextDestination);

                if (assignment.getPath().empty()) {
                    int destinationId = assignment.getDestinationId();
                    assignments.remove(assignment);
                    if (patientDirectory.containsKey(destinationId)) {
                        ambulance.loadPatient(destinationId);
                        Assignment dropOffAssignment = assignmentGenerator.makeHospitalAssignment(mapGrid, new AbstractMap.SimpleEntry<>(ambulanceId, ambulance), hospitalDirectory);
                        Logger.log("DROPOFF\t" + ambulanceId + "\t" + dropOffAssignment.getDestinationId());
                    } else if (hospitalDirectory.containsKey(destinationId)) {
                        if (ambulance.hasPatient()) {
                            int patientId = ambulance.unloadPatient();
                            patientDirectory.remove(patientId);
                            Assignment returnAssignment = assignmentGenerator.makeHomeBaseAssignment(mapGrid, new AbstractMap.SimpleEntry<>(ambulanceId, ambulance), homeBaseDirectory);
                            Logger.log("RETURN\t" + ambulanceId + "\t" + returnAssignment.getDestinationId());
                        }
                    }
                }
            }
        }
    }

    private Map<Integer, Ambulance> getAvailableAmbulances() {
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
            if (assignment.getAmbulanceId() == ambulanceId) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Ambulance> generateAmbulances(Map<Integer, HomeBase> homeBases) {
        Map<Integer, Ambulance> ambulances = new LinkedHashMap<>();
        // just test locations
        for (Map.Entry<Integer, HomeBase> homeBaseEntry: homeBases.entrySet()) {
            ambulances.put(createId(), new Ambulance(homeBaseEntry.getValue().getLocation(), homeBaseEntry.getKey()));
            homeBaseEntry.getValue().houseAmbulance();
        }
        return ambulances;
    }

    private Map<Integer, HomeBase> generateHomeBases() {
        Map<Integer, HomeBase> homeBases = new LinkedHashMap<>();
        // just test locations
        homeBases.put(createId(), (new HomeBase(new Point(25, 10), 3)));
        homeBases.put(createId(), (new HomeBase(new Point(80, 80), 3)));
        homeBases.put(createId(), (new HomeBase(new Point(80, 60), 3)));
        return homeBases;
    }

    private Map<Integer, Hospital> generateHospitals() {
        Map<Integer, Hospital> hospitals = new LinkedHashMap<>();
        // just test locations
        hospitals.put(createId(), (new Hospital(new Point(55, 55))));
        return hospitals;
    }

    public static int createId() {
        return idGen.incrementAndGet();
    }

    public Map<Integer, EmergencyCall> getEmergencyCallDirectory() {
        return emergencyCallDirectory;
    }

    public Map<Integer, Ambulance> getAmbulanceDirectory() {
        return ambulanceDirectory;
    }

    public Map<Integer, Patient> getPatientDirectory() {
        return patientDirectory;
    }

    public Map<Integer, HomeBase> getHomeBaseDirectory() {
        return homeBaseDirectory;
    }

    public Map<Integer, Hospital> getHospitalDirectory() {
        return hospitalDirectory;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}
