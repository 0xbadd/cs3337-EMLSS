package backend.algorithm;

public class Assignment {
    private int ambulanceId;
    private int patientId;
    private int hospitalId;

    public Assignment(int ambulanceId, int patientId, int hospitalId) {
        this.ambulanceId = ambulanceId;
        this.patientId = patientId;
        this.hospitalId = hospitalId;
    }

    public int getAmbulanceId() {
        return ambulanceId;
    }

    public void setAmbulanceId(int ambulanceId) {
        this.ambulanceId = ambulanceId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
}
