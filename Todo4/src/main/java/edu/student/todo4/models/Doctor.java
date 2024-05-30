package edu.student.todo4.models;

public class Doctor {
    private Long id;
    private String name;
    private int maxPatients;
    private int currentPatients;
    private double experience;

    public Doctor(Long id, String name, int maxPatients, int currentPatients, double experience) {
        this.id = id;
        this.name = name;
        this.maxPatients = maxPatients;
        this.currentPatients = currentPatients;
        this.experience = experience;
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

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public int getCurrentPatients() {
        return currentPatients;
    }

    public void setCurrentPatients(int currentPatients) {
        this.currentPatients = currentPatients;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxPatients=" + maxPatients +
                ", currentPatients=" + currentPatients +
                ", experience=" + experience +
                '}';
    }
}
