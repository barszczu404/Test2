package zad1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mother {

    private int id;
    private String firstName;
    private int age;
    private List<Newborn> children = new ArrayList<>();


    public Mother(int id, String firstName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Newborn> getChildren() {
        return children;
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
        return id == mother.id && age == mother.age && Objects.equals(firstName, mother.firstName);//w relacjach dwukierunkowych to jeden z modeli w metodach equals() hashCode() toString() nie moze zawierac relaci do drugiego modleu bo to wywoluje StackOverFlowError
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, age);
    }

    public static Mother createFromString(String line) {
        String[] momsData = line.split(" ");
        Mother mother = new Mother(Integer.parseInt(momsData[0]), momsData[1], Integer.parseInt(momsData[2]));
        return mother;
    }
}
