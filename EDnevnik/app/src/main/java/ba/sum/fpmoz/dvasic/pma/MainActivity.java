package ba.sum.fpmoz.dvasic.pma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView messageTxt;
    private EditText emailInp;
    private EditText passwordInp;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Dohvaćanje instance autentikatora */
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        /* Dohvaćanje elemanta sučelja*/

        this.messageTxt = findViewById(R.id.messageTxt);
        this.emailInp = findViewById(R.id.emailInp);
        this.passwordInp = findViewById(R.id.passwordInp);
        this.loginBtn = findViewById(R.id.loginBtn);
        this.registerBtn = findViewById(R.id.registerBtn);

        /* Na klik akciju dohvati unos korisnika i probaj ga autenticirati */

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInp.getText().toString();
                String password = passwordInp.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            messageTxt.setText("Uspješno ste se prijavili na sustav.");
                            Intent i = new Intent(getApplicationContext(), UserAdminActivity.class);
                            startActivity(i);
                        } else {
                            messageTxt.setText("Nastao je problem s prijavom.");
                        }
                    }
                });
            }
        });

        /* Na klik Registriraj se korisnik otvara novu aktivbnost */

        this.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });


    }
}
