package com.example.mcda5550_hotel_reservation_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;
import com.example.mcda5550_hotel_reservation_app.repository.HotelRepository;

import java.util.List;

public class HotelViewModel extends ViewModel {
    private final MutableLiveData<List<Hotel>> hotelsLiveData = new MutableLiveData<>();
    private final HotelRepository repository;

    public HotelViewModel(HotelRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Hotel>> getHotelsLiveData() {
        return hotelsLiveData;
    }

    public void fetchHotels() {
        List<Hotel> hotels = repository.getHotels();
        hotelsLiveData.setValue(hotels);
    }

}
