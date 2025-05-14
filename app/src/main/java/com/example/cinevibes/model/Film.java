package com.example.cinevibes.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

@Entity
public class Film implements Parcelable {

    @PrimaryKey
    public int uid;

    private int id;
    private String original_language;
    private String overview; //trama
    private String poster_path;
    private String release_date;
    private String title;
    private float vote_avarage;
    private List<Integer> genre_ids;

    @Ignore
    public Film() {} //costruttore
    @Ignore
    public Film(int id, String original_language, String overview, String poster_path, String release_date, String title, float vote_avarage, List<Integer> genre_ids) {
        this.id = id;
        this.original_language = original_language;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_avarage = vote_avarage;
        this.genre_ids = genre_ids;
    }

    public Film(int id, String original_language, String overview, String poster_path, String release_date, String title, float vote_avarage) {
        this(id, original_language, overview, poster_path, release_date, title, vote_avarage, null);
    }

    protected Film(Parcel in) {
        uid = in.readInt();
        id = in.readInt();
        original_language = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_avarage = in.readFloat();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVote_avarage() {
        return vote_avarage;
    }

    public void setVote_avarage(float vote_avarage) {
        this.vote_avarage = vote_avarage;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return uid == film.uid && id == film.id && Float.compare(vote_avarage, film.vote_avarage) == 0 && Objects.equals(original_language, film.original_language) && Objects.equals(overview, film.overview) && Objects.equals(poster_path, film.poster_path) && Objects.equals(release_date, film.release_date) && Objects.equals(title, film.title) && Objects.equals(genre_ids, film.genre_ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, id, original_language, overview, poster_path, release_date, title, vote_avarage, genre_ids);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeInt(this.id);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeString(this.title);
        dest.writeFloat(this.vote_avarage);
        dest.writeList(this.genre_ids);
    }

    public void readFromParcel(Parcel source) {
        this.uid = source.readInt();
        this.id = source.readInt();
        this.original_language = source.readString();
        this.overview = source.readString();
        this.poster_path = source.readString();
        this.release_date = source.readString();
        this.title = source.readString();
        this.vote_avarage = source.readFloat();
        this.genre_ids = source.readArrayList(Integer.class.getClassLoader());
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}