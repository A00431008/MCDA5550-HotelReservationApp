package com.example.mcda5550_hotel_reservation_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.model.ReservationResponse;
import com.example.mcda5550_hotel_reservation_app.repository.ReservationRepository;

public class ReservationViewModel extends ViewModel {

    private final ReservationRepository reservationRepository;

    public ReservationViewModel() {
        this.reservationRepository = new ReservationRepository();
    }

    // Method to make a reservation
    public LiveData<ReservationResponse> makeReservation(Reservation reservation) {
        return reservationRepository.createReservation(reservation);
    }
}
