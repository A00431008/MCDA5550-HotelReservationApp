package com.example.mcda5550_hotel_reservation_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;
import com.example.mcda5550_hotel_reservation_app.repository.HotelRepository;

import java.util.List;

public class HotelViewModel extends ViewModel {
    private final HotelRepository repository;
    private LiveData<List<Hotel>> hotelsLiveData;

    public HotelViewModel() {
        repository = new HotelRepository();
    }

    public LiveData<List<Hotel>> getHotelsLiveData() {
        if (hotelsLiveData == null) {
            hotelsLiveData = repository.getHotels();
        }
        return hotelsLiveData;
    }
}
