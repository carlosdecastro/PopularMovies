package com.example.android.popularmovies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.popularmovies.MovieActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.ReviewsAdapter;
import com.example.android.popularmovies.data.MovieAPI;
import com.example.android.popularmovies.data.MovieRestAdapter;
import com.example.android.popularmovies.data.UrlManager;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.Review;
import com.example.android.popularmovies.model.Reviews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieReviewsFragment extends Fragment {

    private List<Review> review;
    private RecyclerView reviewsRv;
    private Context mContext;
    private TextView emptyTitleText;
    private TextView emptySubtitleText;
    private RelativeLayout messageLayout;
    private ImageView emptyMovieImage;

    public MovieReviewsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_reviews, container, false);

        final ProgressBar progressBar = rootView.findViewById(R.id.progressBar);
        reviewsRv = rootView.findViewById(R.id.rvReviews);
        emptyTitleText = rootView.findViewById(R.id.empty_title_text);
        emptySubtitleText = rootView.findViewById(R.id.empty_subtitle_text);
        emptyMovieImage = rootView.findViewById(R.id.empty_movies_image);
        messageLayout = rootView.findViewById(R.id.empty_reviews_view);
        messageLayout.setVisibility(View.GONE);
        mContext = getContext();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        reviewsRv.setLayoutManager(llm);

        Movie movie = MovieActivity.currentMovie;

        // We use the third party library Retrofit to managed the calls
        // to the API and parsing the data.
        Retrofit retrofit = MovieAPI.provideRetrofit();

        final MovieRestAdapter movieRestAdapter = retrofit.create(MovieRestAdapter.class);

        // We use the object movieRestAdapter to make calls to the API
        // The KEY is defined in data/UrlManager

        Call<Reviews> call = movieRestAdapter.getReviews(movie.getId(), UrlManager.KEY);

        call.enqueue(new Callback<Reviews>() {

            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                Reviews data = response.body();
                review = data.getResults();
                progressBar.setVisibility(View.GONE);

                if (review.size() > 0) {
                    setAdapter();
                } else {
                    messageLayout.setVisibility(View.VISIBLE);
                    emptyMovieImage.setImageResource(R.drawable.ic_film);
                    emptyTitleText.setText(R.string.reviews_empty_title);
                    emptySubtitleText.setText(R.string.reviews_empty_subtitle);
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {
                //errorTv.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                messageLayout.setVisibility(View.VISIBLE);
                emptyTitleText.setText(getString(R.string.error_empty_title));
                emptySubtitleText.setText(R.string.error_empty_subtitle);
                emptyMovieImage.setImageResource(R.drawable.ic_wifi);
            }
        });


        return rootView;
    }

    private void setAdapter() {
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(mContext, review);
        reviewsRv.setAdapter(reviewsAdapter);
    }
}
