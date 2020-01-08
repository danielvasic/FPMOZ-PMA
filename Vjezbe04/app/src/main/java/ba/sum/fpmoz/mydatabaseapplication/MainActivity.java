package ba.sum.fpmoz.mydatabaseapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

import ba.sum.fpmoz.mydatabaseapplication.db.Restoran;
import ba.sum.fpmoz.mydatabaseapplication.db.RestoranHelper;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RestoranHelper dbHelper = new RestoranHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Restoran.Korisnik.COLUMN_NAME_FIRSTNAME, "Daniel");
        cv.put(Restoran.Korisnik.COLUMN_NAME_LASTNAME, "Vasic");
        cv.put(Restoran.Korisnik.COLUMN_NAME_EMAIL, "dvasic1@gmail.com");
        cv.put(Restoran.Korisnik.COLUMN_NAME_PASSWORD, "123456");
        SQLiteDatabase rdb = dbHelper.getReadableDatabase();
        //long id = db.insert(Restoran.Korisnik.TABLE_NAME, null, cv);
        //Log.d("Moja aplikacija", String.valueOf(id));

        this.lista = findViewById(R.id.lista);

        final ArrayList<String> niz = new ArrayList<>();

        Cursor cursor = rdb.query(
                Restoran.Korisnik.TABLE_NAME,
                null,
                "",
                null,
                null,
                null,
                ""
        );

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String firstname = cursor.getString(
                    cursor.getColumnIndex(Restoran.Korisnik.COLUMN_NAME_FIRSTNAME));
            String lastname = cursor.getString(
                    cursor.getColumnIndex(Restoran.Korisnik.COLUMN_NAME_LASTNAME));
            niz.add(firstname + " " + lastname);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                niz
        );

        this.lista.setAdapter(adapter);

        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Kliknuli ste na: " + niz.get(position),
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}
