package com.example.samopumpaj.DB;

import android.util.Pair;

public class TrainingModel {

    private int id;
    private String name;
    private String lastVisit;
    private int numberOfVisits;
    private int workoutId;


    // Constructor
    public TrainingModel(int id, String name, int workoutId) {
        this.id = id;
        this.name = name;
        this.workoutId = workoutId;
    }

    public TrainingModel() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setVisitPair(Pair<Integer, String> visitPair) {
        this.numberOfVisits = visitPair.first;
        this.lastVisit = visitPair.second;
    }
}
