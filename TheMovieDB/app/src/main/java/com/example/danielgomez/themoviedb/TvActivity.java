package com.example.danielgomez.themoviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvActivity extends AppCompatActivity {

    FloatingActionButton fabPopular, fabTopRated;

    Button btn;
    RecyclerView recyclerView;
    TvAdapter tvAdapter;
    List<ResultTV> resultTVs;

    static String popularTV = "popularTV";
    static String topRatedTV = "topRatedTV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);

        btn = (Button) findViewById(R.id.buttonSeries);
        fabPopular = (FloatingActionButton) findViewById(R.id.fab_popular);
        fabTopRated = (FloatingActionButton) findViewById(R.id.fab_top_rated);

        btn = (Button) findViewById(R.id.buttonPeliculas);
        recyclerView = (RecyclerView) findViewById(R.id.resultViewTV);
        recyclerView.setLayoutManager(new GridLayoutManager(TvActivity.this,2));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TvActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        fabPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTV(popularTV);
            }
        });

        fabTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTV(topRatedTV);
            }
        });

    }

    @Override
    protected void onStart() {
        loadTV(popularTV);
        super.onStart();
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.finish();
    }

    private void loadTV(String category){
        InterfaceAPI interfaceAPI = ApiClient.getRetrofit().create(InterfaceAPI.class);
        Call<Tv> call = null;

        if (category.equals(popularTV)){
            call = interfaceAPI.getPopularTV();
        }else if (category.equals(topRatedTV)){
            call = interfaceAPI.getTopRatedTV();
        }

        call.enqueue(new Callback<Tv>() {
            @Override
            public void onResponse(Call<Tv> call, Response<Tv> response) {
                Tv tv = response.body();
                tvAdapter = new TvAdapter(resultTVs);
                tvAdapter.setData(tv.getResults());
                recyclerView.setAdapter(tvAdapter);
            }

            @Override
            public void onFailure(Call<Tv> call, Throwable t) {

            }
        });

    }
}
