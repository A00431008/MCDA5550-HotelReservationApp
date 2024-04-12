package com.example.mcda5550_hotel_reservation_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mcda5550_hotel_reservation_app.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class HotelSearchFragment extends Fragment {

    private EditText guestsCountEditText;
    private EditText guestNameEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hotel_search_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleTextView = view.findViewById(R.id.title_text_view);
        titleTextView.setText(R.string.hotel_search_title_text);

        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text);
        guestNameEditText = view.findViewById(R.id.guest_name_edit_text);
        Button searchButton = view.findViewById(R.id.search_button);

        searchButton.setOnClickListener(v -> handleSearchButtonClick(view));
    }

    private void handleSearchButtonClick(View view) {
        DatePicker checkInDatePicker = view.findViewById(R.id.check_in_date_picker);
        DatePicker checkOutDatePicker = view.findViewById(R.id.check_out_date_picker);
        String checkInDate = dateFormatter(checkInDatePicker);
        String checkOutDate = dateFormatter(checkOutDatePicker);
        String numberOfGuests = guestsCountEditText.getText().toString();
        String guestName = guestNameEditText.getText().toString();
        int numDays = getNumberOfDays(checkInDatePicker, checkInDatePicker);
        if (validateInput(checkInDatePicker, checkOutDatePicker, numberOfGuests, guestName)) {
            startHotelListFragment(checkInDate, checkOutDate, numberOfGuests, guestName, numDays);
        }
    }

    private boolean validateInput(DatePicker checkInDate, DatePicker checkOutDate,
                                  String numberOfGuests, String guestName) {
        return validateDates(checkInDate, checkOutDate)
                && validateFields(numberOfGuests, guestName);
    }

    private Calendar getCalendarFor(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(year, month, day);

        return  calendar;
    }

    private String dateFormatter(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    private boolean validateDates(DatePicker checkInDate, DatePicker checkOutDate) {
        Calendar checkInCal = getCalendarFor(checkInDate);
        Calendar checkOutCal = getCalendarFor(checkOutDate);
        Calendar today = Calendar.getInstance(TimeZone.getDefault());

        if (checkInCal.before(today)) {
            Toast.makeText(getContext(), "Check-in date can't be in the past. Please book for tomorrow or a future date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (checkOutCal.before(checkInCal) || (checkOutCal.getTimeInMillis() - checkInCal.getTimeInMillis()) < 24 * 60 * 60 * 1000) {
            Toast.makeText(getContext(), "Check-out date must be at least one day after the check-in date.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateFields(String numberOfGuests, String guestName) {
        int num = numberOfGuests.isEmpty() ? 0 : Integer.parseInt(numberOfGuests);
        if (num <= 0) {
            showToast("Number of guests must be at least 1");
            return false;
        } else if (!guestName.matches("[a-zA-Z ]+")) {
            showToast("Name can only contain alphabets");
            return false;
        } else if (guestName.length() > 50) {
            showToast("Name must be less than 50 characters");
            return false;
        } else {
            return true;
        }
    }

    private int getNumberOfDays(DatePicker startDatePicker, DatePicker endDatePicker) {
        // Convert DatePickers to Calendar objects
        Calendar startCalendar = getCalendarFor(startDatePicker);
        Calendar endCalendar = getCalendarFor(endDatePicker);

        // Calculate the difference in days
        long differenceInMillis = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        int numDays = (int) (differenceInMillis / (24 * 60 * 60 * 1000));

        return numDays;
    }

    private void startHotelListFragment(String checkInDate, String checkOutDate,
                                        String numberOfGuests, String guestName, int numOfDays) {
        Bundle bundle = new Bundle();
        bundle.putString("checkInDate", checkInDate);
        bundle.putString("checkOutDate", checkOutDate);
        bundle.putString("numberOfGuests", numberOfGuests);
        bundle.putString("guestName", guestName);
        bundle.putInt("numberOfDays", numOfDays);

        HotelsListFragment hotelsListFragment = new HotelsListFragment();
        hotelsListFragment.setArguments(bundle);

        assert getFragmentManager() != null;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.app_container_frame_layout, hotelsListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
