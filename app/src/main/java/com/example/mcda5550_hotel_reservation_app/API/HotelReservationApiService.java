package com.example.mcda5550_hotel_reservation_app.API;

import com.example.mcda5550_hotel_reservation_app.model.Hotel;
import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.model.ReservationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HotelReservationApiService {
    @POST("/create_reservation")
    Call<ReservationResponse> createReservation(@Body Reservation reservation);

    @GET("/get_hotels")
    Call<List<Hotel>> getHotels();

//    @POST("/test_post")
//    Call<ReservationResponse> testPost(@Body Reservation reservation);
}
