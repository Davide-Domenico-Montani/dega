package com.example.cinevibes.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataKt;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinevibes.model.Film;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("SELECT * FROM Film")
    List<Film> getAll();

    @Insert
    void insertAll (Film... users);

    @Delete
    void delete(Film user);

    @Query("DELETE from Film")
    void deleteAll();

    /*@Query("SELECT * FROM Film WHERE liked = 1")
    List<Film> getLiked();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Film... films);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Film> films);
*/
  //  @Update
   // void updateFilm(Film film);

    //@Query("DELETE from Film WHERE liked = 0")
    //void deleteCached();



}
