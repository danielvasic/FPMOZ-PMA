package ba.sum.fpmoz.pma20192020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        final EditText emailKorisnikaTxt = findViewById(R.id.emailKorisnikaTxt);
        final EditText lozinkkaKorisnikaPwd = findViewById(R.id.lozinkaKorisnikaPwd);

        Button registracijaBtn = findViewById(R.id.registracijaBtn);

        registracijaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailKorisnika = emailKorisnikaTxt.getText().toString();
                String lozinkaKorisnika = lozinkkaKorisnikaPwd.getText().toString();

                if (!emailKorisnika.equals("") && !lozinkaKorisnika.equals("")){
                    auth.createUserWithEmailAndPassword(emailKorisnika, lozinkaKorisnika);
                } else {
                    Toast.makeText(getApplicationContext(), "Molimo unesite sva polja.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
