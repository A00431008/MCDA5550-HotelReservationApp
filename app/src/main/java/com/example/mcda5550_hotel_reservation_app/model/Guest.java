package com.example.mcda5550_hotel_reservation_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Guest implements Parcelable {
    private String name;
    private String email;
    private String gender;
    private int age;

    public Guest(String name, String gender, String email, int age) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.age = age;
    }

    protected Guest(Parcel in) {
        name = in.readString();
        gender = in.readString();
        email = in.readString();
        age = in.readInt();
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
    public String getEmail() { return email;}
    public int getAge() { return age;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
