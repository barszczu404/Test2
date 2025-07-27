package zad1.services;

import zad1.models.Mother;
import zad1.models.Newborn;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class DeliveryRoomService {

    //a) Podaj imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.
    public static Newborn getHighestNewbornByGender(List<Newborn> list, String genderMark) {
        Newborn theHighest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getHeight() > theHighest.getHeight() && list.get(i).getGender().equals(genderMark)) {
                theHighest = list.get(i);
            }
        }
        return theHighest;
    }



    public static Map<DayOfWeek, Integer> getDayOfWeekBornChildMap(List<Newborn> list) {
//        String dateString = "1991-10-10";
//        LocalDate localDate = LocalDate.parse(dateString);
//        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
//        System.out.println(dayOfWeek);


        int[] daysOfWeekCounters = new int[7];

        for (int i = 0; i < list.size(); i++) {
            LocalDate data = LocalDate.parse(list.get(i).getDateOfBirth());
            DayOfWeek dayOfWeek = data.getDayOfWeek();
            switch (dayOfWeek.getValue()) {
                case 1 -> daysOfWeekCounters[0]++;
                case 2 -> daysOfWeekCounters[1]++;
                case 3 -> daysOfWeekCounters[2]++;
                case 4 -> daysOfWeekCounters[3]++;
                case 5 -> daysOfWeekCounters[4]++;
                case 6 -> daysOfWeekCounters[5]++;
                case 7 -> daysOfWeekCounters[6]++;
                default -> {
                    try {
                        throw new Exception("błędna data");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        Map<DayOfWeek, Integer> dayOfWeekIntegerMap = new LinkedHashMap<>();
        for (int i = 0; i < daysOfWeekCounters.length; i++) {
            dayOfWeekIntegerMap.put(dayOfWeeks[i], daysOfWeekCounters[i]);
        }

        return dayOfWeekIntegerMap;
    }

    //b) W którym dniu tygodnia urodziło się najwięcej dzieci? Podaj dzien tygodnia i liczbe dzieci
    public static Map.Entry<DayOfWeek, Integer> getDayOfWeekWithMostBirths(List<Newborn> newborns) {
        Map<DayOfWeek, Integer> dayOfWeekBornChildMap = getDayOfWeekBornChildMap(newborns);
        Set<Map.Entry<DayOfWeek, Integer>> entries = dayOfWeekBornChildMap.entrySet();
        Map.Entry<DayOfWeek, Integer> maxEntry = null;
        for (Map.Entry<DayOfWeek, Integer> entry : entries) {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue()){
                maxEntry = entry;
            }
        }
        return maxEntry;


    }

    //c) Podaj imiona kobiet w wieku poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g.
    public static List<Mother> getMothersUnder25YearsOldWithBabiesWithWeightOver4000G(List<Mother> mothers) {
        List<Mother> mothersTmpList = new ArrayList<>();
        for (Mother mother : mothers) {
            if (mother.getAge() < 25) {
                for (Newborn newborn : mother.getChildren()) {
                    if (newborn.getWeight() > 4000) {
                       mothersTmpList.add(mother);
                    }
                }
            }
        }
        return mothersTmpList;
    }

//d) Podaj imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.

    public static List<Newborn> getGirlsNamedAfterMother(List<Newborn> newborns) {
        ArrayList<Newborn> girls = new ArrayList<>();
        for (Newborn newborn : newborns) {
            if (newborn.getGender().equals("c") && newborn.getMother().getFirstName().equals(newborn.getFirstName())) {
                girls.add(newborn);
            }
        }
        return girls;
    }

    //e) Znajdz matki które urodziły bliźnięta
    public static List<Mother> getMothersOfTwins(List<Mother> mothers) {
        List<Mother> mothersOfTwins = new ArrayList<>();
       for (Mother mother : mothers){
           List<Newborn> childrenList = mother.getChildren();
           TreeSet<Newborn> treeSet = new TreeSet<>(Comparator.comparing(Newborn::getDateOfBirth));
           for (Newborn newborn : childrenList) {
               treeSet.add(newborn);
           }
           if (childrenList.size() != treeSet.size()) {
               mothersOfTwins.add(mother);
           }
       }
        return mothersOfTwins;
    }
}
