package com.example.android.popularmovies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.MovieActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.util.Helper;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieInfoFragment extends Fragment {


    public MovieInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_info, container, false);

        Movie movie = MovieActivity.currentMovie;

        ImageView imageIv = rootView.findViewById(R.id.image_iv);
        TextView releaseDateTv = rootView.findViewById(R.id.origin_tv);
        TextView voteAverageTv = rootView.findViewById(R.id.also_known_tv);
        TextView plotSynopsisTv = rootView.findViewById(R.id.description_tv);

        Picasso.with(getContext()).load(Helper.imageUrl(movie.getBackdropPath())).into(imageIv);

        releaseDateTv.setText(movie.getReleaseDate());
        voteAverageTv.setText(movie.getVoteAverage().toString());
        plotSynopsisTv.setText(movie.getOverview());

        return rootView;

    }

}
