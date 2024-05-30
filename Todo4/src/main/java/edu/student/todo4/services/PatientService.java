package edu.student.todo4.services;

import edu.student.todo4.models.Doctor;
import edu.student.todo4.models.Patient;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PatientService {
    private final DoctorService doctorService;
    private ArrayList<Patient> patients = new ArrayList<Patient>(Arrays.asList(
            new Patient(1L,"Momen Assaf",32.2,false, new ArrayList<Long>(Arrays.asList(1L,2L))),
            new Patient(2L, "Salameh", 2.0, false, new ArrayList<Long>(Arrays.asList(1L)))
    ));

    public PatientService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public boolean addPatient(Patient patient) {
        return this.patients.add(patient);
    }

    public Patient getPatient(long id) {
        for(int i = 0; i < patients.size(); i++){
            if(patients.get(i).getId() == id)return patients.get(i);
        }
        return null;
    }

    public boolean deletePatient(long id) {
        for(int i = 0; i < patients.size(); i++){
            if(patients.get(i).getId() == id){
                patients.remove(patients.get(i));
                return true;
            }
        }
        return false;
    }

    public boolean releasePatient(long id) {
        for (Patient patient : patients) {// remove patient from doctor patients as well
            if (patient.getId() == id) {
                patient.setCured(true);

                for (Long doctorId : patient.getDoctors()) {
                    Doctor doctor = doctorService.getDoctor(doctorId);
                    if (doctor != null) {
                        doctor.setCurrentPatients(doctor.getCurrentPatients() - 1);
                    }
                }
                patient.setDoctors(new ArrayList<Long>());
                return true;
            }
        }
        return false;
    }

    public boolean assignDoctor(long doctorId, long patientId){
        Doctor doctor = doctorService.getDoctor(doctorId);
        Patient patient = getPatient(patientId);

        if (doctor == null || patient == null) {
            return false;
        }

        if(doctor.getExperience() >= patient.getIllnessExperienceRequirement()){
            if(doctor.getCurrentPatients() < doctor.getMaxPatients()){
                doctor.setCurrentPatients(doctor.getCurrentPatients()+1);
                patient.getDoctors().add(doctorId);
                return true;
            }
        }
        return false;
    }
}
