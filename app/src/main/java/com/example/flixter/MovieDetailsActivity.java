package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;

    // View Objects.
    TextView tvDetailTitle;
    TextView tvDetailOverview;
    RatingBar rbVoteAverage;
    ImageView ivDetailPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Assign the Views based on their ids.
        tvDetailTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvDetailOverview = (TextView) findViewById(R.id.tvDetailOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        ivDetailPoster = (ImageView) findViewById(R.id.ivDetailPoster);

        // Unwrap the parcel
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // Bind the viewholders.
        tvDetailTitle.setText(movie.getTitle());
        tvDetailOverview.setText(movie.getOverview());

        // Set the vote average of the movie to the rating bar view.
        // Since vote averages range from 0 - 10 (by API), we scale the range to
        // 0 - 5.
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        String imageUrl;

        // Image-related view
        // if the phone orientation is in landscape
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // then use the background image
            imageUrl = movie.getBackDropPath();
        } else {
            // else use the poster image
            imageUrl = movie.getPosterPath();
        }

        // Show the placeholder image if the image has not fully loaded.
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(ivDetailPoster);
    }
}