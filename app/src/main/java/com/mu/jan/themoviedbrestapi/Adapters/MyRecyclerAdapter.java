package com.mu.jan.themoviedbrestapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mu.jan.themoviedbrestapi.Models.Movie;
import com.mu.jan.themoviedbrestapi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context context;
    private static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";
    List<Movie> movieList;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String image_path =  IMAGE_URL_BASE_PATH + movieList.get(position).getPoster_path();
        //load image
        Picasso.with(context)
                .load(image_path)
                .placeholder(android.R.drawable.stat_sys_upload)
                .error(android.R.drawable.stat_sys_upload)
                .into(holder.imageView);

        if(movieList.get(position).getTitle()!=null)  holder.title.setText(movieList.get(position).getTitle());
        else  holder.title.setText(movieList.get(position).getTv_name());
        holder.title.setText(movieList.get(position).getShort_title());
        holder.about.setText(movieList.get(position).getOverview());
        holder.rating.setText(movieList.get(position).getVote_average()+"");

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,rating,about;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title_movie);
            rating = (TextView)itemView.findViewById(R.id.popularity_text);
            about =(TextView)itemView.findViewById(R.id.overview_text);
            imageView = (ImageView)itemView.findViewById(R.id.imagePoster_id);

        }
    }
}
