package com.example.mojadrugaaplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button prijava = findViewById(R.id.loginBtn);
        prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText korisnikTxt = findViewById(R.id.korisnikTxt);
                EditText lozinkaPwd = findViewById(R.id.lozinkaPwd);

                String unos = korisnikTxt.getText() + " " + lozinkaPwd.();
                Toast.makeText(getApplicationContext(), unos, Toast.LENGTH_LONG).show();
            }
        });
    }
}
