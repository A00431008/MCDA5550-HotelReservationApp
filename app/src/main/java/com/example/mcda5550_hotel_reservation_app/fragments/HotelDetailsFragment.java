package com.example.mcda5550_hotel_reservation_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mcda5550_hotel_reservation_app.R;

public class HotelDetailsFragment extends Fragment {
    private TextView hotelNameTextView;
    private TextView checkInDateTextView;
    private TextView checkOutDateTextView;
    private TextView priceTextView;

    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private String price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hotel_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hotelNameTextView = view.findViewById(R.id.hotel_name_text_view);
        checkInDateTextView = view.findViewById(R.id.check_in_date_text_view);
        checkOutDateTextView = view.findViewById(R.id.check_out_date_text_view);
        priceTextView = view.findViewById(R.id.price_text_view);

        if (getArguments() != null) {
            hotelName = getArguments().getString("hotelName");
            checkInDate = getArguments().getString("checkInDate");
            checkOutDate = getArguments().getString("checkOutDate");
            price = getArguments().getString("price");

            hotelNameTextView.setText(hotelName);
            checkInDateTextView.setText(checkInDate);
            checkOutDateTextView.setText(checkOutDate);
            priceTextView.setText(price);
        }
    }
}
