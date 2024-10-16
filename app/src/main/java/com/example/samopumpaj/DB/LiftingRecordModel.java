package com.example.samopumpaj.DB;

import java.time.LocalDateTime;

public class LiftingRecordModel {

    private int id;
    private int exerciseFk;
    private float weight;
    private int sets;
    private int level;
    private int reps;
    private LocalDateTime date;


    public LiftingRecordModel(int id, int exerciseFk, float weight, int sets, int level, int reps, LocalDateTime date) {
        this.id = id;
        this.exerciseFk = exerciseFk;
        this.weight = weight;
        this.sets = sets;
        this.level = level;
        this.reps = reps;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExerciseFk() {
        return exerciseFk;
    }

    public void setExerciseFk(int exerciseFk) {
        this.exerciseFk = exerciseFk;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
