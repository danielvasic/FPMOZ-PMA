package ba.sum.fpmoz.smart;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RezervacijaParkinga extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RezervacijeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervacija_parkinga);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final String parkingId = getIntent().getExtras().getString("parkingId");

        DatabaseReference referenca = database.getReference("rezervacije").child(parkingId);


        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.rezervacijeLista);
        recyclerView.setHasFixedSize(true);

        ArrayList<Rezervacija> rezervacijaList = new ArrayList<>();
        adapter = new RezervacijeAdapter(rezervacijaList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DodajRezervaciju drf = DodajRezervaciju.newInstance(parkingId);
                drf.show(fm, "dodaj_Rezervaciju_fragment");
            }
        });


        referenca.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Rezervacija> rezervacijaList = new ArrayList<>();
                for (DataSnapshot rezervacijaDS:dataSnapshot.getChildren()){
                    Rezervacija rezervacija = rezervacijaDS.getValue(Rezervacija.class);
                    rezervacijaList.add(rezervacija);
                }
                adapter.updateList(rezervacijaList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }

}
