package com.example.samopumpaj.DB;

import java.time.LocalDateTime;

public class VisitModel {

    private int id;
    private double kilos;
    private LocalDateTime dateTime;
    private int workoutFk;
    private int trainingFk;

    public VisitModel(int id, double kilos, LocalDateTime dateTime, int workoutFk, int trainingFk) {
        this.id = id;
        this.kilos = kilos;
        this.dateTime = dateTime;
        this.workoutFk = workoutFk;
        this.trainingFk = trainingFk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKilos() {
        return kilos;
    }

    public void setKilos(double kilos) {
        this.kilos = kilos;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getWorkoutFk() {
        return workoutFk;
    }

    public void setWorkoutFk(int workoutFk) {
        this.workoutFk = workoutFk;
    }

    public int getTrainingFk() {
        return trainingFk;
    }

    public void setTrainingFk(int trainingFk) {
        this.trainingFk = trainingFk;
    }
}
