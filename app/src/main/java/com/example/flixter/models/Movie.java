package com.example.flixter.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String title;
    String overview;

    public Movie (JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List<Movie> listOfMovies = new ArrayList<>();
        for (int i = 0; i < movieJSONArray.length(); i++) {
            listOfMovies.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return listOfMovies;
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
