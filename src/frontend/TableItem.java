package frontend;
public class TableItem {

    private String ambulanceId;
    private String callId;
    private String status;
    private String condition;
    private String result;
    private String patientId;
    private String hospitalId;


	public TableItem(){
        this.ambulanceId = "Ambulance 00";
        this.callId = "Call 900";
        this.status = "Status : Incomplete";
    }

    public TableItem(String callId,String ambulanceId, String status){
        this.ambulanceId = ambulanceId;
        this.callId = callId;
        this.status = status;
    }
    public TableItem(String ambulanceId, String callId,String condition, String status){
        this.ambulanceId = ambulanceId;
        this.callId = callId;
        this.status = status;
        this.condition=condition;
    }

    public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAmbulanceId() {
        return ambulanceId;
    }

    public void setAmbulanceId(String ambulanceId) {
        this.ambulanceId = ambulanceId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

}