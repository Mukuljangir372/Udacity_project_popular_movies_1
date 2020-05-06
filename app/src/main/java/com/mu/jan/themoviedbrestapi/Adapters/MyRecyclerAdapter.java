package com.mu.jan.themoviedbrestapi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mu.jan.themoviedbrestapi.Models.Movie;
import com.mu.jan.themoviedbrestapi.R;
import com.mu.jan.themoviedbrestapi.Utils.AppConstants;
import com.mu.jan.themoviedbrestapi.activities.DetailedActivity;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final List<Movie> movieList;

    public MyRecyclerAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String image_path = AppConstants.IMAGE_URL_BASE_PATH + movieList.get(position).getPoster_path();
        //load image
        Glide.with(context)
                .load(image_path)
                .placeholder(android.R.drawable.stat_sys_upload)
                .error(android.R.drawable.stat_sys_upload)
                .into(holder.imageView);

        //sometimes title get null
        //to prevent this,i'm using if else statement
        if(movieList.get(position).getTitle()!=null) holder.title.setText(movieList.get(position).getTitle());
        holder.title.setText(movieList.get(position).getShort_title());
        holder.about.setText(movieList.get(position).getOverview());

        //onClick
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String image_path = AppConstants.IMAGE_URL_BASE_PATH + movieList.get(position).getPoster_path();

                Intent i = new Intent(context, DetailedActivity.class);
                i.putExtra("name",movieList.get(position).getShort_title());
                i.putExtra("image",image_path);
                i.putExtra("rating",String.valueOf(movieList.get(position).getPopularity()));
                i.putExtra("des",movieList.get(position).getOverview());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        final TextView title;
        final TextView about;
        final ImageView imageView;
        final LinearLayout linearLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_movie);
            about =itemView.findViewById(R.id.overview_text);
            imageView =itemView.findViewById(R.id.imagePoster_id);
            linearLayout = itemView.findViewById(R.id.single_movie_item_layout);

        }
    }
}
