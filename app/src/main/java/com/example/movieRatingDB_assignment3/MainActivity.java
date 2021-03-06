//Student name: Bahman Ghassemieh
package com.example.movieRatingDB_assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycle = findViewById(R.id.recycle1);
        movieAdapter = new MovieAdapter(movieList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycle.setLayoutManager(layoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        DBClass db = new DBClass(this);
        movieList.addAll(db.getMovies_Active());
        recycle.setAdapter(movieAdapter);

        Button btnAddRecord = findViewById(R.id.btnAddRecord);
        ToggleButton tglBtn = findViewById(R.id.tgBtnShowHideDeletedRecord);
        tglBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DBClass db = new DBClass(getApplicationContext());
                RecyclerView recyclerView = findViewById(R.id.recycle1);
                movieList.clear();

                if (isChecked){
                    setTitle("Deleted Items List");
                    movieList.addAll(db.getMovies_Inactive());
                    recyclerView.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Deleted Items List", Toast.LENGTH_LONG).show();
                }else{
                    setTitle("Active Movies Lists");
                    movieList.addAll(db.getMovies_Active());
                    recyclerView.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Movies List", Toast.LENGTH_LONG).show();
                }

            }
        });

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

        ToggleButton tglSetting = findViewById(R.id.tgBtnShowHideDeletedRecord);
        tglSetting.setChecked(false);

        if(resultCode == RESULT_OK && requestCode == 1)
        {
            setTitle("Inserted new Items List");
            movieList.clear();

            RecyclerView recycle = findViewById(R.id.recycle1);
            movieAdapter = new MovieAdapter(movieList);
            DBClass db = new DBClass(this);
            movieList.addAll(db.getMovies_Active());
            recycle.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"Record Added", Toast.LENGTH_LONG).show();
        }

        if(resultCode == RESULT_CANCELED && requestCode == 1)
        {
            setTitle("Active Movies Lists");
            Toast.makeText(getApplicationContext(),"Cancelled", Toast.LENGTH_LONG).show();
        }

    }

}
