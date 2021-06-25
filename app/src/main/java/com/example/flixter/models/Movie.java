package com.example.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String backDropPath;
    String posterPath;
    String title;
    String overview;
    Double voteAverage;

    public Movie(){}

    public Movie (JSONObject jsonObject) throws JSONException {
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List<Movie> listOfMovies = new ArrayList<>();
        for (int i = 0; i < movieJSONArray.length(); i++) {
            listOfMovies.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return listOfMovies;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", this.backDropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", this.posterPath);
    }

    public String getTitle() {
        return this.title;
    }

    public String getOverview() {
        return this.overview;
    }
}
