package com.example.android.popularmovies;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.util.Helper;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ImageView imageIv = findViewById(R.id.image_iv);
        TextView releaseDateTv = findViewById(R.id.origin_tv);
        TextView voteAverageTv = findViewById(R.id.also_known_tv);
        TextView plotSynopsisTv = findViewById(R.id.description_tv);

        Movie movie = getIntent().getParcelableExtra("movie");

        setTitle(movie.getTitle());

        Picasso.with(this).load(Helper.imageUrl(movie.getBackdropPath())).into(imageIv);


        releaseDateTv.setText(movie.getReleaseDate());
        voteAverageTv.setText(movie.getVoteAverage().toString());
        plotSynopsisTv.setText(movie.getOverview());

    }

}
