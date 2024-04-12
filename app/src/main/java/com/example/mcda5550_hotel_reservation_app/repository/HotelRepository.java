package com.example.mcda5550_hotel_reservation_app.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mcda5550_hotel_reservation_app.API.ApiClient;
import com.example.mcda5550_hotel_reservation_app.API.HotelReservationApiService;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;

import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelRepository {

    private static final String TAG = "HotelRepository";

    private final HotelReservationApiService hotelReservationApiService;

    public HotelRepository() {
        this.hotelReservationApiService = ApiClient.getClient().create(HotelReservationApiService.class);
    }

    public LiveData<List<Hotel>> getHotels() {
        MutableLiveData<List<Hotel>> hotelsLiveData = new MutableLiveData<>();
        Call<List<Hotel>> call = hotelReservationApiService.getHotels();
        call.enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(response.isSuccessful()) {
                    hotelsLiveData.setValue(response.body());
                } else {
                    Log.e(TAG, "Failed to fetch hotels. Error: " + response.message());
                    hotelsLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e(TAG, "Network error. Unable to fetch hotels. Error: " +
                        t.getMessage());
                hotelsLiveData.setValue(null);
            }
        });
        return hotelsLiveData;
    }

    public List<Hotel> getMockHotels() {
        // MOCKING the data here for initial design
        // NOTE: This is to be replaced with api during api integration
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
