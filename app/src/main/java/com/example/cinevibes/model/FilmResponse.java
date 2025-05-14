package com.example.cinevibes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmResponse implements Parcelable {

    @SerializedName("films")
    private List<Film> filmList;

    public FilmResponse() {
    }
    public FilmResponse(List<Film> filmList) {
        this.filmList = filmList;
    }
    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public String toString() {
        return "FilmResponse{" +
                "filmList=" + filmList +
                '}';
    }


    public static final Creator<FilmResponse> CREATOR = new Creator<FilmResponse>() {
        @Override
        public FilmResponse createFromParcel(Parcel in) {
            return new FilmResponse(in);
        }

        @Override
        public FilmResponse[] newArray(int size) {
            return new FilmResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.filmList);
    }

    public void readFromParcel(Parcel source) {
        this.filmList = source.createTypedArrayList(Film.CREATOR);
    }

    protected FilmResponse(Parcel in) {
        this.filmList = in.createTypedArrayList(Film.CREATOR);
    }

}
