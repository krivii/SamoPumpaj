package com.example.samopumpaj.DB;

public class ExerciseModel {

    private int id;

    private String name;

    private String targetMuscle;

    private String videoLink;

    private String lastUpdate;

    private int orderNumber;

    private int numberOfUpdates;

    private int trainingFk;

    public ExerciseModel(int id, String name, String targetMuscle, String videoLink,  int orderNumber, int trainingFk) {
        this.id = id;
        this.name = name;
        this.targetMuscle = targetMuscle;
        this.videoLink = videoLink;
        this.orderNumber = orderNumber;
        this.trainingFk = trainingFk;
    }

    public ExerciseModel(){}


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

    public String getTargetMuscle() {
        return targetMuscle;
    }

    public void setTargetMuscle(String targetMuscle) {
        this.targetMuscle = targetMuscle;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getNumberOfUpdates() {
        return numberOfUpdates;
    }

    public void setNumberOfUpdates(int numberOfUpdates) {
        this.numberOfUpdates = numberOfUpdates;
    }

    public int getTrainingFk() {
        return trainingFk;
    }

    public void setTrainingFk(int trainingFk) {
        this.trainingFk = trainingFk;
    }
}
