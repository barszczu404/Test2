package zad3.models;

import java.util.Objects;

public class Employee extends Person {

    private String position;
    private double salary;

    public Employee(String firstName, String lastName, String pesel, String city, String position, double salary) {
        super(firstName, lastName, pesel, city);
        this.position = position;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public double getIncome() {
        return salary;
    }

    @Override
    public String getData() {
        return getClass().getSimpleName() + ","
                + firstName + ","
                + lastName + ","
                + pesel + ","
                + city + ","
                + position + ","
                + salary;
    }


    @Override
    public String toString() {
        return super.toString() +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 && Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, salary);
    }
}
