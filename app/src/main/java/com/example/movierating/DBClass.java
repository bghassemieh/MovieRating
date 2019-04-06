package com.example.movierating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBClass extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;

    public DBClass (Context context){
        super(context, Movie.TABLE_NAME, null, DATABASE_VERSION);
    }

    public Movie addMovie(Movie m)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Movie.COLUMN_NAME, m.getMovieName());
        values.put(Movie.COLUMN_DESCRIPTION, m.getMovieDescription());
        values.put(Movie.COLUMN_RATING, m.getMovieRating());
        values.put(Movie.COLUMN_ACTIVEFLAG, m.isMovieActiveFlag());

        db.insert(Movie.TABLE_NAME, null, values);
        return m;
    }

    public List<Movie> getMovies()
    {

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + Movie.TABLE_NAME + " where " + Movie.COLUMN_ACTIVEFLAG + " =1";

        Cursor c = db.rawQuery(selectQuery, null);

        List<Movie> movieList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                Movie foundMovie = new Movie();
                foundMovie.setMovieId(c.getInt(c.getColumnIndex(Movie.COLUMN_ID)));
                foundMovie.setMovieName(c.getString(c.getColumnIndex(Movie.COLUMN_NAME)));
                foundMovie.setMovieDescription(c.getString(c.getColumnIndex(Movie.COLUMN_DESCRIPTION)));
                foundMovie.setMovieRating(c.getFloat(c.getColumnIndex(Movie.COLUMN_RATING)));
                foundMovie.setMovieActiveFlag(c.getInt(c.getColumnIndex(Movie.COLUMN_ACTIVEFLAG)) !=0);

                movieList.add(foundMovie);
            }while (c.moveToNext());
        }
        db.close();
        return movieList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Movie.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
