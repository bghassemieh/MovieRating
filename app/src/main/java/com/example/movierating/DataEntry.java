package com.example.movierating;

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


        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBClass db = new DBClass(getApplicationContext());

                EditText edMovieName = findViewById(R.id.edMovieName);
                EditText edMovieDesc = findViewById(R.id.edMovieDescription);
                RatingBar ratingBar = findViewById(R.id.ratingBarDataEntry);

                Movie newMovie = new Movie(edMovieName.getText().toString(),edMovieDesc.getText().toString(),
                        ratingBar.getRating(), true);

                db.addMovie(newMovie);

                Intent data = new Intent();
                setResult(RESULT_OK,data);
                DataEntry.this.finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }
}
