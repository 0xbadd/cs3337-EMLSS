package mainController;

import ambulanceAssignmentGenerator.AssignmentGenerator;
import ambulanceAssignmentGenerator.Assignment;
import emergencyCharacteristicFunction.EmergencyCall;
import emergencyCharacteristicFunction.EmergencyCallGenerator;
import models.*;

import java.util.*;
import java.util.concurrent.*;

public class MainController {
    private static int idGen = 0;
    private Map<Integer, EmergencyCall> emergencyCallDirectory;
    private Map<Integer, Ambulance> ambulanceDirectory;
    private Map<Integer, Patient> patientDirectory;
    private Map<Integer, HomeBase> homeBaseDirectory;
    private Map<Integer, Hospital> hospitalDirectory;
    private List<Assignment> assignments;
    private Queue<Map.Entry<Integer, Patient>> patientQueue;
    private AssignmentGenerator assignmentGenerator;
    private MapGrid mapGrid;

    public MainController() {
        emergencyCallDirectory = new LinkedHashMap<>();
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
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new EmergencyCallGenerator(emergencyCallDirectory, patientDirectory, patientQueue));

        Runnable patientPickupAssignmentManager = () -> {
            while (true) {
                while (!patientQueue.isEmpty()) {
                    Map<Integer, Ambulance> availableAmbulanceDirectory = getAvaialableAmbulances();
                    Map.Entry<Integer, Patient> patientEntry = patientQueue.poll();
                    assignmentGenerator.makePatientAssignment(mapGrid, patientEntry, availableAmbulanceDirectory);
                }
            }
        };
        executor.submit(patientPickupAssignmentManager);

        Runnable assignmentAdvance = () -> {
            while (true) {
                progressAssignments();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.submit(assignmentAdvance);
    }

    Map<Integer, Ambulance> getAvaialableAmbulances() {
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

    void progressAssignments() {
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
                        assignmentGenerator.makeHospitalAssignment(mapGrid, new AbstractMap.SimpleEntry<>(ambulanceId, ambulance), hospitalDirectory);
                    } else if (hospitalDirectory.containsKey(destinationId)) {
                        if (ambulance.hasPatient()) {
                            int patientId = ambulance.unloadPatient();
                            patientDirectory.remove(patientId);
                        }
                    }
                }
            }
        }
    }

    Map<Integer, Ambulance> generateAmbulances(Map<Integer, HomeBase> homeBases) {
        Map<Integer, Ambulance> ambulances = new LinkedHashMap<>();
        // just test locations
        for (HomeBase hb : homeBases.values()) {
            int id = idGen++;
            ambulances.put(id, new Ambulance(hb.getLocation(), id));
            hb.houseAmbulance();
        }
        return ambulances;
    }

    Map<Integer, HomeBase> generateHomeBases() {
        Map<Integer, HomeBase> homeBases = new LinkedHashMap<>();
        // just test locations
        homeBases.put(idGen++, (new HomeBase(new Point(25, 10), 3)));
        homeBases.put(idGen++, (new HomeBase(new Point(80, 80), 3)));
        homeBases.put(idGen++, (new HomeBase(new Point(80, 60), 3)));
        return homeBases;
    }

    Map<Integer, Hospital> generateHospitals() {
        Map<Integer, Hospital> hospitals = new LinkedHashMap<>();
        // just test locations
        hospitals.put(idGen++, (new Hospital(new Point(55, 55))));
        return hospitals;
    }

    public static int createId() {
        return idGen++;
    }
}
