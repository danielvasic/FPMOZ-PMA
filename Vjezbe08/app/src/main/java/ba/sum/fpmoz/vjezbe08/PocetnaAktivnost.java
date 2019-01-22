package ba.sum.fpmoz.vjezbe08;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import ba.sum.fpmoz.vjezbe08.holders.MovieViewHolder;
import ba.sum.fpmoz.vjezbe08.model.Movie;
import ba.sum.fpmoz.vjezbe08.model.Rating;

public class PocetnaAktivnost extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference mDatabase;
    RecyclerView movieRecycleView;
    FirebaseRecyclerAdapter<Movie, MovieViewHolder> adapter;
    Button noviFilmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna_aktivnost);
        auth = FirebaseAuth.getInstance();
        this.user = auth.getCurrentUser();
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.movieRecycleView = findViewById(R.id.recycleview_movie);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        this.movieRecycleView.setLayoutManager(manager);
        this.movieRecycleView.setHasFixedSize(true);

        this.noviFilmBtn = findViewById(R.id.novi_film_btn);

        // Postavite postavke za pretvorbu JSON objekta u Movie klasu
        FirebaseRecyclerOptions<Movie> options = new FirebaseRecyclerOptions.Builder<Movie>().setQuery(
                mDatabase.child("filmovi"), new SnapshotParser<Movie>() {
                    @NonNull
                    @Override
                    public Movie parseSnapshot(@NonNull DataSnapshot snapshot) {
                        return snapshot.getValue(Movie.class);
                    }
                }
        ).build();

        // Adapter koji dohvaca elemente iz baze
        adapter = new FirebaseRecyclerAdapter<Movie, MovieViewHolder>(options) {
            @NonNull
            @Override
            public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_movie_item, viewGroup, false);
                MovieViewHolder movieViewHolder = new MovieViewHolder(view);
                movieViewHolder.setOnItemClickListener(new MovieViewHolder.ItemClickListener() {
                    @Override
                    public void onItemClickListener(View v, int position) {
                        DatabaseReference movieReference = getRef(position);
                        Toast.makeText(getApplicationContext(), movieReference.getKey(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLongItemClickListener(View v, int position) {
                        DatabaseReference movieReference = getRef(position);
                        movieReference.removeValue();
                    }

                    @Override
                    public void onRateItemListener(View v, int position, float rating) {
                        DatabaseReference movieReference = getRef(position);
                        DatabaseReference ratingReference = mDatabase.child("ocjene");
                        Rating userRating = new Rating(
                                user.getUid(),
                                rating
                        );
                        String ref = ratingReference.child(movieReference.getKey()).push().getKey();
                        ratingReference.child(movieReference.getKey()).child(ref).setValue(userRating);
                    }
                });
                return movieViewHolder;
            }

            @Override
            protected void onBindViewHolder(@NonNull MovieViewHolder holder, int position, @NonNull Movie model) {
                holder.setMovie(model);
            }
        };

        this.movieRecycleView.setAdapter(adapter);

        this.noviFilmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PocetnaAktivnost.this, FilmAktivnost.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
