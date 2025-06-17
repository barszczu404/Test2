package zad1.app;

import zad1.models.Mother;
import zad1.models.Newborn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

public class DeliveryRoomRunner {
    public static void main(String[] args) {

        Mother[] mothers = new Mother[174];
        Newborn[] babyArray = new Newborn[180];

        try {
            Scanner scanner = new Scanner(new FileReader("src/main/java/zad1/models/noworodki.txt"));

            Scanner scanner2 = new Scanner(new FileReader("src/main/java/zad1/models/mamy.txt"));


            while (scanner2.hasNext()) {
                for (int j = 0; j < mothers.length; j++) {
                    String line2 = scanner2.nextLine();
                    String[] momsData = line2.split(" ");
                    Mother mother = new Mother(Integer.parseInt(momsData[0]), momsData[1], Integer.parseInt(momsData[2]));
                    mothers[j] = mother;
                }
            }

            while (scanner.hasNext()) {
                for (int i = 0; i < babyArray.length; i++) {
                    String line = scanner.nextLine();
                    String[] dataFromLine = line.split(" ");
                    Newborn newborn = new Newborn(Integer.parseInt(dataFromLine[0]), dataFromLine[1], dataFromLine[2], dataFromLine[3], Integer.parseInt(dataFromLine[4]), Integer.parseInt(dataFromLine[5]));
                    for (int j = 0; j < babyArray.length; j++) {
                        for (Mother mother : mothers) {
                            if (mother.getId() == Integer.parseInt(dataFromLine[6])) {
                                newborn.setMother(mother);
                                mother.getChildren().add(newborn);
                            }
                        }
                    }
                    babyArray[i] = newborn;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(getHeightAndNameOfTallestBoyAndGirl(babyArray));
        System.out.println(getDayOfBirthForMostChildren(babyArray));
        System.out.println(getNamesOfMothersOfBabyWeightOver4000G(mothers));
        System.out.println(getGirlsNamedAfterMother(babyArray));
        System.out.println(getMothersOfTwins(babyArray));
    }

    //a) Podaj imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.
    public static String getHeightAndNameOfTallestBoyAndGirl(Newborn[] newborns) {
        String tallestBabies = "";
        ArrayList<Newborn> boys = new ArrayList<>();
        ArrayList<Newborn> girls = new ArrayList<>();
        Comparator<Newborn> comparator = new Comparator<Newborn>() {
            @Override
            public int compare(Newborn o1, Newborn o2) {
                return o1.getHeight() - o2.getHeight();
            }
        };
        for (int i = 0; i < newborns.length; i++) {
            for (Newborn newborn : newborns) {
                if ((newborn.getGender().equals("s"))) {
                    boys.add(newborn);
                } else {
                    girls.add(newborn);
                }
            }
            //boys.sort(Comparator.comparingDouble(Newborn::getHeight));
            Collections.sort(boys, comparator);
            Newborn tallestBoy = boys.get((boys.size() - 1));

            //girls.sort(Comparator.comparingDouble(Newborn::getHeight));
            Collections.sort(girls, comparator);
            Newborn tallestGirl = girls.get((girls.size() - 1));
            tallestBabies = tallestBoy.getFirstName() + " " + tallestBoy.getHeight() + ", " + tallestGirl.getFirstName() + " " + tallestGirl.getHeight();
        }
        return tallestBabies;
    }

    //b) W którym dniu tygodnia urodziło się najwięcej dzieci? Podaj dzien tygodnia i liczbe dzieci
    public static String getDayOfBirthForMostChildren(Newborn[] newborns) {
        String dayOfBirthMostChildren = "";
        int childrenCnt = 0;
        int mondaysCnt = 0;
        int tuesdayCnt = 0;
        int wednesdayCnt = 0;
        int thursdayCnt = 0;
        int fridayCnt = 0;
        int saturdayCnt = 0;
        int sundayCnt = 0;

        for (int i = 0; i < newborns.length; i++) {
            LocalDate data = LocalDate.parse(newborns[i].getDateOfBirth());
            DayOfWeek dayOfWeek = data.getDayOfWeek();
            switch (dayOfWeek.getValue()) {
                case 1 -> mondaysCnt++;
                case 2 -> tuesdayCnt++;
                case 3 -> wednesdayCnt++;
                case 4 -> thursdayCnt++;
                case 5 -> fridayCnt++;
                case 6 -> saturdayCnt++;
                case 7 -> sundayCnt++;
                default -> {
                    try {
                        throw new Exception("błędna data");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        int[] daysOfWeek = new int[]{mondaysCnt, tuesdayCnt, wednesdayCnt, thursdayCnt, fridayCnt, saturdayCnt, sundayCnt};
        int max = 0;
        int indexOfDay = 0;
        for (int i = 0; i < daysOfWeek.length; i++) {
            if (daysOfWeek[i] > max) {
                max = daysOfWeek[i];
                indexOfDay = i;
            }
        }
        Arrays.sort(daysOfWeek);
        childrenCnt = daysOfWeek[6];
        DayOfWeek dayOfBirth = DayOfWeek.of(indexOfDay + 1); //zeby ustawic nr dni od 1 do 7
        dayOfBirth.getDisplayName(TextStyle.FULL, new Locale("pl", "PL"));
        dayOfBirthMostChildren = dayOfBirth + " " + childrenCnt;

        return dayOfBirthMostChildren;
    }

    //c) Podaj imiona kobiet w wieku poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g.
    public static String getNamesOfMothersOfBabyWeightOver4000G(Mother[] mothers) {
        StringBuilder sb = new StringBuilder();
        String motherNames = "";
        for (Mother mother : mothers) {
            if (mother.getAge() < 25) {
                for (Newborn newborn : mother.getChildren()) {
                    if (newborn.getWeight() > 4000) {
                        sb.append(mother.getFirstName());
                        sb.append(", ");
                        break;
                    }
                }
            }
        }
        motherNames = sb.toString();
        return motherNames;
    }

//d) Podaj imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.

    public static String getGirlsNamedAfterMother(Newborn[] newborns) {
        String girlData;
        StringBuilder sb = new StringBuilder("");
        ArrayList<Newborn> girls = new ArrayList<>();
        ArrayList<Newborn> girlsNamedAfterMothers = new ArrayList<>();
        for (Newborn newborn : newborns) {
            if (newborn.getGender().equals("c")) {
                girls.add(newborn);
            }
        }
        for (Newborn newborn : girls) {
            if (newborn.getMother().getFirstName().equals(newborn.getFirstName())) {
                girlsNamedAfterMothers.add(newborn);
                sb.append(newborn.getFirstName());
                sb.append(" ");
                sb.append(newborn.getDateOfBirth());
                sb.append(", ");
            }
        }
        girlData = sb.toString();

        return girlData;
    }

    //e) Znajdz matki które urodziły bliźnięta
    public static String getMothersOfTwins(Newborn[] newborns) {
        String mothersOfTwins;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < newborns.length; i++) {
            if (newborns[0].getMother().equals(newborns[i].getMother())) {
                if (newborns[0].getDateOfBirth().equals(newborns[i].getDateOfBirth())) {
                    sb.append(newborns[i].getMother().getFirstName());
                    sb.append(", ");
                }
            }
        }
        mothersOfTwins = sb.toString();
        return mothersOfTwins;
    }
}



