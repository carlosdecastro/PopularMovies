package com.example.android.popularmovies;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.adapter.MoviesInfoAdapter;
import com.example.android.popularmovies.database.MovieDatabase;
import com.example.android.popularmovies.model.Movie;

public class MovieActivity extends AppCompatActivity {

    public static Movie currentMovie;
    //private int[] icImageId = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private String[] tabTitles = new String[]{"Info", "Trailers", "Reviews"};
    private MenuItem menuFavorite;

    private MovieDatabase mDb;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mDb = MovieDatabase.getInstance(getApplicationContext());

        currentMovie = getIntent().getParcelableExtra("movie");

        if (savedInstanceState == null) {
            checkFavorite(currentMovie.getId());
        } else {
            if (savedInstanceState.containsKey("ISFAVORITE")) {
                isFavorite = savedInstanceState.getBoolean("ISFAVORITE");
            }
        }

        ViewPager movieCategoriesPager = findViewById(R.id.movie_categories_pager);
        MoviesInfoAdapter moviesInfoAdapter = new MoviesInfoAdapter(getSupportFragmentManager());

        movieCategoriesPager.setAdapter(moviesInfoAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(movieCategoriesPager);

        /*for (int i = 0; i < icImageId.length; i++) {
            tabLayout.getTabAt(i).setIcon(icImageId[i]);
        }*/

        for (int i = 0; i < tabTitles.length; i++) {
            tabLayout.getTabAt(i).setText(tabTitles[i]);
        }

        setTitle(currentMovie.getTitle());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("ISFAVORITE", isFavorite);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menuFavorite = menu.getItem(0);
        Log.v("MovieActivity", "onPrepareMenu" + isFavorite);
        changeMenuIcon();


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Prevent that the navigation button in the menu destroys the activity
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_favorite:
                if (!isFavorite) {
                    menuFavorite.setIcon(R.drawable.ic_star_on);
                } else {
                    menuFavorite.setIcon(R.drawable.ic_star_off);
                }
                changeFavorite(currentMovie.getId());
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeFavorite(final int id) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!isFavorite) {
                    isFavorite = true;
                    mDb.movieDao().insertMovie(currentMovie);
                    Log.v("MovieActivity", "Insertado " + isFavorite);
                } else {
                    isFavorite = false;
                    mDb.movieDao().deleteMovie(currentMovie);
                    Log.v("MovieActivity", "Borrado " + isFavorite);

                }
            }
        });
    }

    public void checkFavorite(final int id) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.v("MovieActivity", "Checking for favorite ...");
                isFavorite = mDb.movieDao().loadMoviebyId(id) != null;
            }
        });
    }

    private void changeMenuIcon() {
        if (isFavorite) {
            menuFavorite.setIcon(R.drawable.ic_star_on);
            Log.v("MovieActivity", "Cambiado a launcher " + isFavorite);

        } else {
            menuFavorite.setIcon(R.drawable.ic_star_off);
            Log.v("MovieActivity", "Cambiado a background " + isFavorite);
        }
    }
}
