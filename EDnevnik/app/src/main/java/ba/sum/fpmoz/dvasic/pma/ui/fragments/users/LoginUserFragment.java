package ba.sum.fpmoz.dvasic.pma.ui.fragments.users;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ba.sum.fpmoz.dvasic.pma.HomeNavigationActivity;
import ba.sum.fpmoz.dvasic.pma.R;
import ba.sum.fpmoz.dvasic.pma.TabbedUserAdminActivity;

public class LoginUserFragment extends Fragment {
    private FirebaseAuth mAuth;
    private TextView messageTxt;
    private EditText emailInp;
    private EditText passwordInp;
    private Button loginBtn;

    public LoginUserFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login_user, container, false);
        /* Dohvaćanje instance autentikatora */
        mAuth = FirebaseAuth.getInstance();

        /* Dohvaćanje elemanta sučelja*/

        this.messageTxt = loginView.findViewById(R.id.messageTxt);
        this.emailInp = loginView.findViewById(R.id.emailInp);
        this.passwordInp = loginView.findViewById(R.id.passwordInp);
        this.loginBtn = loginView.findViewById(R.id.loginBtn);

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
                                    Intent i = new Intent(getContext(), HomeNavigationActivity.class);
                                    startActivity(i);
                                } else {
                                    messageTxt.setText("Nastao je problem s prijavom.");
                                }
                            }
                        });
            }
        });
        return loginView;
    }
}