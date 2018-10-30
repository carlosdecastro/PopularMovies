package com.example.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.adapter.MoviesAdapter;

import com.example.android.popularmovies.data.MovieAPI;
import com.example.android.popularmovies.data.MovieRestAdapter;
import com.example.android.popularmovies.data.UrlManager;

import com.example.android.popularmovies.model.Movie;
import com.example.android.popularmovies.model.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ListItemClickListener {

    private RecyclerView moviesRv;
    private List<Movie> movies;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView infoTv = findViewById(R.id.tvInfo);
        final ImageView errorIv = findViewById(R.id.ivError);
        final ProgressBar progressBar = findViewById(R.id.loading_spinner);

        moviesRv = findViewById(R.id.rvMovies);

        // Set the layout of the recycler view to a gridlayout
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));

        // We initialises the sharedPreferences to read the default one
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String orderBy  = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        // We use the third party library Retrofit to managed the calls
        // to the API and parsing the data.
        Retrofit retrofit = MovieAPI.provideRetrofit();

        final MovieRestAdapter movieRestAdapter = retrofit.create(MovieRestAdapter.class);

        // We use the object movieRestAdapter to make calls to the API
        // The KEY is defined in data/UrlManager
        Call<Movies> call = movieRestAdapter.getMoviesBy(orderBy, UrlManager.KEY);

        call.enqueue(new Callback<Movies>() {

            @Override
            public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                if (!response.isSuccessful()) {
                    infoTv.setText(response.code());
                    moviesRv.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                progressBar.setVisibility(View.GONE);
                Movies data = response.body();
                movies = data.getResults();
                //If everything is OK with the data we populated the adapter with it.
                setAdapter();
            }


            // If something wrong happens with the data we show the exception throw a textView
            @Override
            public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {
                infoTv.setText(t.getMessage());
                errorIv.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setAdapter() {
        moviesAdapter = new MoviesAdapter(getBaseContext(), movies, this);
        moviesRv.setAdapter(moviesAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("movie", movies.get(clickedItemIndex));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
