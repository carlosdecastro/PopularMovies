package com.example.android.popularmovies.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//This class defines the objects Movies which essentially is a list of objects Movie
public class Movies implements Parcelable {

    @SerializedName("page")
    private Integer mPage;

    @SerializedName("total_results")
    private Integer mTotalResults;

    @SerializedName("total_pages")
    private Integer mTotalPages;

    @SerializedName("results")
    private List<Movie> mMovies = null;


    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };

    protected Movies(Parcel in) {
        if (in.readByte() == 0) {
            mPage = null;
        } else {
            mPage = in.readInt();
        }
        if (in.readByte() == 0) {
            mTotalResults = null;
        } else {
            mTotalResults = in.readInt();
        }
        if (in.readByte() == 0) {
            mTotalPages = null;
        } else {
            mTotalPages = in.readInt();
        }
        mMovies = in.createTypedArrayList(Movie.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mPage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mPage);
        }
        if (mTotalResults == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mTotalResults);
        }
        if (mTotalPages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mTotalPages);
        }
        dest.writeTypedList(mMovies);
    }
}
