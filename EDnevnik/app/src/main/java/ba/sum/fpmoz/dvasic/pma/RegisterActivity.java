package ba.sum.fpmoz.dvasic.pma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView messageTxt;
    private EditText displayNameInp;
    private EditText emailInp;
    private EditText passwordInp;
    private EditText passwordCnfInp;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Dohvaćanje instance autentikatora */
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);

        this.messageTxt = findViewById(R.id.registerMsg);
        this.displayNameInp = findViewById(R.id.displayNameInp);
        this.emailInp = findViewById(R.id.emialInp1);
        this.passwordInp = findViewById(R.id.passwordInp1);
        this.passwordCnfInp = findViewById(R.id.passwordCnfInp);

        this.registerBtn = findViewById(R.id.registerBtn1);
        this.loginBtn = findViewById(R.id.loginBtn1);

        this.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String displayName = displayNameInp.getText().toString();
                String email = emailInp.getText().toString();
                String password = passwordInp.getText().toString();
                String passwordCnf = passwordCnfInp.getText().toString();
                if (!password.equals(passwordCnf)){
                    messageTxt.setText("Lozinke se ne podudaraju");
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest
                                                .Builder()
                                                .setDisplayName(displayName)
                                                .build();
                                        user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                               if (task.isSuccessful()){
                                                   emailInp.setText("");
                                                   passwordInp.setText("");
                                                   passwordCnfInp.setText("");
                                                   messageTxt.setText("Vaš korisnički račun je uspješno napravljen");
                                                   displayNameInp.setText("");
                                                   Log.d("Poruka", "Profil je ažuriran");
                                               }
                                            }
                                        });
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Nastala je greška s registracijom na sustav: " + task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                    );
                }
            }
        });

        /* Na klik Prijavi se korisnik otvara aktivnost prijave */
        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
