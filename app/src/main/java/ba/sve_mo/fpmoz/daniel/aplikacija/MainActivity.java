package ba.sve_mo.fpmoz.daniel.aplikacija;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KontektiDB baza = new KontektiDB(getApplicationContext());
        Kontekt k1 = new Kontekt(0, "Daniel", "Vasic", "dvasic1@gmail.com");
        baza.dodajKontekt(k1);

        Kontekt k2 = new Kontekt(0, "Pero", "Peric", "pero@gmail.com");
        baza.dodajKontekt(k2);

        for (Kontekt k : baza.dajSveKontekte()) {
            Log.d("Baza ime", k.getIme());
            Log.d("Baza prezime", k.getPrezime());
            Log.d("Baza email", k.getEmail());
        }
    }

    @Override
    protected void onStart () {
        super.onStart();
    }
}
