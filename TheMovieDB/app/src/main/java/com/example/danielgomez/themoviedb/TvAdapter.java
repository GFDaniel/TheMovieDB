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

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder>{
    List<ResultTV> results;

    public TvAdapter(List<ResultTV> results) {
        this.results = results;
    }

    @Override
    public TvHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tv_list, viewGroup, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(final TvHolder viewHolder, final int i) {
        Picasso.with(viewHolder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w185/"+ results.get(i).getPosterPath())
                .into(viewHolder.Img);
        viewHolder.TvTitle.setText(results.get(i).getOriginalName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultTV data = results.get(i);
                Intent intent = new Intent(viewHolder.itemView.getContext(),InformationTV.class);
                intent.putExtra("tv", new GsonBuilder().create().toJson(data));
                viewHolder.itemView.getContext().startActivity(intent);
                Activity activity = (Activity) viewHolder.itemView.getContext();
                activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }
    public void setData(List<ResultTV> results){ this.results = results; }

    @Override
    public int getItemCount() { return results.size(); }

    class TvHolder extends RecyclerView.ViewHolder{
        ImageView Img;
        TextView TvTitle;
        public TvHolder(@NonNull View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.Img_of_tv_list);
            TvTitle = (TextView) itemView.findViewById(R.id.Tv_title);
        }
    }
}
