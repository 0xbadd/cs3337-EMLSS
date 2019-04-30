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
    private final Map<Integer, EmergencyCall> emergencyCallDirectory;
    private final Map<Integer, Ambulance> ambulanceDirectory;
    private final Map<Integer, Patient> patientDirectory;
    private final Map<Integer, HomeBase> homeBaseDirectory;
    private final Map<Integer, Hospital> hospitalDirectory;
    private final List<Assignment> assignments;
    private final Queue<Map.Entry<Integer, Patient>> patientQueue;
    private final AssignmentGenerator assignmentGenerator;
    private final MapGrid mapGrid;

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
        executor.submit(new PatientPickupAssignmentManager(assignments, ambulanceDirectory, patientQueue, mapGrid, assignmentGenerator));

        Runnable advanceAssignments = () -> {
            while(true) {
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
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.submit(advanceAssignments);
    }

    private Map<Integer, Ambulance> generateAmbulances(Map<Integer, HomeBase> homeBases) {
        Map<Integer, Ambulance> ambulances = new LinkedHashMap<>();
        // just test locations
        for (HomeBase hb : homeBases.values()) {
            int id = idGen++;
            ambulances.put(id, new Ambulance(hb.getLocation(), id));
            hb.houseAmbulance();
        }
        return ambulances;
    }

    private Map<Integer, HomeBase> generateHomeBases() {
        Map<Integer, HomeBase> homeBases = new LinkedHashMap<>();
        // just test locations
        homeBases.put(idGen++, (new HomeBase(new Point(25, 10), 3)));
        homeBases.put(idGen++, (new HomeBase(new Point(80, 80), 3)));
        homeBases.put(idGen++, (new HomeBase(new Point(80, 60), 3)));
        return homeBases;
    }

    private Map<Integer, Hospital> generateHospitals() {
        Map<Integer, Hospital> hospitals = new LinkedHashMap<>();
        // just test locations
        hospitals.put(idGen++, (new Hospital(new Point(55, 55))));
        return hospitals;
    }

    public static int createId() {
        return idGen++;
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
