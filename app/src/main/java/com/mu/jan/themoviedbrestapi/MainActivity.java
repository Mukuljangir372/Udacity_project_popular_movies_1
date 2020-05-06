package com.mu.jan.themoviedbrestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;

import com.mu.jan.themoviedbrestapi.Adapters.MyRecyclerAdapter;
import com.mu.jan.themoviedbrestapi.Api.MovieInterface;
import com.mu.jan.themoviedbrestapi.Models.Movie;

import java.util.List;

//created by mukul jangir, 15 March 2020

/*
 *For learning how to use Retrofit, Explore retrofit project
 *
 */

public class MainActivity extends AppCompatActivity {

    private CardView movies_card,thisYearmovies_card,upcoming_movies_card;
    private CardView tvshows_card,topTvshows_card;
    private RecyclerView recyclerView;

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies_card = (CardView)findViewById(R.id.movies_card);
        thisYearmovies_card = (CardView)findViewById(R.id.thisyear_movies_card);
        upcoming_movies_card = (CardView)findViewById(R.id.movies_upcoming_card);
        tvshows_card= (CardView)findViewById(R.id.tvShows_card);
        topTvshows_card = (CardView)findViewById(R.id.tvShowstop_card);

        recyclerView =(RecyclerView)findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //using retrofit library
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //implementation of interface using retrofit
        final MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        movies_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Call<MovieResult> list = movieInterface.getMovies(getString(R.string.api_key));
             setListToAdapter(list);
            }
        });

        upcoming_movies_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MovieResult> list = movieInterface.getUpComingMovies(getString(R.string.api_key));
                setListToAdapter(list);
            }
        });

        thisYearmovies_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MovieResult> list = movieInterface.getTopRatedMovies(getString(R.string.api_key));
                setListToAdapter(list);
            }
        });

        tvshows_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MovieResult> list = movieInterface.getTvShows(getString(R.string.api_key));
                setListToAdapter(list);
            }
        });

        topTvshows_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<MovieResult> list = movieInterface.getTopTvShows(getString(R.string.api_key));
                setListToAdapter(list);
            }
        });
    }

    private void setListToAdapter(Call<MovieResult> list){
        list.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                List<Movie> movieList = response.body().getList();
                recyclerView.setAdapter(new MyRecyclerAdapter(MainActivity.this,movieList));
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
    }
}
