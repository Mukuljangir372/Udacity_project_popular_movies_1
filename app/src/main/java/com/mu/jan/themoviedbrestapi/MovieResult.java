package com.mu.jan.themoviedbrestapi;

import com.google.gson.annotations.SerializedName;
import com.mu.jan.themoviedbrestapi.Models.Movie;

import java.util.List;

public class MovieResult {

    @SerializedName("results")
    private List<Movie> list;

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }
}
