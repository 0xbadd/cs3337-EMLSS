package mainController;

import models.Patient;

import java.util.Map;

public class PatientEntry implements Map.Entry<Integer, Patient>, Comparable {
    private final Integer key;
    private Patient value;

    public PatientEntry(Integer key, Patient value) {
        this.key = key;
        this.value = value;
    }

    @Override public Integer getKey() {
        return key;
    }

    @Override public Patient getValue() {
        return value;
    }

    @Override public Patient setValue(Patient value) {
        Patient old = this.value;
        this.value = value;
        return old;
    }

    @Override public int compareTo(Object o) {
        PatientEntry p = (PatientEntry) o;
        return this.value.getInjurySeverity().getPriority() - p.value.getInjurySeverity().getPriority();
    }
}
