package com.example.danielgomez.themoviedb;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceAPI {

    public static String DB_API = "9c81c4123055fd42569d1d62a43f80ff";

    @GET("movie/popular?api_key="+DB_API)
    Call<Movie> getPopularMovie();

    @GET("movie/top_rated?api_key="+DB_API)
    Call<Movie> getTopRatedMovie();

    @GET("movie/upcoming?api_key="+DB_API)
    Call<Movie> getUpcomingMovie();

    @GET("tv/popular?api_key="+DB_API)
    Call<Tv> getPopularTV();

    @GET("tv/top_rated?api_key="+DB_API)
    Call<Tv> getTopRatedTV();


}

