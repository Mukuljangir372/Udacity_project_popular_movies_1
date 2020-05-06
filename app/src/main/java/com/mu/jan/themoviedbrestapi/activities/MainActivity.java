package com.mu.jan.themoviedbrestapi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import android.os.Bundle;
import android.widget.Toast;

import com.mu.jan.themoviedbrestapi.Adapters.MyRecyclerAdapter;
import com.mu.jan.themoviedbrestapi.Api.ApiClient;
import com.mu.jan.themoviedbrestapi.Api.MovieInterface;
import com.mu.jan.themoviedbrestapi.Models.Movie;
import com.mu.jan.themoviedbrestapi.Models.MovieResult;
import com.mu.jan.themoviedbrestapi.R;

import java.util.List;
//created by mukul jangir
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //provide retrofit
        Retrofit retrofit = new ApiClient().provideRetrofit();

        //implementation of interface using retrofit
        final MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        //api call using Retrofit and set adapter
        Call<MovieResult> topMoviesList = movieInterface.getTopRatedMovies(getResources().getString(R.string.api_key));
        //set adapter
        setListToAdapter(topMoviesList);

    }

    private void setListToAdapter(Call<MovieResult> list){
        list.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                //get list from body and set to adapter
                List<Movie> movieList = response.body().getList();
                recyclerView.setAdapter(new MyRecyclerAdapter(MainActivity.this,movieList));
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Toast.makeText(MainActivity.this,R.string.toast_error_while_loading,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
