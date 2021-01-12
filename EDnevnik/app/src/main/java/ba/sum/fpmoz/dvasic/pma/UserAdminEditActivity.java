package ba.sum.fpmoz.dvasic.pma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ba.sum.fpmoz.dvasic.pma.model.Student;

public class UserAdminEditActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;

    EditText studentNameEdt;
    EditText studentSurnameEdt;
    EditText studentUidEdt;
    Button studentEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin_edit);

        this.studentNameEdt = findViewById(R.id.studentNameEdt);
        this.studentSurnameEdt = findViewById(R.id.studentSurnameEdt);
        this.studentUidEdt = findViewById(R.id.studentUidEdt);
        this.studentEditBtn = findViewById(R.id.studentEditBtn);



        final String key = getIntent().getStringExtra("USER_ID");

        this.db = FirebaseDatabase.getInstance();
        this.ref = this.db.getReference("edenvnik/ucenici/").child(key);

        this.studentEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s = new Student();
                s.name = studentNameEdt.getText().toString();
                s.surname = studentSurnameEdt.getText().toString();
                s.uid = studentUidEdt.getText().toString();
                ref.setValue(s);
            }
        });
        this.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);
                studentNameEdt.setText(student.name);
                studentSurnameEdt.setText(student.surname);
                studentUidEdt.setText(student.uid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}