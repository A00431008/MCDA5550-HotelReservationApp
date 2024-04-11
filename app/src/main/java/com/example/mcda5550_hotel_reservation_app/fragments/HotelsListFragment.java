package com.example.mcda5550_hotel_reservation_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.adapters.HotelsAdapter;
import com.example.mcda5550_hotel_reservation_app.interfaces.ItemClickListener;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;
import com.example.mcda5550_hotel_reservation_app.viewmodel.HotelViewModel;

public class HotelsListFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private HotelsAdapter hotelsAdapter;
    private ProgressBar progressBar;

    private HotelViewModel hotelViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize viewModel
        hotelViewModel = new ViewModelProvider(this).get(HotelViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hotel_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI components
        TextView titleTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
        Button backButton = view.findViewById(R.id.back_button);
        recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        // get arguments from the bundle
        Bundle args = getArguments();

        // assign arguments to respective variables if arguments is not null
        if (args != null) {
            String checkInDate = args.getString("checkInDate");
            String checkOutDate = args.getString("checkOutDate");
            String numberOfGuests = args.getString("numberOfGuests");
            String guestName = args.getString("guestName");

            // Display Header Text
            String headerText = "Welcome " + guestName +
                    "!! Displaying hotel for " + numberOfGuests +
                    " guests staying from " + checkInDate +
                    " to " + checkOutDate;
            titleTextView.setText(headerText);
        }

        // Click listener for back button
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hotelsAdapter = new HotelsAdapter(getContext(), null, this); // Pass null initially
        recyclerView.setAdapter(hotelsAdapter);

        // Observe hotel list LiveData from ViewModel
        hotelViewModel.getHotelsLiveData().observe(getViewLifecycleOwner(), hotels -> {
            if (hotels != null) {
                hideProgress();
                hotelsAdapter.setHotelList(hotels);
            }
        });

        // Fetch hotels
        showProgress(); // Show progress bar when fetching data
        hotelViewModel.fetchHotels();
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view, int position) {
        // Handle hotel item click
        Hotel selectedHotel = hotelsAdapter.getItem(position);

        // Navigate to hotel details fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("selected_hotel", selectedHotel);

        HotelDetailsFragment hotelDetailsFragment = new HotelDetailsFragment();
        hotelDetailsFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.app_container_frame_layout, hotelDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

}
