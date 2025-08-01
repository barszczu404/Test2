package zad3.app;

import zad3.models.Employee;
import zad3.models.Person;
import zad3.models.Student;

import java.io.*;
import java.util.*;

public class Runner {
    public static void main(String[] args) {

        Student janek = new Student("Jan", "Zabielski", "97030209155", "Kraków", "A", 800.50);
        Student marta = new Student("Marta", "Jarząbek", "03102300422", "Opole", "B", 1000);
        Student olaf = new Student("Olaf", "Jarocki", "98023009177", "Włocławek", "A", 960);
        Employee stach = new Employee("Stanisław", "Barwiński", "65060804233", "Katowice", "sztygar", 7300);
        Employee marlena = new Employee("Marlena", "Opoka", "79041509188", "Sławno", "lekarz", 4200);
        Employee judyta = new Employee("Judyta", "Zajakała", "86122506466", "Lublin", "kasjerka", 3500);
        Employee zenek = new Employee("Zenon", "Łodyga", "86102399877", "Ząbkowice", "śmieciarz", 10000);

        Person[] people = new Person[]{null,janek, marta, olaf, stach, marlena, judyta, null, zenek};
        Person highestIncomePerson = getHighestIncomePerson(people);
        System.out.println("highestIncomePerson = " + highestIncomePerson);
        int numberOfWomenInGroup = getNumberOfWomenInGroup(people);
        System.out.println("numberOfWomenInGroup = " + numberOfWomenInGroup);
        List<Person> peopleList = Arrays.asList(people);
        ArrayList<Person> peopleArrayList = new ArrayList<>(peopleList);
        savePersonListToFile(peopleList, "ludziska.txt");

        List<Person> loadedPeople = readPeopleFromFileToList("ludziska.txt");

    }

    public static Person getHighestIncomePerson(Person[] array) {
        int index = 0;
        Person highestIncomePerson = array[index];
        for (int i = 0; i <array.length; i++) {
            if (highestIncomePerson == null){
                highestIncomePerson = array[index++];
                continue;
            }

            if (array[i] != null && array[i].getIncome() > highestIncomePerson.getIncome()){
                highestIncomePerson = array[i];
            }
        }
        return highestIncomePerson;
    }

    public static int getNumberOfWomenInGroup(Person[] array) {
        int women = 0;
        for (Person person : array) {

            if (person != null && person.getGender().equals("kobieta")) {
                women++;
            }
        }
        return women;
    }

    // stworz metode pozwalającą zapisać liste osób do pliku w taki sposób aby można było stworzyć metodę wczytującą liste osob z pliku.
    public static void savePersonListToFile(List<Person> list, String fileName) {
        try (
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw);
                ) {
            for (Person person : list) {
                if (person != null) {
                    bw.write(person.getData());
                    bw.newLine();
                }

            }

        } catch (IOException e) {
           e.printStackTrace();
        }
    }


    public static List<Person> readPeopleFromFileToList(String fileName) {
        List<Person> loadedPeople = new ArrayList<>();

        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                Person personFromData = Person.createFromData(line);
                loadedPeople.add(personFromData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedPeople;

    }

}
