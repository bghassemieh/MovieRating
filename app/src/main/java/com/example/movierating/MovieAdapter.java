package com.example.movierating;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    List<Movie> movieList;

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView mId, mName;
        RatingBar rbar;
        ImageButton imgBtnDel;
        EditText mDescription;

        MyViewHolder(View view)
        {
            super(view);

            mId = view.findViewById(R.id.txtShowId);
            mName = view.findViewById(R.id.txtShowName);
            rbar = view.findViewById(R.id.ratingBar1);
            imgBtnDel = view.findViewById(R.id.imgBtnDel);
            mDescription = view.findViewById(R.id.edMovieDescription);
        }
    }


    MovieAdapter(List<Movie> movieList)
    {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_custom_layout, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MyViewHolder viewHolder, int i) {
        final Movie movie = movieList.get(i);

        viewHolder.mId.setText(String.valueOf(movie.getMovieId()));
        viewHolder.mName.setText(movie.getMovieName());
        viewHolder.mDescription.setText(movie.getMovieDescription());
        viewHolder.rbar.setRating(movie.getMovieRating());
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

}
