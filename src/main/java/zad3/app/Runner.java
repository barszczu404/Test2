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

        Person[] people = new Person[]{janek, marta, olaf, stach, marlena, judyta};
        getHighestIncome(people);
        getNumberOfWomanInGroup(people);
        List<Person> peopleList = Arrays.asList(people);
        ArrayList<Person> peopleArrayList = new ArrayList<>(peopleList);
        savePersonListToFile(peopleArrayList, "\"C:\\Users\\admin\\Desktop\\testzad3.txt\"");
        getListOfPeopleFromFile("C:\\Users\\admin\\IdeaProjects\\Test2\\src\\main\\java\\zad2\\lekarze.txt");


    }

    public static Person getHighestIncome(Person[] array) {
        //Person[] peopleSortedByIncome = new Person[array.length];
        ArrayList<Person> peopleSortedByIncome = new ArrayList<>();
        //int personCnt = 0;
        for (Person person : array) {
            if (person.getIncome() < 0) {
//               peopleSortedByIncome[personCnt] = person;
//               personCnt++;
                peopleSortedByIncome.add(person);
            }
        }
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (int) (o1.getIncome() - o2.getIncome());
            }
        };
        Collections.sort(peopleSortedByIncome, comparator);
        //peopleSortedByIncome.sort(Comparator.comparingDouble(Person::getIncome));
        Person personWithHighestIncome = peopleSortedByIncome.get((peopleSortedByIncome.size() - 1));//zapis nie dziala -1
        return personWithHighestIncome;
    }

    public static int getNumberOfWomanInGroup(Person[] genderArray) {
        int women = 0;
        //int man = 0;
        for (Person person : genderArray) {
            if (person.getGender() == "kobieta") {
                women++;
            }
            //man++;
        }
        return women;
    }

    // stworz metode pozwalającą zapisać liste osób do pliku w taki sposób aby można było stworzyć metodę wczytującą liste osob z pliku.
    public static File savePersonListToFile(ArrayList<Person> personArrayList, String fileName) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(personArrayList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new File(fileName);
    }

    public static ArrayList getListOfPeopleFromFile(String fileName) {
        ArrayList<Person> peopleFromFile = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Person> dataFromFile = (ArrayList<Person>) objectInputStream.readObject();
            dataFromFile.forEach(System.out::println);
            peopleFromFile.addAll(dataFromFile);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList(peopleFromFile);
    }
}
