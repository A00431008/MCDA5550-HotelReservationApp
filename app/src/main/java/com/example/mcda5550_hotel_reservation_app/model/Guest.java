package com.example.mcda5550_hotel_reservation_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Guest implements Parcelable {
    private String name;
    private String gender;

    public Guest(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    protected Guest(Parcel in) {
        name = in.readString();
        gender = in.readString();
    }

    public static final Creator<Guest> CREATOR = new Creator<Guest>() {
        @Override
        public Guest createFromParcel(Parcel in) {
            return new Guest(in);
        }

        @Override
        public Guest[] newArray(int size) {
            return new Guest[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
