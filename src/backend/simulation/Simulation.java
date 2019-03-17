package backend.simulation;

import backend.algorithm.Algorithm;
import backend.algorithm.Assignment;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Simulation {
    private static int idGen = 0;
    private static final int MAX_MAP_X = 100;
    private static final int MAX_MAP_Y = 100;
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
        mapGrid = new int[MAX_MAP_X][MAX_MAP_Y];
    }

    public void startSimulation() {
        //#TODO make threads to spawn Patients randomly and to progress assignments simultaneously
        progressAssignments();
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
