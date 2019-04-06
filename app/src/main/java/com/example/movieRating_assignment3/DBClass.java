package com.example.movieRating_assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    public List<Movie> getMovies_Active()
    {

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + Movie.TABLE_NAME + " where " + Movie.COLUMN_ACTIVEFLAG + " = 1";

        Cursor c = db.rawQuery(selectQuery, null);

        List<Movie> movieList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                Movie foundMovie = new Movie();
                foundMovie.setMovieId(c.getInt(c.getColumnIndex(Movie.COLUMN_ID)));
                foundMovie.setMovieName(c.getString(c.getColumnIndex(Movie.COLUMN_NAME)));
                foundMovie.setMovieDescription(c.getString(c.getColumnIndex(Movie.COLUMN_DESCRIPTION)));
                foundMovie.setMovieRating(c.getFloat(c.getColumnIndex(Movie.COLUMN_RATING)));
                foundMovie.setMovieActiveFlag(c.getInt(c.getColumnIndex(Movie.COLUMN_ACTIVEFLAG)) != 0);

                movieList.add(foundMovie);
            }while (c.moveToNext());
        }
        db.close();
        return movieList;
    }

    public List<Movie> getMovies_Inactive()
    {

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + Movie.TABLE_NAME + " where " + Movie.COLUMN_ACTIVEFLAG + " = 0";

        Cursor c = db.rawQuery(selectQuery, null);

        List<Movie> movieList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                Movie foundMovie = new Movie();
                foundMovie.setMovieId(c.getInt(c.getColumnIndex(Movie.COLUMN_ID)));
                foundMovie.setMovieName(c.getString(c.getColumnIndex(Movie.COLUMN_NAME)));
                foundMovie.setMovieDescription(c.getString(c.getColumnIndex(Movie.COLUMN_DESCRIPTION)));
                foundMovie.setMovieRating(c.getFloat(c.getColumnIndex(Movie.COLUMN_RATING)));
                foundMovie.setMovieActiveFlag(c.getInt(c.getColumnIndex(Movie.COLUMN_ACTIVEFLAG)) != 1);

                movieList.add(foundMovie);
            }while (c.moveToNext());
        }
        db.close();
        return movieList;
    }



    public Movie updateActiveFlag(Movie m)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Movie.COLUMN_ACTIVEFLAG, 0);
        db.update(Movie.TABLE_NAME, contentValues, Movie.COLUMN_ID + " = ?",
                new String[]{Integer.toString(m.getMovieId())});
        return m;
    }

    public int updateRatingBarValue(Movie movie, float rbUpdateValue)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Movie.COLUMN_RATING, rbUpdateValue );
        db.update(Movie.TABLE_NAME, values, Movie.COLUMN_ID + " =?",
                new String[]{Float.toString(movie.getMovieId())});
        return 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Movie.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
