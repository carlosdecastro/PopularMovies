package com.example.android.popularmovies.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.android.popularmovies.adapter.TrailersAdapter;
import com.example.android.popularmovies.data.MovieAPI;
import com.example.android.popularmovies.data.MovieRestAdapter;
import com.example.android.popularmovies.data.UrlManager;
import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.Trailer;
import com.example.android.popularmovies.model.Trailers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MovieTrailersFragment extends Fragment implements TrailersAdapter.ListItemClickListener {

    private List<Trailer> trailers;
    private RecyclerView trailersRv;
    private Context mContext;
    private RelativeLayout messageLayout;
    private TextView emptyTitleText;
    private TextView emptySubtitleText;
    private ImageView emptyMovieImage;

    public MovieTrailersFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_trailers, container, false);

        trailersRv = rootView.findViewById(R.id.rvTrailers);
        final ProgressBar progressBar = rootView.findViewById(R.id.progressBar);
        emptyTitleText = rootView.findViewById(R.id.empty_title_text);
        emptyMovieImage = rootView.findViewById(R.id.empty_movies_image);
        emptySubtitleText = rootView.findViewById(R.id.empty_subtitle_text);
        messageLayout = rootView.findViewById(R.id.empty_trailers_view);
        messageLayout.setVisibility(View.GONE);

        mContext = getContext();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        trailersRv.setLayoutManager(llm);

        Movie movie = MovieActivity.currentMovie;

        // We use the third party library Retrofit to managed the calls
        // to the API and parsing the data.
        Retrofit retrofit = MovieAPI.provideRetrofit();

        final MovieRestAdapter movieRestAdapter = retrofit.create(MovieRestAdapter.class);

        // We use the object movieRestAdapter to make calls to the API
        // The KEY is defined in data/UrlManager

        Call<Trailers> call = movieRestAdapter.getTrailers(movie.getId(), UrlManager.KEY);

        call.enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {

                Trailers data = response.body();
                trailers = data.getResults();
                progressBar.setVisibility(View.GONE);

                if (trailers.size() > 0) {

                    setAdapter();

                } else {
                    messageLayout.setVisibility(View.VISIBLE);
                    emptyMovieImage.setImageResource(R.drawable.ic_film);
                    emptyTitleText.setText(R.string.trailers_empty_title);
                    emptySubtitleText.setText(getString(R.string.trailers_empty_subtitle));
                }
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {
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
        TrailersAdapter trailersAdapter = new TrailersAdapter(mContext, trailers, this);
        trailersRv.setAdapter(trailersAdapter);
    }


    @Override
    public void onListItemClick(Trailer trailer) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setPackage("com.google.android.youtube");

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("www.youtube.com")
                .appendPath("watch")
                .appendQueryParameter("v", trailer.getKey());

        String myUrl = builder.build().toString();
        intent.setData(Uri.parse(myUrl));

        startActivity(intent);

    }
}
