package emergencyCharacteristicFunction;

import mainController.Logger;
import mainController.MainController;
import models.InjurySeverity;
import models.MapGrid;
import models.Patient;
import models.Point;

import java.util.*;

public class EmergencyCallGenerator {
    public static void getCalls(
            Queue<Map.Entry<Integer, EmergencyCall>> emergencyCallQueue, Map<Integer, Patient> patientDirectory
    ) {
        Logger.emergencyCallHeader();
        for (int numCalls = 0; numCalls < 30; numCalls++) {
            int numPatients = getRandomNumPatients();
            Point emergencyLocation = getRandomEmergencyLocation();

            Map<Integer, Patient> patients = new LinkedHashMap<>();
            for (int i = 0; i < numPatients; i++) {
                patients.put(MainController.createId(), spawnPatient(emergencyLocation));
            }

            List<Integer> patientIdList = new LinkedList<>(patients.keySet());
            int time = numCalls + 1200;
            EmergencyCall emergencyCall = new EmergencyCall(time, numPatients, emergencyLocation, patientIdList);

            int callID = MainController.createId();
            Logger.log(callID + "\t" + emergencyCall.getLogString());
            emergencyCallQueue.add(new AbstractMap.SimpleEntry<>(callID, emergencyCall));
            patientDirectory.putAll(patients);
        }
    }

    private static Patient spawnPatient(Point location) {
        int injurySeverityRoll = (int) (Math.random() * 101);
        InjurySeverity injurySeverity;
        if (injurySeverityRoll <= 70) {
            injurySeverity = InjurySeverity.NON_LIFE_THREATENING;
        } else if (injurySeverityRoll <= 90) {
            injurySeverity = InjurySeverity.MODERATELY_INJURED;
        } else {
            injurySeverity = InjurySeverity.LIFE_THREATENING;
        }

        return new Patient("Bob", location, injurySeverity);
    }

    private static int getRandomNumPatients() {
        int numPatientsRoll = (int) (Math.random() * 101);
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

    private static Point getRandomEmergencyLocation() {
        int emergencyX = (int) (Math.random() * (MapGrid.MAP_SIZE_X + 1));
        int emergencyY = (int) (Math.random() * (MapGrid.MAP_SIZE_Y + 1));
        return new Point(emergencyX, emergencyY);
    }
}
