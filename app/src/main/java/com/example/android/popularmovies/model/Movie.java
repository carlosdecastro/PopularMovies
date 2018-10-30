package com.example.android.popularmovies.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.SerializedName;

// This class defines the objects Movie, we use the third party library retrofit wit Gson for parse the json information.
// We implements Parcelable because it has better performance passing objects throw activities

public class Movie implements Parcelable {

    @SerializedName("vote_count")
    private Integer mVoteCount;

    @SerializedName("id")
    private Integer mId;

    @SerializedName("video")
    private Boolean mVideo;

    @SerializedName("vote_average")
    private Float mVoteAverage;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("popularity")
    private Float mPopularity;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("original_language")
    private String mOriginalLanguage;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("genre_ids")
    private List<Integer> mGenreIds = null;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("adult")
    private Boolean mAdult;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;


    private Movie(Parcel in) {

        if (in.readByte() == 0) {
            mVoteCount = null;
        } else {
            mVoteCount = in.readInt();
        }

        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }

        if (in.readByte() == 0) {
            mVoteAverage = null;
        } else {
            mVoteAverage = in.readFloat();
        }

        if (in.readByte() == 0) {
            mPopularity = null;
        } else {
            mPopularity = in.readFloat();
        }

        mTitle = in.readString();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
        mBackdropPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();

        byte tmpVideo = in.readByte();
        mVideo = tmpVideo == 0 ? null : tmpVideo == 1;

        byte tmpAdult = in.readByte();
        mAdult = tmpAdult == 0 ? null : tmpAdult == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (mVoteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mVoteCount);
        }

        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mId);
        }

        if (mVoteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(mVoteAverage);
        }

        if (mPopularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(mPopularity);
        }

        dest.writeString(mTitle);
        dest.writeString(mPosterPath);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mOriginalTitle);
        dest.writeString(mBackdropPath);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);

        dest.writeByte((byte) (mVideo == null ? 0 : mVideo ? 1 : 2));
        dest.writeByte((byte) (mAdult == null ? 0 : mAdult ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public Integer getId() {
        return mId;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public Float getVoteAverage() {
        return mVoteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public Float getPopularity() {
        return mPopularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

}


