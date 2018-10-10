package com.example.danielgomez.themoviedb;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{
    List<Result> results;

    public MovieAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder viewHolder, final int i) {
        Picasso.with(viewHolder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w185/"+ results.get(i).getPosterPath())
                .into(viewHolder.Img);

        viewHolder.MovieTitle.setText(results.get(i).getTitle());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result data = results.get(i);
                Intent intent = new Intent(viewHolder.itemView.getContext(),Information.class);
                intent.putExtra("movie", new GsonBuilder().create().toJson(data));
                viewHolder.itemView.getContext().startActivity(intent);
                Activity activity = (Activity) viewHolder.itemView.getContext();
                activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
    public void setData(List<Result> results){
        this.results = results;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        ImageView Img;
        TextView MovieTitle;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.Img_of_movie_list);
            MovieTitle = (TextView) itemView.findViewById(R.id.Movie_title);
        }
    }
}