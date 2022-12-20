package ba.sum.fpmoz.filmoteka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginEmailTxt = findViewById(R.id.loginEmailTxt);
        EditText loginPasswordTxt = findViewById(R.id.loginPasswordTxt);

        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmailTxt.getText().toString();
                String password = loginPasswordTxt.getText().toString();
                if (email.equals("") && password.equals("")) {
                    Toast.makeText(getBaseContext(), "Morate unjeti sva polja.", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Prijavili ste se na sustav, dobrodošli.", Toast.LENGTH_SHORT).show();
                                Intent movieIntent = new Intent(LoginActivity.this, MovieActivity.class);
                                startActivity(movieIntent);
                            } else {
                                Toast.makeText(getBaseContext(), "Pogrešni korisnički podaci.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}