package zad2.app;

import zad2.models.Doctor;
import zad2.models.Visit;
import zad2.models.Patient;
import zad2.services.ClinicService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ClinicRunner {
    public static void main(String[] args) {

        Map<Integer, Doctor> doctorsMap = new HashMap<>();
        Map<Integer, Patient> patientsMap = new HashMap<>();
        List<Visit> visits = new ArrayList<>();
        List<Visit> verifedVisitsList = new ArrayList<>();

        try (
                Scanner scanner = new Scanner(new FileReader("src/main/java/zad2/lekarze.txt"));
                Scanner scanner2 = new Scanner(new FileReader("src/main/java/zad2/pacjenci.txt"));
                Scanner scanner3 = new Scanner(new FileReader("src/main/java/zad2/wizyty.txt"));
        ) {
            scanner.nextLine();
            scanner2.nextLine();
            scanner3.nextLine();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] dataAboutDoctors = line.split("\t");
                Doctor doctor = new Doctor(Integer.parseInt(dataAboutDoctors[0]), dataAboutDoctors[2], dataAboutDoctors[1], dataAboutDoctors[6], dataAboutDoctors[4], dataAboutDoctors[3], dataAboutDoctors[5]);
                doctorsMap.put(doctor.getId(), doctor);
            }

            while (scanner2.hasNext()) {
                String line2 = scanner2.nextLine();
                String[] dataAboutPatients = line2.split("\t");
                Patient patient = new Patient(Integer.parseInt(dataAboutPatients[0]), dataAboutPatients[1], dataAboutPatients[2], dataAboutPatients[3], dataAboutPatients[4]);
                patientsMap.put(patient.getId(), patient);
            }

            while (scanner3.hasNext()) {
                String line3 = scanner3.nextLine();
                Visit visit = Visit.create(line3, doctorsMap, patientsMap);
                if (visit != null)
                    visits.add(visit);
            }

            for (Visit visit : visits) {
                for (Map.Entry<Integer, Doctor> entry : doctorsMap.entrySet()) {
                    if (visit.getDoctor().equals(entry.getValue())) {
                        for (Map.Entry<Integer, Patient> entry1 : patientsMap.entrySet()) {
                            if (visit.getPatient().equals(entry1.getValue())) {
                                verifedVisitsList.add(visit); //na dane zlosliwe
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Doctor doctorWhoMadeMostVisits = ClinicService.getDoctorWhoMadeMostVisits(doctorsMap);
        System.out.println("doctorWhoMadeMostVisits = " + doctorWhoMadeMostVisits);
        Patient patientWhoHasMostVisits = ClinicService.getPatientWhoHasMostVisits(patientsMap);
        System.out.println("patientWhoHasMostVisits = " + patientWhoHasMostVisits);

        String doctorsFavSpecialization1 = ClinicService.getDoctorsFavSpecialization(new ArrayList<>(doctorsMap.values()));
        System.out.println("doctorsFavSpecialization1 = " + doctorsFavSpecialization1);

        int mostVisitsYear = ClinicService.getMostVisitsYear(verifedVisitsList);
        System.out.println("mostVisitsYear = " + mostVisitsYear);
        List<Doctor> doctors = ClinicService.get5OldestDoctors(doctorsMap);
        System.out.println("doctors = " + doctors);
        List<Doctor> mostWorkingDoctors = ClinicService.get5DoctorsThatMadeMostVisits(doctorsMap);
        System.out.println("mostWorkingDoctors = " + mostWorkingDoctors);
        List<Patient> patientsThatVisitsAbove5Doctors = ClinicService.getPatientsThatVisitsAbove5Doctors(patientsMap);
        System.out.println("patientsThatVisitsAbove5Doctors = " + patientsThatVisitsAbove5Doctors);
        List<Doctor> doctorsThatHaveOnly1Patient = ClinicService.getDoctorsThatHaveOnly1Patient(doctorsMap);
        System.out.println("doctorsThatHaveOnly1Patient = " + doctorsThatHaveOnly1Patient);


    }
}
