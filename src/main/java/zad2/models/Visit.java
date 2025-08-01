package zad2.models;

import java.util.Map;
import java.util.Objects;

public class Visit {


    private Doctor doctor;
    private Patient patient;
    private String dateOfVisit;



    public Visit(Doctor doctor, Patient patient, String dateOfVisit) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateOfVisit = dateOfVisit;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit that = (Visit) o;
        return Objects.equals(doctor, that.doctor) && Objects.equals(patient, that.patient) && Objects.equals(dateOfVisit, that.dateOfVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, dateOfVisit);
    }

    public static Visit create(String line , Map<Integer, Doctor> doctorsMap, Map<Integer, Patient> patientsMap) {
        String[] dataAboutVisits = line.split("\t");
        String dateLiteral = dataAboutVisits[2];
        if (dateLiteral.trim().length() != 10)
            return null;
        Doctor d = doctorsMap.get(Integer.parseInt(dataAboutVisits[0]));
        Patient p = patientsMap.get(Integer.parseInt(dataAboutVisits[1]));
        Visit visit = new Visit(d, p, dateLiteral);
        d.getVisits().add(visit);
        p.getVisits().add(visit);
        return visit;
    }
}
