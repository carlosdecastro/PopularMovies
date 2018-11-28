package com.example.android.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.popularmovies.fragments.MovieInfoFragment;
import com.example.android.popularmovies.fragments.MovieReviewsFragment;
import com.example.android.popularmovies.fragments.MovieTrailersFragment;

public class MoviesInfoAdapter extends FragmentPagerAdapter {
    public MoviesInfoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return new MovieInfoFragment();

            case 1:
                return new MovieTrailersFragment();

            case 2:
                return new MovieReviewsFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
