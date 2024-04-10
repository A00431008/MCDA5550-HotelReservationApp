package com.example.mcda5550_hotel_reservation_app.model;

// Model class for Guest with attributes name and gender
public class Guest {
    // Instance Variables for Guest
    private String name;
    private String gender;

    // PRIMARY CONSTRUCTOR
    public Guest(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
