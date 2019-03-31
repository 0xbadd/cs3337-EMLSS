package backend.simulation;

import backend.algorithm.Algorithm;
import backend.algorithm.Assignment;

import java.util.*;
import java.util.concurrent.*;

public class Simulation {
    private static int idGen = 0;
    private static final int MAP_SIZE_X = 100;
    private static final int MAP_SIZE_Y = 100;
    private Map<Integer, Ambulance> ambulanceDirectory;
    private Map<Integer, Patient> patientDirectory;
    private Map<Integer, HomeBase> homeBaseDirectory;
    private Map<Integer, Hospital> hospitalDirectory;
    private List<Assignment> assignments;
    private int[][] mapGrid;

    public Simulation() {
        homeBaseDirectory = generateHomeBases();
        ambulanceDirectory = generateAmbulances(homeBaseDirectory);
        patientDirectory = new LinkedHashMap<>();
        hospitalDirectory = generateHospitals();
        assignments = new LinkedList<>();
        mapGrid = new int[MAP_SIZE_X][MAP_SIZE_Y];
    }

    public void startSimulation() {
        Algorithm algorithm = new Algorithm(this.ambulanceDirectory);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable patientCreator = () -> {
            while (true) {
                int spawnTime = (int)(Math.random() * 60 + 1); // 1 - 60 seconds
                try {
                    Thread.sleep(spawnTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Patient patient = spawnPatient();
                patientDirectory.put(idGen++, patient);
            }
        };
        executor.submit(patientCreator);

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

    Patient spawnPatient() {
        int patient_x = (int)(Math.random() * (MAP_SIZE_X + 1));
        int patient_y = (int)(Math.random() * (MAP_SIZE_Y + 1));

        int injurySeverityRoll = (int)(Math.random() * 101);
        InjurySeverity injurySeverity;
        if (injurySeverityRoll <= 90) {
            injurySeverity = InjurySeverity.NON_LIFE_THREATENING;
        } else {
            injurySeverity = InjurySeverity.LIFE_THREATENING;
        }

        return new Patient(new Point(patient_x,patient_y), injurySeverity);
    }

    void progressAssignments() {
        if (!assignments.isEmpty()) {
            for (Assignment assignment : assignments) {
                int ambulanceId = assignment.getAmbulanceId();
                Ambulance ambulance = ambulanceDirectory.get(ambulanceId);
                Point nextDestination = assignment.getNextMovementPoint();

                ambulance.driveTo(nextDestination);

                if (assignment.getPath().empty()) {
                    assignments.remove(assignment);
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
}
