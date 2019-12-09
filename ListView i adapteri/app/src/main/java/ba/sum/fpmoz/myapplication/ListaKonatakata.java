package ba.sum.fpmoz.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListaKonatakata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_konatakata);
        List<String> korisnici = new ArrayList<>();
        korisnici.add("Daniel");
        korisnici.add("Pero");
        Adapter a = new Adapter(korisnici, getApplicationContext());

        ListView lw = findViewById(R.id.listaKorisnika);
        lw.setAdapter(a);
    }
}
