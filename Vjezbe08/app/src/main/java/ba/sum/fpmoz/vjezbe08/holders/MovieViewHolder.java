package ba.sum.fpmoz.vjezbe08.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ba.sum.fpmoz.vjezbe08.R;
import ba.sum.fpmoz.vjezbe08.model.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    View mView;

    ImageView imageImg;
    TextView titleTxt;
    TextView descTxt;
    RatingBar rateBar;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        this.imageImg = itemView.findViewById(R.id.movie_image);
        this.titleTxt = itemView.findViewById(R.id.movie_title);
        this.descTxt = itemView.findViewById(R.id.movie_desc);
        this.rateBar = itemView.findViewById(R.id.movie_rate);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClickListener(v, getAdapterPosition());
            }
        });

        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onLongItemClickListener(v, getAdapterPosition());
                return true;
            }
        });

        this.rateBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mClickListener.onRateItemListener(ratingBar.getRootView(), getAdapterPosition(), rating);
            }
        });
    }

    public void setMovie (Movie movie) {
        this.titleTxt.setText(movie.naziv);
        this.descTxt.setText(movie.opis);
        Picasso.get().load(movie.slika).into(this.imageImg);
    }

    private ItemClickListener mClickListener;

    public void setOnItemClickListener(ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public interface ItemClickListener {
        public void onItemClickListener(View v, int position);
        public void onLongItemClickListener(View v, int position);
        public void onRateItemListener(View v, int position, float rating);
    }
}
