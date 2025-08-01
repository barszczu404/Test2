package zad2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor extends Person {


    private String specialization;
    private String nip;
    private List<Visit> visits = new ArrayList<>();


    public Doctor(int id, String firstName, String lastName, String pesel, String dateOfBirth, String specialization, String nip) {
        super(id, firstName, lastName, pesel, dateOfBirth);
        this.specialization = specialization;
        this.nip = nip;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public List<Visit> getVisits() {
        return visits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(specialization, doctor.specialization) && Objects.equals(nip, doctor.nip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialization, nip);
    }
}
