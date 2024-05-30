package edu.student.todo4.services;

import edu.student.todo4.models.Doctor;

import java.util.ArrayList;
import java.util.Arrays;

public class DoctorService {
    private ArrayList<Doctor> doctors = new ArrayList<Doctor>(Arrays.asList(
            new Doctor(1L,"Rani",30,5,33.2),
            new Doctor(2L, "Mohammad", 10, 10, 3)
    ));
}
