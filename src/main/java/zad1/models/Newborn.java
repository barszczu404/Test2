package zad1.models;

import java.util.Map;
import java.util.Objects;

public class Newborn {

    private int id;
    private String gender;
    private String firstName;
    private String dateOfBirth;
    private double weight;
    private int height;
    private Mother mother;


    public Newborn(int id, String gender, String firstName, String dateOfBirth, double weight, int height) {
        this.id = id;
        this.gender = gender;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        //if (mother.getId() == this.mother.getId())
        this.mother = mother;
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
        Newborn newborn = (Newborn) o;
        return id == newborn.id && Double.compare(weight, newborn.weight) == 0 && height == newborn.height && Objects.equals(gender, newborn.gender) && Objects.equals(firstName, newborn.firstName) && Objects.equals(dateOfBirth, newborn.dateOfBirth) && Objects.equals(mother, newborn.mother);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, firstName, dateOfBirth, weight, height, mother);
    }

    public static Newborn createFromString(String line){
        String[] dataFromLine = line.split(" ");
        Newborn newborn = new Newborn(Integer.parseInt(dataFromLine[0]), dataFromLine[1], dataFromLine[2], dataFromLine[3], Integer.parseInt(dataFromLine[4]), Integer.parseInt(dataFromLine[5]));
        return newborn;
    }

     public static Newborn createFromString(String line, Map<Integer, Mother> mothersMap){
        String[] dataFromLine = line.split(" ");
         int motherId = Integer.valueOf(dataFromLine[6]);
         Newborn newborn = new Newborn(Integer.parseInt(dataFromLine[0]), dataFromLine[1], dataFromLine[2], dataFromLine[3], Integer.parseInt(dataFromLine[4]), Integer.parseInt(dataFromLine[5]));
         Mother motherForNewborn = mothersMap.get(motherId);
         newborn.setMother(motherForNewborn);
         motherForNewborn.getChildren().add(newborn);
        return newborn;
    }


}
