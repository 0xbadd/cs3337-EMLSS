package backend.mainController;

import backend.ambulanceAssignmentGenerator.AssignmentGenerator;
import backend.ambulanceAssignmentGenerator.Assignment;
import backend.emergencyCharacteristicFunction.EmergencyCall;

import java.util.*;
import java.util.concurrent.*;

public class MainController {
    private static int idGen = 0;
    private static final int MAP_SIZE_X = 100;
    private static final int MAP_SIZE_Y = 100;
    private Map<Integer, EmergencyCall> emergencyCallDirectory;
    private Map<Integer, Ambulance> ambulanceDirectory;
    private Map<Integer, Patient> patientDirectory;
    private Map<Integer, HomeBase> homeBaseDirectory;
    private Map<Integer, Hospital> hospitalDirectory;
    private List<Assignment> assignments;
    private Queue<Map.Entry<Integer, Patient>> patientQueue;
    private AssignmentGenerator assignmentGenerator;
    private int[][] mapGrid;

    public MainController() {
        emergencyCallDirectory = new LinkedHashMap<>();
        homeBaseDirectory = generateHomeBases();
        patientDirectory = new LinkedHashMap<>();
        hospitalDirectory = generateHospitals();
        assignments = new LinkedList<>();
        patientQueue = new PriorityQueue<>();
        assignmentGenerator = new AssignmentGenerator();
        mapGrid = new int[MAP_SIZE_X][MAP_SIZE_Y];
    }

    public void startAcceptingEmergencyCalls() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable emergencyCallGenerator = () -> {
            while (true) {
                int spawnTime = (int)(Math.random() * 60 + 1); // 1 - 60 seconds
                try {
                    Thread.sleep(spawnTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int numPatients;
                int numPatientsRoll = (int)(Math.random() * 101);
                if (numPatientsRoll <= 50) {
                    numPatients = 1;
                } else if (numPatientsRoll <= 70) {
                    numPatients = 2;
                } else if (numPatientsRoll <= 85) {
                    numPatients = 3;
                } else if (numPatientsRoll <= 95) {
                    numPatients = 4;
                } else {
                    numPatients = 5;
                }

                int emergencyX = (int)(Math.random() * (MAP_SIZE_X + 1));
                int emergencyY = (int)(Math.random() * (MAP_SIZE_Y + 1));
                Point emergencyLocation = new Point(emergencyX, emergencyY);

                Map<Integer, Patient> patients = new LinkedHashMap<>();
                for (int i = 0; i < numPatients; i++) {
                    patients.put(MainController.createId(), spawnPatient(emergencyLocation));
                }

                List<Integer> patientIdList = new LinkedList<>(patients.keySet());
                EmergencyCall emergencyCall = new EmergencyCall(0, numPatients, emergencyLocation, patientIdList);

                emergencyCallDirectory.put(MainController.createId(), emergencyCall);
                patientDirectory.putAll(patients);
                patientQueue.addAll(patients.entrySet());
            }
        };
        executor.submit(emergencyCallGenerator);

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

    Patient spawnPatient(Point location) {
        int injurySeverityRoll = (int)(Math.random() * 101);
        InjurySeverity injurySeverity;
        if (injurySeverityRoll <= 90) {
            injurySeverity = InjurySeverity.NON_LIFE_THREATENING;
        } else {
            injurySeverity = InjurySeverity.LIFE_THREATENING;
        }

        return new Patient(location, injurySeverity);
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
