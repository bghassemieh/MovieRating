package com.example.movieRating_assignment3;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TabHost;
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
        TextView mDescription;

        MyViewHolder(View view)
        {
            super(view);

            mId = view.findViewById(R.id.txtShowId);
            mName = view.findViewById(R.id.txtShowName);
            rbar = view.findViewById(R.id.ratingBar1);
            imgBtnDel = view.findViewById(R.id.imgBtnDel);
            mDescription = view.findViewById(R.id.txtShowDesc);
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


    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MyViewHolder viewHolder, int i) {
        final Movie movie = movieList.get(i);

        viewHolder.mId.setText(String.valueOf(movie.getMovieId()));
        viewHolder.mName.setText(movie.getMovieName());
        viewHolder.mDescription.setText(movie.getMovieDescription());
        viewHolder.rbar.setRating(movie.getMovieRating());

        viewHolder.imgBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBClass db = new DBClass(v.getContext());
                db.updateActiveFlag(movieList.get(viewHolder.getAdapterPosition()));
                movieList.remove(viewHolder.getAdapterPosition());
                Toast.makeText(v.getContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();

            }
        });

        viewHolder.rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float rbChange = rating;
                DBClass db = new DBClass(ratingBar.getContext());
                db.updateRatingBarValue(movieList.get(viewHolder.getAdapterPosition()),rbChange);

                Toast.makeText(ratingBar.getContext(), "Rating Bar value was changed to --> " + rbChange, Toast.LENGTH_SHORT).show();

            }
        });
}

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

}
