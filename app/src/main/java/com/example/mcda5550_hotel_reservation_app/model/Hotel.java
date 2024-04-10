package com.example.mcda5550_hotel_reservation_app.model;

// Model class for a Hotel: Attributes are name, price and availability
public class Hotel {
    // Instance variables for attributes
    private String name;
    private double price;
    private boolean availability;

    // Primary constructor
    public Hotel(String name, double price, boolean availability) {
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    // Getters and Setters for instance variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
