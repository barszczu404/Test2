package zad2.models;

import java.util.Objects;

public class DoctorsVisit {

    //numer identyfikacyjny lekarza, numer
    //identyfikacyjny pacjenta oraz datę wizyty lekarskiej przeprowadzonej przez lekarza
    //u pacjenta.
    private Doctor doctor;
    private Patient patient;
    private String dateOfVisit;

    public DoctorsVisit() {
    }

    public DoctorsVisit(String dateOfVisit) {
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
        DoctorsVisit that = (DoctorsVisit) o;
        return Objects.equals(doctor, that.doctor) && Objects.equals(patient, that.patient) && Objects.equals(dateOfVisit, that.dateOfVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, dateOfVisit);
    }
}
