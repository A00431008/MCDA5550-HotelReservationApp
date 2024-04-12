package com.example.mcda5550_hotel_reservation_app.model;

import java.util.List;

// Model class for a Reservation: Attributes are hotelName, checkin and checkout date
// and a list of guests
public class Reservation {

    // Instance variables for attributes of reservation
    private String hotelName;
    private String checkIn;
    private String checkOut;
    private List<Guest> guests_list;
    private String confirmationNumber;

    //PRIMARY CONSTRUCTOR


    public Reservation(String hotelName, String checkInDate, String checkOutDate, List<Guest> guests) {
        this.hotelName = hotelName;
        this.checkIn = checkInDate;
        this.checkOut = checkOutDate;
        this.guests_list = guests;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckInDate() {
        return checkIn;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkIn = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOut;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOut = checkOutDate;
    }

    public List<Guest> getGuests() {
        return guests_list;
    }

    public void setGuests(List<Guest> guests_list) {
        this.guests_list = guests_list;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
