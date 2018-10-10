package com.example.danielgomez.themoviedb;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class Information extends AppCompatActivity {
    ImageView Img;
    TextView NameMovie, DescriptionMovie, DateMovie, RateMovie;
    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Img = (ImageView) findViewById(R.id.Image_movie);
        NameMovie = (TextView) findViewById(R.id.Name_movie);
        DateMovie = (TextView) findViewById(R.id.Date_of_movie);
        RateMovie = (TextView) findViewById(R.id.Rate_of_movie);
        DescriptionMovie = (TextView) findViewById(R.id.Description_of_movie);

        result = new GsonBuilder().create()
                .fromJson(getIntent().getStringExtra("movie"),Result.class);

        Picasso.with(Information.this)
                .load("http://image.tmdb.org/t/p/w185/"+ result.getPosterPath())
                .into(Img);

        NameMovie.setText(result.getTitle());
        DateMovie.setText(result.getReleaseDate());
        RateMovie.setText("Puntuacion: " + Double.toString(result.getVoteAverage()));
        DescriptionMovie.setText(result.getOverview());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
