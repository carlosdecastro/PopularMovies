package com.example.android.popularmovies.data;

import com.example.android.popularmovies.model.Movies;
import com.example.android.popularmovies.model.Reviews;
import com.example.android.popularmovies.model.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieRestAdapter {

    @GET(UrlManager.MOVIE)
    Call<Movies> getMoviesBy(@Path("orderBy") String orderBy, @Query("api_key") String apiKey);

    @GET(UrlManager.MOVIE_REVIEWS)
    Call<Reviews> getReviews(@Path("id") int id, @Query("api_key") String apiKey);

    @GET(UrlManager.MOVIE_VIDEOS)
    Call<Trailers> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);

}

