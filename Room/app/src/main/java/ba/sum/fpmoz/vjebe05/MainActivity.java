package ba.sum.fpmoz.vjebe05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Baza db = Room.databaseBuilder(
                getApplicationContext(),
                Baza.class,
                "fpmoz"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        Korisnik k = new Korisnik();

        k.ime = "Daniel";
        k.prezime = "Vasic";

        db.korisnici().insert(k);

        for (Korisnik korisnik: db.korisnici().listAll()){
            Log.e("Korisnik", korisnik.ime + " " + korisnik.prezime);
        }
    }
}
