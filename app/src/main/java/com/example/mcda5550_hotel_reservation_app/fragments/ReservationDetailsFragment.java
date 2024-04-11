package com.example.mcda5550_hotel_reservation_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.adapters.GuestAdapter;
import com.example.mcda5550_hotel_reservation_app.model.Guest;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsFragment extends Fragment {


    public ReservationDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.reservation_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        TextView hotelNameTextView = view.findViewById(R.id.hotel_name_text_view);
        TextView checkInDateTextView = view.findViewById(R.id.check_in_date_text_view);
        TextView checkOutDateTextView = view.findViewById(R.id.check_out_date_text_view);
        TextView priceTextView = view.findViewById(R.id.price_text_view);
        RecyclerView guestRecyclerView = view.findViewById(R.id.guest_recycler_view);
        Button submitButton = view.findViewById(R.id.confirm_reservation_button);




        if (args != null) {
            // Retrieve hotel details from bundle
            Hotel selectedHotel = (Hotel) args.getSerializable("selected_hotel");
            String checkInDate = args.getString("checkInDate");
            String checkOutDate = args.getString("checkOutDate");


            // Set hotel details to corresponding TextViews
            hotelNameTextView.setText(selectedHotel.getName());
            checkInDateTextView.setText("Check In Date: " + checkInDate);
            checkOutDateTextView.setText("Check Out Date: " + checkOutDate);
            priceTextView.setText("Price: $" + selectedHotel.getPrice());

            // Initialize guest list
            List<Guest> guestList = new ArrayList<>();

            int numGuests = Integer.parseInt(args.getString("numberOfGuests"));
            // Add empty guests to the list based on the number of guests
            for (int i = 0; i < numGuests; i++) {
                guestList.add(new Guest("", ""));
            }
            // Initialize guest adapter with the number of guests and guest list
            GuestAdapter guestAdapter = new GuestAdapter(guestList);

            // Set layout manager and adapter for guestRecyclerView
            guestRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            guestRecyclerView.setAdapter(guestAdapter);
        }
        submitButton.setOnClickListener(v -> submitReservation());
    }

    private void submitReservation() {

    }
}
