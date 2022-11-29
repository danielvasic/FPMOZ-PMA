package ba.sum.fpmoz.filmoteka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ba.sum.fpmoz.filmoteka.model.UserProfile;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance("https://filmoteka-8adc2-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        EditText profileFirstnameTxt = findViewById(R.id.profileFirstnameTxt);
        EditText profileLastnameTxt = findViewById(R.id.profileLastnemeTxt);
        EditText profileEmailTxt = findViewById(R.id.profileEmailTxt);
        EditText profileDateOfBirth = findViewById(R.id.profileDateOfBith);
        Button profileSaveBtn = findViewById(R.id.profileSaveBtn);

        if (currentUser != null) {
            DatabaseReference profileRef = mDatabase.getReference("profile").child(currentUser.getUid());
            profileEmailTxt.setText(currentUser.getEmail());

            profileRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                        UserProfile profile = task.getResult().getValue(UserProfile.class);
                        if (profile != null) {
                           profileFirstnameTxt.setText(profile.getFirstname());
                           profileLastnameTxt.setText(profile.getLastname());
                           profileDateOfBirth.setText(profile.getDateOfBirth().toString());
                        }
                   }
            });


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            profileSaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String firstname = profileFirstnameTxt.getText().toString();
                    String lastname = profileLastnameTxt.getText().toString();
                    String email = profileEmailTxt.getText().toString();
                    try {
                        Date dateOfBirth = sdf.parse(profileDateOfBirth.getText().toString());
                        UserProfile profile = new UserProfile(firstname, lastname, email, dateOfBirth);
                        profileRef.setValue(profile);
                    } catch (ParseException e) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Morate unijeti ispravan datum.",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
            });
        }
    }
}