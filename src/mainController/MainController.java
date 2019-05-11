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
    private final Map<Integer, Assignment> assignmentDirectory;
    private final Queue<PatientEntry> patientQueue;
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
        assignmentDirectory = new LinkedHashMap<>();
        patientQueue = new PriorityQueue<>();
        assignmentGenerator = new AssignmentGenerator();
        mapGrid = new MapGrid();
    }

    public void startAcceptingEmergencyCalls() {
        Logger.startNew();
        EmergencyCallGenerator.getCalls(emergencyCallQueue, patientDirectory);
        Logger.assignmentsHeader();
        while (!emergencyCallQueue.isEmpty() || !assignmentDirectory.isEmpty()) {
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
        if (!emergencyCallQueue.isEmpty()) {
            Map.Entry<Integer, EmergencyCall> emergencyCallEntry = emergencyCallQueue.poll();
            assert emergencyCallEntry != null;
            emergencyCallDirectory.put(emergencyCallEntry.getKey(), emergencyCallEntry.getValue());

            List<Integer> patientIDs = emergencyCallEntry.getValue().getPatientIDList();
            for (int id : patientIDs) {
                Patient patient = patientDirectory.get(id);
                patientQueue.add(new PatientEntry(id, patient));
            }
        }
    }

    private void managePatientPickup() {
        List<PatientEntry> toAdd = new LinkedList<>();
        while (!patientQueue.isEmpty()) {
            Map<Integer, Ambulance> availableAmbulanceDirectory = getAvailableAmbulances();
            PatientEntry patientEntry = patientQueue.poll();
            assert patientEntry != null;
            if (availableAmbulanceDirectory.isEmpty()) {
                toAdd.add(patientEntry);
                continue;
            }
            Assignment pickupAssignment = assignmentGenerator.makePatientAssignment(mapGrid, patientEntry, availableAmbulanceDirectory);
            Logger.log(pickupAssignment.getLogString());
            assignmentDirectory.put(createId(), pickupAssignment);
        }
        patientQueue.addAll(toAdd);
    }

    private void advanceAssignments() {
        if (!assignmentDirectory.isEmpty()) {
            Set<Integer> toRemove = new HashSet<>();
            Map<Integer, Assignment> toAdd = new LinkedHashMap<>();
            for (Map.Entry<Integer, Assignment> assignmentEntry : assignmentDirectory.entrySet()) {
                Assignment assignment = assignmentEntry.getValue();
                int ambulanceId = assignment.getAmbulanceID();
                Ambulance ambulance = ambulanceDirectory.get(ambulanceId);
                Point nextDestination = assignment.getNextMovementPoint();

                ambulance.driveTo(nextDestination);

                if (assignment.getPath().empty()) {
                    Map.Entry<Integer, Ambulance> ambulanceEntry = new AbstractMap.SimpleEntry<>(ambulanceId, ambulance);
                    int destinationId = assignment.getDestinationID();
                    toRemove.add(assignmentEntry.getKey());
                    if (patientDirectory.containsKey(destinationId)) {
                        ambulance.loadPatient(destinationId);
                        Assignment dropOffAssignment = assignmentGenerator.makeHospitalAssignment(mapGrid, ambulanceEntry, hospitalDirectory);
                        Logger.log(dropOffAssignment.getLogString());
                        toAdd.put(createId(), dropOffAssignment);
                    } else if (hospitalDirectory.containsKey(destinationId)) {
                        if (ambulance.hasPatient()) {
                            int patientId = ambulance.unloadPatient();
                            patientDirectory.remove(patientId);
                            Assignment returnAssignment = assignmentGenerator.makeHomeBaseAssignment(mapGrid, ambulanceEntry, homeBaseDirectory);
                            Logger.log(returnAssignment.getLogString());
                            toAdd.put(createId(), returnAssignment);
                        }
                    }
                }
            }
            assignmentDirectory.keySet().removeAll(toRemove);
            assignmentDirectory.putAll(toAdd);
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
        for (Assignment assignment : assignmentDirectory.values()) {
            if (assignment.getAmbulanceID() == ambulanceId) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Ambulance> generateAmbulances(Map<Integer, HomeBase> homeBases) {
        Map<Integer, Ambulance> ambulances = new LinkedHashMap<>();
        for (Map.Entry<Integer, HomeBase> homeBaseEntry: homeBases.entrySet()) {
            HomeBase homeBase = homeBaseEntry.getValue();
            int homeBaseCapacity = homeBaseEntry.getValue().getCurrentCapacity();
            for (int i = 0; i < homeBaseCapacity; i++) {
                String name = homeBase.getName().substring(0, homeBase.getName().indexOf(' ')) + " " + (i + 1);
                ambulances.put(createId(), new Ambulance(name, homeBase.getLocation(), homeBaseEntry.getKey()));
                homeBaseEntry.getValue().houseAmbulance();
            }
        }
        return ambulances;
    }

    private Map<Integer, HomeBase> generateHomeBases() {
        Map<Integer, HomeBase> homeBases = new LinkedHashMap<>();
        homeBases.put(createId(), (new HomeBase("Alpha Station", new Point(25, 10), 3, 60)));
        homeBases.put(createId(), (new HomeBase("Beta Station", new Point(10, 80), 3, 60)));
        homeBases.put(createId(), (new HomeBase("Charlie Station", new Point(75, 65), 3, 60)));
        return homeBases;
    }

    private Map<Integer, Hospital> generateHospitals() {
        Map<Integer, Hospital> hospitals = new LinkedHashMap<>();
        hospitals.put(createId(), (new Hospital(new Point(50, 50), "Mercy Hospital")));
        return hospitals;
    }

    public static int createId() {
        return idGen.incrementAndGet();
    }

    public Map<Integer, EmergencyCall> getEmergencyCallDirectory() {
        return emergencyCallDirectory;
    }

    public Map<Integer, Assignment> getAssignmentDirectory() {
        return assignmentDirectory;
    }
}
