package edu.student.todo4.services;

import edu.student.todo4.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DoctorService {
    private ArrayList<Doctor> doctors = new ArrayList<Doctor>(Arrays.asList(
            new Doctor(1L, "Rani", 30, 5, 33.2),
            new Doctor(2L, "Mohammad", 10, 10, 3.0)
    ));

    public String welcomeMessage(){
        return "Welcome, I'm Momen Assaf";
    }
    public ArrayList<Doctor> getDoctors() {
        return this.doctors;
    }

    public boolean addDoctor(Doctor doctor) {
        return this.doctors.add(doctor);
    }

    public Doctor getDoctor(long id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                return doctors.get(i);
            }
        }
        return null;
    }

    public boolean deleteDoctor(long id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                doctors.remove(i);
                return true;
            }
        }
        return false;
    }

}
