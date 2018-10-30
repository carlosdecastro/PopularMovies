package com.example.android.popularmovies.model;


import java.util.List;
import com.google.gson.annotations.SerializedName;

//This class defines the objects Movies which essentially is a list of objects Movie
public class Movies {

    @SerializedName("page")
    private Integer mPage;

    @SerializedName("total_results")
    private Integer mTotalResults;

    @SerializedName("total_pages")
    private Integer mTotalPages;

    @SerializedName("results")
    private List<Movie> mMovies = null;


    public Movies() {
    }

    public Integer getPage() {
        return mPage;
    }

    public Integer getTotalResults() {
        return mTotalResults;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public List<Movie> getResults() {
        return mMovies;
    }

}
