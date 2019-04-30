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
        while (true) {
            int spawnTime = (int)(Math.random() * 10 + 1); // 1 - 10 seconds
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

            int emergencyX = (int)(Math.random() * (MapGrid.MAP_SIZE_X + 1));
            int emergencyY = (int)(Math.random() * (MapGrid.MAP_SIZE_Y + 1));
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
}
