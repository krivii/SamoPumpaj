package com.example.samopumpaj.DB;

public class WorkoutModel {

    private int id;  // Primary key (will be managed externally)

    private String name;
    private String lastVisit;
    private int numberOfVisits;

    // Constructor
    public WorkoutModel(String name, String lastVisit, int numberOfVisits) {
        this.name = name;
        this.lastVisit = lastVisit;
        this.numberOfVisits = numberOfVisits;
    }

    // Getters and Setters
    public int getId() {
        return id; // Getter for the primary key
    }

    public String getName() {
        return name; // Getter for name
    }

    public void setName(String name) {
        this.name = name; // Setter for name
    }

    public String getLastVisit() {
        return lastVisit; // Getter for last visit date
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit; // Setter for last visit date
    }

    public int getNumberOfVisits() {
        return numberOfVisits; // Getter for number of visits
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits; // Setter for number of visits
    }
}
