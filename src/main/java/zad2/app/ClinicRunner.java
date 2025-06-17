package zad2.app;

import zad2.models.Doctor;
import zad2.models.DoctorsVisit;
import zad2.models.Patient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClinicRunner {
    public static void main(String[] args) {

        Set<Doctor> doctors = new HashSet<>();
        Set<Patient> patients = new HashSet<>();
        Set<DoctorsVisit> doctorsVisits = new HashSet<>();

        try {
            Scanner scanner = new Scanner(new FileReader("src/main/java/zad2/lekarze.txt"));
            scanner.nextLine();

            Scanner scanner2 = new Scanner(new FileReader("src/main/java/zad2/pacjenci.txt"));
            scanner2.nextLine();

            Scanner scanner3 = new Scanner(new FileReader("src/main/java/zad2/wizyty.txt"));
            scanner3.nextLine();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] dataAboutDoctors = line.split("\t");
                Doctor doctor = new Doctor(Integer.parseInt(dataAboutDoctors[0]), dataAboutDoctors[1], dataAboutDoctors[2], dataAboutDoctors[3], dataAboutDoctors[4], dataAboutDoctors[5], dataAboutDoctors[6]);
                doctors.add(doctor); //hashset na dane zlosliwe na etapie pobierania danych
            }

            while (scanner2.hasNext()) {
                String line2 = scanner2.nextLine();
                String[] dataAboutPatients = line2.split("\t");
                Patient patient = new Patient(Integer.parseInt(dataAboutPatients[0]), dataAboutPatients[1], dataAboutPatients[2], dataAboutPatients[3], dataAboutPatients[4]);
                patients.add(patient);
            }

            while (scanner3.hasNext()) {
                String line3 = scanner3.nextLine();
                String[] dataAboutVisits = line3.split("\t");
                DoctorsVisit doctorsVisit = new DoctorsVisit();
                doctorsVisit.setDateOfVisit(dataAboutVisits[2]);
                for (Doctor doctor : doctors) {
                    if (dataAboutVisits[0].equals(doctor.getId())) { //nie przypisuje tu lekarza ani pacjenta i stad null
                        doctorsVisit.setDoctor(doctor);
                    }
                }
                for (Patient patient : patients) {
                    if (dataAboutVisits[1].equals(patient.getId())) {
                        doctorsVisit.setPatient(patient);
                    }
                }
                doctorsVisits.add(doctorsVisit);
            }

            for (Doctor doctor : doctors){
                for (DoctorsVisit doctorsVisit : doctorsVisits){
                    if (doctor.getId() == doctorsVisit.getDoctor().getId()){ //
                        doctor.getDoctorsVisits().add(doctorsVisit);
                    }
                }
            }

            for (Patient patient : patients){
                for (DoctorsVisit doctorsVisit : doctorsVisits){
                    if (patient.getId() == doctorsVisit.getPatient().getId()){
                        patient.getVisits().add(doctorsVisit);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(doctors.size()); //to potem wykasowac
        System.out.println(patients.size());
        System.out.println(doctorsVisits.size());

        getDoctorWhoMadeMostVisits(doctors);
        getPatientWhoHasMostVisits(patients);
        getDoctorsFavSpecialization(doctors);
        //getMostVisitsYear(doctorsVisits);
        get5OldestDoctors(doctors);
        get5DoctorsThatMadeMostVisits(doctors);
        getPatientsThatVisitsAbove5Doctors(patients);
        getDoctorsThatHaveOnly1Patient(doctors);

    }

    //- znajdź lekarza ktory miał najwięcej wizyt
    public static String getDoctorWhoMadeMostVisits(Set<Doctor> doctors) {//Set<DoctorsVisit> doctorsVisits
        String superDoctor = "";
        ArrayList<Doctor> doctorsArray = new ArrayList<>(doctors);
        Comparator<Doctor> comparator = new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getDoctorsVisits().size() - o2.getDoctorsVisits().size();
            }
        };
        Collections.sort(doctorsArray, comparator);
        superDoctor = "" + doctorsArray.get(doctorsArray.size());
//        List<Integer> doctorsId = new ArrayList<>();
//        for (DoctorsVisit doctorsVisit : doctorsVisits){
//            doctorsId.add(doctorsVisit.getDoctor().getId());
//        }
//        int mostCommonId = -1;
//        int maxOccurrences = 0;
//        for (int i = 0; i < doctorsId.size(); i++) {
//            int temporaryId = doctorsId.get(i);
//            int occurrences = 0;
//            for (int j = 0; j < doctorsId.size(); j++) {
//                if (doctorsId.get(j) == temporaryId){
//                    occurrences++;
//                }
//            }
//            if (occurrences > maxOccurrences){
//                maxOccurrences = occurrences;
//                mostCommonId = temporaryId;
//            }
//        }
//        for (DoctorsVisit doctorsVisit : doctorsVisits){
//            if (doctorsVisit.getDoctor().getId() == mostCommonId){
//                superDoctor = String.valueOf(doctorsVisit.getDoctor());
//            }
//        }
        return superDoctor;
    }

 //   - znajdź pacjenta który miał najwięcej wizyt
    public static String getPatientWhoHasMostVisits(Set<Patient> patients){
        String sicklyPatient;
        ArrayList<Patient> patientsArray = new ArrayList<>(patients);
        Comparator<Patient> comparator = new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getVisits().size() - o2.getVisits().size();
            }
        };
        Collections.sort(patientsArray, comparator);
        sicklyPatient = "" + patientsArray.get(patientsArray.size());

        return sicklyPatient;
    }

    //- która specalizacja cieszy się największym powodzeniem?
    public static String getDoctorsFavSpecialization(Set<Doctor>doctors){
        String commonSpecialization;
        List<String> specialization = new ArrayList<>();
        List<Integer> counters = new ArrayList<>();
        for (Doctor doctor : doctors){
            String doctorSpecialization = doctor.getSpecialization();
            int index = specialization.indexOf(doctorSpecialization);
            if (index == 1){
                specialization.add(doctorSpecialization);
                counters.add(1);
            } else {
                counters.set(index, counters.get(index)+ 1);
            }
        }
        List<String> result = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < counters.size(); i++) {
            int number = counters.get(i);
            if (number > max){
                max = number;
                result.clear();
                result.add(specialization.get(i));
            } else if (number == max){
                result.add(specialization.get(i));
            }
        }
        commonSpecialization = "" + result;
        return commonSpecialization;
    }

    //- którego roku było najwięcej wizyt?
    public static String getMostVisitsYear(Set<DoctorsVisit>doctorsVisits){
        String year = "";
        List<Integer> visitYear = new ArrayList<>();
        for (DoctorsVisit doctorsVisit : doctorsVisits){
            LocalDate visitDate = LocalDate.parse(doctorsVisit.getDateOfVisit());
            int yearOfVisit = visitDate.getYear();
            visitYear.add(yearOfVisit);
        }
        int mostCommonYear = -1;
        int maxOccurrences = 0;
        for (int i = 0; i < visitYear.size(); i++) {
            int tmpYear = visitYear.get(i);
            int occurrences = 0;
            for (int j = 0; j < visitYear.size(); j++) {
                if (visitYear.get(j)) == tmpYear){ //tu
                    occurrences++;
                }
            }
            if (occurrences > maxOccurrences){
                maxOccurrences = occurrences;
                mostCommonYear = tmpYear;
            }
        }
        year = "" + mostCommonYear;
        return year;
    }

    //- wypisz top 5 najstarszych lekarzy
    public static String get5OldestDoctors(Set<Doctor>doctors){
        String oldest5Doctors;
        StringBuilder sb = new StringBuilder("");
        ArrayList<Doctor> doctorsArray = new ArrayList<>(doctors);
        for (int i = 0; i < doctorsArray.size(); i++) {
            String birthDate = doctorsArray.get(i).getDateOfBirth();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate doctorBirthDate = LocalDate.parse(birthDate, dateTimeFormatter);
            LocalDate now = LocalDate.now();
            Period period = Period.between(doctorBirthDate, now);
            doctorsArray.get(i).setAge(period.getYears());
        }
        Comparator<Doctor> comparator = new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        Collections.sort(doctorsArray, comparator);
        for (int i = Math.max(0, doctorsArray.size() - 5); i < doctorsArray.size(); i++) {
            sb.append(doctorsArray.get(i));
            sb.append(", ");
        }
        oldest5Doctors = sb.toString();
        return oldest5Doctors;
    }

    //- wypisz top 5 lekarzy co mieli najwiecej wizyt
    public static String get5DoctorsThatMadeMostVisits(Set<Doctor>doctors){
        String most5EffectiveDoctors;
        StringBuilder sb = new StringBuilder("");
        ArrayList<Doctor> doctorsArray = new ArrayList<>(doctors);
        Comparator<Doctor> comparator = new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getDoctorsVisits().size() - o2.getDoctorsVisits().size();
            }
        };
        Collections.sort(doctorsArray, comparator);
        for (int i = Math.max(0, doctorsArray.size() - 5); i < doctorsArray.size(); i++) {
            sb.append(doctorsArray.get(i));
            sb.append(", ");
        }
        most5EffectiveDoctors = sb.toString();
        return most5EffectiveDoctors;
    }
    //- zwroc pacjentow ktorzy byli u minumum 5ciu roznych lekarzy
    public static String getPatientsThatVisitsAbove5Doctors(Set<Patient> patients) {
        String patientsVisited5DifferentDoctors;
        StringBuilder sb = new StringBuilder("");
        for (Patient patient : patients) {
            if (patient.getVisits().size() <= 5) {
                for (DoctorsVisit doctorsVisit : patient.getVisits()) {
                    for (int i = 0; i < patient.getVisits().size(); i++) {//?
                        if (!(patient.getVisits().get(0).equals(patient.getVisits().get(i)))) {
                            sb.append(patient);
                            sb.append(", ");
                        }
                    }
                }
            }
        }
        patientsVisited5DifferentDoctors = sb.toString();
        return patientsVisited5DifferentDoctors;
    }
    //- zwroc lekarzy ktorzy przyjeli tylko jednego pacjenta
    public static String getDoctorsThatHaveOnly1Patient(Set<Doctor>doctors){
        String unpopularDoctors = "";
        StringBuilder sb = new StringBuilder("");
        for (Doctor doctor : doctors){
            if (doctor.getDoctorsVisits().size() == 1);
            sb.append(doctor);
            sb.append(", ");

        }
        unpopularDoctors = sb.toString();
        return unpopularDoctors;
    }
}
