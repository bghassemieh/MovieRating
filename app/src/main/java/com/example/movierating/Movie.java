package com.example.movierating;

import java.io.Serializable;

public class Movie implements Serializable {

    private int movieId = 0;
    private String movieName = "";
    private float movieRating = 0.0f;

    public Movie() {

    }

    public Movie(int movieId, String movieName, float movieRating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieRating = movieRating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }
}

