package com.example.mcda5550_hotel_reservation_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcda5550_hotel_reservation_app.R;
import com.example.mcda5550_hotel_reservation_app.model.Guest;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ViewHolder> {

    private List<Guest> guestList;

    public GuestAdapter(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public void addItem(Guest guest) {
        guestList.add(guest);
        notifyItemInserted(guestList.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guest_row_input_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(guestList.get(position));
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private EditText nameEditText;
        private RadioGroup genderRadioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameEditText = itemView.findViewById(R.id.layout_guest_name_edit_text);
            genderRadioGroup = itemView.findViewById(R.id.gender_radio_group);
        }

        public void bind(Guest guest) {
            // Set guest name if available
            if (guest.getName() != null) {
                nameEditText.setText(guest.getName());
            } else {
                nameEditText.setText("");
            }

            // Set gender radio button if available
            if (guest.getGender() != null) {
                String gender = guest.getGender();
                if (gender.equals("Male")) {
                    genderRadioGroup.check(R.id.radio_button_male);
                } else if (gender.equals("Female")) {
                    genderRadioGroup.check(R.id.radio_button_female);
                }
            }
        }
    }
}
