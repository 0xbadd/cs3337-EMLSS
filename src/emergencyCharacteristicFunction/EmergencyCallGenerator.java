package emergencyCharacteristicFunction;

import mainController.MainController;
import models.InjurySeverity;
import models.MapGrid;
import models.Patient;
import models.Point;

import java.util.*;

public class EmergencyCallGenerator implements Runnable {
    private final Map<Integer, EmergencyCall> emergencyCallDirectory;
    private final Map<Integer, Patient> patientDirectory;
    private final Queue<Map.Entry<Integer, Patient>> patientQueue;

    public EmergencyCallGenerator(Map<Integer, EmergencyCall> emergencyCallDirectory, Map<Integer, Patient> patientDirectory, Queue<Map.Entry<Integer, Patient>> patientQueue) {
        this.emergencyCallDirectory = emergencyCallDirectory;
        this.patientDirectory = patientDirectory;
        this.patientQueue = patientQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int spawnTime = (int)(Math.random() * 10 + 1); // 1 - 10 seconds
                Thread.sleep(spawnTime * 1000);

                int numPatients = getRandomNumPatients();
                Point emergencyLocation = getRandomEmergencyLocation();

                Map<Integer, Patient> patients = new LinkedHashMap<>();
                for (int i = 0; i < numPatients; i++) {
                    patients.put(MainController.createId(), spawnPatient(emergencyLocation));
                }

                List<Integer> patientIdList = new LinkedList<>(patients.keySet());
                EmergencyCall emergencyCall = new EmergencyCall(0, numPatients, emergencyLocation, patientIdList);

                emergencyCallDirectory.put(MainController.createId(), emergencyCall);
                patientDirectory.putAll(patients);
                patientQueue.addAll(patients.entrySet());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Patient spawnPatient(Point location) {
        int injurySeverityRoll = (int)(Math.random() * 101);
        InjurySeverity injurySeverity;
        if (injurySeverityRoll <= 90) {
            injurySeverity = InjurySeverity.NON_LIFE_THREATENING;
        } else {
            injurySeverity = InjurySeverity.LIFE_THREATENING;
        }

        return new Patient(location, injurySeverity);
    }

    private int getRandomNumPatients() {
        int numPatientsRoll = (int)(Math.random() * 101);
        if (numPatientsRoll <= 50) {
            return 1;
        } else if (numPatientsRoll <= 70) {
            return 2;
        } else if (numPatientsRoll <= 85) {
            return 3;
        } else if (numPatientsRoll <= 95) {
            return 4;
        } else {
            return 5;
        }
    }

    private Point getRandomEmergencyLocation() {
        int emergencyX = (int)(Math.random() * (MapGrid.MAP_SIZE_X + 1));
        int emergencyY = (int)(Math.random() * (MapGrid.MAP_SIZE_Y + 1));
        return new Point(emergencyX, emergencyY);
    }
}
