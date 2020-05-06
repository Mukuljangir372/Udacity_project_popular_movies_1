package com.mu.jan.themoviedbrestapi.Api;

import com.mu.jan.themoviedbrestapi.Models.Movie;
import com.mu.jan.themoviedbrestapi.MovieResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("movie/now_playing")
    Call<MovieResult> getMovies(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<MovieResult> getTopRatedMovies(@Query("api_key") String apikey);

    @GET("movie/upcoming")
    Call<MovieResult> getUpComingMovies(@Query("api_key") String apikey);

    @GET("tv/popular")
    Call<MovieResult> getTvShows(@Query("api_key") String apikey);

    @GET("tv/top_rated")
    Call<MovieResult> getTopTvShows(@Query("api_key") String apikey);

}
