package zad3.models;

import java.util.Objects;

public class Student extends Person {

    private String group;
    private double scholarship;

    public Student(String firstName, String lastName, String pesel, String city, String group, double scholarship) {
        super(firstName, lastName, pesel, city);
        this.group = group;
        this.scholarship = scholarship;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getScholarship() {
        return scholarship;
    }

    public void setScholarship(double scholarship) {
        this.scholarship = scholarship;
    }

    @Override
    public double getIncome() {
        return scholarship;
    }


    @Override
    public String getData() {
        return getClass().getSimpleName() + ","
                + firstName + ","
                + lastName + ","
                + pesel + ","
                + city + ","
                + group + ","
                + scholarship;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", group='" + group + '\'' +
                ", scholarship=" + scholarship +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(scholarship, student.scholarship) == 0 && Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), group, scholarship);
    }
}
