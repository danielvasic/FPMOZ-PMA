package com.example.daniel.mojaprvaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GlavnaAktivnost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavna_aktivnost);

        Button otvori = findViewById(R.id.otvori_aktivnost);

        otvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(), DrugaAktivnost.class);
                startActivity(i);
            }
        });
    }
}
