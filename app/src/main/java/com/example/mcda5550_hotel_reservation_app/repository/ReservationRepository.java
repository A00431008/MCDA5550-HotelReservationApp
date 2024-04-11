package com.example.mcda5550_hotel_reservation_app.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.API.ApiClient;
import com.example.mcda5550_hotel_reservation_app.API.HotelApiService;
import com.example.mcda5550_hotel_reservation_app.model.ReservationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationRepository {

    private static final String TAG = "ReservationRepository";

    private final HotelApiService hotelApiService;

    public ReservationRepository() {
        this.hotelApiService = ApiClient.getClient().create(HotelApiService.class);
    }

    // Method to make a reservation
    public LiveData<String> makeReservation(Reservation reservation) {
        MutableLiveData<String> confirmationNumberLiveData = new MutableLiveData<>();

        hotelApiService.makeReservation(reservation).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ReservationResponse reservationResponse = response.body();
                    String confirmationNumber = reservationResponse.getConfirmationNumber();
                    confirmationNumberLiveData.setValue(confirmationNumber);
                } else {
                    Log.e(TAG, "Failed to make reservation. Response code: " + response.code());
                    confirmationNumberLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                Log.e(TAG, "Failed to make reservation. Error: " + t.getMessage());
                confirmationNumberLiveData.setValue(null);
            }
        });

        return confirmationNumberLiveData;
    }
}
