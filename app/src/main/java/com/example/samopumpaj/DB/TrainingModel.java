package com.example.samopumpaj.DB;

public class TrainingModel {

    private int id; // Primary key

    private String name; // Training name
    private String lastVisit; // Last visit date
    private int numberOfVisits; // Number of visits
    private int workoutId; // Foreign key referencing the Workout table

    // Constructor
    public TrainingModel(String name, String lastVisit, int numberOfVisits, int workoutId) {
        this.name = name;
        this.lastVisit = lastVisit;
        this.numberOfVisits = numberOfVisits;
        this.workoutId = workoutId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
