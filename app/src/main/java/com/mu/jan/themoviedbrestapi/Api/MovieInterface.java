package com.mu.jan.themoviedbrestapi.Api;

import com.mu.jan.themoviedbrestapi.Models.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
    @GET("movie/top_rated")
    Call<MovieResult> getTopRatedMovies(@Query("api_key") String key);

}
