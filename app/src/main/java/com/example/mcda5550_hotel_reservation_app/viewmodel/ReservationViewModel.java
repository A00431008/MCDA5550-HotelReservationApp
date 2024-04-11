package com.example.mcda5550_hotel_reservation_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.repository.ReservationRepository;

public class ReservationViewModel extends ViewModel {

    private final ReservationRepository reservationRepository;

    public ReservationViewModel() {
        this.reservationRepository = new ReservationRepository();
    }

    public LiveData<String> makeReservation(Reservation reservation) {
        return reservationRepository.makeReservation(reservation);
    }
}
