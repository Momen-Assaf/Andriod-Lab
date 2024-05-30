package edu.student.todo4.controllers;

import edu.student.todo4.models.Doctor;
import edu.student.todo4.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DoctorController {
    @Autowired
    protected DoctorService doctorService;

    @GetMapping("/")
    public String welcomeMessage(){
        return doctorService.welcomeMessage();
    }

    @GetMapping("/doctors")
    public ArrayList<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping("/doctors/{doctorId}")
    public Doctor getDoctor(@PathVariable Long doctorId){
        return doctorService.getDoctor(doctorId);
    }

    @PostMapping("/doctors")
    public boolean addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @DeleteMapping("/doctors/{doctorId}")
    public boolean deleteDoctor(@PathVariable int doctorId){
        return doctorService.deleteDoctor(doctorId);
    }
}
