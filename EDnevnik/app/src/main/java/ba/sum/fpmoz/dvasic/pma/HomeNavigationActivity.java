package ba.sum.fpmoz.dvasic.pma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.dvasic.pma.model.Student;
import ba.sum.fpmoz.dvasic.pma.model.User;

public class HomeNavigationActivity extends AppCompatActivity {

    private ImageButton userAdminBtn;
    private ImageButton classesBtn;
    private TextView loggedUserTextView;
    private TextView loggedUserRoleTextView;
    private User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation);
        setTitle("Dobrodošli u administraciju");
        this.userAdminBtn = findViewById(R.id.usersBtn);
        this.userAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabbedUserAdminActivity.class);
                startActivity(i);
            }
        });

        this.loggedUserTextView = findViewById(R.id.loggedUserTxt);
        this.loggedUserRoleTextView = findViewById(R.id.loggedUserRoleTxt);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("/edenvnik/korisnici").getRef().child(user.getUid());

        loggedUserTextView.setText("Dobrodošli: " + user.getDisplayName());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loggedUser = snapshot.getValue(User.class);
                loggedUserRoleTextView.setText("Prijavljeni ste kao " + loggedUser.role);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        this.classesBtn = findViewById(R.id.classesBtn);
        this.classesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TabbedClassesActivity.class);
                startActivity(i);
            }
        });
    }
}