package zad1.app;

import zad1.models.Mother;
import zad1.models.Newborn;
import zad1.services.DeliveryRoomService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.util.*;

public class DeliveryRoomRunner {
    public static void main(String[] args) {

        Map<Integer, Mother> mothersMap = new HashMap<>();
        List<Newborn> babies = new ArrayList<>();

        try (
                Scanner scanner1 = new Scanner(new FileReader("src/main/java/zad1/models/mamy.txt"));
                Scanner scanner2 = new Scanner(new FileReader("src/main/java/zad1/models/noworodki.txt"));
        ) {

            while (scanner1.hasNext()) {
                Mother m = Mother.createFromString(scanner1.nextLine());
                mothersMap.put(m.getId(), m);
            }

            while (scanner2.hasNext()) {
               Newborn n = Newborn.createFromString(scanner2.nextLine(), mothersMap);
               babies.add(n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Newborn theHighestSon = DeliveryRoomService.getHighestNewbornByGender(babies, "s");
        System.out.println("Najwyższy syn to: " + theHighestSon.getFirstName() + ", wzrost: " + theHighestSon.getHeight());
        Newborn theHighestDaughter = DeliveryRoomService.getHighestNewbornByGender(babies, "c");
        System.out.println("Najwyższa córka to: " + theHighestDaughter.getFirstName() + ", wzrost: " + theHighestDaughter.getHeight());


        System.out.println("--------------");
        Map<DayOfWeek, Integer> dayOfWeekBornChildMap = DeliveryRoomService.getDayOfWeekBornChildMap(babies);

//        Map.Entry<DayOfWeek, Integer> dayOfWeekWithMaxBirths = dayOfWeekBornChildMap.entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .orElseThrow();
//        System.out.println(dayOfWeekWithMaxBirths);

        System.out.println("DeliveryRoomService.getDayOfWeekWithMostBirths(babies) = " + DeliveryRoomService.getDayOfWeekWithMostBirths(babies));

        List<Mother> mothersUnder25YearsOldWithBabiesWithWeightOver4000G = DeliveryRoomService.getMothersUnder25YearsOldWithBabiesWithWeightOver4000G(new ArrayList<>(mothersMap.values()));//w mijescu na argument tworzymy arrayliste na podstawie kolekcji matek ta kolekcja zostala pobrana z mapy
        System.out.println("Imiona matek z poniżej 25 lat z dziećmi o wadze minimum 4000g:");
        for (Mother mother : mothersUnder25YearsOldWithBabiesWithWeightOver4000G) {
            System.out.print(mother.getFirstName() + " ");
        }
        System.out.println();

        List<Newborn> girlsNamedAfterMother = DeliveryRoomService.getGirlsNamedAfterMother(babies);
        for (Newborn newborn : girlsNamedAfterMother) {
            System.out.println(newborn.getFirstName() + " " + newborn.getDateOfBirth());
        }


        System.out.println("-------------------");
        DeliveryRoomService.getMothersOfTwins(new ArrayList<>(mothersMap.values()));
//        System.out.println(getHeightAndNameOfTallestBoyAndGirl(babyArray));
//        System.out.println(getDayOfBirthForMostChildren(babyArray));
//        System.out.println(getNamesOfMothersOfBabyWeightOver4000G(mothers));
//        System.out.println(getGirlsNamedAfterMother(babyArray));
//        System.out.println(getMothersOfTwins(babyArray));
    }


}



