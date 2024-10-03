package com.example.samopumpaj.DB;

import java.util.Date;

public class LiftingRecordModel {

    private int id;  // Primary key
    private int exerciseId;  // Foreign key referencing the Exercise table
    private float weight;
    private int reps;
    private Date date; // Changed from String to Date

    // Constructor
    public LiftingRecordModel(float weight, int reps, Date date) {
        this.exerciseId = exerciseId;
        this.weight = weight;
        this.reps = reps;
        this.date = date;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
