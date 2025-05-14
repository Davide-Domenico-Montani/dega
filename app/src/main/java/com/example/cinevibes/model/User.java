package com.example.cinevibes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {


    String userId;
    String name;
    String surname;
    String email;
    String password;
    int eta;

    public User(String userId, String name, String surname, String email, String password, int eta) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.eta = eta;
    }

    public User(String userId, String email, String password){
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public User(String userId, String email){
        this.userId = userId;
        this.email = email;
    }
    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeInt(eta);
    }

    public void readFromParcel(Parcel source) {
        userId = source.readString();
        name = source.readString();
        surname = source.readString();
        email = source.readString();
        password = source.readString();
        eta = source.readInt();
    }

    protected User(Parcel in) {
        userId = in.readString();
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        password = in.readString();
        eta = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
