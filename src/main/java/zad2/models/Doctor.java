package zad2.models;

import java.util.ArrayList;
import java.util.Objects;

public class Doctor {

    private int id;
    private String lastName;
    private String firstName;
    private String specialization;
    private String dateOfBirth;
    private String nip;
    private String pesel;
    private ArrayList<DoctorsVisit> doctorsVisits;
    private int age;

    public Doctor(int id, String lastName, String firstName, String specialization, String dateOfBirth, String nip, String pesel) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.specialization = specialization;
        this.dateOfBirth = dateOfBirth;
        this.nip = nip;
        this.pesel = pesel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public ArrayList<DoctorsVisit> getDoctorsVisits() {
        return doctorsVisits;
    }

    public void setDoctorsVisits(ArrayList<DoctorsVisit> doctorsVisits) {
        this.doctorsVisits = doctorsVisits;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && Objects.equals(lastName, doctor.lastName) && Objects.equals(firstName, doctor.firstName) && Objects.equals(specialization, doctor.specialization) && Objects.equals(dateOfBirth, doctor.dateOfBirth) && Objects.equals(nip, doctor.nip) && Objects.equals(pesel, doctor.pesel) && Objects.equals(doctorsVisits, doctor.doctorsVisits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, specialization, dateOfBirth, nip, pesel, doctorsVisits);
    }
}
