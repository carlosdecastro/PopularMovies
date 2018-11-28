package com.example.android.popularmovies.util;

import android.net.Uri;
import android.util.Log;

public final class Helper {

    // This method returns a valid URL for the image

    public static String imageUrl(String path) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w185")
                .appendEncodedPath(path);
        String myUrl = builder.build().toString();

        Log.v("laurl", myUrl + "  path: " + path);

        return myUrl;
    }

}