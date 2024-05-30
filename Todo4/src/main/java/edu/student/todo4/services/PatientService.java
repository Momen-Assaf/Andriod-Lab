package edu.student.todo4.services;

import edu.student.todo4.models.Patient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PatientService {
    private ArrayList<Patient> patients = new ArrayList<Patient>(Arrays.asList(
            new Patient(1L,"Momen Assaf",32.2,false, new ArrayList<Long>(Arrays.asList(1L,2L))),
            new Patient(2L, "Salameh", 2, true,new ArrayList<Long>(Arrays.asList(1L)))
    ));

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public boolean addPatient(Patient patient) {
        return this.patients.add(patient);
    }

    public String getPatient(long id) {
        for(int i = 0; i < patients.size(); i++){
            if(patients.get(i).getId() == id)return patients.get(i).toString();
        }
        return "There is no such patient";
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
        for(int i = 0; i < patients.size(); i++){
            if(patients.get(i).getId() == id){
                patients.get(i).setCured(true);
                patients.get(i).setDoctors(new ArrayList<Long>());
                return true;
            }
        }
        return false;
    }
}
