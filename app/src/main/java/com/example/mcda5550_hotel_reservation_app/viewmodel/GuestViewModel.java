package com.example.mcda5550_hotel_reservation_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mcda5550_hotel_reservation_app.model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestViewModel {
    private MutableLiveData<List<Guest>> guestListLiveData;
    private List<Guest> guestList;

    public GuestViewModel() {
        guestList = new ArrayList<>();
        guestListLiveData = new MutableLiveData<>();
    }

    public void addGuest(Guest guest) {
        guestList.add(guest);
        guestListLiveData.setValue(guestList);
    }

    public LiveData<List<Guest>> getGuestListLiveData() {
        return guestListLiveData;
    }
}
