package com.example.mcda5550_hotel_reservation_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.model.Guest;
import com.example.mcda5550_hotel_reservation_app.model.Hotel;
import com.example.mcda5550_hotel_reservation_app.model.Reservation;
import com.example.mcda5550_hotel_reservation_app.model.ReservationResponse;
import com.example.mcda5550_hotel_reservation_app.viewmodel.ReservationViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReservationDetailsFragment extends Fragment {

    private ViewGroup guestContainer;
    private Bundle args;
    private Hotel selectedHotel;
    private String checkInDate, checkOutDate;

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
        args = getArguments();

        TextView hotelNameTextView = view.findViewById(R.id.hotel_name_text_view);
        TextView checkInDateTextView = view.findViewById(R.id.check_in_date_text_view);
        TextView checkOutDateTextView = view.findViewById(R.id.check_out_date_text_view);
        TextView pricePerDayTextView = view.findViewById(R.id.price_per_day_text_view);
        TextView numOfDaysTextView = view.findViewById(R.id.number_of_days_text_view);
        TextView totalPriceTextView = view.findViewById(R.id.total_price_text_view);
        Button submitButton = view.findViewById(R.id.confirm_reservation_button);
        guestContainer = view.findViewById(R.id.guest_container);


        if (args != null) {
            // Retrieve hotel details from bundle
            selectedHotel = (Hotel) args.getSerializable("selected_hotel");
            checkInDate = args.getString("checkInDate");
            checkOutDate = args.getString("checkOutDate");
            int numberOfDays = args.getInt("numberOfDays");
            double pricePerDay = selectedHotel.getPrice();
            int numGuests = Integer.parseInt(args.getString("numberOfGuests"));
            double totalPrice = numberOfDays * pricePerDay * numGuests;

            // Set hotel details to corresponding TextViews
            hotelNameTextView.setText(selectedHotel.getName());
            checkInDateTextView.setText("Check In Date: " + checkInDate);
            checkOutDateTextView.setText("Check Out Date: " + checkOutDate);
            pricePerDayTextView.setText("Price per day : $" + pricePerDay);
            numOfDaysTextView.setText("Duration: " + numberOfDays + " Days");
            totalPriceTextView.setText("Total Price: $ " + totalPrice );


        }

        // ADD GUEST LIST input here
        int numGuests = Integer.parseInt(args.getString("numberOfGuests"));
        for (int i = 0; i < numGuests; i++) {
            addGuestInput();
        }

        // SUBMIT BUTTON CLICK LISTENER
        submitButton.setOnClickListener(v -> submitReservation());

    }

    private void addGuestInput() {
        View guestInputView = getLayoutInflater().inflate(
                R.layout.guest_row_input_layout, guestContainer, false);
        guestContainer.addView(guestInputView);
        TextView label = guestInputView.findViewById(R.id.guest_details_label);

        // Set default value for the first input layout - Name is already entered in screen 1
        if (guestContainer.getChildCount() == 1) {
            EditText nameEditText = guestInputView.findViewById(R.id.layout_guest_name_edit_text);
            nameEditText.setText(args.getString("guestName"));
            label.setText("Enter your details");
        } else {
            label.setText("Enter details for Guest " + guestContainer.getChildCount());
        }
    }

    private void submitReservation() {

        List<Guest> guests_list = new ArrayList<>();
        for (int i = 0; i < guestContainer.getChildCount(); i++) {
            View guestView = guestContainer.getChildAt(i);
            EditText nameEditText = guestView.findViewById(R.id.layout_guest_name_edit_text);
            RadioGroup genderRadioGroup = guestView.findViewById(R.id.gender_radio_group);
            EditText emailEditText = guestView.findViewById(R.id.layout_guest_email_edit_text);
            EditText ageEditText = guestView.findViewById(R.id.layout_guest_age_edit_text);

            String name = nameEditText.getText().toString();
            String gender = getSelectedGender(genderRadioGroup);
            String email = emailEditText.getText().toString();
            int age = Integer.parseInt(ageEditText.getText().toString());
            guests_list.add(new Guest(name, gender, email, age));
        }

        // Code to submit data to server goes here
        Reservation reservation = new Reservation(selectedHotel.getName(), checkInDate, checkOutDate, guests_list);
        ReservationViewModel reservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);

        reservationViewModel.makeReservation(reservation).observe(getViewLifecycleOwner(), reservationResponse -> {
            if (reservationResponse != null) {
                initiateConfirmationScreen(reservationResponse);
            } else {
                showToast("Failed to create reservation. Please try again.");
            }
        });

    }

    private void initiateConfirmationScreen(ReservationResponse reservationResponse) {
        Bundle bundle = new Bundle();
        bundle.putString("confirmation_number", reservationResponse.getConfirmationNumber());

        ReservationConfirmationFragment reservationConfirmationFragment = new ReservationConfirmationFragment();
        reservationConfirmationFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.app_container_frame_layout, reservationConfirmationFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Function to show toast message
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    // Function to validate user inputs
    private boolean validateInputs(String name, String gender, String email, int age,
                                   boolean isBookingGuest) {
        // Check if name is empty
        if (name.isEmpty()) {
            showToast("Please enter a name for all guests");
            return false;
        }

        // Check if gender is selected
        if (gender.isEmpty()) {
            showToast("Please select a gender for all guests");
            return false;
        }

        // Check if email is empty or not in proper format
        if (email.isEmpty() || !isValidEmail(email)) {
            showToast("Please enter a valid email");
            return false;
        }

        // Check if age is a valid positive number
        if (age <= 0) {
            showToast("Please enter a valid age");
            return false;
        }

        if (age < 18 && isBookingGuest) {
            showToast("You must be at least 18 years to book the hotel");
        }

        // return true if valid
        return true;
    }

    // Function to validate email format
    private boolean isValidEmail(String email) {
        // Regular expression to check if email is in proper format
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email.replace(" ", "").length() > 0) {
            return pattern.matcher(email).matches();
        } else {
            return true;
        }
    }

    private String getSelectedGender(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.radio_button_male) {
            return "Male";
        } else if (checkedRadioButtonId == R.id.radio_button_female) {
            return "Female";
        }
        return "Other";
    }
}
