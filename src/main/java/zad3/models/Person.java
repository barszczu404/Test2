package zad3.models;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person {

    protected String firstName;
    protected String lastName;
    protected String pesel;
    protected String city;

    public Person(String firstName, String lastName, String pesel, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public abstract double getIncome();

    public abstract String getData();

    public static Person createFromData(String data){
        String[] split = data.split(",");
        return switch (split[0]) {
            case "Employee" -> new Employee(split[1], split[2], split[3], split[4], split[5], Double.parseDouble(split[6]));
            case "Student" -> new Student(split[1], split[2], split[3], split[4], split[5], Double.parseDouble(split[6]));
            default -> null;
        };

    }

    public String getGender(){
        if(getPesel().charAt(9) %2 == 0){
            return "kobieta";
        }
        return "mężczyzna";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, city);
    }
}
