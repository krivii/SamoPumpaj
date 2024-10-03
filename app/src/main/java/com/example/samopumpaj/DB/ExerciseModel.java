package com.example.samopumpaj.DB;

public class ExerciseModel {

    private int id;  // Primary key

    private String name;

    private String targetMuscle;

    private String videoLink;

    private String lastUpdate;

    private int orderNumber;

    private int numberOfUpdates;

    private int trainingId;  // Foreign key referencing the Training table

    // Constructor
    public ExerciseModel(String name, String targetMuscle, String videoLink, String lastUpdate,
                         int orderNumber, int numberOfUpdates, int trainingId) {
        this.name = name;
        this.targetMuscle = targetMuscle;
        this.videoLink = videoLink;
        this.lastUpdate = lastUpdate;
        this.orderNumber = orderNumber;
        this.numberOfUpdates = numberOfUpdates;
        this.trainingId = trainingId;
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

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
}
