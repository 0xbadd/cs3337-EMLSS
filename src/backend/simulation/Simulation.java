package backend.simulation;

import backend.algorithm.Algorithm;
import backend.algorithm.Assignment;

import java.util.*;
import java.util.concurrent.*;

class Simulation {
    private static int idGen = 0;
    private static final int MAP_SIZE_X = 100;
    private static final int MAP_SIZE_Y = 100;
    private Map<Integer, Ambulance> ambulances;
    private Map<Integer, Patient> patients;
    private Map<Integer, HomeBase> homeBases;
    private Map<Integer, Hospital> hospitals;
    private List<Assignment> assignments;
    private int[][] mapGrid;

    public Simulation() {
        homeBases = generateHomeBases();
        ambulances = generateAmbulances(homeBases);
        patients = new LinkedHashMap<>();
        hospitals = generateHospitals();
        assignments = new LinkedList<>();
        mapGrid = new int[MAP_SIZE_X][MAP_SIZE_Y];
    }

    public void startSimulation() {
        Algorithm algorithm = new Algorithm(this.ambulances);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable patientCreator = () -> {
            while (true) {
                int spawnTime = (int)(Math.random() * 60 + 1); // 1 - 60 seconds
                try {
                    Thread.sleep(spawnTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Patient patient = spawnPatient();
                patients.put(idGen++, patient);
            }
        };
        executorService.submit(patientCreator);

        while (true) {
            progressAssignments();
        }

    }

    private Patient spawnPatient() {
        int patient_x = (int)(Math.random() * (MAP_SIZE_X + 1));
        int patient_y = (int)(Math.random() * (MAP_SIZE_Y + 1));

        int injurySeverityRoll = (int)(Math.random() * 101);
        InjurySeverity injurySeverity;
        if (injurySeverityRoll >= 90) {
            injurySeverity = InjurySeverity.NON_LIFE_THREATENING;
        } else {
            injurySeverity = InjurySeverity.LIFE_THREATENING;
        }

        return new Patient(new Point(patient_x,patient_y), injurySeverity);
    }

    private void progressAssignments() {
        if (!assignments.isEmpty()) {
            for (Assignment assignment : assignments) {
                int ambulanceId = assignment.getAmbulanceId();
                Ambulance ambulance = ambulances.get(ambulanceId);
                Point nextDestination = assignment.getNextMovementPoint();

                ambulance.driveTo(nextDestination);

                if (assignment.getPath().empty()) {
                    assignments.remove(assignment);
                }
            }
        }
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
}
