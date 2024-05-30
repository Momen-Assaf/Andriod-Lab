package edu.student.todo4.controllers;

import edu.student.todo4.models.Patient;
import edu.student.todo4.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.student.todo4.services.PatientService;

import java.util.ArrayList;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    public ArrayList<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("patients/{patientId}")
    public Patient getPatient(@PathVariable long patientId){
        System.out.println(patientId);
        return patientService.getPatient(patientId);
    }

    @PostMapping("/patients")
    public boolean addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @DeleteMapping("patients/{patientId}")
    public boolean deletePatient(@PathVariable long patientId){
        return patientService.deletePatient(patientId);
    }

    @PutMapping("release/{patientId}")
    public boolean releasePatient(@PathVariable long patientId){
        return patientService.releasePatient(patientId);
    }
}
