package com.example.mcda5550_hotel_reservation_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.interfaces.ItemClickListener;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;

import java.util.List;


public class HotelsAdapter extends RecyclerView.Adapter<HotelViewHolder> {
    private Context context;
    private List<Hotel> hotelList;
    private ItemClickListener listener;

    public HotelsAdapter(Context context, List<Hotel> hotelList, ItemClickListener listener) {
        this.context = context;
        this.hotelList = hotelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_list_layout, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.hotelNameTextView.setText(hotel.getName());
        holder.priceTextView.setText(String.valueOf(hotel.getPrice()));
        holder.availabilityTextView.setText(hotel.getAvailability() ? "Available" : "Not Available");

        holder.itemView.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                listener.onClick(v, adapterPosition);
            }
        });
    }

    // Method to set the hotel list
    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

    // Method to get the hotel at a specific position
    public Hotel getItem(int position) {
        if (position >= 0 && position < hotelList.size()) {
            return hotelList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}