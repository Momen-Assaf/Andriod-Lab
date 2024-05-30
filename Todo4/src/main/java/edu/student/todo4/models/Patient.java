package edu.student.todo4.models;

import java.util.ArrayList;

public class Patient {
    private Long id;
    private String name;
    private double illnessExperienceRequirement;
    private boolean isCured = false;
    private ArrayList<Long> doctors;

    public Patient(Long id, String name, double illnessExperienceRequirement, boolean isCured, ArrayList<Long> doctors) {
        this.id = id;
        this.name = name;
        this.illnessExperienceRequirement = illnessExperienceRequirement;
        this.isCured = isCured;
        this.doctors = doctors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIllnessExperienceRequirement() {
        return illnessExperienceRequirement;
    }

    public void setIllnessExperienceRequirement(double illnessExperienceRequirement) {
        this.illnessExperienceRequirement = illnessExperienceRequirement;
    }

    public boolean isCured() {
        return isCured;
    }

    public void setCured(boolean cured) {
        isCured = cured;
    }

    public ArrayList<Long> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Long> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", illnessExperienceRequirement=" + illnessExperienceRequirement +
                ", isCured=" + isCured +
                ", doctors=" + doctors +
                '}';
    }
}
