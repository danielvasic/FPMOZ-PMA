package ba.sum.fpmoz.vjezbe07;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ba.sum.fpmoz.vjezbe07.holders.MovieViewHolder;
import ba.sum.fpmoz.vjezbe07.model.Movie;

public class PocetnaAktivnost extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference mDatabase;
    RecyclerView movieRecycleView;
    FirebaseRecyclerAdapter<Movie, MovieViewHolder> adapter;

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
                return new MovieViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MovieViewHolder holder, int position, @NonNull Movie model) {
                holder.setMovie(model);
            }
        };

        this.movieRecycleView.setAdapter(adapter);
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
