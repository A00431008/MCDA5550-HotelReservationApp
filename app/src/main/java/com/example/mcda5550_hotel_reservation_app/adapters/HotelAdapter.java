package com.example.mcda5550_hotel_reservation_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.ItemClickListener;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private final Context context;
    private List<Hotel> hotelList;
    private final ItemClickListener listener;

    public HotelAdapter(Context context, List<Hotel> hotelList, ItemClickListener listener) {
        this.context = context;
        this.hotelList = hotelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.bind(hotel);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hotelNameTextView;
        TextView priceTextView;
        TextView availabilityTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelNameTextView = itemView.findViewById(R.id.hotel_name_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            availabilityTextView = itemView.findViewById(R.id.availability_text_view);
            itemView.setOnClickListener(this); // Set click listener on the item view
        }

        public void bind(final Hotel hotel) {
            hotelNameTextView.setText(hotel.getName());
            priceTextView.setText(String.valueOf(hotel.getPrice()));
            availabilityTextView.setText((hotel.getAvailability() ? "Available" : "Not Available"));
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(view, position);
            }
        }
    }
}
