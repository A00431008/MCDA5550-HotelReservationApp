package com.example.mcda5550_hotel_reservation_app.repository;

import android.util.Log;

import androidx.annotation.NonNull;
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

//    public LiveData<ReservationResponse> testPost(Reservation reservation) {
//        MutableLiveData<ReservationResponse> testLiveData = new MutableLiveData<>();
//        Call<ReservationResponse> call = hotelReservationApiService.testPost(reservation);
//        call.enqueue(new Callback<ReservationResponse>() {
//            @Override
//            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
//                testLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ReservationResponse> call, Throwable t) {
//                testLiveData.setValue(null);
//            }
//        });
//
//        return testLiveData;
//    }

    // Method to make a reservation - creates reservation through API call
    // Binds and returns the response from the API call with ReservationResponse Model
    public LiveData<ReservationResponse> createReservation(Reservation reservation) {
        MutableLiveData<ReservationResponse> reservationLiveData = new MutableLiveData<>();
        Call<ReservationResponse> call = hotelReservationApiService.createReservation(reservation);
        call.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReservationResponse> call, @NonNull Response<ReservationResponse> response) {
                if (response.code() == 201) {
                    reservationLiveData.setValue(response.body());
                } else {
                    ReservationResponse dummy = new ReservationResponse();
                    dummy.setConfirmationNumber(response.message());
                    Log.e(TAG, "Failed to create reservation. Error: " + response.message());
                    reservationLiveData.setValue(dummy);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ReservationResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Network error. Unable to create reservation. Error: " + t.getMessage());
                reservationLiveData.setValue(null);
            }
        });
        return reservationLiveData;
    }
}
