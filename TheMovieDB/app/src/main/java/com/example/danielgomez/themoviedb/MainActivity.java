package com.example.danielgomez.themoviedb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabPopular, fabTopRated, fabUpComing;

    Button btn;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<Result> results;

    static String popularMovie = "popularMovie";
    static String topRatedMovie = "topRatedMovie";
    static String upcomingMovie = "upcomingMovie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.buttonSeries);
        fabPopular = (FloatingActionButton) findViewById(R.id.fab_popular);
        fabTopRated = (FloatingActionButton) findViewById(R.id.fab_top_rated);
        fabUpComing = (FloatingActionButton) findViewById(R.id.fab_up_coming);

        recyclerView = (RecyclerView) findViewById(R.id.resultView);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TvActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        fabPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMovie(popularMovie);
            }
        });

        fabTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMovie(topRatedMovie);
            }
        });

        fabUpComing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMovie(upcomingMovie);
            }
        });

    }

    @Override
    protected void onStart() {
        loadMovie(popularMovie);
        super.onStart();
    }
    private void loadMovie(String category){
        InterfaceAPI interfaceAPI = ApiClient.getRetrofit().create(InterfaceAPI.class);
        Call<Movie> call = null;

        if (category.equals(popularMovie)){
            call = interfaceAPI.getPopularMovie();
        }else if (category.equals(topRatedMovie)){
            call = interfaceAPI.getTopRatedMovie();
        }else if (category.equals(upcomingMovie)){
            call = interfaceAPI.getUpcomingMovie();
        }

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                movieAdapter = new MovieAdapter(results);
                movieAdapter.setData(movie.getResults());
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }
}
