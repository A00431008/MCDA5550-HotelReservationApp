package com.example.mcda5550_hotel_reservation_app.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mcda5550_hotel_reservation_app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HotelSearchFragment extends Fragment {
    // Declare variables
    View view;
    ConstraintLayout mainLayout;
    TextView titleTextView;
    private Button checkInDateButton, checkOutDateButton;
    private DatePickerDialog.OnDateSetListener checkInDateSetListener, checkOutDateSetListener;
    private Calendar checkInCalendar, checkOutCalendar;
    private SimpleDateFormat dateFormat;
    private EditText guestsCountEditText, guestNameEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_text_view);
        titleTextView.setText(R.string.hotel_search_title_text);

        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text);
        guestNameEditText = view.findViewById(R.id.guest_name_edit_text);
        Button searchButton = view.findViewById(R.id.search_button);

        checkInDateButton = view.findViewById(R.id.check_in_date_button);
        checkOutDateButton = view.findViewById(R.id.check_out_date_button);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        checkInCalendar = Calendar.getInstance();
        checkOutCalendar = Calendar.getInstance();

        // Initialize DateSetListeners
        checkInDateSetListener = (view1, year, month, dayOfMonth) -> {
            checkInCalendar.set(Calendar.YEAR, year);
            checkInCalendar.set(Calendar.MONTH, month);
            checkInCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateButtonText();
        };

        checkOutDateSetListener = (view12, year, month, dayOfMonth) -> {
            checkOutCalendar.set(Calendar.YEAR, year);
            checkOutCalendar.set(Calendar.MONTH, month);
            checkOutCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateButtonText();
        };

        // Set OnClickListener for check-in and check-out date buttons
        checkInDateButton.setOnClickListener(v -> new DatePickerDialog(requireContext(),
                checkInDateSetListener,
                checkInCalendar.get(Calendar.YEAR),
                checkInCalendar.get(Calendar.MONTH),
                checkInCalendar.get(Calendar.DAY_OF_MONTH)).show());

        checkOutDateButton.setOnClickListener(v -> new DatePickerDialog(requireContext(),
                checkOutDateSetListener,
                checkOutCalendar.get(Calendar.YEAR),
                checkOutCalendar.get(Calendar.MONTH),
                checkOutCalendar.get(Calendar.DAY_OF_MONTH)).show());

        searchButton.setOnClickListener(v -> {
            String checkInDate = dateFormat.format(checkInCalendar.getTime());
            String checkOutDate = dateFormat.format(checkOutCalendar.getTime());
            String numberOfGuests = guestsCountEditText.getText().toString();
            String guestName = guestNameEditText.getText().toString();
            if (dateValidator(checkInDate, checkOutDate)
                && fieldsValidator(numberOfGuests, guestName)) {
                Bundle bundle = new Bundle();
                bundle.putString("checkInDate", checkInDate);
                bundle.putString("checkOutDate", checkOutDate);
                bundle.putString("numberOfGuests", numberOfGuests);
                bundle.putString("guestName", guestName);

                    HotelsListFragment hotelsListFragment = new HotelsListFragment();
                    hotelsListFragment.setArguments(bundle);

                assert getFragmentManager() != null;
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.app_container_frame_layout, hotelsListFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
            }
        });
    }

    // Update text on Date buttons with selected date
    private void updateDateButtonText() {
        checkInDateButton.setText(dateFormat.format(checkInCalendar.getTime()));
        checkOutDateButton.setText(dateFormat.format(checkOutCalendar.getTime()));
    }

    private boolean dateValidator(String checkInDate, String checkOutDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        try {
            Date checkInDateObj = dateFormat.parse(checkInDate);
            Date checkOutDateObj = dateFormat.parse(checkOutDate);
            Calendar checkInCal = Calendar.getInstance();
            assert checkInDateObj != null;
            checkInCal.setTime(checkInDateObj);
            Calendar checkOutCal = Calendar.getInstance();
            assert checkOutDateObj != null;
            checkOutCal.setTime(checkOutDateObj);
            Calendar today = Calendar.getInstance(TimeZone.getDefault());

            int ms_in_a_day = 24 * 60 * 60 * 1000; // Milliseconds in a day
            if (checkInCal.before(today)) {
                Toast.makeText(getContext(),
                        "Check-in date can't be in the past. " +
                                "Please book for tomorrow or a future date",
                        Toast.LENGTH_SHORT).show();
                return false;
            } else if (checkOutCal.before(checkInCal) ||
                    (checkOutCal.getTimeInMillis() - checkInCal.getTimeInMillis()) < ms_in_a_day) {
                Toast.makeText(getContext(),
                        "Check-out date must be at least one day after the check-in date.",
                        Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            Toast.makeText(getContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean fieldsValidator(String numGuests, String nameGuest) {
        int num = numGuests.isEmpty() ? 0: Integer.parseInt(numGuests);
        if (num <= 0) {
            Toast.makeText(getContext(),
                    "Number of guests must be at least 1",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (!nameGuest.matches("[a-zA-Z ]+")) {
            Toast.makeText(getContext(),
                    "Name can only contain alphabets",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (nameGuest.length() > 50) {
            Toast.makeText(getContext(),
                    "Name must be less than 50 characters",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
