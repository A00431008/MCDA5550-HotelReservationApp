package com.example.mcda5550_hotel_reservation_app.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.API.ApiClient;
import com.example.mcda5550_hotel_reservation_app.API.HotelReservationApiService;
import com.example.mcda5550_hotel_reservation_app.model.ReservationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationRepository {
    private static final String TAG = "ReservationRepository";
    private final HotelReservationApiService hotelReservationApiService;

    // Repository CONSTRUCTOR - instantiates the hotelReservationApiService with ApiClient
    public ReservationRepository() {
        this.hotelReservationApiService = ApiClient.getClient().create(HotelReservationApiService.class);
    }

    // Method to make a reservation - creates reservation through API call
    // Binds and returns the response from the API call with ReservationResponse Model
    public LiveData<ReservationResponse> createReservation(Reservation reservation) {
        MutableLiveData<ReservationResponse> reservationLiveData = new MutableLiveData<>();
        Call<ReservationResponse> call = hotelReservationApiService.createReservation(reservation);
        call.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                if (response.isSuccessful()) {
                    reservationLiveData.setValue(response.body());
                } else {
                    Log.e(TAG, "Failed to create reservation. Error: " + response.message());
                    reservationLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                Log.e(TAG, "Network error. Unable to create reservation. Error: " + t.getMessage());
                reservationLiveData.setValue(null);
            }
        });
        return reservationLiveData;
    }
}
