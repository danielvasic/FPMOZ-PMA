package ba.sum.fpmoz.filmoteka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.filmoteka.adapter.MovieAdapter;
import ba.sum.fpmoz.filmoteka.model.Movie;

public class MovieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://filmoteka-8adc2-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        this.recyclerView = findViewById(R.id.moviesListView);
        this.recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
        FirebaseRecyclerOptions<Movie> options = new FirebaseRecyclerOptions.Builder<Movie>().setQuery(
                this.mDatabase.getReference("movie"),
                Movie.class
        ).build();

        this.movieAdapter = new MovieAdapter(options);
        this.recyclerView.setAdapter(this.movieAdapter);

        FloatingActionButton openAddMovieBtn = findViewById(R.id.openAddMovieBtn);
        openAddMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MovieActivity.this, AddMovieActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.movieAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.movieAdapter.stopListening();
    }
}