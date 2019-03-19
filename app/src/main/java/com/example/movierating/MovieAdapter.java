package com.example.movierating;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        Button btnDeleteRecord;

        MyViewHolder(View view)
        {
            super(view);

            mId = view.findViewById(R.id.txtShowId);
            mName = view.findViewById(R.id.txtShowName);
            rbar = view.findViewById(R.id.ratingBar1);
            btnDeleteRecord = view.findViewById(R.id.btnDelete);
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
        viewHolder.rbar.setRating(Float.valueOf(movie.getMovieRating()));

        viewHolder.btnDeleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), String.valueOf(movie.getMovieName() + " " +
                        "Deleted:"), Toast.LENGTH_LONG).show();
                int position = viewHolder.getAdapterPosition();
                movieList.remove(position);
                notifyDataSetChanged();
            }
        });



 viewHolder.rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int pos = viewHolder.getAdapterPosition();
                float valRate = viewHolder.rbar.getRating();
                movieList.get(pos).setMovieRating(valRate);
                notifyDataSetChanged();
                Toast.makeText(ratingBar.getContext(),
                        "Rate value changed to:" + "   "+ movieList.get(pos).getMovieRating(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

}
