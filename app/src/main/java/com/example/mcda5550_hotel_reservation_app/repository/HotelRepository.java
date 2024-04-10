package com.example.mcda5550_hotel_reservation_app.repository;

import com.example.mcda5550_hotel_reservation_app.model.Hotel;

import java.util.List;
import java.util.ArrayList;
public class HotelRepository {
    public List<Hotel> getHotels() {
        return generateHotelList();
    }

    // MOCK DATA to generate hotel list initially
    // NOTE: This function to be removed later when api is integrated
    public List<Hotel> generateHotelList() {
        List<Hotel> hotelList = new ArrayList<>();

        // Generate mock data for 10 hotels
        hotelList.add(new Hotel("Hotel A", 100.0, true));
        hotelList.add(new Hotel("Hotel B", 150.0, false));
        hotelList.add(new Hotel("Hotel C", 120.0, true));
        hotelList.add(new Hotel("Hotel D", 200.0, true));
        hotelList.add(new Hotel("Hotel E", 180.0, false));
        hotelList.add(new Hotel("Hotel F", 130.0, true));
        hotelList.add(new Hotel("Hotel G", 170.0, true));
        hotelList.add(new Hotel("Hotel H", 190.0, true));
        hotelList.add(new Hotel("Hotel I", 110.0, false));
        hotelList.add(new Hotel("Hotel J", 220.0, true));

        return hotelList;
    }
}
