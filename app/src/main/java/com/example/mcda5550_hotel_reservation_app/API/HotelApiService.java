package com.example.mcda5550_hotel_reservation_app.API;

import com.example.mcda5550_hotel_reservation_app.model.Hotel;
import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.model.ReservationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HotelApiService {
    @POST("make_reservation")
    Call<ReservationResponse> makeReservation(@Body Reservation reservation);

    @GET("get_hotels")
    Call<List<Hotel>> getHotels();
}
