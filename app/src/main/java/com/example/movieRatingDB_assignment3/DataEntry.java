//Student name : Bahman Ghassemieh
package com.example.movieRatingDB_assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;


public class DataEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        final EditText edMovieName = findViewById(R.id.edMovieName);
        final EditText edMovieDesc = findViewById(R.id.edMovieDescription);
        final RatingBar ratingBar = findViewById(R.id.ratingBarDataEntry);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        final Intent data = new Intent();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBClass db = new DBClass(getApplicationContext());

                Movie newMovie = new Movie(edMovieName.getText().toString(),edMovieDesc.getText().toString(),
                        ratingBar.getRating(), true);

                db.addMovie(newMovie);

                setResult(RESULT_OK,data);
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,data);
                finish();
            }
        });


    }
}
