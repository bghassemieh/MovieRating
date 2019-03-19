package com.example.movierating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnAddRecord = findViewById(R.id.btnAddRecord);

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DataEntry.class);
                startActivityForResult(i,1);
            }
        });
    }
    @Override
    protected void
    onActivityResult(int requestCode, int resultCode, Intent data) {
        int countArray = movieList.size()+1;
        // Handle Return
        if(resultCode == RESULT_OK && requestCode == 1)
        {
            if (data.hasExtra("movieName"))
            {
                RecyclerView recycle = findViewById(R.id.recycle1);
                movieAdapter = new MovieAdapter(movieList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recycle.setLayoutManager(layoutManager);
                recycle.setItemAnimator(new DefaultItemAnimator());

                // OPTIONAL
                recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                recycle.setAdapter(movieAdapter);
                movieList.add(new Movie(countArray,data.getStringExtra("movieName"),
                        data.getFloatExtra("movieRate", 0.0f)));
                Toast.makeText(getApplicationContext(),"Record Added", Toast.LENGTH_LONG).show();
            }

        }
        if(resultCode == RESULT_CANCELED && requestCode == 1)
        {
            Toast.makeText(getApplicationContext(),"Cancelled", Toast.LENGTH_LONG).show();
        }
    }

}
