package com.example.android.popularmovies.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// This class defines the objects Movie, we use the third party library retrofit wit Gson for parse the json information.
// We implements Parcelable because it has better performance passing objects throw activities

@Entity(tableName = "movie")
public class Movie implements Parcelable {

    @SerializedName("vote_count")
    private Integer mVoteCount;

    @Ignore
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
    @PrimaryKey
    @SerializedName("id")
    private Integer mId;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("adult")
    private Boolean mAdult;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;
    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds = null;

    public Movie(Integer mVoteCount, Integer mId, Boolean mVideo, Float mVoteAverage, String mTitle, Float mPopularity, String mPosterPath, String mOriginalLanguage, String mOriginalTitle, String mBackdropPath, Boolean mAdult, String mOverview, String mReleaseDate) {
        this.mVoteCount = mVoteCount;
        this.mId = mId;
        this.mVideo = mVideo;
        this.mVoteAverage = mVoteAverage;
        this.mTitle = mTitle;
        this.mPopularity = mPopularity;
        this.mPosterPath = mPosterPath;
        this.mOriginalLanguage = mOriginalLanguage;
        this.mOriginalTitle = mOriginalTitle;
        this.mBackdropPath = mBackdropPath;
        this.mAdult = mAdult;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
    }

    @Ignore
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

    @Ignore
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

    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

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

    public void setmVoteCount(Integer mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public void setmVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }

    public void setmVoteAverage(Float mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmPopularity(Float mPopularity) {
        this.mPopularity = mPopularity;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public void setmGenreIds(List<Integer> mGenreIds) {
        this.mGenreIds = mGenreIds;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public void setmAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

}


