package com.mu.jan.themoviedbrestapi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mu.jan.themoviedbrestapi.R;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        TextView movie_name = findViewById(R.id.movie_name);
        ImageView movie_image = findViewById(R.id.image_movie);
        TextView description_text = findViewById(R.id.des_text);
        TextView rating_text = findViewById(R.id.rating);

        //get intent and load data
        String name = getIntent().getStringExtra("name");
        String image_url = getIntent().getStringExtra("image");
        String description = getIntent().getStringExtra("des");
        String ratings = getIntent().getStringExtra("rating");

        //load imge using Glide
        Glide.with(this).load(image_url).into(movie_image);

        //load text of movie
        movie_name.setText(name);
        rating_text.setText(ratings);
        description_text.setText(description);


    }
}
