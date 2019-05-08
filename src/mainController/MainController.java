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
        if (!emergencyCallQueue.isEmpty()) {
            Map.Entry<Integer, EmergencyCall> emergencyCallEntry = emergencyCallQueue.poll();
            assert emergencyCallEntry != null;
            emergencyCallDirectory.put(emergencyCallEntry.getKey(), emergencyCallEntry.getValue());
            System.out.println(emergencyCallEntry.getValue().toString());

            List<Integer> patientIDs = emergencyCallEntry.getValue().getPatientIDList();
            for (int id : patientIDs) {
                Patient patient = patientDirectory.get(id);
                patientQueue.add(new PatientEntry(id, patient));
            }
        }
    }

    private void managePatientPickup() {
        if (!patientQueue.isEmpty()) {
            Map<Integer, Ambulance> availableAmbulanceDirectory = getAvailableAmbulances();
            if (availableAmbulanceDirectory.isEmpty()) {
                return;
            }
            PatientEntry patientEntry = patientQueue.poll();
            assert patientEntry != null;
            Assignment pickupAssignment = assignmentGenerator.makePatientAssignment(mapGrid, patientEntry, availableAmbulanceDirectory);
            System.out.println("[+] PICKUP " + pickupAssignment.getPrintString());
            assignments.add(pickupAssignment);
        }
    }

    private void advanceAssignments() {
        if (!assignments.isEmpty()) {
            List<Assignment> toRemove = new LinkedList<>();
            for (Assignment assignment : assignments) {
                int ambulanceId = assignment.getAmbulanceID();
                Ambulance ambulance = ambulanceDirectory.get(ambulanceId);
                Point nextDestination = assignment.getNextMovementPoint();

                ambulance.driveTo(nextDestination);

                if (assignment.getPath().empty()) {
                    int destinationId = assignment.getDestinationID();
                    toRemove.add(assignment);
                    if (patientDirectory.containsKey(destinationId)) {
                        ambulance.loadPatient(destinationId);
                        Assignment dropOffAssignment = assignmentGenerator.makeHospitalAssignment(mapGrid, new AbstractMap.SimpleEntry<>(ambulanceId, ambulance), hospitalDirectory);
                        Logger.log("DROPOFF\t" + ambulance.getName() + "\t" + dropOffAssignment.getDestinationName());
                        System.out.println("[-] DROPOFF " + dropOffAssignment.getPrintString());
                    } else if (hospitalDirectory.containsKey(destinationId)) {
                        if (ambulance.hasPatient()) {
                            int patientId = ambulance.unloadPatient();
                            patientDirectory.remove(patientId);
                            Assignment returnAssignment = assignmentGenerator.makeHomeBaseAssignment(mapGrid, new AbstractMap.SimpleEntry<>(ambulanceId, ambulance), homeBaseDirectory);
                            Logger.log("RETURN\t" + ambulance.getName() + "\t" + returnAssignment.getDestinationName());
                            System.out.println("[*] RETURN " + returnAssignment.getPrintString());
                        }
                    }
                }
            }
            assignments.removeAll(toRemove);
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
        homeBases.put(createId(), (new HomeBase("Alpha Station", new Point(25, 10), 3)));
        homeBases.put(createId(), (new HomeBase("Beta Station", new Point(80, 80), 3)));
        homeBases.put(createId(), (new HomeBase("Charlie Station", new Point(80, 60), 3)));
        return homeBases;
    }

    private Map<Integer, Hospital> generateHospitals() {
        Map<Integer, Hospital> hospitals = new LinkedHashMap<>();
        hospitals.put(createId(), (new Hospital(new Point(55, 55), "Mercy Hospital")));
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
