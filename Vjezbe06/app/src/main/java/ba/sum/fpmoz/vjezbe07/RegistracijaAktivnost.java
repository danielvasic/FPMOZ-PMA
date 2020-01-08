package ba.sum.fpmoz.vjezbe07;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistracijaAktivnost extends AppCompatActivity {

    EditText email_korisnika_txt;
    EditText lozinka_korisnika_txt;
    Button registracija_btn;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija_aktivnost);

        auth = FirebaseAuth.getInstance();

        this.email_korisnika_txt = findViewById(R.id.email_korisnika_txt);
        this.lozinka_korisnika_txt = findViewById(R.id.lozinka_korisnika_txt);
        this.registracija_btn = findViewById(R.id.registracija_btn);

        registracija_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_korisnika_txt.getText().toString();
                String lozinka = lozinka_korisnika_txt.getText().toString();

                auth.createUserWithEmailAndPassword(email, lozinka)
                        .addOnCompleteListener(RegistracijaAktivnost.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "Uspješno ste registrirani.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
