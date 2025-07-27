package zad2.services;

import zad2.models.Doctor;
import zad2.models.Visit;
import zad2.models.Patient;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ClinicService {

    //- znajdź lekarza ktory miał najwięcej wizyt
    public static Doctor getDoctorWhoMadeMostVisits(Map<Integer, Doctor> doctors) {
        Doctor superDoctor = null;
        for (Map.Entry<Integer, Doctor> entry : doctors.entrySet()) {
            if (superDoctor == null || superDoctor.getVisits().size() < entry.getValue().getVisits().size()) {
                superDoctor = entry.getValue();
            }
        }
        return superDoctor;
    }

    //   - znajdź pacjenta który miał najwięcej wizyt
    public static Patient getPatientWhoHasMostVisits(Map<Integer, Patient> patients) {
        Patient sicklyPatient = null;
        for (Map.Entry<Integer, Patient> entry : patients.entrySet()) {
            if (sicklyPatient == null || sicklyPatient.getVisits().size() < entry.getValue().getVisits().size()) {
                sicklyPatient = entry.getValue();
            }
        }
        return sicklyPatient;
    }

    //- która specalizacja cieszy się największym powodzeniem?
    public static String getDoctorsFavSpecialization(List<Doctor> doctors) {
        Map<String, Integer> specializationsCountersMap = new HashMap<>();
        for (Doctor doctor : doctors) {
            specializationsCountersMap.put(doctor.getSpecialization(), specializationsCountersMap.getOrDefault(doctor.getSpecialization(), 0) + 1);
        }
        //z mapy wyciagnac entrySet i znalezc obiekt entry o najwyzszej wrtosci czyli value() i wyjac jego klucz
        Set<Map.Entry<String, Integer>> specializationsSet = specializationsCountersMap.entrySet();

        int specializationsOccurrencesCnt = 0;
        Map.Entry<String, Integer> commonSpecialization = null;
        for (Map.Entry<String, Integer> entry : specializationsSet){
            if (entry.getValue() > specializationsOccurrencesCnt){
                specializationsOccurrencesCnt = entry.getValue();
                commonSpecialization = entry;//wyjac obiket a nie wartosc
            }

        }
        //rzutowanie tylko wtedy gdy dany typ znajduje sie w hierarchii!
        return commonSpecialization.getKey();

    }

    //- którego roku było najwięcej wizyt?
    public static int getMostVisitsYear(List<Visit> visits) {
        Map<Integer, Integer> yearsCnt = new HashMap<>();

//        List<LocalDate> years = new ArrayList<>();
        int tmpYear = 0;
        for (Visit visit : visits) {
            LocalDate date = LocalDate.parse(visit.getDateOfVisit());
            tmpYear = date.getYear();
//            years.add(date);
//        }
//        for (int i = 0; i < years.size(); i++) {
//            int counter = 0;
//            if (years.get(i).equals()) {
//                tmpYear = years.get(i);
//                counter++;
//            }
            yearsCnt.put(tmpYear, yearsCnt.getOrDefault(tmpYear, 0) + 1);
        }
        int mostCommonYear = 0;
        for (Map.Entry<Integer, Integer> entry : yearsCnt.entrySet()) {
            if (entry.getValue() > mostCommonYear) {
                mostCommonYear = entry.getValue();
            }
        }
        return mostCommonYear;
    }

    //- wypisz top 5 najstarszych lekarzy
    public static List<Doctor> get5OldestDoctors(Map<Integer, Doctor> doctors) {
        List<Doctor> oldest5Doctors = new ArrayList<>();
        int doctorsAge = 0;

        Map<Integer, Doctor> doctorsAgeMap = new HashMap<>();
        for (Map.Entry<Integer, Doctor> entry : doctors.entrySet()) {
            String birthDate = entry.getValue().getDateOfBirth();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate doctorBirthDate = LocalDate.parse(birthDate, dateTimeFormatter);
            LocalDate now = LocalDate.now();
            Period period = Period.between(doctorBirthDate, now);
            doctorsAge = period.getYears();
            doctorsAgeMap.put(doctorsAge, entry.getValue());
        }
        Map<Integer, Doctor> sortedMap = doctorsAgeMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        for (int i = Math.max(0, sortedMap.size() - 5); i < sortedMap.size(); i++) {
            oldest5Doctors.add(sortedMap.get(i));
        }
        return oldest5Doctors;
    }

    //- wypisz top 5 lekarzy co mieli najwiecej wizyt
    public static List<Doctor> get5DoctorsThatMadeMostVisits(Map<Integer, Doctor> doctors) {
        List<Doctor> most5EffectiveDoctors = new ArrayList<>();
        ArrayList<Doctor> doctorsArray = new ArrayList<>(doctors.values());
        Comparator<Doctor> comparator = new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getVisits().size() - o2.getVisits().size();
            }
        };
        Collections.sort(doctorsArray, comparator);
        for (int i = Math.max(0, doctorsArray.size() - 5); i < doctorsArray.size(); i++) {
            most5EffectiveDoctors.add(doctorsArray.get(i));
        }
        return most5EffectiveDoctors;
    }

    //- zwroc pacjentow ktorzy byli u minumum 5ciu roznych lekarzy
    public static List<Patient> getPatientsThatVisitsAbove5Doctors(Map<Integer, Patient> patients) {
        List<Patient> patientsVisited5DifferentDoctors = new ArrayList<>();
        for (Map.Entry<Integer, Patient> entry : patients.entrySet()) {
            if (entry.getValue().getVisits().size() <= 5) {
                for (Visit visit : entry.getValue().getVisits()) {
                    for (int i = 0; i < entry.getValue().getVisits().size(); i++) {
                        if (!(entry.getValue().getVisits().get(0).equals(entry.getValue().getVisits().get(i)))) {
                            patientsVisited5DifferentDoctors.add(entry.getValue());
                        }
                    }
                }
            }
        }
        return patientsVisited5DifferentDoctors;
    }

    //- zwroc lekarzy ktorzy przyjeli tylko jednego pacjenta
    public static List<Doctor> getDoctorsThatHaveOnly1Patient(Map<Integer, Doctor> doctors) {
        List<Doctor> unpopularDoctors = new ArrayList<>();
        for (Map.Entry<Integer, Doctor> entry : doctors.entrySet()) {
            if (entry.getValue().getVisits().size() == 1) ;
            unpopularDoctors.add(entry.getValue());
        }
        return unpopularDoctors;
    }
}
