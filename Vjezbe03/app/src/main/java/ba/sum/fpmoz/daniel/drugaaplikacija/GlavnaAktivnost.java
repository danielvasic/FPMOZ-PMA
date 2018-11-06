package ba.sum.fpmoz.daniel.drugaaplikacija;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GlavnaAktivnost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavna_aktivnost);

        //Slanje e-mailova
        //Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
        //email.putExtra(Intent.EXTRA_EMAIL, "dvasic1@gmail.com");
        //email.putExtra(Intent.EXTRA_SUBJECT,"Test");
        //email.putExtra(Intent.EXTRA_TEXT, "Sadržaj email poruke");
        //startActivity(Intent.createChooser(email, "Odaberite email klijenta..."));

        //Pretraga
        //Intent pretraga = new Intent(Intent.ACTION_WEB_SEARCH);
        //pretraga.putExtra(SearchManager.QUERY, "FPMOZ");
        //startActivity(pretraga);

        Intent i = new Intent(this, MojPrimatelj.class);
        i.setAction("ba.sum.fpmoz.daniel.drugaaplikacija.PORUKA");
        sendBroadcast(i);

        Button trazi = findViewById(R.id.trazi);
        trazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pojam = findViewById(R.id.pojam);
                String pojam_txt = pojam.getText().toString();
                if (pojam_txt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Unesite pojam za pretragu...",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent pretraga = new Intent(Intent.ACTION_WEB_SEARCH);
                    pretraga.putExtra(SearchManager.QUERY, pojam_txt);
                    startActivity(pretraga);
                }
            }
        });
    }
}
