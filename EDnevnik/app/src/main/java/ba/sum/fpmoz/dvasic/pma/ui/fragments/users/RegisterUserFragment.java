package ba.sum.fpmoz.dvasic.pma.ui.fragments.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.FirebaseDatabase;

import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.model.User;

public class RegisterUserFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private TextView messageTxt;
    private EditText displayNameInp;
    private EditText emailInp;
    private EditText passwordInp;
    private EditText passwordCnfInp;
    private Button registerBtn;

    public RegisterUserFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View registerView = inflater.inflate(R.layout.fragment_register_user, container, false);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        this.messageTxt = registerView.findViewById(R.id.registerMsg);
        this.displayNameInp = registerView.findViewById(R.id.displayNameInp);
        this.emailInp = registerView.findViewById(R.id.emialInp1);
        this.passwordInp = registerView.findViewById(R.id.passwordInp1);
        this.passwordCnfInp = registerView.findViewById(R.id.passwordCnfInp);
        this.registerBtn = registerView.findViewById(R.id.registerBtn1);

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
                                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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
                                                db.getReference("/edenvnik/korisnici").child(user.getUid()).setValue(new User("nastavnik"));
                                            }
                                        }
                                    });


                                } else {
                                    Toast.makeText(getContext(), "Nastala je greška s registracijom na sustav: " + task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    );
                }
            }
        });
        return registerView;
    }
}