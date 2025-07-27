package zad2.models;

import java.util.ArrayList;
import java.util.List;

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

}
