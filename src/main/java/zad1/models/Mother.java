package zad1.models;

import java.util.ArrayList;
import java.util.Objects;

public class Mother {

    private int id;
    private String firstName;
    private int age;
    private ArrayList<Newborn> children = new ArrayList<>();

    public Mother() {
    }

    public Mother(int id, String firstName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }

    public Mother(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Newborn> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Newborn> children) {
        this.children = children;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mother mother = (Mother) o;
        return id == mother.id && age == mother.age && Objects.equals(firstName, mother.firstName) && Objects.equals(children, mother.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, age, children);
    }
}
