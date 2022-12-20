package ba.sum.fpmoz.filmoteka.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import ba.sum.fpmoz.filmoteka.R;
import ba.sum.fpmoz.filmoteka.model.Movie;

public class MovieAdapter extends FirebaseRecyclerAdapter<Movie, MovieAdapter.MovieViewHolder> {

    Context cxt;
    public MovieAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position, @NonNull Movie model) {
        holder.movieTitleTxt.setText(model.getName());
        holder.movieGenereTxt.setText(model.getGenere());
        holder.movieScoreTxt.setText(model.getScore().toString());
        Picasso
                .get()
                .load(model.getImage())
                .into(holder.movieImage);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.cxt = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view, parent, false);
        return new MovieAdapter.MovieViewHolder(view);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitleTxt, movieGenereTxt, movieScoreTxt;
        ImageView movieImage;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitleTxt = itemView.findViewById(R.id.movieTitleTxt);
            movieGenereTxt = itemView.findViewById(R.id.movieGenereTxt);
            movieScoreTxt = itemView.findViewById(R.id.movieScoreTxt);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}
