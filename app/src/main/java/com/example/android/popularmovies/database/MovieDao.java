package com.example.android.popularmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.popularmovies.model.Movie;

import java.util.List;


@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> loadAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("SELECT * FROM movie WHERE mId = :id")
    Movie loadMoviebyId(int id);
    //LiveData<Movie> loadMoviebyId(int id);
}
