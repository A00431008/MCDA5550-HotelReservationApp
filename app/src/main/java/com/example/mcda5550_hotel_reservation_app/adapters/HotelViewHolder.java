package com.example.mcda5550_hotel_reservation_app.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.interfaces.ItemClickListener;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;

public class HotelViewHolder extends RecyclerView.ViewHolder {
    TextView hotelNameTextView;
    TextView priceTextView;
    TextView availabilityTextView;

    public HotelViewHolder(@NonNull View itemView) {
        super(itemView);
        hotelNameTextView = itemView.findViewById(R.id.hotel_name_text_view);
        priceTextView = itemView.findViewById(R.id.price_text_view);
        availabilityTextView = itemView.findViewById(R.id.availability_text_view);
    }

    public void bind(final Hotel hotel, final ItemClickListener listener) {
        hotelNameTextView.setText(hotel.getName());
        priceTextView.setText(String.valueOf(hotel.getPrice()));
        availabilityTextView.setText((hotel.getAvailability() ? "Available" : "Not Available"));
        itemView.setOnClickListener(view -> {
            listener.onClick(view, getAdapterPosition());
        });
    }
}
