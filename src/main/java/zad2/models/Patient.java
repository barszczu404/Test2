package zad2.models;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {

    private List<Visit> visits = new ArrayList<>();

    public Patient(int id, String firstName, String lastName, String pesel, String dateOfBirth) {
        super(id, firstName, lastName, pesel, dateOfBirth);
    }

    public List<Visit> getVisits() {
        return visits;
    }


}
