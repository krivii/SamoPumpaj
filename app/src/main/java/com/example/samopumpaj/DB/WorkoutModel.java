package com.example.samopumpaj.DB;

public class WorkoutModel {

    private int id;
    private String name;
    private int numberOfVisits;


    public WorkoutModel(int id, String name) {
        this.id = id;
        this.name = name;;
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

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    @Override
    public String toString() {
        return "WorkoutModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                '}';
    }
}
