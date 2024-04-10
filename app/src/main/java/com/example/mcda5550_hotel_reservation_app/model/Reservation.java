package com.example.mcda5550_hotel_reservation_app.model;

import java.util.List;

// Model class for a Reservation: Attributes are hotelName, checkin and checkout date
// and a list of guests
public class Reservation {

    // Instance variables for attributes of reservation
    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private List<Guest> guests;

    //PRIMARY CONSTRUCTOR


    public Reservation(String hotelName, String checkInDate, String checkOutDate, List<Guest> guests) {
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guests = guests;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
}
