package com.example.mcda5550_hotel_reservation_app.model;

import java.util.List;

// Model class for a Reservation: Attributes are hotelName, checkin and checkout date
// and a list of guests
public class Reservation {

    // Instance variables for attributes of reservation
    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private List<Guest> guestsList;
    private String confirmationNumber;

    //PRIMARY CONSTRUCTOR


    public Reservation(String hotelName, String checkInDate, String checkOutDate, List<Guest> guests) {
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestsList = guests;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotel(String hotelName) {
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
        return guestsList;
    }

    public void setGuests(List<Guest> guests_list) {
        this.guestsList = guests_list;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
