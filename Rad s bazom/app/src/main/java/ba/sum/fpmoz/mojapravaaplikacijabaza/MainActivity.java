package ba.sum.fpmoz.mojapravaaplikacijabaza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final KnjiznicaDBHelper helper = new KnjiznicaDBHelper(getApplicationContext());

        Button dodajKnjiguBtn = findViewById(R.id.dodajKnjiguBtn);

        dodajKnjiguBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase baza = helper.getWritableDatabase();

                EditText nazivTxt = findViewById(R.id.nazivTxt);
                EditText autorTxt = findViewById(R.id.autorTxt);
                EditText isbnTxt = findViewById(R.id.ISBNTxt);

                String naziv = nazivTxt.getText().toString();
                String autor = autorTxt.getText().toString();
                String isbn = isbnTxt.getText().toString();

                ContentValues knjigaCnt = new ContentValues();
                knjigaCnt.put(Knjznica.Knjiga.COLUMN_NAZIV, naziv);
                knjigaCnt.put(Knjznica.Knjiga.COLUMN_AUTOR, autor);
                knjigaCnt.put(Knjznica.Knjiga.COLUMN_ISBN, isbn);

                baza.insert(Knjznica.Knjiga.TABLE_NAME, null,knjigaCnt);

                nazivTxt.setText("");
                autorTxt.setText("");
                isbnTxt.setText("");
            }
        });


    }
}
