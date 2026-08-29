package com.michelle;

public class Plant {
    private int id;
    private String name;
    private String type;
    private String wateringFrequency;
    private String sunlight;
    private String notes;
    private String dateAdded;

    public Plant(int id, String name, String type, String wateringFrequency,
                 String sunlight, String notes, String dateAdded) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.wateringFrequency = wateringFrequency;
        this.sunlight = sunlight;
        this.notes = notes;
        this.dateAdded = dateAdded;
    }

    public Plant(String name, String type, String wateringFrequency,
                 String sunlight, String notes, String dateAdded) {
        this.name = name;
        this.type = type;
        this.wateringFrequency = wateringFrequency;
        this.sunlight = sunlight;
        this.notes = notes;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getWateringFrequency() {
        return wateringFrequency;
    }

    public String getSunlight() {
        return sunlight;
    }

    public String getNotes() {
        return notes;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}