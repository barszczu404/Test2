package zad2.models;

import java.util.ArrayList;
import java.util.Objects;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String dateOfBirth;
    private ArrayList<DoctorsVisit> visits;

    public Patient(int id, String firstName, String lastName, String pesel, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ArrayList<DoctorsVisit> getVisits() {
        return visits;
    }

    public void setVisits(ArrayList<DoctorsVisit> visits) {
        this.visits = visits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(pesel, patient.pesel) && Objects.equals(dateOfBirth, patient.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pesel, dateOfBirth);
    }
}
