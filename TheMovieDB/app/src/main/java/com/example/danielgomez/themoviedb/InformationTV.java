package com.example.danielgomez.themoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class InformationTV extends AppCompatActivity {
    ImageView Img;
    TextView NameMovie, DescriptionMovie, DateMovie, RateMovie;
    ResultTV result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_tv);

        Img = (ImageView) findViewById(R.id.Image_tv);
        NameMovie = (TextView) findViewById(R.id.Name_tv);
        DateMovie = (TextView) findViewById(R.id.Date_of_tv);
        RateMovie = (TextView) findViewById(R.id.Rate_of_tv);
        DescriptionMovie = (TextView) findViewById(R.id.Description_of_tv);

        result = new GsonBuilder().create()
                .fromJson(getIntent().getStringExtra("tv"),ResultTV.class);

        Picasso.with(InformationTV.this)
                .load("http://image.tmdb.org/t/p/w185/"+ result.getPosterPath())
                .into(Img);

        NameMovie.setText(result.getOriginalName());
        DateMovie.setText(result.getFirstAirDate());
        RateMovie.setText("Puntuacion: " + Double.toString(result.getVoteAverage()));
        DescriptionMovie.setText(result.getOverview());
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
