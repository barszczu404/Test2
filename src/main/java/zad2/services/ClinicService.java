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
        Set<Map.Entry<String, Integer>> specializationsSet = specializationsCountersMap.entrySet();

        int specializationsOccurrencesCnt = 0;
        Map.Entry<String, Integer> commonSpecialization = null;
        for (Map.Entry<String, Integer> entry : specializationsSet) {
            if (entry.getValue() > specializationsOccurrencesCnt) {
                specializationsOccurrencesCnt = entry.getValue();
                commonSpecialization = entry;//wyjac obiket a nie wartosc
            }

        }
        return commonSpecialization.getKey();

    }

    //- którego roku było najwięcej wizyt?
    public static int getMostVisitsYear(List<Visit> visits) {
        Map<Integer, Integer> yearsCnt = new HashMap<>();

        int tmpYear = 0;
        for (Visit visit : visits) {
            LocalDate date = LocalDate.parse(visit.getDateOfVisit());
            tmpYear = date.getYear();
            yearsCnt.put(tmpYear, yearsCnt.getOrDefault(tmpYear, 0) + 1);
        }
        int mostCommonYear = 0;
        for (Map.Entry<Integer, Integer> entry : yearsCnt.entrySet()) {
            if (entry.getKey() > mostCommonYear) {
                mostCommonYear = entry.getKey();
            }
        }
        return mostCommonYear;
    }

    //- wypisz top 5 najstarszych lekarzy
    public static List<Doctor> get5OldestDoctors(Map<Integer, Doctor> doctors) {
        int doctorsAge = 0;
        Map<Doctor, Integer> doctorsAgeMap = new HashMap<>();
        for (Map.Entry<Integer, Doctor> entry : doctors.entrySet()) {
            String birthDate = entry.getValue().getDateOfBirth();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate doctorBirthDate = LocalDate.parse(birthDate, dateTimeFormatter);
            LocalDate now = LocalDate.now();
            Period period = Period.between(doctorBirthDate, now);
            doctorsAge = period.getYears();
            doctorsAgeMap.put(entry.getValue(), doctorsAge);
        }
        Map<Doctor, Integer> sortedMap = doctorsAgeMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        List<Doctor> oldest5Doctors = doctorsAgeMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Doctor, Integer>comparingByValue().reversed()) // sortujemy malejąco po wieku
                .limit(5) // bierzemy 5 najstarszych
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

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
    public static List<Patient> getPatientsThatVisitsAbove5Doctors(Map<Integer, Patient> patientsMap) {
        List<Patient> patientsVisited5DifferentDoctors = new ArrayList<>();
        for (Patient p : patientsMap.values()) {
            Set<Doctor> doctorSet = new HashSet<>();
            for (Visit visit : p.getVisits()) {
                doctorSet.add(visit.getDoctor());
            }
            if (doctorSet.size() >= 5) {
                patientsVisited5DifferentDoctors.add(p);
            }

        }

        return patientsVisited5DifferentDoctors;
    }

    //- zwroc lekarzy ktorzy przyjeli tylko jednego pacjenta
    public static List<Doctor> getDoctorsThatHaveOnly1Patient(Map<Integer, Doctor> doctorMap) {
        List<Doctor> unpopularDoctors = new ArrayList<>();
        for (Doctor doctor : doctorMap.values()) {
            Set<Patient> patientsSet = new HashSet<>();
            for (Visit v : doctor.getVisits()) {
                patientsSet.add(v.getPatient());
            }
            if (patientsSet.size() == 1) {
                unpopularDoctors.add(doctor);
            }
        }

        return unpopularDoctors;
    }
}

