package com.example.android.popularmoviesstage1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmoviesstage1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> movies = new ArrayList<>();

    private MovieClickListener movieClickListener;

    interface MovieClickListener{
        void onClickMovie(int id);
    }

    MovieAdapter(MovieClickListener clickListener){
        movieClickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int movieId = movies.get(getAdapterPosition()).getId();
            movieClickListener.onClickMovie(movieId);
        }

        private ImageView movieImageView;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImageView = itemView.findViewById(R.id.list_movie_image);
            itemView.setOnClickListener(this);
        }

        void bind(Movie movie){
            String moviePoster = Movie.BASE_POSTER_PATH.concat(movie.getPosterPath());
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
                    .load(moviePoster)
                    .error(R.drawable.ic_error_image)
                    .into(movieImageView);
        }


    }

}
