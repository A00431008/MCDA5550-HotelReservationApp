package com.example.mcda5550_hotel_reservation_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;

public class ReservationConfirmationFragment extends Fragment {

    private TextView confirmationTextView;
    private String confirmationNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.reservation_success_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button backButton = view.findViewById(R.id.home_button);
        confirmationTextView = view.findViewById(R.id.confirmation_number_text_view);

        String confirmationNumber = getArguments().getString("confirmation_number");
        confirmationTextView.setText(confirmationNumber + "\n\n\n"); // line 37

        backButton.setOnClickListener(v -> handleBackButtonClick(view));
    }

    private void handleBackButtonClick(View view) {
        HotelSearchFragment hotelSearchFragment = new HotelSearchFragment();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.app_container_frame_layout, hotelSearchFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

