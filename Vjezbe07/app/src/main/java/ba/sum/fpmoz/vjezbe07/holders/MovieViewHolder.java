package ba.sum.fpmoz.vjezbe07.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ba.sum.fpmoz.vjezbe07.R;
import ba.sum.fpmoz.vjezbe07.model.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    View mView;

    ImageView imageImg;
    TextView titleTxt;
    TextView descTxt;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        this.imageImg = itemView.findViewById(R.id.movie_image);
        this.titleTxt = itemView.findViewById(R.id.movie_title);
        this.descTxt = itemView.findViewById(R.id.movie_desc);
    }

    public void setMovie (Movie movie) {
        this.titleTxt.setText(movie.naziv);
        this.descTxt.setText(movie.opis);
        Picasso.get().load(movie.slika).into(this.imageImg);
    }


}
