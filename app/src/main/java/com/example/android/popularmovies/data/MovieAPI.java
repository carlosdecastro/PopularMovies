package com.example.android.popularmovies.data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieAPI {

    public static Retrofit provideRetrofit(){
        OkHttpClient client =
                new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        //.readTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlManager.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
