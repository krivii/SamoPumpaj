package com.example.samopumpaj.DB;

import android.util.Pair;

public class TrainingModel {

    private int id;
    private String name;
    private String lastVisit;
    private int numberOfVisits;
    private int workoutFk;


    // Constructor
    public TrainingModel(int id, String name, int workoutFk) {
        this.id = id;
        this.name = name;
        this.workoutFk = workoutFk;
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

    public String getNumberOfVisits() {
        return String.valueOf(numberOfVisits);
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public int getWorkoutFk() {
        return workoutFk;
    }

    public void setWorkoutFk(int workoutFk) {
        this.workoutFk = workoutFk;
    }

    public void setVisitPair(Pair<Integer, String> visitPair) {
        this.numberOfVisits = visitPair.first;
        this.lastVisit = visitPair.second;
    }
}
