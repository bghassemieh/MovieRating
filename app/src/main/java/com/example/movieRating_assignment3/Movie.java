package com.example.movieRating_assignment3;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_ACTIVEFLAG = "active";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_RATING + " FLOAT,"
            + COLUMN_ACTIVEFLAG + " INTEGER)";

    private int movieId;
    private String movieName;
    private String movieDescription;
    private float movieRating;
    private boolean movieActiveFlag;

    public Movie() {

    }

    public Movie(String movieName, String movieDescription, float movieRating, boolean movieActiveFlag) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieRating = movieRating;
        this.movieActiveFlag = movieActiveFlag;
    }


    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescriptio) {
        this.movieDescription = movieDescriptio;
    }

    public boolean isMovieActiveFlag() {
        return movieActiveFlag;
    }

    public void setMovieActiveFlag(boolean movieActiveFlag) {
        this.movieActiveFlag = movieActiveFlag;
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

